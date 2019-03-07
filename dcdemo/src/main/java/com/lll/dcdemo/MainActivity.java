package com.lll.dcdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lll.dcdemo.adapter.LeftAdapter;
import com.lll.dcdemo.adapter.RightAdapter;
import com.lll.dcdemo.bean.Goods;
import com.lll.dcdemo.bean.Result;
import com.lll.dcdemo.core.DataCall;
import com.lll.dcdemo.presenter.ShopPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.left_recy)
    RecyclerView leftRecy;
    @BindView(R.id.right_recy)
    RecyclerView rightRecy;
    @BindView(R.id.shop_car)
    ImageView shopCar;
    @BindView(R.id.goods_price)
    TextView goodsPrice;
    @BindView(R.id.btn_js)
    Button btnJs;
    private ShopPresenter shopPresenter;
    private LeftAdapter leftAdapter;
    private RightAdapter rightAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //创建对象
        shopPresenter = new ShopPresenter(new ShopCall());

        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        leftRecy.setLayoutManager(new LinearLayoutManager(this));
        leftAdapter = new LeftAdapter();
        leftRecy.setAdapter(leftAdapter);

        rightRecy.setLayoutManager(new LinearLayoutManager(this));
        rightAdapter = new RightAdapter();
        rightRecy.setAdapter(rightAdapter);

        shopPresenter.reqeust();
    }

    private class ShopCall implements DataCall<Result<List<Goods>>> {
        @Override
        public void success(Result<List<Goods>> data) {
            List<Goods> data1 = data.getData();
            leftAdapter.AddAll(data1);
            leftAdapter.notifyDataSetChanged();
            leftAdapter.setShop(new LeftAdapter.Shop() {
                @Override
                public void success(List<Goods.ListBean> mlist) {
                    rightAdapter.clear();
                    rightAdapter.AddAll(mlist);
                    rightAdapter.notifyDataSetChanged();
                }
            });
//            for (int i = 0; i < data1.size(); i++) {
//                List<Goods.ListBean> list = data1.get(i).getList();
//                rightAdapter.AddAll(list);
//                rightAdapter.notifyDataSetChanged();
//            }

        }

        @Override
        public void fail(Result result) {

        }
    }
}
