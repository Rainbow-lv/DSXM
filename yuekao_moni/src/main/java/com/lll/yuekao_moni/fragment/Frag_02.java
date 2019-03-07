package com.lll.yuekao_moni.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lll.yuekao_moni.FlowActivity;
import com.lll.yuekao_moni.R;
import com.lll.yuekao_moni.adapter.GoodsListAdapter;
import com.lll.yuekao_moni.adapter.JiuAdapter;
import com.lll.yuekao_moni.bean.GoodsListBean;
import com.lll.yuekao_moni.bean.JiuBean;
import com.lll.yuekao_moni.bean.Result;
import com.lll.yuekao_moni.core.DataCall;
import com.lll.yuekao_moni.presenter.GoodsListPresenter;
import com.lll.yuekao_moni.presenter.JiuPresenter;
import com.lll.yuekao_moni.view.FlowLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class Frag_02 extends Fragment {
    @BindView(R.id.recy_jiu)
    RecyclerView recyJiu;
    Unbinder unbinder;
    @BindView(R.id.recy_list)
    RecyclerView recyList;
    @BindView(R.id.btn_fly)
    Button btnFly;
    private JiuPresenter jiuPresenter;
    private JiuAdapter jiuAdapter;
    private GoodsListPresenter goodsListPresenter;
    private GoodsListAdapter goodsListAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_02, container, false);
        unbinder = ButterKnife.bind(this, view);
        //创建对象
        jiuPresenter = new JiuPresenter(new JiuCall());
        jiuPresenter.reqeust();
        //自定义适配器
        jiuAdapter = new JiuAdapter();
        //设置布局
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4);
        recyJiu.setLayoutManager(gridLayoutManager);
        recyJiu.setAdapter(jiuAdapter);
        //创建列表对象
        goodsListPresenter = new GoodsListPresenter(new GoodsCall());
        goodsListPresenter.reqeust();
        //自定义适配器
        goodsListAdapter = new GoodsListAdapter();
        //设置布局
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyList.setLayoutManager(linearLayoutManager);
        recyList.setAdapter(goodsListAdapter);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btn_fly)
    public void onViewClicked() {
      startActivity(new Intent(getContext(),FlowActivity.class));
    }

    //九宫格
    private class JiuCall implements DataCall<Result<List<JiuBean>>> {

        @Override
        public void success(Result<List<JiuBean>> data) {
            List<JiuBean> data1 = data.getData();
            jiuAdapter.AddAll(data1);
            jiuAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(Result result) {

        }
    }

    //列表展示
    private class GoodsCall implements DataCall<Result<List<GoodsListBean>>> {
        @Override
        public void success(Result<List<GoodsListBean>> data) {
            List<GoodsListBean> data1 = data.getData();
            for (int i = 0; i < data1.size(); i++) {
                goodsListAdapter.AddAll(data1);
                goodsListAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void fail(Result result) {

        }
    }
}
