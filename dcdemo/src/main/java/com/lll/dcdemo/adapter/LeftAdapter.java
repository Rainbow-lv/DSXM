package com.lll.dcdemo.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lll.dcdemo.R;
import com.lll.dcdemo.bean.Goods;

import java.util.ArrayList;
import java.util.List;

public class LeftAdapter extends RecyclerView.Adapter<LeftAdapter.MyHolder> {
    List<Goods> mlist = new ArrayList<>();
    public void AddAll(List<Goods> data1) {
        if (data1 != null){
            mlist.addAll(data1);
        }
    }
    //接口回调
    private Shop shop;
    public interface Shop{
        void success( List<Goods.ListBean> mlist);
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.left_item, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, final int i) {
        Goods goods = mlist.get(i);
        myHolder.left_text.setText(goods.getSellerName());
        myHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shop.success(mlist.get(i).getList());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }



    public class MyHolder extends RecyclerView.ViewHolder {
        private final TextView left_text;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            left_text = itemView.findViewById(R.id.left_text);
        }
    }
}
