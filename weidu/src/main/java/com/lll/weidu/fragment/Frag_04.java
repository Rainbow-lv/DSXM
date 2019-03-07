package com.lll.weidu.fragment;

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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lll.weidu.DaoMaster;
import com.lll.weidu.DaoSession;
import com.lll.weidu.R;
import com.lll.weidu.UserDao;
import com.lll.weidu.adapter.FinAdapter;
import com.lll.weidu.adapter.FinishAdapter;
import com.lll.weidu.adapter.OrderAdapter;
import com.lll.weidu.adapter.ReCommAdapter;
import com.lll.weidu.adapter.WaitAdapter;
import com.lll.weidu.bean.Result;
import com.lll.weidu.bean.User;
import com.lll.weidu.bean.UserSelectOrder;
import com.lll.weidu.core.DataCall;
import com.lll.weidu.core.exception.ApiException;
import com.lll.weidu.presenter.AllOrderPresenter;
import com.lll.weidu.presenter.DeleteOrderPresenter;
import com.lll.weidu.presenter.RecivePresenter;
import com.lll.weidu.presenter.WaitOrderPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class Frag_04 extends Fragment {
    @BindView(R.id.radio_all)
    RadioButton radioAll;
    @BindView(R.id.radio_pay)
    RadioButton radioPay;
    @BindView(R.id.radio_receive)
    RadioButton radioReceive;
    @BindView(R.id.radio_comm)
    RadioButton radioComm;
    @BindView(R.id.radio_finish)
    RadioButton radioFinish;
    @BindView(R.id.radioGroup01)
    RadioGroup radioGroup01;
    @BindView(R.id.recylayout)
    RecyclerView recylayout;
    Unbinder unbinder;
    private OrderAdapter orderAdapter;
    private long userId;
    private String sessionId;
    private LinearLayoutManager linearLayoutManager;
    private AllOrderPresenter allOrderPresenter;
    private WaitAdapter waitAdapter;
    private ReCommAdapter reCommAdapter;
    private FinishAdapter finishAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_04, container, false);
        unbinder = ButterKnife.bind(this, view);
        DaoSession daoSession = DaoMaster.newDevSession(getContext(), UserDao.TABLENAME);
        UserDao userDao = daoSession.getUserDao();
        List<User> list = userDao.loadAll();
        userId = list.get(0).getUserId();
        sessionId = list.get(0).getSessionId();

        //创建对象
        allOrderPresenter = new AllOrderPresenter(new OrderCall());

        //自定义适配器
        orderAdapter = new OrderAdapter();


        // recylayout.setAdapter(waitAdapter);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.radio_all, R.id.radio_pay, R.id.radio_receive, R.id.radio_comm, R.id.radio_finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.radio_all:
                //全部订单
                //设置布局
                linearLayoutManager = new LinearLayoutManager(getContext());
                recylayout.setLayoutManager(linearLayoutManager);
                allOrderPresenter.reqeust(userId, sessionId, 0, 1, 5);
                recylayout.setAdapter(orderAdapter);
                orderAdapter.delete();
                orderAdapter.notifyDataSetChanged();
//                Toast.makeText(getContext(), "111", Toast.LENGTH_SHORT).show();
                break;
            case R.id.radio_pay:
                //代付款
                linearLayoutManager = new LinearLayoutManager(getContext());
                recylayout.setLayoutManager(linearLayoutManager);
                allOrderPresenter.reqeust(userId, sessionId, 1, 1, 5);
                recylayout.setAdapter(orderAdapter);
                orderAdapter.delete();
                orderAdapter.notifyDataSetChanged();
                break;
            case R.id.radio_receive:
                //待收货
                allOrderPresenter.reqeust(userId, sessionId, 2, 1, 5);
                waitAdapter = new WaitAdapter();
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                recylayout.setLayoutManager(linearLayoutManager);

                recylayout.setAdapter(waitAdapter);
                waitAdapter.delete();
                waitAdapter.notifyDataSetChanged();
                //接口回调  确认收货
                waitAdapter.setThsGoodsButton(new WaitAdapter.ThsGoodsButton() {
                    @Override
                    public void successful(String orderId) {
                        //创建对象
                        RecivePresenter recivePresenter = new RecivePresenter(new ReciveCall());
                        recivePresenter.reqeust(userId, sessionId, orderId);
                    }
                });
                break;
            case R.id.radio_comm:
                //待评价
                allOrderPresenter.reqeust(userId, sessionId, 3, 1, 5);
                //自定义适配器
                reCommAdapter = new ReCommAdapter();
                LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext());
                recylayout.setLayoutManager(linearLayoutManager1);
                recylayout.setAdapter(reCommAdapter);
                reCommAdapter.delete();
                reCommAdapter.notifyDataSetChanged();
                reCommAdapter.setDeleteOrder(new ReCommAdapter.DeleteOrder() {
                    @Override
                    public void success(String order) {
                        //创建对象
                        DeleteOrderPresenter deleteOrderPresenter = new DeleteOrderPresenter(new DeleteOrderCall());
                        deleteOrderPresenter.reqeust(userId,sessionId,order);
                    }
                });
                //Toast.makeText(getContext(), "待评价", Toast.LENGTH_SHORT).show();
                break;
            case R.id.radio_finish:
                //已完成
                allOrderPresenter.reqeust(userId, sessionId, 9, 1, 5);
                //自定义适配器
                finishAdapter = new FinishAdapter();
                LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext());
                recylayout.setLayoutManager(linearLayoutManager2);
                recylayout.setAdapter(finishAdapter);
                finishAdapter.delete();
                finishAdapter.notifyDataSetChanged();
                finishAdapter.setFinishDeleteOrder(new FinishAdapter.FinishDeleteOrder() {
                    @Override
                    public void success(String order) {
                        //创建对象
                        DeleteOrderPresenter deleteOrderPresenter = new DeleteOrderPresenter(new DeleteOrderCall());
                        deleteOrderPresenter.reqeust(userId,sessionId,order);
                    }
                });
                //  Toast.makeText(getContext(), "已完成", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    //全部订单、带付款
    class OrderCall implements DataCall<Result<List<UserSelectOrder>>> {
        @Override
        public void success(Result<List<UserSelectOrder>> result) {
            List<UserSelectOrder> result1 = result.getOrderList();
            orderAdapter.addAll(result1);
            waitAdapter.AddAll(result1);
            reCommAdapter.AddAll(result1);
            finishAdapter.AddAll(result1);
            // Toast.makeText(getActivity(), "iiiiiii"+result1.size(), Toast.LENGTH_SHORT).show();
            orderAdapter.notifyDataSetChanged();
            waitAdapter.notifyDataSetChanged();
            reCommAdapter.notifyDataSetChanged();
            finishAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException e) {

        }
    }

    //待收货
    private class ReciveCall implements DataCall<Result> {
        @Override
        public void success(Result result) {
            Toast.makeText(getContext(), "" + result.getMessage(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void fail(ApiException e) {

        }
    }

    //删除订单
    private class DeleteOrderCall implements DataCall<Result> {
        @Override
        public void success(Result result) {
            Toast.makeText(getContext(), ""+result.getMessage(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void fail(ApiException e) {

        }
    }
}
