package com.lll.zk02_lx.core;

import com.lll.zk02_lx.GoodsList;
import com.lll.zk02_lx.bean.GoodsBanner;
import com.lll.zk02_lx.bean.Result;
import com.lll.zk02_lx.bean.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface IRequest {

    /**
     * banner轮播图
     *
     * @return
     */
    @GET("small/commodity/v1/bannerShow")
    Observable<Result<List<GoodsBanner>>> banner();

    /**
     * 列表展示
     * @return
     */
    @GET("small/commodity/v1/commodityList")
    Observable<Result<GoodsList>> getlist();
}
