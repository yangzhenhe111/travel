package cn.Travels_App.network;




import android.graphics.pdf.PdfDocument;

import java.util.List;

import cn.Travels_App.common.Constants;

import cn.Travels_App.model.dto.CommentRespDTO;
import cn.Travels_App.model.dto.PageBean;
import cn.Travels_App.model.dto.PageRequest;
import cn.Travels_App.model.dto.QueryTravelsDTO;
import cn.Travels_App.model.dto.TravelCollectionDTO;
import cn.Travels_App.model.dto.TravelsHistoryDTO;
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
    Observable<HttpResult<PageBean<Travels>>> queryTravelsByCondition(@Body PageRequest<QueryTravelsDTO> request);


    //根据游记ID查询评论
    @Headers({"name:super"})
    @POST(Constants.URL_FIND_COMMENT)
    Observable<HttpResult<PageBean<CommentRespDTO>>> queryCommentList(@Body PageRequest<Comment> request);

    //获取历史游记
    @Headers({"name:super"})
    @POST(Constants.URL_findHistoryTracels)
    Observable<HttpResult<PageBean<TravelsHistoryDTO>>> findHistoryTracels(@Body PageRequest<TravelsHistoryDTO> request);

    //获取收藏游记
    @Headers({"name:super"})
    @POST(Constants.URL_findCollectionTracels)
    Observable<HttpResult<PageBean<TravelCollectionDTO>>> findCollectionTracels(@Body PageRequest<TravelCollectionDTO> request);

    //根据游记ID添加收藏
    @Headers({"name:super"})
    @POST(Constants.URL_COLLECTION)
    Observable<HttpResult<TravelCollectionDTO>> collection(@Body TravelCollectionDTO request);

    //根据游记ID取消收藏
    @Headers({"name:super"})
    @POST(Constants.URL_CANCEL_COLLECTION)
    Observable<HttpResult<TravelCollectionDTO>> cancelCollection(@Body TravelCollectionDTO collectionDTO);


    //根据游记ID取消收藏
    @Headers({"name:super"})
    @POST(Constants.URL_IS_COLLECTION)
    Observable<HttpResult<TravelCollectionDTO>> selectOne(@Body TravelCollectionDTO collectionDTO);

    //根据游记ID获取收藏数
    @Headers({"name:super"})
    @POST(Constants.URL_COLLECTION_COUNT)
    Observable<HttpResult<Integer>> selectCount(@Body TravelCollectionDTO collectionDTO);

    //根据游记ID获取游记详情
    @Headers({"name:super"})
    @POST(Constants.URL_getDetailsTRAVELS)
    Observable<HttpResult<Travels>> getDetails(@Body Travels travels);


    /*//根据一级评论ID查询二级评论
    @Headers({"name:super"})
    @POST(Constants.URL_FINDTJSPOTS)
    Observable<HttpResult<List<Comment>>> querySecondaryComment(@Query("id") Long id);*/

}
