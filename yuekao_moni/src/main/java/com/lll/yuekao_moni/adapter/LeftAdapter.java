package com.lll.yuekao_moni.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lll.yuekao_moni.R;
import com.lll.yuekao_moni.bean.LeftBean;
import com.lll.yuekao_moni.bean.ShopCar;

import java.util.ArrayList;
import java.util.List;

public class LeftAdapter extends RecyclerView.Adapter<LeftAdapter.MyHolder> {
    List<ShopCar> mlist = new ArrayList<>();
    public void AddAll(List<ShopCar> data1) {
        if (data1 != null){
            mlist.addAll(data1);
        }
    }
    //接口回调
    private OnItemClickListenter onItemClickListenter;



    public interface OnItemClickListenter{
        void onItemClick(ShopCar shopCar);
    }

    public void setOnItemClickListenter(OnItemClickListenter onItemClickListenter) {
        this.onItemClickListenter = onItemClickListenter;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.left_item, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        final ShopCar shopCar = mlist.get(i);
        myHolder.left_text.setText(shopCar.getSellerName());
        //设置点击
        myHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListenter.onItemClick(shopCar);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public List<ShopCar> getList() {
        return mlist;
    }


    public class MyHolder extends RecyclerView.ViewHolder {

        private final TextView left_text;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            left_text = itemView.findViewById(R.id.left_text);
        }
    }
}
