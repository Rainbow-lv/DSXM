package com.lll.weidu.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.lll.weidu.PayActivity;
import com.lll.weidu.R;
import com.lll.weidu.bean.UserSelectOrder;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyHolder> {
    List<UserSelectOrder> list = new ArrayList();

    public void addAll(List<UserSelectOrder> result1) {
        if (result1 != null) {
            list.addAll(result1);
        }
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.order_item, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder myHolder, int i) {
        UserSelectOrder userSelectOrder = list.get(i);
        //给字段赋值
        myHolder.mTV_dingdanhao.setText(userSelectOrder.getOrderId());
        //  myHolder.mTV_jian_order.setText(userSelectOrder.getOrderStatus()+"");
        myHolder.recyclerView.setLayoutManager(new LinearLayoutManager(myHolder.itemView.getContext()));
        List<UserSelectOrder.DetailListBean> detailList = userSelectOrder.getDetailList();
        //自定义适配器
        OrderContentAdapter orderContentAdapter = new OrderContentAdapter();
        orderContentAdapter.AddAll(detailList);
        myHolder.recyclerView.setAdapter(orderContentAdapter);
        myHolder.mBt_zhifu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(myHolder.itemView.getContext(),PayActivity.class);
                String orderId = list.get(0).getOrderId();
                int payAmount = list.get(0).getPayAmount();
                intent.putExtra("ShopID",orderId);
                intent.putExtra("sum",payAmount);
                myHolder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void delete(){
        list.clear();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private final TextView mTV_yuan;
        RecyclerView recyclerView;
        private final Button mBt_zhifu;
        private final Button mBt_cancel;
        private final TextView mTV_dingdanhao;


        public MyHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.mrecycler_inside);
            mBt_zhifu = itemView.findViewById(R.id.mBt_zhifu);
            mBt_cancel = itemView.findViewById(R.id.mBt_cancel);
            mTV_dingdanhao = itemView.findViewById(R.id.mTV_dingdanhao);
            mTV_yuan = itemView.findViewById(R.id.mTV_yuan);
        }
    }
}
