package cn.Travels_App.network;




import java.util.List;

import cn.Travels_App.common.Constants;

import cn.Travels_App.model.dto.PageRequest;
import cn.Travels_App.model.dto.QueryTravelsDTO;
import cn.Travels_App.model.entity.Comment;
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
    @POST(Constants.URL_Login)
    Observable<HttpResult<UserEntity>> login(@Query("username")String username,
                                         @Query("password")String password);

    //注册
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

    /*@Headers({"name:super"})
    @POST(Constants.URL_FINDMYORDER)
    Observable<HttpResult<String>> findMyYdOrder(@Query("id") Long id);*/

    //查找推荐的景区
    @Headers({"name:super"})
    @POST(Constants.URL_FINDTJSPOTS)
    Observable<HttpResult<List<Travels>>> findTjTravels();

    //根据条件查询景区
    @Headers({"name:super"})
    @POST(Constants.URL_FINDSPOTSBYCONDITION)
    Observable<HttpResult<List<Travels>>> queryTravelsByCondition(@Body PageRequest<QueryTravelsDTO> request);

    /*//根据游记ID查询评论
    @Headers({"name:super"})
    @POST(Constants.URL_FINDComment)
    Observable<HttpResult<List<CommentResp>>> queryAllComment(@Query("id") Long id);*/

    /*//根据一级评论ID查询二级评论
    @Headers({"name:super"})
    @POST(Constants.URL_FINDTJSPOTS)
    Observable<HttpResult<List<Comment>>> querySecondaryComment(@Query("id") Long id);*/

}
