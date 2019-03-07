package com.lll.zk02_lx.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lll.zk02_lx.GoodsList;
import com.lll.zk02_lx.R;

import java.util.ArrayList;
import java.util.List;

public class GoodsListAdapter extends RecyclerView.Adapter<GoodsListAdapter.MyHolder> {
    List<GoodsList.RxxpBean.CommodityListBeanXX> list = new ArrayList();

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        GoodsList.RxxpBean.CommodityListBeanXX commodityListBeanXX = list.get(i);
        myHolder.lin_img.setImageURI(commodityListBeanXX.getMasterPic());
        myHolder.tv_name.setText(commodityListBeanXX.getCommodityName());
        myHolder.tv_price.setText("￥"+commodityListBeanXX.getPrice());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void AddAll(List<GoodsList.RxxpBean.CommodityListBeanXX> commodityList) {
        if (commodityList != null){
            list.addAll(commodityList);
        }
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView lin_img;
        private final TextView tv_name;
        private final TextView tv_price;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            lin_img = itemView.findViewById(R.id.lin_img);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_price = itemView.findViewById(R.id.tv_price);
        }
    }
}
