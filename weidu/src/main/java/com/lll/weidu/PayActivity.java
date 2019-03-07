package com.lll.weidu;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.lll.weidu.bean.Result;
import com.lll.weidu.bean.User;
import com.lll.weidu.core.DataCall;
import com.lll.weidu.core.exception.ApiException;
import com.lll.weidu.fragment.Frag_04;
import com.lll.weidu.presenter.PayPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PayActivity extends AppCompatActivity {

    @BindView(R.id.pay_relative)
    RelativeLayout payRelative;
    @BindView(R.id.pay_image1)
    ImageView payImage1;
    @BindView(R.id.pay_my)
    RadioButton payMy;
    @BindView(R.id.pay_image2)
    ImageView payImage2;
    @BindView(R.id.pay_wx)
    RadioButton payWx;
    @BindView(R.id.pay_image3)
    ImageView payImage3;
    @BindView(R.id.pay_wzb)
    RadioButton payWzb;
    @BindView(R.id.pay_but_ok)
    Button payButOk;
    private int way = 1;
    private PayPresenter payPresenter;
    private String orderId;
    private User userBean;
    private View parent;
    private View inflate;
    private PopupWindow window_ok;
    private PopupWindow window_back;
    private View inflate_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        ButterKnife.bind(this);
        //获取Intent的传值
        Intent intent = getIntent();
        orderId = intent.getStringExtra("ShopID");
        double sum = intent.getDoubleExtra("sum", 0);
        DaoSession daoSession = DaoMaster.newDevSession(this, UserDao.TABLENAME);
        UserDao userDao = daoSession.getUserDao();
        List<User> users = userDao.loadAll();
        userBean = users.get(0);
        payButOk.setText("余额支付"+sum+"元");
        //创建对象
        payPresenter = new PayPresenter(new PayCall());
        parent = View.inflate(this, R.layout.activity_pay, null);
        inflate = View.inflate(this, R.layout.popu_pay_layout, null);
        window_ok = new PopupWindow(inflate,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        window_ok.setFocusable(true);
        window_ok.setTouchable(true);
        window_ok.setOutsideTouchable(true);
        window_ok.setBackgroundDrawable(new BitmapDrawable());
        setWindow_ok();
        inflate_back = View.inflate(this, R.layout.popu_pay_back_layout, null);
        window_back = new PopupWindow(inflate_back,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        window_back.setFocusable(true);
        window_back.setTouchable(true);
        window_back.setOutsideTouchable(true);
        window_back.setBackgroundDrawable(new BitmapDrawable());
        getWindow_back();
    }

    //返回 支付失败
    private void getWindow_back() {
        Button popu_pay_back_finish = inflate_back.findViewById(R.id.popu_pay_back_finish);
        popu_pay_back_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                window_back.dismiss();
            }
        });
    }

    //支付成功
    private void setWindow_ok() {
        Button popu_pay_ok_back= inflate.findViewById(R.id.popu_pay_ok_back);
        Button popu_pay_ok_finish= inflate.findViewById(R.id.popu_pay_ok_finish);
        popu_pay_ok_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                window_ok.dismiss();
                Intent intent = new Intent(PayActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
        popu_pay_ok_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                window_ok.dismiss();
                finish();
            }
        });
    }

    @OnClick({R.id.pay_my, R.id.pay_wx, R.id.pay_wzb, R.id.pay_but_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.pay_my:
                payWx.setChecked(false);
                payWzb.setChecked(false);
                way = 1;
                break;
            case R.id.pay_wx:
                payMy.setChecked(false);
                payWzb.setChecked(false);
                way = 2;
                break;
            case R.id.pay_wzb:
                payWx.setChecked(false);
                payMy.setChecked(false);
                way = 3;
                break;
            case R.id.pay_but_ok:
                if (way == 1){
                    payPresenter.reqeust(userBean.getUserId(),userBean.getSessionId(),orderId,way);
                }else if (way == 2){
                    window_back.showAtLocation(parent,Gravity.NO_GRAVITY,0,0);
                }else if (way == 3){
                    window_back.showAtLocation(parent,Gravity.NO_GRAVITY,0,0);
                }
                break;
        }
    }

    private class PayCall implements DataCall<Result> {
        @Override
        public void success(Result result) {
            if (result.getStatus().equals("0000")){
                window_ok.showAtLocation(parent,Gravity.NO_GRAVITY,0,0);
            }
            Toast.makeText(PayActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void fail(ApiException e) {

        }
    }
}
