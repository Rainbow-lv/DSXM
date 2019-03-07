package com.lll.weidu.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.lll.weidu.R;
import com.lll.weidu.bean.AddressBean;

import java.util.ArrayList;
import java.util.List;

public class UserAddressAdapter extends RecyclerView.Adapter<UserAddressAdapter.MyHolder> {
    List<AddressBean> list = new ArrayList<>();

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_address, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, final int i) {
        myHolder.a_name.setText(list.get(i).getRealName());
        myHolder.a_phone.setText(list.get(i).getPhone());
        myHolder.a_address.setText(list.get(i).getAddress());
        myHolder.radio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = list.get(i).getId();
                onItemclick.onItem(id);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void AddAll(List<AddressBean> result1) {
        if (result1 != null){
            list.addAll(result1);
        }
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private final TextView a_name;
        private final TextView a_phone;
        private final TextView a_address;
        private final Button btn_update;
        private final Button btn_delete;
        private final RadioButton radio;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            a_name = itemView.findViewById(R.id.address_name);
            a_phone = itemView.findViewById(R.id.address_phone);
            a_address = itemView.findViewById(R.id.address_address);
            btn_update = itemView.findViewById(R.id.address_update);
            btn_delete = itemView.findViewById(R.id.address_delete);
            radio = itemView.findViewById(R.id.address_radio);
        }
    }
    private OnItemclick onItemclick;
    public interface OnItemclick{
        void onItem(int position);
    }
    public void setOnItemclicks(OnItemclick onItemclick){
        this.onItemclick = onItemclick;
    }
}
