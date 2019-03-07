package com.lll.weidu.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.lll.weidu.R;
import com.lll.weidu.bean.UserSelectOrder;

import java.util.ArrayList;
import java.util.List;

public class WaitAdapter extends RecyclerView.Adapter<WaitAdapter.MyHolder> {
    List<UserSelectOrder> list = new ArrayList();
    public void AddAll(List<UserSelectOrder> result1) {
        if (result1 != null){
            list.addAll(result1);
        }
    }
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.waite_item, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        final UserSelectOrder userSelectOrder = list.get(i);
        //外部参数
        myHolder.mTV_dingdanhao.setText(userSelectOrder.getOrderId());
        myHolder.tv_gongsi.setText(userSelectOrder.getExpressCompName());
        myHolder.tv_kuaidi.setText(userSelectOrder.getExpressSn());
        //内部参数
        myHolder.recycleview.setLayoutManager(new LinearLayoutManager(myHolder.itemView.getContext()));
        List<UserSelectOrder.DetailListBean> detailList = userSelectOrder.getDetailList();
        //自定义适配器
        WaitContentAdapter orderContentAdapter = new WaitContentAdapter();
        orderContentAdapter.AddAll(detailList);
        myHolder.recycleview.setAdapter(orderContentAdapter);
        //点击确认收货
        myHolder.mBt_recive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thsGoodsButton.successful(userSelectOrder.getOrderId());
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

        private final TextView mTV_dingdanhao;
        private final RecyclerView recycleview;
        private final TextView tv_gongsi;
        private final TextView tv_kuaidi;
        private final Button mBt_recive;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            mTV_dingdanhao = itemView.findViewById(R.id.mTV_dingdanhao);
            recycleview = itemView.findViewById(R.id.mrecycler_inside);
            tv_gongsi = itemView.findViewById(R.id.tv_gongsi);
            tv_kuaidi = itemView.findViewById(R.id.tv_kuaidi);
            mBt_recive = itemView.findViewById(R.id.mBt_recive);
        }
    }
    //适配器设置监听
    public interface ThsGoodsButton{
        void successful(String orderId);
    }
    private ThsGoodsButton thsGoodsButton;

    public ThsGoodsButton getThsGoodsButton() {
        return thsGoodsButton;
    }

    public void setThsGoodsButton(ThsGoodsButton thsGoodsButton) {
        this.thsGoodsButton = thsGoodsButton;
    }
}
