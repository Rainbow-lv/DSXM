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

public class FinAdapter extends RecyclerView.Adapter<FinAdapter.MyView> {
    private List<GoodsBean.MlssBean.CommodityListBeanXX> list = new ArrayList<>();
    public void rxaddList(List<GoodsBean.MlssBean.CommodityListBeanXX> mlsscommodityList) {
            if (mlsscommodityList != null){
                list.addAll(mlsscommodityList);
            }
    }
    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fin_layout, viewGroup, false);
        return new MyView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyView myView, int i) {
        final GoodsBean.MlssBean.CommodityListBeanXX commodityListBeanXX = list.get(i);
        myView.fin_title.setText(commodityListBeanXX.getCommodityName());
        myView.fin_price.setText("￥"+commodityListBeanXX.getPrice());
        myView.fin_image.setImageURI(commodityListBeanXX.getMasterPic());
       // Glide.with(MyApp.getContext()).load(commodityListBeanXX.getMasterPic()).into(myView.fin_image);
        myView.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(myView.itemView.getContext(), DetailsActivity.class);
                intent.putExtra("commodityId", commodityListBeanXX.getCommodityId());
        myView.itemView.getContext().startActivity(intent);
    }
});
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class MyView extends RecyclerView.ViewHolder {

        private final SimpleDraweeView fin_image;
        private final TextView fin_title;
        private final TextView fin_price;

        public MyView(@NonNull View itemView) {
            super(itemView);
            fin_image = itemView.findViewById(R.id.fin_image);
            fin_title = itemView.findViewById(R.id.fin_title);
            fin_price = itemView.findViewById(R.id.fin_price);
        }
    }
}
