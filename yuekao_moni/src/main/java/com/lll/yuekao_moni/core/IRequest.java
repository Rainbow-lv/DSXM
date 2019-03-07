package com.lll.yuekao_moni.core;

import com.lll.yuekao_moni.bean.GoodsListBean;
import com.lll.yuekao_moni.bean.JiuBean;
import com.lll.yuekao_moni.bean.LeftBean;
import com.lll.yuekao_moni.bean.Result;
import com.lll.yuekao_moni.bean.ShopCar;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface IRequest {
    //购物车
    @GET("ks/product/getCarts?uid=51")
    Observable<Result<List<ShopCar>>> shopCar();
    //九宫格
    @GET("ks/product/getCatagory")
    Observable<Result<List<JiuBean>>> jiu();
    //列表
    @GET("ks/ad/getAd")
    Observable<Result<List<GoodsListBean>>> goods();
//    //左边条目
//    @GET("ks/product/getProductCatagory")
//    Observable<Result<LeftBean>> left();
}
