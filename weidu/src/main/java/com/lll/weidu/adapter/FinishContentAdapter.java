package com.lll.weidu.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lll.weidu.R;
import com.lll.weidu.app.MyApp;
import com.lll.weidu.bean.UserSelectOrder;

import java.util.ArrayList;
import java.util.List;

public class FinishContentAdapter extends RecyclerView.Adapter<FinishContentAdapter.MyHolder> {
    List<UserSelectOrder.DetailListBean> mlist = new ArrayList<>();
    public void AddAll(List<UserSelectOrder.DetailListBean> detailList) {
        if (detailList != null){
            mlist.addAll(detailList);
        }
    }
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.finish_item_layout, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        UserSelectOrder.DetailListBean detailListBean = mlist.get(i);
       // myHolder.finish_Iamge.setImageURI(detailListBean.getCommodityPic());
        myHolder.finish_title.setText(detailListBean.getCommodityName());
        myHolder.finish_price.setText("￥"+detailListBean.getCommodityPrice());
        //得到图片集
        String images = mlist.get(i).getCommodityPic();
        String[] split = images.split(",");//得到一个图片
        if (split.length>0) {
            //将https成http  进行联网显示
            String replace = split[0].replace("https", "http");
            Glide.with(MyApp.getContext()).load(replace).into(myHolder.finish_Iamge);//设置图片
        }
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }



    public class MyHolder extends RecyclerView.ViewHolder {

        private final ImageView finish_Iamge;
        private final TextView finish_title;
        private final TextView finish_price;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            finish_Iamge = itemView.findViewById(R.id.finish_Iamge);
            finish_title = itemView.findViewById(R.id.finish_title);
            finish_price = itemView.findViewById(R.id.finish_price);
        }
    }
}
