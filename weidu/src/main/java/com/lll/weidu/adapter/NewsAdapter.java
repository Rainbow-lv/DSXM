package com.lll.weidu.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lll.weidu.DetailsActivity;
import com.lll.weidu.R;
import com.lll.weidu.app.MyApp;
import com.lll.weidu.bean.GoodsBean;
import com.lll.weidu.bean.GoodsList;


import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyView> {

    private List<GoodsBean.RxxpBean.CommodityListBean> list = new ArrayList<>();

    public void rxaddList(List<GoodsBean.RxxpBean.CommodityListBean> rxxpcommodityList) {
        if (rxxpcommodityList != null){
            list.addAll(rxxpcommodityList);
        }
    }
    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_layout, viewGroup, false);
        return new MyView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyView myView, int i) {
        final GoodsBean.RxxpBean.CommodityListBean commodityListBean = list.get(i);
        myView.news_title.setText(commodityListBean.getCommodityName());
        myView.news_price.setText("￥"+commodityListBean.getPrice());
        myView.news_image.setImageURI(commodityListBean.getMasterPic());
      //  Glide.with(MyApp.getContext()).load(commodityListBean.getMasterPic()).into(myView.news_image);
        myView.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(myView.itemView.getContext(), DetailsActivity.class);
                intent.putExtra("commodityId", commodityListBean.getCommodityId());
                myView.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class MyView extends RecyclerView.ViewHolder {

        private final SimpleDraweeView news_image;
        private final TextView news_title;
        private final TextView news_price;

        public MyView(@NonNull View itemView) {
            super(itemView);
            news_image = itemView.findViewById(R.id.news_image);
            news_title = itemView.findViewById(R.id.news_title);
            news_price = itemView.findViewById(R.id.news_price);
        }
    }
}
