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
import com.lll.zk02_lx.bean.User;

import java.util.ArrayList;
import java.util.List;

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.MyHolder> {
    List<GoodsList.PzshBean.CommodityListBeanX> list = new ArrayList();
    public void AddAll(List<GoodsList.PzshBean.CommodityListBeanX> pzshcommodityList) {
        if (pzshcommodityList != null){
            list.addAll(pzshcommodityList);
        }
    }
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.grid_item, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        GoodsList.PzshBean.CommodityListBeanX commodityListBeanX = list.get(i);
        myHolder.grid_img.setImageURI(commodityListBeanX.getMasterPic());
        myHolder.grid_tv.setText(commodityListBeanX.getCommodityName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }




    public class MyHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView grid_img;
        private final TextView grid_tv;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            grid_img = itemView.findViewById(R.id.grid_image);
            grid_tv = itemView.findViewById(R.id.grid_tv);
        }
    }
}
