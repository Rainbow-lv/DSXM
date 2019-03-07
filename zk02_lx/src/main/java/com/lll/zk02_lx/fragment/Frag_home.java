package com.lll.zk02_lx.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lll.zk02_lx.GoodsList;
import com.lll.zk02_lx.R;
import com.lll.zk02_lx.adapter.GoodsListAdapter;
import com.lll.zk02_lx.adapter.GridAdapter;
import com.lll.zk02_lx.bean.GoodsBanner;
import com.lll.zk02_lx.bean.Result;
import com.lll.zk02_lx.bean.User;
import com.lll.zk02_lx.core.DataCall;
import com.lll.zk02_lx.presenter.BannerPresenter;
import com.lll.zk02_lx.presenter.GoodsListPresenter;
import com.lll.zk02_lx.presenter.GridPresenter;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Frag_home extends Fragment {
    @BindView(R.id.banner)
    Banner banner;
    Unbinder unbinder;
    @BindView(R.id.grid_recy)
    RecyclerView gridRecy;
    @BindView(R.id.lin_recy)
    RecyclerView linRecy;
    private BannerPresenter bannerPresenter;
    private GridPresenter gridPresenter;
    private GridAdapter gridAdapter;
    private GridLayoutManager gridLayoutManager;
    private GoodsListPresenter goodsListPresenter;
    private GoodsListAdapter goodsListAdapter;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        //创建banner对象
        bannerPresenter = new BannerPresenter(new BannerCall());
        bannerPresenter.request();
        //创建九宫格对象
        gridPresenter = new GridPresenter(new GridCall());
        gridPresenter.request();
        //设置布局
        gridLayoutManager = new GridLayoutManager(getContext(),3);
        gridRecy.setLayoutManager(gridLayoutManager);
        //自定义适配器
        gridAdapter = new GridAdapter();
        gridRecy.setAdapter(gridAdapter);
        //创建列表对象
        goodsListPresenter = new GoodsListPresenter(new ListCall());
        goodsListPresenter.request();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    //轮播图
    private class BannerCall implements DataCall<Result<List<GoodsBanner>>> {
        @Override
        public void success(Result<List<GoodsBanner>> result) {
            List<String> list = new ArrayList<>();
            List<GoodsBanner> result1 = result.getResult();
            for (int i = 0; i < result1.size(); i++) {
                list.add(result1.get(i).getImageUrl());
            }
            banner.setImages(list);
            banner.setImageLoader(new MyBanner());
            banner.start();
        }

        @Override
        public void fail(Result result) {

        }
    }

    private class MyBanner extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Uri parse = Uri.parse((String) path);
            imageView.setImageURI(parse);
        }

        @Override
        public ImageView createImageView(Context context) {
            SimpleDraweeView simpleDraweeView = (SimpleDraweeView) View.inflate(getContext(), R.layout.banner_item, null);
            return simpleDraweeView;
        }
    }

    private class ListCall implements DataCall<Result<GoodsList>> {

        @Override
        public void success(Result<GoodsList> result) {
            GoodsList result1 = result.getResult();
            List<GoodsList.RxxpBean> rxxp = result1.getRxxp();
            List<GoodsList.RxxpBean.CommodityListBeanXX> commodityList = rxxp.get(0).getCommodityList();
            //自定义适配器
            goodsListAdapter = new GoodsListAdapter();
            goodsListAdapter.AddAll(commodityList);
            //设置瀑布流
            staggeredGridLayoutManager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
            linRecy.setLayoutManager(staggeredGridLayoutManager);
            linRecy.setAdapter(goodsListAdapter);
        }

        @Override
        public void fail(Result result) {

        }
    }

    private class GridCall implements DataCall<Result<GoodsList>> {
        @Override
        public void success(Result<GoodsList> result) {
            GoodsList result1 = result.getResult();
            List<GoodsList.PzshBean> pzsh = result1.getPzsh();
            List<GoodsList.PzshBean.CommodityListBeanX> pzshcommodityList = pzsh.get(0).getCommodityList();
            //自定义适配器
            gridAdapter = new GridAdapter();
            gridAdapter.AddAll(pzshcommodityList);
            //设置布局
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),4);
            gridRecy.setLayoutManager(gridLayoutManager);
            gridRecy.setAdapter(gridAdapter);
        }

        @Override
        public void fail(Result result) {

        }
    }
}
