package cn.Travels_App.common;

public class Constants {
    public static final String BASE_URL = "http://59.110.222.11:8080/";
//    public static final String BASE_URL = "http://192.168.0.109:8080/";
//=======================================================================//
    public static final String KEY_FRAGMENT = "key_fragment";
    public static final String REQUEST_CODE = "200";
    public static final String UserBean="user";

    //登录请求路径
    public static final String URL_Login="front/user/login";
    //注册请求路径
    public static final String URL_REG = "/front/user/register";
    //查找推荐的景区
    public static final String URL_FINDTJSPOTS = "/front/travels/getAllTravelsList" ;
    //查询所有的景区
    public static final String URL_FINDALLSPOTS = "front/travels/getAllTravelsList" ;
    //根据条件查询景区
    public static final String URL_FINDSPOTSBYCONDITION = "/front/travels/listPage";
   /* //查询所有的酒店
    public static final String URL_FINDALLHOTELS = "/front/hotel/all";
    //保存预定的订单
    public static final String URL_SAVEYDORDER = "/front/order/makeScheduleOrder";
    //查询我的订单
    public static final String URL_FINDMYORDER = "/front/order/queryMyOrder";
    //所有的问答
    public static final String URL_FINDALLQA = "/front/qa/queryAllQa";
    //支付我的订单
    public static final String URL_PAYMYORDER = "/front/order/goPayOrder";*/
    //保存游记
    public static final String URL_SAVETRAVELS = "front/travels/saveOrUpdata";
    //发表游记
    public static final String URL_PUBTRAVELS ="front/travels/submit";
    //查询评论
    public static final String URL_FINDComment = "front/comment/getCommentListAll";


}
