package com.lll.weidu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lll.weidu.adapter.PopuAddressAdapter;
import com.lll.weidu.adapter.UserAddressAdapter;
import com.lll.weidu.bean.AddressBean;
import com.lll.weidu.bean.Result;
import com.lll.weidu.bean.User;
import com.lll.weidu.core.DataCall;
import com.lll.weidu.core.exception.ApiException;
import com.lll.weidu.presenter.AddressPreseter;
import com.lll.weidu.presenter.DefaultPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserAddressActivity extends AppCompatActivity {

    @BindView(R.id.address_finish)
    TextView addressFinish;
    @BindView(R.id.address_recy)
    RecyclerView addressRecy;
    @BindView(R.id.btn_address)
    Button btnAddress;
    private AddressPreseter addressPreseter;
    private long userId;
    private String sessionId;
    private UserAddressAdapter userAddressAdapter;
    private int a;
    private DefaultPresenter defaultPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_address);
        ButterKnife.bind(this);

        DaoSession daoSession = DaoMaster.newDevSession(this, UserDao.TABLENAME);
        UserDao userDao = daoSession.getUserDao();
        List<User> list = userDao.loadAll();
        userId = list.get(0).getUserId();
        sessionId = list.get(0).getSessionId();
        //创建对象
        addressPreseter = new AddressPreseter(new AddressCall());
        addressPreseter.reqeust((int)userId,sessionId);
        //自定义适配器
        userAddressAdapter = new UserAddressAdapter();
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        addressRecy.setLayoutManager(manager);
        addressRecy.setAdapter(userAddressAdapter);
        userAddressAdapter.setOnItemclicks(new UserAddressAdapter.OnItemclick() {
            @Override
            public void onItem(int position) {
                a = position;
            }
        });

    }

    @OnClick({R.id.address_finish, R.id.btn_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.address_finish:
                //完成
                defaultPresenter = new DefaultPresenter(new DefauCall());
                defaultPresenter.reqeust((int)userId,sessionId,a);
                finish();
                break;
            case R.id.btn_address:
                //新增收货地址
                startActivity(new Intent(this,NewAddressActivity.class));
                finish();
                break;
        }
    }

    private class AddressCall implements DataCall<Result<List<AddressBean>>> {
        @Override
        public void success(Result<List<AddressBean>> result) {
            List<AddressBean> result1 = result.getResult();
            userAddressAdapter.AddAll(result1);
            userAddressAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException e) {

        }
    }

    private class DefauCall implements DataCall<Result> {
        @Override
        public void success(Result result) {
            if(result.getStatus().equals("0000")){
                Toast.makeText(getBaseContext(), result.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void fail(ApiException e) {

        }
    }
}
