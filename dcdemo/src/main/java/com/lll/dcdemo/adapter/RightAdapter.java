package com.lll.dcdemo.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lll.dcdemo.R;
import com.lll.dcdemo.app.MyApp;
import com.lll.dcdemo.bean.Goods;
import com.lll.dcdemo.view.AddSubLayout;

import java.util.ArrayList;
import java.util.List;

public class RightAdapter extends RecyclerView.Adapter<RightAdapter.MyHolder> {
    List<Goods.ListBean> mlist = new ArrayList<>();
    public void AddAll(List<Goods.ListBean> list) {
        if (list != null){
            mlist.addAll(list);
        }
    }
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.right_item, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        Goods.ListBean listBean = mlist.get(i);
        myHolder.text.setText(listBean.getTitle());
        //单价
        myHolder.price.setText("￥"+listBean.getPrice());
        String images = mlist.get(i).getImages();//得到图片集
        String[] split = images.split("!");//得到一个图片
        if (split.length>0) {
            //将https成http  进行联网显示
            String replace = split[0].replace("https", "http");
            if (MyApp.getcontext() != null){
                Glide.with(MyApp.getcontext()).load(replace).into(myHolder.image);//设置图片
            }

        }
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public void clear(){
        mlist.clear();
    }



    public class MyHolder extends RecyclerView.ViewHolder {
        TextView text;
        TextView price;
        ImageView image;
        AddSubLayout addSub;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.text);
            price = itemView.findViewById(R.id.text_price);
            image = itemView.findViewById(R.id.image);
            addSub = itemView.findViewById(R.id.add_sub_layout);
        }
    }
}
