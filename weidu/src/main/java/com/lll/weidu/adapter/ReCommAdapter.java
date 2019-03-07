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

import java.util.ArrayList;
import java.util.List;

public class ReCommAdapter extends RecyclerView.Adapter<ReCommAdapter.MyHolder> {
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
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recomm_item, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder myHolder, int i) {
        final UserSelectOrder userSelectOrder = list.get(i);
        //外部参数
        myHolder.myOrderStateCode.setText(userSelectOrder.getOrderId());
        myHolder.myOrderStateTime.setText(userSelectOrder.getUserId()+"");
        //内部参数
        myHolder.myOrderStateRecyc.setLayoutManager(new LinearLayoutManager(myHolder.itemView.getContext()));
        List<UserSelectOrder.DetailListBean> detailList = list.get(i).getDetailList();
        //自定义内部适配器
        ReCommContentAdapter reCommContentAdapter = new ReCommContentAdapter(list.get(i).getOrderId());
        reCommContentAdapter.AddAll(detailList);
        myHolder.myOrderStateRecyc.setAdapter(reCommContentAdapter);
        //点击小圆点弹出popupWindow
        myHolder.mOrderPoP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = View.inflate(myHolder.itemView.getContext(), R.layout.pop_item, null);
                mPoPDelete = view.findViewById(R.id.mPoPDelete);
                PopupWindow popupWindow = new PopupWindow(myHolder.itemView.getContext());
                popupWindow.setContentView(view);
                popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);

                popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.setOutsideTouchable(true);
                popupWindow.showAsDropDown(myHolder.mOrderPoP);
                //给删除按钮设置监听
                mPoPDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deleteOrder.success(userSelectOrder.getOrderId());
                    }
                });
            }
        });
    }



    // 接口回调
    public interface DeleteOrder{
        void success(String order);
    }

    private DeleteOrder deleteOrder;

    public DeleteOrder getDeleteOrder() {
        return deleteOrder;
    }

    public void setDeleteOrder(DeleteOrder deleteOrder) {
        this.deleteOrder = deleteOrder;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void delete(){
        list.clear();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private final TextView myOrderStateCode;
        private final RecyclerView myOrderStateRecyc;
        private final TextView myOrderStateTime;
        private final ImageView mOrderPoP;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            myOrderStateCode = itemView.findViewById(R.id.myOrderStateCode);
            myOrderStateRecyc = itemView.findViewById(R.id.myOrderStateRecyc);
            myOrderStateTime = itemView.findViewById(R.id.myOrderStateTime);
            mOrderPoP = itemView.findViewById(R.id.mOrderPoP);
        }
    }
}
