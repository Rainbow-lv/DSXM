package com.lll.yuekao_moni.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lll.yuekao_moni.R;
import com.lll.yuekao_moni.app.MyApp;
import com.lll.yuekao_moni.bean.Goods;
import com.lll.yuekao_moni.view.AddSubLayout;

import java.util.ArrayList;
import java.util.List;

public class RightAdapter extends RecyclerView.Adapter<RightAdapter.MyHolder> {
    List<Goods> mlist = new ArrayList<>();
    public void AddAll(List<Goods> list) {
        if (list != null){
            mlist.addAll(list);
        }
    }
    //接口回调
    private OnNumListener onNumListener;




    public interface OnNumListener{
        void onNum();
    }

    public void setOnNumListener(OnNumListener onNumListener) {
        this.onNumListener = onNumListener;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.right_item, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        final Goods goods = mlist.get(i);
        myHolder.text.setText(goods.getTitle());
        //单价
        myHolder.price.setText("￥"+goods.getPrice());
        String imageurl = "https" + goods.getImages().split("https")[1];
     //   Log.i("dt", "imageUrl: " + imageurl);
        imageurl = imageurl.substring(0, imageurl.lastIndexOf(".jpg") + ".jpg".length());
        if (MyApp.getcontext() != null){
            Glide.with(MyApp.getcontext()).load(imageurl).into(myHolder.image);//加载图片
        }
        myHolder.addSub.setCount(goods.getNum());
        myHolder.addSub.setAddSubListener(new AddSubLayout.AddSubListener() {
            @Override
            public void addSub(int count) {
                goods.setNum(count);
                //计算价格
                onNumListener.onNum();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public void clearList() {
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
