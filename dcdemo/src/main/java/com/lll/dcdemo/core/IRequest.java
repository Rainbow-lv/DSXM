package com.lll.dcdemo.core;

import com.lll.dcdemo.bean.Goods;
import com.lll.dcdemo.bean.Result;


import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface IRequest {
    //购物车
    @GET("ks/product/getCarts?uid=51")
    Observable<Result<List<Goods>>> shopCar();
//    //九宫格
//    @GET("ks/product/getCatagory")
//    Observable<Result<List<JiuBean>>> jiu();
//    //列表
//    @GET("ks/ad/getAd")
//    Observable<Result<List<GoodsListBean>>> goods();
//    //左边条目
//    @GET("ks/product/getProductCatagory")
//    Observable<Result<LeftBean>> left();
}
