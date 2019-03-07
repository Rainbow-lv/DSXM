package com.lll.zk02_lx.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lll.zk02_lx.R;
import com.lll.zk02_lx.adapter.ShangAdapter;
import com.lll.zk02_lx.bean.GouBean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class Frag_car extends Fragment implements View.OnClickListener{


    @BindView(R.id.recycler_zong)
    RecyclerView recyclerZong;
    @BindView(R.id.checkbox_All)
    CheckBox checkboxAll;
    @BindView(R.id.count_Price)
    TextView countPrice;
    Unbinder unbinder;
    private InputStreamReader inputStreamReader;
    private GouBean gouBean;
    private List<GouBean.DataBean> goodList;
    private ShangAdapter shangAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_car, container, false);
        unbinder = ButterKnife.bind(this, view);
        initfile();
        checkboxAll.setOnCheckedChangeListener(null);
        checkboxAll.setOnClickListener(this);
        List<GouBean.DataBean> data = gouBean.getData();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerZong.setLayoutManager(linearLayoutManager);
        ShangAdapter shangAdapter = new ShangAdapter(R.layout.item_good,data);
        recyclerZong.setAdapter(shangAdapter);
        shangAdapter.notifyDataSetChanged();
        //gouData();

        return view;
    }

    private void gouData() {
//        checkboxAll.setOnCheckedChangeListener(null);
//        checkboxAll.setOnClickListener(this);
        goodList = gouBean.getData();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerZong.setLayoutManager(linearLayoutManager);
        shangAdapter = new ShangAdapter(R.layout.item_good, goodList);
        recyclerZong.setAdapter(shangAdapter);
        shangAdapter.notifyDataSetChanged();


        //全选反选
        shangAdapter.setOnGoodItemClickListenerClick(new ShangAdapter.OnGoodItemClickListenerClick() {
            @Override
            public void CallBack() {
                boolean result = true;
                //商家
                for (int i = 0; i < goodList.size(); i++) {
                    boolean goodListChecked = goodList.get(i).getGoodListChecked();
                    result = result & goodListChecked;
                    for (int j = 0; j < goodList.get(i).getList().size(); j++) {
                        boolean goodsListChecked = goodList.get(i).getList().get(j).getGoodsListChecked();
                        result = result & goodsListChecked;
                    }
                }
                checkboxAll.setChecked(result);
                //总价
                totalCount();
            }
        });
    }

    //总价
    private void totalCount() {
        double countData = 0;
        for (int i = 0; i < goodList.size(); i++) {
            for (int j = 0; j < goodList.get(i).getList().size(); j++) {
                if (goodList.get(i).getList().get(j).getGoodsListChecked() == true) {
                    double price = goodList.get(i).getList().get(j).getPrice();
                    int defalutNumber = goodList.get(i).getList().get(j).getDefalutNumber();
                    double count = price * defalutNumber;
                    countData = countData + count;
                }
            }
        }
        countPrice.setText("总价是：" + countData);
    }

    private void initfile() {
        try {
            inputStreamReader = new InputStreamReader(getResources().getAssets().open("cart.json.txt"), "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            StringBuilder builder = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line);
            }
            inputStreamReader.close();
            bufferedReader.close();
            String string = builder.toString();
            Log.i("hh", "" + string + "jjj");
            Gson gson = new Gson();
            gouBean = gson.fromJson(string, GouBean.class);
            Log.i("hh", "" + gouBean.getMsg());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        for (int i = 0; i <goodList.size(); i++) {
            goodList.get(i).setGoodListChecked(checkboxAll.isChecked());
            for (int j = 0; j <goodList.get(i).getList().size(); j++) {
                goodList.get(i).getList().get(j).setGoodsListChecked(checkboxAll.isChecked());
            }
        }
        shangAdapter.notifyDataSetChanged();
        totalCount();
    }
}
