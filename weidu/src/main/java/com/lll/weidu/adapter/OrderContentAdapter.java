package com.lll.weidu.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lll.weidu.R;
import com.lll.weidu.app.MyApp;
import com.lll.weidu.bean.UserSelectOrder;

import java.util.ArrayList;
import java.util.List;

public class OrderContentAdapter extends RecyclerView.Adapter<OrderContentAdapter.MyHolder> {
    List<UserSelectOrder.DetailListBean> mlist = new ArrayList<>();
    public void AddAll(List<UserSelectOrder.DetailListBean> detailList) {
        if (detailList != null){
            mlist.addAll(detailList);
        }
    }
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.order_item_layout, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        UserSelectOrder.DetailListBean detailListBean = mlist.get(i);
        myHolder.mOrder_nei_name.setText(detailListBean.getCommodityName());
        myHolder.mOrder_nei_price.setText("￥"+detailListBean.getCommodityPrice());
        myHolder.mOrder_nei_number.setText(detailListBean.getCommentStatus()+"");
        //得到图片集
        String images = mlist.get(i).getCommodityPic();
        String[] split = images.split(",");//得到一个图片
        if (split.length>0) {
            //将https成http  进行联网显示
            String replace = split[0].replace("https", "http");
            Glide.with(MyApp.getContext()).load(replace).into(myHolder.mOrder_nei_touxiang);//设置图片
        }
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }



    public class MyHolder extends RecyclerView.ViewHolder {
        private final ImageView mOrder_nei_touxiang;
        private final TextView mOrder_nei_name;
        private final TextView mOrder_nei_price;
        private final TextView mOrder_nei_number;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            mOrder_nei_touxiang = itemView.findViewById(R.id.mOrder_nei_touxiang);
            mOrder_nei_name = itemView.findViewById(R.id.mOrder_nei_name);
            mOrder_nei_price = itemView.findViewById(R.id.mOrder_nei_price);
            mOrder_nei_number = itemView.findViewById(R.id.mOrder_nei_number);
        }
    }
}
