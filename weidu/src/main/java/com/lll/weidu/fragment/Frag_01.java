package com.lll.weidu.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lll.weidu.R;
import com.lll.weidu.SearchActivity;
import com.lll.weidu.adapter.FinAdapter;
import com.lll.weidu.adapter.LifAdapter;
import com.lll.weidu.adapter.NewsAdapter;
import com.lll.weidu.bean.GoodsBanner;
import com.lll.weidu.bean.GoodsBean;
import com.lll.weidu.bean.GoodsList;
import com.lll.weidu.bean.Result;
import com.lll.weidu.core.DataCall;
import com.lll.weidu.core.exception.ApiException;
import com.lll.weidu.presenter.BannerPresenter;
import com.lll.weidu.presenter.GoodsListPresenter;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class Frag_01 extends Fragment {
    @BindView(R.id.search_text)
    TextView searchText;
    @BindView(R.id.banner)
    MZBannerView banner;
    @BindView(R.id.recy_news)
    RecyclerView recyNews;
    @BindView(R.id.recy_fin)
    RecyclerView recyFin;
    @BindView(R.id.recy_lif)
    RecyclerView recyLif;
    Unbinder unbinder;
    @BindView(R.id.img_menu)
    ImageView imgMenu;
    @BindView(R.id.img_search)
    ImageView imgSearch;
    private BannerPresenter bannerPresenter;
    private GoodsListPresenter goodsListPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_01, container, false);
        unbinder = ButterKnife.bind(this, view);
        bannerPresenter = new BannerPresenter(new BannerCall());
        bannerPresenter.reqeust();
        goodsListPresenter = new GoodsListPresenter(new GoodsCall());
        goodsListPresenter.reqeust();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

    }

    @OnClick({R.id.img_menu, R.id.img_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_menu:
                //popwindow
                //popWindow展示框
//                final View view1 = View.inflate(getActivity(), R.layout.pop_layput, null);
//                final PopupWindow window = new PopupWindow(view, 1000, 250, true);
//                //focusable可聚焦的
//                window.setBackgroundDrawable(new ColorDrawable());
//                window.setOutsideTouchable(true);
//                window.setTouchable(true);
//                window.showAsDropDown(searchText);
//                final RadioGroup popupwindowItemClass = view.findViewById(R.id.popupwindow_item_class);
//                final RadioGroup popupwindowItemTitle = view.findViewById(R.id.popupwindow_item_title);
//                popupwindowItemClass.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//                    @Override
//                    public void onCheckedChanged(RadioGroup group, int checkedId) {
//                        RadioButton child = view1.findViewById(popupwindowItemClass.getCheckedRadioButtonId());
//                        Toast.makeText(getActivity(), "" + child.getText(), Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(getActivity(), SearchActivity.class);
//                        intent.putExtra("name", child.getText());
//                        startActivity(intent);
//                    }
//                });
//                popupwindowItemTitle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//                    @Override
//                    public void onCheckedChanged(RadioGroup group, int checkedId) {
//                        //获取子控件的id
//                        RadioButton child= view1.findViewById(popupwindowItemTitle.getCheckedRadioButtonId());
//                        Intent intent=new Intent(getActivity(),SearchActivity.class);
//                        intent.putExtra("name",child.getText());
//                        startActivity(intent);
//                    }
//                });
                break;
            case R.id.img_search:
                //跳转到搜索页面
                startActivity(new Intent(getActivity(), SearchActivity.class));
                break;
        }
    }


    private class BannerCall implements DataCall<Result<List<GoodsBanner>>> {
        @Override
        public void success(Result<List<GoodsBanner>> result) {
            if (result.getStatus().equals("0000")) {
                banner.setIndicatorVisible(false);
                banner.setPages(result.getResult(), new MZHolderCreator<BannerViewHolder>() {
                    @Override
                    public BannerViewHolder createViewHolder() {
                        return new BannerViewHolder();
                    }
                });
                banner.start();
            }
        }

        @Override
        public void fail(ApiException e) {
            Toast.makeText(getContext(), "失败", Toast.LENGTH_SHORT).show();
        }
    }

    private class GoodsCall implements DataCall<Result<GoodsBean>> {

        @Override
        public void success(Result<GoodsBean> result) {
            GoodsBean result1 = result.getResult();
            GoodsBean.MlssBean mlss = result1.getMlss();
            List<GoodsBean.MlssBean.CommodityListBeanXX> mlsscommodityList = mlss.getCommodityList();
            GoodsBean.PzshBean pzsh = result1.getPzsh();
            List<GoodsBean.PzshBean.CommodityListBeanX> pzshcommodityList = pzsh.getCommodityList();
            GoodsBean.RxxpBean rxxp = result1.getRxxp();
            List<GoodsBean.RxxpBean.CommodityListBean> rxxpcommodityList = rxxp.getCommodityList();

            //自定义适配器
            NewsAdapter newsAdapter = new NewsAdapter();
            FinAdapter finAdapter = new FinAdapter();
            LifAdapter lifAdapter = new LifAdapter();

            newsAdapter.rxaddList(rxxpcommodityList);
            finAdapter.rxaddList(mlsscommodityList);
            lifAdapter.rxaddList(pzshcommodityList);

            //设置瀑布流
            StaggeredGridLayoutManager news = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
            StaggeredGridLayoutManager fin = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
            StaggeredGridLayoutManager lif = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

            //设置适配器
            recyNews.setLayoutManager(news);
            recyNews.setAdapter(newsAdapter);
            recyFin.setLayoutManager(fin);
            recyFin.setAdapter(finAdapter);
            recyLif.setLayoutManager(lif);
            recyLif.setAdapter(lifAdapter);
        }

        @Override
        public void fail(ApiException e) {
            Toast.makeText(getContext(), e.getDisplayMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private class BannerViewHolder implements MZViewHolder<GoodsBanner> {
        private SimpleDraweeView image;

        @Override
        public View createView(Context context) {
            View view = LayoutInflater.from(context).inflate(R.layout.banner_layout, null);
            image = view.findViewById(R.id.banner_image);
            return view;
        }

        @Override
        public void onBind(Context context, int i, GoodsBanner goodsBanner) {
            image.setImageURI(Uri.parse(goodsBanner.getImageUrl()));
        }
    }
}
