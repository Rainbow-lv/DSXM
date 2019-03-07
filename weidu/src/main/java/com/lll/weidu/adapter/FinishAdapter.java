package com.lll.weidu.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.lll.weidu.R;
import com.lll.weidu.bean.UserSelectOrder;
import com.lll.weidu.untils.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FinishAdapter extends RecyclerView.Adapter<FinishAdapter.MyHolder> {
    List<UserSelectOrder> list = new ArrayList<>();
    public void AddAll(List<UserSelectOrder> result1) {
        if (result1 != null){
            list.addAll(result1);
        }
    }
    private Button mPoPDelete;


    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.finish_item, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder myHolder, int i) {
        final UserSelectOrder userSelectOrder = list.get(i);
        //外部参数
        myHolder.finish_orderId.setText(userSelectOrder.getOrderId());
        myHolder.finishTime.setText("" + userSelectOrder.getUserId());

        //内部参数
        myHolder.finishRecyc.setLayoutManager(new LinearLayoutManager(myHolder.itemView.getContext()));
        List<UserSelectOrder.DetailListBean> detailList = list.get(i).getDetailList();
        //自定义适配器
        FinishContentAdapter finishContentAdapter = new FinishContentAdapter();
        finishContentAdapter.AddAll(detailList);
        myHolder.finishRecyc.setAdapter(finishContentAdapter);
        //点击小圆点弹出popupwindow
        myHolder.finish_pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = View.inflate(myHolder.itemView.getContext(), R.layout.pop_item, null);
                mPoPDelete = view.findViewById(R.id.mPoPDelete);
                PopupWindow popupWindow = new PopupWindow(myHolder.itemView.getContext());
                popupWindow.setContentView(view);
                popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);

                popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.setOutsideTouchable(true);
                popupWindow.showAsDropDown(myHolder.finish_pop);
                //点击删除
                mPoPDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finishDeleteOrder.success(userSelectOrder.getOrderId());
                    }
                });
            }
        });
    }



    //接口回调
    // 接口回调
    public interface FinishDeleteOrder{
        void success(String order);
    }
    private FinishDeleteOrder finishDeleteOrder;

    public FinishDeleteOrder getFinishDeleteOrder() {
        return finishDeleteOrder;
    }

    public void setFinishDeleteOrder(FinishDeleteOrder finishDeleteOrder) {
        this.finishDeleteOrder = finishDeleteOrder;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void delete(){
        list.clear();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private final TextView finish_orderId;
        private final RecyclerView finishRecyc;
        private final TextView finishTime;
        private final ImageView finish_pop;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            finish_orderId = itemView.findViewById(R.id.finish_orderId);
            finishRecyc = itemView.findViewById(R.id.finishRecyc);
            finishTime = itemView.findViewById(R.id.finishTime);
            finish_pop = itemView.findViewById(R.id.finish_pop);
        }
    }
}
