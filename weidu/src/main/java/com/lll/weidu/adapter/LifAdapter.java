package com.lll.weidu.adapter;

import android.content.Context;
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

public class LifAdapter extends RecyclerView.Adapter<LifAdapter.MyView> {

    private List<GoodsBean.PzshBean.CommodityListBeanX> list = new ArrayList<>();

    public void rxaddList(List<GoodsBean.PzshBean.CommodityListBeanX> pzshcommodityList) {
        if (pzshcommodityList != null){
            list.addAll(pzshcommodityList);
        }
    }
//    public void rxaddList(List<GoodsList.PzshBean.CommodityListBeanX> user) {
//        if (user != null) {
//            list.addAll(user);
//        }
//    }

    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lif_layout, viewGroup, false);
        return new MyView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyView myView, int i) {
        final GoodsBean.PzshBean.CommodityListBeanX commodityListBeanX = list.get(i);
        myView.lif_title.setText(commodityListBeanX.getCommodityName());
        myView.lif_price.setText("￥" + commodityListBeanX.getPrice());
        myView.lif_image.setImageURI(commodityListBeanX.getMasterPic());
       // Glide.with(MyApp.getContext()).load(commodityListBeanX.getMasterPic()).into(myView.lif_image);
        myView.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(myView.itemView.getContext(), DetailsActivity.class);
                intent.putExtra("commodityId", commodityListBeanX.getCommodityId());
                myView.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class MyView extends RecyclerView.ViewHolder {

        private final SimpleDraweeView lif_image;
        private final TextView lif_title;
        private final TextView lif_price;

        public MyView(@NonNull View itemView) {
            super(itemView);
            lif_image = itemView.findViewById(R.id.lif_image);
            lif_title = itemView.findViewById(R.id.lif_title);
            lif_price = itemView.findViewById(R.id.lif_price);
        }
    }
}
