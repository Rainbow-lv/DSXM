package com.lll.weidu.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lll.weidu.R;
import com.lll.weidu.app.MyApp;
import com.lll.weidu.bean.UserCircleBean;
import com.lll.weidu.untils.DateUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyCircleAdapter extends RecyclerView.Adapter<MyCircleAdapter.MyHolder> {
    List<UserCircleBean> list = new ArrayList<>();
    private String replace;

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mycircle_item, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        UserCircleBean userCircleBean = list.get(i);
        myHolder.mycircle_nickname.setText(userCircleBean.getNickName());
        myHolder.mycircle_header.setImageURI(userCircleBean.getHeadPic());
        myHolder.mycirlce_tvcontent.setText(userCircleBean.getContent());
        myHolder.myprise_count.setText(""+userCircleBean.getGreatNum());
        String images = userCircleBean.getImage();
        String[] split = images.split(",");//得到一个图片
        if (split.length > 0) {
            //将https成http  进行联网显示
            replace = split[0].replace("https", "http");
            Glide.with(MyApp.getContext()).load(replace).into(myHolder.sim_mycircle);//设置图片
        }
        try {
            myHolder.mycircle_time.setText(DateUtils.dateFormat(new Date(userCircleBean.getCreateTime()), DateUtils.MINUTE_PATTERN));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addAll(List<UserCircleBean> result1) {
        if (result1 != null){
            list.addAll(result1);
        }
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private final TextView mycircle_nickname;
        private final SimpleDraweeView mycircle_header;
        private final TextView mycirlce_tvcontent;
        private final TextView mycircle_time;
        private final TextView myprise_count;
        private final ImageView sim_mycircle;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            mycircle_nickname = itemView.findViewById(R.id.mycircle_nickname);
            mycircle_header = itemView.findViewById(R.id.mycircle_header);
            mycirlce_tvcontent = itemView.findViewById(R.id.mycirlce_tvcontent);
            mycircle_time = itemView.findViewById(R.id.mycircle_time);
            myprise_count = itemView.findViewById(R.id.myprise_count);
            sim_mycircle = itemView.findViewById(R.id.sim_mycircle);
        }
    }
}
