package com.lll.weidu.adapter;

import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lll.weidu.GoCommActivity;
import com.lll.weidu.R;
import com.lll.weidu.app.MyApp;
import com.lll.weidu.bean.UserSelectOrder;

import java.util.ArrayList;
import java.util.List;

public class ReCommContentAdapter extends RecyclerView.Adapter<ReCommContentAdapter.MyHolder> {
    List<UserSelectOrder.DetailListBean> mlist = new ArrayList<>();

    String orderId;
    private String replace;

    public ReCommContentAdapter(String orderId) {
        this.orderId = orderId;
    }

    public void AddAll(List<UserSelectOrder.DetailListBean> detailList) {
        if (detailList != null){
            mlist.addAll(detailList);
        }
    }
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recomm_item_layout, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder myHolder, final int i) {
        UserSelectOrder.DetailListBean detailListBean = mlist.get(i);
        myHolder.go_comment_title.setText(detailListBean.getCommodityName());
        myHolder.go_comment_price.setText("￥"+detailListBean.getCommodityPrice());
    //    myHolder.go_comment_Iamge.setImageURI(detailListBean.getCommodityPic());
        String images = mlist.get(i).getCommodityPic();//得到图片集
        String[] split = images.split(",");//得到一个图片
        if (split.length > 0) {
            //将https成http  进行联网显示
            replace = split[0].replace("https", "http");
            Glide.with(MyApp.getContext()).load(replace).into(myHolder.go_comment_Iamge);//设置图片
        }
        myHolder.go_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(myHolder.itemView.getContext(),GoCommActivity.class);
                double price = mlist.get(i).getCommodityPrice();
                String name = mlist.get(i).getCommodityName();
                int id = mlist.get(i).getCommodityId();
                intent.putExtra("image", replace);
                intent.putExtra("name", name);
                intent.putExtra("price",price);
                intent.putExtra("id",id);
                intent.putExtra("orderId",orderId);
                myHolder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }



    public class MyHolder extends RecyclerView.ViewHolder {
        private final TextView go_comment_title;
        private final TextView go_comment_price;
        private final ImageView go_comment_Iamge;
        private final Button go_comment;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            go_comment_title = itemView.findViewById(R.id.go_comment_title);
            go_comment_price = itemView.findViewById(R.id.go_comment_price);
            go_comment_Iamge = itemView.findViewById(R.id.go_comment_Iamge);
            go_comment = itemView.findViewById(R.id.go_comment);
        }
    }
}
