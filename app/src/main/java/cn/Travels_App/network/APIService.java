package cn.Travels_App.network;




import java.util.List;

import cn.Travels_App.common.Constants;

import cn.Travels_App.model.entity.Travels;
import cn.Travels_App.model.entity.UserEntity;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIService {

    @Headers({"name:super"})
    @POST("ssw/rest/eventupper/createEvent")
    Observable<HttpResult<String>> createEvent(@Body RequestBody params);

    //登录
    @Headers({"name:super"})
    @POST(Constants.URL_Login)
    Observable<HttpResult<UserEntity>> login(@Query("username")String username,
                                         @Query("password")String password);

    //注册
    @Headers({"name:super"})
    @POST(Constants.URL_REG)
    Observable<HttpResult<UserEntity>> reg(@Body UserEntity userEntity);

    //保存游记
    @Headers({"name:super"})
    @POST(Constants.URL_SAVETRAVELS)
    Observable<HttpResult<Travels>> maketravels(@Body Travels travels);

    //发表游记
    @Headers({"name:super"})
    @POST(Constants.URL_PUBTRAVELS)
    Observable<HttpResult<Travels>> pubtravels(@Body Travels travels);

    @Headers({"name:super"})
    @POST(Constants.URL_FINDMYORDER)
    Observable<HttpResult<String>> findMyYdOrder(@Query("id") Long id);

    //查找推荐的景区
    @Headers({"name:super"})
    @POST(Constants.URL_FINDTJSPOTS)
    Observable<HttpResult<List<Travels>>> findTjTravels();

    /*@Headers({"name:super"})
    @POST(Constants.URL_FINDALLSPOTS)
    Observable<HttpResult<List<Travels>>> queryAllTravels();*/

    //根据条件查询景区
    @Headers({"name:super"})
    @POST(Constants.URL_FINDSPOTSBYCONDITION)
    Observable<HttpResult<List<Travels>>> queryTravelsByCondition(@Query("cityid")String sousuo);

    //查询所有的酒店信息
    @Headers({"name:super"})
    @POST(Constants.URL_FINDALLHOTELS)
    Observable<HttpResult<String>> queryAllHotel();



    @POST(Constants.URL_FINDALLQA)
    @Headers({"name:super"})
    Observable<HttpResult<String>> findAllQa();

    @POST(Constants.URL_PAYMYORDER)
    @Headers({"name:super"})
    Observable<HttpResult<String>>  payMyOrder(@Query("id")Long id);
}
