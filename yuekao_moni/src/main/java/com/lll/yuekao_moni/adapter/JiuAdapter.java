package com.lll.yuekao_moni.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lll.yuekao_moni.R;
import com.lll.yuekao_moni.bean.JiuBean;
import com.lll.yuekao_moni.fragment.Frag_02;

import java.util.ArrayList;
import java.util.List;

public class JiuAdapter extends RecyclerView.Adapter<JiuAdapter.MyHolder> {
    List<JiuBean> mlist = new ArrayList();
    public void AddAll(List<JiuBean> data1) {
        if (data1 != null){
            mlist.addAll(data1);
        }
    }
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.jiu_item, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        JiuBean jiuBean = mlist.get(i);
        myHolder.jiu_image.setImageURI(jiuBean.getIcon());
        myHolder.jiu_name.setText(jiuBean.getName());
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }



    public class MyHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView jiu_image;
        private final TextView jiu_name;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            jiu_image = itemView.findViewById(R.id.jiu_image);
            jiu_name = itemView.findViewById(R.id.jiu_name);
        }
    }
}
