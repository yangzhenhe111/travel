package cn.Travels_App.di.module;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.google.gson.Gson;

import cn.Travels_App.App;
import cn.Travels_App.common.Constants;
import cn.Travels_App.model.entity.UserEntity;
import cn.Travels_App.network.APIService;
import cn.Travels_App.network.converter.CustomGsonConverterFactory;


import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;
import javax.net.ssl.SSLContext;

import cn.Travels_App.utils.CommonUtils;
import cn.Travels_App.utils.StringUtils;
import dagger.Module;
import dagger.Provides;
import okhttp3.ConnectionSpec;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.TlsVersion;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

@Module
public class AppModule {

    /**
     * 默认超时时间 单位/秒
     */
    private static final int DEFAULT_TIME_OUT = 15;

    private Context context;

    private String baseUrl;


    public AppModule(App context, String baseUrl) {
        this.context = context;
        this.baseUrl = baseUrl;
       ;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(CustomGsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(provideOkHttpClient())
                .build();
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder()
                .followRedirects(true)
                .followSslRedirects(true)
                .retryOnConnectionFailure(true)
                .cache(null)
                .connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .addInterceptor(new MoreBaseUrlInterceptor());
        return enableTls12OnPreLollipop(okHttpClient).build();
    }

    /**
     * 兼容android4.x不支持SSL 导致https协议无法访问网络的问题
     *
     * @return
     */
    public static OkHttpClient.Builder enableTls12OnPreLollipop(OkHttpClient.Builder client) {
        if (Build.VERSION.SDK_INT >= 16 && Build.VERSION.SDK_INT < 22) {
            try {
                SSLContext sc = SSLContext.getInstance("TLSv1.2");
                sc.init(null, null, null);
                client.sslSocketFactory(new Tls12SocketFactory(sc.getSocketFactory()));

                ConnectionSpec cs = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                        .tlsVersions(TlsVersion.TLS_1_2)
                        .build();

                List<ConnectionSpec> specs = new ArrayList<>();
                specs.add(cs);
                specs.add(ConnectionSpec.COMPATIBLE_TLS);
                specs.add(ConnectionSpec.CLEARTEXT);

                client.connectionSpecs(specs);
            } catch (Exception exc) {
                Log.e("OkHttpTLSCompat", "Error while setting TLS 1.2", exc);
            }
        }

        return client;
    }

    @Provides
    @Singleton
    public APIService provideAPIService() {
        return provideRetrofit().create(APIService.class);
    }

    /**
     * 开启okhttp打印连接日志
     *
     * @return
     */
    private HttpLoggingInterceptor getHttpLoggingInterceptor() {
        boolean isDebug = true;//是否开启
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

        if (isDebug) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        }

        //开启打印连接日志
        return interceptor;
    }

    public class MoreBaseUrlInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {

            //获取原始的originalRequest
            Request originalRequest = chain.request();
            //获取老的url
            HttpUrl oldUrl = originalRequest.url();
            //获取originalRequest的创建者builder
            Request.Builder builder = originalRequest.newBuilder();

            //获取头信息的集合如：manage,mdffx
            List<String> urlnameList = originalRequest.headers("name");
            if (urlnameList != null && urlnameList.size() > 0) {
                //删除原有配置中的值,就是namesAndValues集合里的值
                builder.removeHeader("name");

                UserEntity user = CommonUtils.getLoginUser(context);
                user.setUsername(null);
                String str = new Gson().toJson(user).trim();
                String strUTF8 = URLDecoder.decode(str, "UTF-8");
                builder.addHeader("header-user", strUTF8);//添加请求头
                //获取头信息中配置的value,如：manage或者mdffx
                String urlname = urlnameList.get(0);
                HttpUrl baseURL = HttpUrl.parse(Constants.BASE_URL);

                //重建新的HttpUrl，需要重新设置的url部分
                HttpUrl newHttpUrl = oldUrl.newBuilder()
                        .scheme(baseURL.scheme())//http协议如：http或者https
                        .host(baseURL.host())//主机地址
                        .port(baseURL.port())//端口
                        //.setEncodedQueryParameter("sass_id", "-1")// 添加公共参数sass_id
                        .build();
                Log.e("请求url---->", newHttpUrl.url()+"");
                //获取处理后的新newRequest
                Request newRequest = builder.url(newHttpUrl).build();
                return chain.proceed(newRequest);
            } else {
                return chain.proceed(originalRequest);
            }

        }
    }
}
