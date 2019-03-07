package com.lll.yuekao_moni;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lll.yuekao_moni.adapter.LeftAdapter;
import com.lll.yuekao_moni.adapter.RightAdapter;
import com.lll.yuekao_moni.bean.Goods;
import com.lll.yuekao_moni.bean.Result;
import com.lll.yuekao_moni.bean.ShopCar;
import com.lll.yuekao_moni.core.DataCall;
import com.lll.yuekao_moni.presenter.GoodsListPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DcActivity extends AppCompatActivity {

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
    private GoodsListPresenter goodsListPresenter;
    private LeftAdapter leftAdapter;
    private RightAdapter rightAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dc);
        ButterKnife.bind(this);
        //创建对象
        goodsListPresenter = new GoodsListPresenter(new GoodsCall());
        goodsListPresenter.reqeust();
        //设置布局
        leftRecy.setLayoutManager(new LinearLayoutManager(this));
        rightRecy.setLayoutManager(new LinearLayoutManager(this));
        //自定义适配器
        leftAdapter = new LeftAdapter();
        rightAdapter = new RightAdapter();


    }

    private class GoodsCall implements DataCall<Result<List<ShopCar>>> {
        @Override
        public void success(Result<List<ShopCar>> data) {
            List<ShopCar> data1 = data.getData();
            leftAdapter.AddAll(data1);
            leftAdapter.notifyDataSetChanged();
            List<Goods> list = data1.get(1).getList();
            rightAdapter.AddAll(list);
            rightAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(Result result) {

        }
    }
}
