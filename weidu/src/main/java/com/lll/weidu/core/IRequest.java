package com.lll.weidu.core;

import com.lll.weidu.bean.AddressBean;
import com.lll.weidu.bean.CircleBean;
import com.lll.weidu.bean.Comment;
import com.lll.weidu.bean.DetailsBean;
import com.lll.weidu.bean.GoodsBanner;
import com.lll.weidu.bean.GoodsBean;
import com.lll.weidu.bean.GoodsList;
import com.lll.weidu.bean.Result;
import com.lll.weidu.bean.SearchBean;
import com.lll.weidu.bean.ShopCar;
import com.lll.weidu.bean.User;
import com.lll.weidu.bean.UserCircleBean;
import com.lll.weidu.bean.UserFootBean;
import com.lll.weidu.bean.UserPresonBean;
import com.lll.weidu.bean.UserSelectOrder;
import com.lll.weidu.bean.UserWallet;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface IRequest {

    /**
     * 登录
     *
     * @param mobile
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("small/user/v1/login")
    Observable<Result<User>> login(@Field("phone") String mobile,
                                   @Field("pwd") String password);

    /**
     * 注册
     *
     * @param mobile
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("small/user/v1/register")
    Observable<Result> reg(@Field("phone") String mobile,
                           @Field("pwd") String password);

    /**
     * banner轮播图
     *
     * @return
     */
    @GET("small/commodity/v1/bannerShow")
    Observable<Result<List<GoodsBanner>>> bann();

    /**
     * 首页列表展示
     *
     * @return
     */
    @GET("small/commodity/v1/commodityList")
    Observable<Result<GoodsBean>> getlist();

    /**
     * 搜索页面
     *
     * @param keyword
     * @param page
     * @param count
     * @return
     */
    @GET("small/commodity/v1/findCommodityByKeyword")
    Observable<Result<List<SearchBean>>> search(@Query("keyword") String keyword,
                                                @Query("page") int page,
                                                @Query("count") int count);

    /**
     * 圈子
     *
     * @param page
     * @param count
     * @return
     */
    @GET("small/circle/v1/findCircleList")
    Observable<Result<List<CircleBean>>> circle(
            @Header("userId") long userId,
            @Header("sessionId") String sessionId,
            @Query("page") int page,
            @Query("count") int count);

    /**
     * 圈子点赞
     */
    @FormUrlEncoded
    @POST("small/circle/verify/v1/addCircleGreat")
    Observable<Result> addCircleGreat(
            @Header("userId") String userId,
            @Header("sessionId") String sessionId,
            @Field("circleId") long circleId);

    /**
     * 详情页面
     *
     * @param userId
     * @param sessionId
     * @param commodityId
     * @return
     */
    @GET("small/commodity/v1/findCommodityDetailsById")
    Observable<Result<DetailsBean>> details(@Header("userId") long userId,
                                            @Header("sessionId") String sessionId,
                                            @Query("commodityId") int commodityId);

    /**
     * 购物车列表
     *
     * @param userId
     * @param sessionId
     * @return
     */
    @GET("small/order/verify/v1/findShoppingCart")
    Observable<Result<List<ShopCar>>> shopcar(@Header("userId") long userId,
                                              @Header("sessionId") String sessionId);

    /**
     * 评论
     *
     * @param commodityId
     * @param page
     * @param count
     * @return
     */
    @GET("small/commodity/v1/CommodityCommentList")
    Observable<Result<List<Comment>>> comment(@Query("commodityId") int commodityId,
                                              @Query("page") int page,
                                              @Query("count") int count);

    /**
     * 同步购物车数据
     *
     * @param userId
     * @param sessionId
     * @param data
     * @return
     */
    @PUT("small/order/verify/v1/syncShoppingCart")
    @FormUrlEncoded
    Observable<Result> addTo(@Header("userId") long userId,
                             @Header("sessionId") String sessionId,
                             @Field("data") String data);


    /**
     * 收货地址列表
     *
     * @param userId
     * @param sessionId
     * @return
     */
    @GET("small/user/verify/v1/receiveAddressList")
    Observable<Result<List<AddressBean>>> addresslist(@Header("userId") int userId,
                                                      @Header("sessionId") String sessionId);

    /**
     * 创建订单
     *
     * @param userId
     * @param sessionId
     * @param data
     * @param totalPrice
     * @param addressId
     * @return
     */
    @POST("small/order/verify/v1/createOrder")
    @FormUrlEncoded
    Observable<Result> createOreder(@Header("userId") long userId,
                                    @Header("sessionId") String sessionId,
                                    @Field("orderInfo") String data,
                                    @Field("totalPrice") double totalPrice,
                                    @Field("addressId") int addressId);

    /**
     * 个人资料
     *
     * @param userId
     * @param sessionId
     * @return
     */
    @GET("small/user/verify/v1/getUserById")
    Observable<Result<UserPresonBean>> person(@Header("userId") long userId,
                                              @Header("sessionId") String sessionId);


    /**
     * 我的足迹
     *
     * @param userId
     * @param sessionId
     * @param page
     * @param count
     * @return
     */
    @GET("small/commodity/verify/v1/browseList")
    Observable<Result<List<UserFootBean>>> foot(@Header("userId") long userId,
                                                @Header("sessionId") String sessionId,
                                                @Query("page") int page,
                                                @Query("count") int count);

    /**
     * 我的钱包
     *
     * @param userId
     * @param sessionId
     * @param page
     * @param count
     * @return
     */
    @GET("small/user/verify/v1/findUserWallet")
    Observable<Result<UserWallet>> wallet(@Header("userId") long userId,
                                          @Header("sessionId") String sessionId,
                                          @Query("page") int page,
                                          @Query("count") int count);

    /**
     * 我的圈子
     *
     * @param userId
     * @param sessionId
     * @param page
     * @param count
     * @return
     */
    @GET("small/circle/verify/v1/findMyCircleById")
    Observable<Result<List<UserCircleBean>>> myCircle(@Header("userId") long userId,
                                                      @Header("sessionId") String sessionId,
                                                      @Query("page") int page,
                                                      @Query("count") int count);

    /**
     * 默认收货地址
     *
     * @param userId
     * @param sessionId
     * @param id
     * @return
     */
    @POST("small/user/verify/v1/setDefaultReceiveAddress")
    @FormUrlEncoded
    Observable<Result> moren(@Header("userId") int userId,
                             @Header("sessionId") String sessionId,
                             @Field("id") int id);

    /**
     * 新增收货地址
     *
     * @param userId
     * @param sessionId
     * @param realName
     * @param phone
     * @param address
     * @param zipCode
     * @return
     */
    @POST("small/user/verify/v1/addReceiveAddress")
    @FormUrlEncoded
    Observable<Result> addAddrss(@Header("userId") long userId,
                                 @Header("sessionId") String sessionId,
                                 @Field("realName") String realName,
                                 @Field("phone") String phone,
                                 @Field("address") String address,
                                 @Field("zipCode") String zipCode);

    /**
     * 发布圈子
     */
    @POST("small/circle/verify/v1/releaseCircle")
    Observable<Result> releaseCircle(@Header("userId") long userId,
                                     @Header("sessionId") String sessionId,
                                     @Body MultipartBody body);

    /**
     * 删除我发布过的圈子
     *
     * @param userId
     * @param sessionId
     * @param circleId
     * @return
     */
    @DELETE("small/circle/verify/v1/deleteCircle")
    Observable<Result> deleteCircle(@Header("userId") long userId,
                                    @Header("sessionId") String sessionId,
                                    @Query("circleId") int circleId);

    /**
     * 查询全部订单
     *
     * @param userId
     * @param sessionId
     * @param status
     * @param page
     * @param count
     * @return
     */
    @GET("small/order/verify/v1/findOrderListByStatus")
    Observable<Result<List<UserSelectOrder>>> AllOrder(@Header("userId") long userId,
                                                       @Header("sessionId") String sessionId,
                                                       @Query("status") int status,
                                                       @Query("page") int page,
                                                       @Query("count") int count);

    /**
     * 支付
     * @param userId
     * @param sessionId
     * @param orderId
     * @param payType
     * @return
     */
    @FormUrlEncoded
    @POST("small/order/verify/v1/pay")
    Observable<Result> pay(@Header("userId") long userId,
                           @Header("sessionId") String sessionId,
                           @Field("orderId") String orderId,
                           @Field("payType") int payType);

    /**
     * 收货
     * @param userId
     * @param sessionId
     * @param orderId
     * @return
     */
    @FormUrlEncoded
    @PUT("small/order/verify/v1/confirmReceipt")
    Observable<Result> recive(@Header("userId")long userId,
                              @Header("sessionId")String sessionId,
                              @Field("orderId")String orderId);

    /**
     * 商品评价
     * @param userId
     * @param sessionId
     * @param body
     * @return
     */
    @POST("small/commodity/verify/v1/addCommodityComment")
    Observable<Result> shopComm(@Header("userId")long userId,
                                @Header("sessionId")String sessionId,
                                @Body MultipartBody body);

    /**
     * 删除订单
     * @param userId
     * @param sessionId
     * @param orderId
     * @return
     */
    @DELETE("small/order/verify/v1/deleteOrder")
    Observable<Result> deleteorder(@Header("userId")long userId,
                                @Header("sessionId")String sessionId,
                                @Query("orderId")String orderId);

    //上传头像
    @POST("small/user/verify/v1/modifyHeadPic")
    Observable<Result> head(@Header("userId")long userId,
                            @Header("sessionId")String sessionId,
                            @Body MultipartBody body);

    /**
     * 修改昵称
     * @param userId
     * @param sessionId
     * @param nickName
     * @return
     */
    @FormUrlEncoded
    @PUT("small/user/verify/v1/modifyUserNick")
    Observable<Result> updatename(@Header("userId")long userId,
                                  @Header("sessionId")String sessionId,
                                  @Field("nickName")String nickName);
}

