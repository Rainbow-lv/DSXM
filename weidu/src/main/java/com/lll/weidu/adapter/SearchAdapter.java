package com.lll.weidu.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lll.weidu.R;
import com.lll.weidu.app.MyApp;
import com.lll.weidu.bean.SearchBean;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyHolder> {
    private List<SearchBean> list = new ArrayList<>();
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.search_item, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        SearchBean searchBean = list.get(i);
        Glide.with(MyApp.getContext()).load(searchBean.getMasterPic()).into(myHolder.searchimage);
        myHolder.searchcontext.setText(searchBean.getCommodityName());
        myHolder.searchprice.setText("￥" + searchBean.getPrice());
        myHolder.searchnum.setText(searchBean.getSaleNum() + "");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addList(List<SearchBean> result) {
        if (result != null){
            list.addAll(result);
        }
    }
    public void deleteAll() {
        list.clear();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private final SimpleDraweeView searchimage;
        private final TextView searchcontext;
        private final TextView searchprice;
        private final TextView searchnum;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            searchimage = itemView.findViewById(R.id.search_image);
            searchcontext = itemView.findViewById(R.id.search_context);
            searchprice = itemView.findViewById(R.id.search_price);
            searchnum = itemView.findViewById(R.id.search_num);
        }
    }
}
