package com.lll.weidu.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lll.weidu.R;
import com.lll.weidu.bean.UserFootBean;
import com.lll.weidu.untils.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

public class UserFootAdapter extends RecyclerView.Adapter<UserFootAdapter.MyHolder> {

    List<UserFootBean> list = new ArrayList<>();

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_foot_item, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        UserFootBean userFootBean = list.get(i);
        //设置数据
        myHolder.foot_image.setImageURI(userFootBean.getMasterPic());
        myHolder.foot_context.setText(userFootBean.getCommodityName());
        myHolder.foot_price.setText("￥"+userFootBean.getPrice());
        myHolder.foot_see.setText("已浏览"+userFootBean.getBrowseNum()+"次");
        try {
            myHolder.foot_time.setText(DateUtils.dateFormat(new Date(userFootBean.getBrowseTime()),DateUtils.MINUTE_PATTERN));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void AddAll(List<UserFootBean> result1) {
        if (result1 != null){
            list.addAll(result1);
        }
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView foot_image;
        private final TextView foot_context;
        private final TextView foot_price;
        private final TextView foot_see;
        private final TextView foot_time;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            foot_image = itemView.findViewById(R.id.foot_image);
            foot_context = itemView.findViewById(R.id.foot_context);
            foot_price = itemView.findViewById(R.id.foot_price);
            foot_see = itemView.findViewById(R.id.foot_see);
            foot_time = itemView.findViewById(R.id.foot_time);
        }
    }
}
