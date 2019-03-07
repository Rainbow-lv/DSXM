package com.lll.yuekao_moni.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.lll.yuekao_moni.R;
import com.lll.yuekao_moni.adapter.ShopAdapter;
import com.lll.yuekao_moni.bean.Result;
import com.lll.yuekao_moni.bean.ShopCar;
import com.lll.yuekao_moni.core.DataCall;
import com.lll.yuekao_moni.presenter.ShopPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class Frag_01 extends Fragment implements ShopAdapter.TotalPriceListener{


    @BindView(R.id.exlv)
    ExpandableListView exlv;
    @BindView(R.id.checkbox01)
    CheckBox checkbox01;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.btn_men)
    Button btnMen;
    Unbinder unbinder;
    private ShopAdapter shopAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_01, container, false);
        unbinder = ButterKnife.bind(this, view);
        checkbox01.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                shopAdapter.checkAll(isChecked);
            }
        });

        //自定义适配器
        shopAdapter = new ShopAdapter();
        //设置适配器
        exlv.setAdapter(shopAdapter);
        //创建对象
        ShopPresenter shopPresenter = new ShopPresenter(new ShopCall());
        shopPresenter.reqeust();
        shopAdapter.setTotalPriceListener(this);//设置总价回调器
        //去掉小箭头
        exlv.setGroupIndicator(null);
        exlv.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.checkbox01)
    public void onViewClicked() {
    }

    @Override
    public void totalPrice(double totalPrice) {
        tvNum.setText(String.valueOf(totalPrice));
    }

    private class ShopCall implements DataCall<Result> {


        @Override
        public void success(Result data) {
            List<ShopCar> data1 = (List<ShopCar>) data.getData();
            shopAdapter.AddAll(data1);
            int size = data1.size();
            for (int i = 0; i < size; i++) {
                exlv.expandGroup(i);
            }
            shopAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(Result result) {

        }
    }
}
