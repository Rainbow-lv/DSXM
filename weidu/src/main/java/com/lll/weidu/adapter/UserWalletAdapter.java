package com.lll.weidu.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lll.weidu.R;
import com.lll.weidu.bean.WalletBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserWalletAdapter extends RecyclerView.Adapter<UserWalletAdapter.MyHolder> {

    List<WalletBean> list = new ArrayList<>();
    public void addList(List<WalletBean> detailList) {
        if (detailList != null){
            list.addAll(detailList);
        }
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_wallet_item, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        myHolder.text_price.setText(list.get(i).getAmount()+"");
        long time = list.get(i).getCreateTime();
        Date currentTime = new Date(time);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateString = formatter.format(currentTime);
        myHolder.text_time.setText(dateString);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class MyHolder extends RecyclerView.ViewHolder {
        private final TextView text_price;
        private final TextView text_time;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            text_price = itemView.findViewById(R.id.mywalletprice);
            text_time = itemView.findViewById(R.id.mywallettime);
        }
    }
}
