package com.lll.weidu;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lll.weidu.adapter.MyTJAdapter;
import com.lll.weidu.adapter.PopuAddressAdapter;
import com.lll.weidu.bean.AddressBean;
import com.lll.weidu.bean.Result;
import com.lll.weidu.bean.ShopCar;
import com.lll.weidu.bean.User;
import com.lll.weidu.bean.XQBean;
import com.lll.weidu.core.DataCall;
import com.lll.weidu.core.exception.ApiException;
import com.lll.weidu.presenter.AddressPreseter;
import com.lll.weidu.presenter.CreateOrederPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AccountActivity extends AppCompatActivity {

    @BindView(R.id.ti_image)
    ImageView tiImage;
    @BindView(R.id.ti_recy)
    RecyclerView tiRecy;
    @BindView(R.id.shopping_num)
    TextView shoppingNum;
    @BindView(R.id.shopping_sum)
    TextView shoppingSum;
    @BindView(R.id.address_text_name)
    TextView addressTextName;
    @BindView(R.id.address_text_phone)
    TextView addressTextPhone;
    @BindView(R.id.address_text_address)
    TextView addressTextAddress;
    @BindView(R.id.tijiao)
    Button tijiao;
    private MyTJAdapter myTJAdapter;
    private View view;
    private RecyclerView recyclerView;
    private PopuAddressAdapter popuAddressAdapter;
    private PopupWindow popupWindow;
    private List<ShopCar> list;
    private long userId;
    private String sessionId;
    private double sum;
    private int id2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        ButterKnife.bind(this);
        DaoSession daoSession = DaoMaster.newDevSession(this, ShopCarDao.TABLENAME);
        ShopCarDao shoppingBeanDao = daoSession.getShopCarDao();
        list = shoppingBeanDao.loadAll();
        getSum();
        myTJAdapter = new MyTJAdapter();
        myTJAdapter.setCountListener(new MyTJAdapter.CountListener() {
            @Override
            public void getCount(int count) {
                getSum();
            }
        });
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        myTJAdapter.addList(list);
        tiRecy.setLayoutManager(manager);
        tiRecy.setAdapter(myTJAdapter);
        DaoSession daoSession1 = DaoMaster.newDevSession(this, UserDao.TABLENAME);
        UserDao userDao = daoSession1.getUserDao();
        List<User> list1 = userDao.loadAll();
        userId = list1.get(0).getUserId();
        sessionId = list1.get(0).getSessionId();
        view = View.inflate(this, R.layout.popu_address_layout, null);
        new AddressPreseter(new My()).reqeust((int) userId, sessionId);
        recyclerView = view.findViewById(R.id.address_recycle_show);
        popuAddressAdapter = new PopuAddressAdapter();
        StaggeredGridLayoutManager manager1 = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager1);
        recyclerView.setAdapter(popuAddressAdapter);
        popuAddressAdapter.setOnCheckListener(new PopuAddressAdapter.CheckListener() {
            @Override
            public void check(AddressBean address) {
                String address1 = address.getAddress();
                String phone = address.getPhone();
                String realName = address.getRealName();
                id2 = address.getId();
                addressTextName.setText(realName);
                addressTextPhone.setText(phone);
                addressTextAddress.setText(address1);
                popupWindow.dismiss();
            }
        });
    }

    @OnClick(R.id.ti_image)
    public void onViewClicked() {
        popupWindow = new PopupWindow(view, 800, 200,
                true);
//        int height = getWindowManager().getDefaultDisplay().getHeight();
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x000000));
        popupWindow.showAsDropDown(tiImage);

    }

    @OnClick(R.id.tijiao)
    public void onViewClicked(View view) {
       // startActivity(new Intent(AccountActivity.this,PayActivity.class));
        List<XQBean> list2 = new ArrayList<>();
        for (int i = 0; i <list.size() ; i++) {
            XQBean orderInfo = new XQBean();
            orderInfo.setCount(list.get(i).getCount());
            orderInfo.setCommodityId(list.get(i).getCommodityId());
            list2.add(orderInfo);
        }
        Gson gson = new Gson();
        String s = gson.toJson(list2);

       // new CreateOrederPresenter(new MyJs()).reqeust((int)userId,sessionId,s,sum+"",599);
    }
    class MyJs implements DataCall<Result> {

        @Override
        public void success(Result result) {
            if(result.getStatus().equals("0000")){
                Toast.makeText(AccountActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void fail(ApiException e) {

        }
    }
    class My implements DataCall<Result<List<AddressBean>>> {


        @Override
        public void success(Result<List<AddressBean>> result) {
            if (result.getStatus().equals("0000")) {
                List<AddressBean> result1 = result.getResult();
                popuAddressAdapter.addList(result1);
                popuAddressAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void fail(ApiException e) {

        }
    }

    public void getSum() {
        sum = 0.0;
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i).getCount() * list.get(i).getPrice();
            count += list.get(i).getCount();
        }
        shoppingSum.setText(sum + "");
        shoppingNum.setText(count + "");
    }
}
