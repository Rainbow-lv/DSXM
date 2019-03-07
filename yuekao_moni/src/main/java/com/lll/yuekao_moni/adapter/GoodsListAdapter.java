package com.lll.yuekao_moni.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lll.yuekao_moni.DcActivity;
import com.lll.yuekao_moni.R;
import com.lll.yuekao_moni.bean.GoodsListBean;

import java.util.ArrayList;
import java.util.List;

public class GoodsListAdapter extends RecyclerView.Adapter<GoodsListAdapter.MyHolder> {
    List<GoodsListBean> list = new ArrayList<>();
    public void AddAll(List<GoodsListBean> data1) {
        if (data1 != null){
            list.addAll(data1);
        }
    }
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder myHolder, int i) {
        GoodsListBean goodsListBean = list.get(i);
        myHolder.list_img.setImageURI(goodsListBean.getIcon());
        myHolder.list_name.setText(goodsListBean.getTitle());
        myHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myHolder.itemView.getContext().startActivity(new Intent(myHolder.itemView.getContext(),DcActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class MyHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView list_img;
        private final TextView list_name;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            list_img = itemView.findViewById(R.id.list_img);
            list_name = itemView.findViewById(R.id.list_name);
        }
    }
}
