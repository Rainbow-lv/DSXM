package com.lll.weidu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lll.weidu.adapter.MyCircleAdapter;
import com.lll.weidu.bean.Result;
import com.lll.weidu.bean.User;
import com.lll.weidu.bean.UserCircleBean;
import com.lll.weidu.core.DataCall;
import com.lll.weidu.core.exception.ApiException;
import com.lll.weidu.presenter.DeleteCirclePresenter;
import com.lll.weidu.presenter.MyCirclePresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.support.v7.widget.LinearLayoutManager.*;

public class UserCircleActivity extends AppCompatActivity implements XRecyclerView.LoadingListener,View.OnClickListener {

    @BindView(R.id.circle_delet)
    ImageView circleDelet;
    @BindView(R.id.mycir_xrecy)
    XRecyclerView mycirXrecy;
    private MyCirclePresenter myCirclePresenter;
    private LinearLayoutManager linearLayoutManager;
    private MyCircleAdapter myCircleAdapter;
    private DaoSession daoSession;
    private UserDao userDao;
    private long userId;
    private String sessionId;
    private DeleteCirclePresenter deleteCirclePresenter;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_circle);
        ButterKnife.bind(this);

        circleDelet.setOnClickListener(this);
        daoSession = DaoMaster.newDevSession(this, UserDao.TABLENAME);
        userDao = daoSession.getUserDao();
        List<User> users = userDao.loadAll();
        userId = users.get(0).getUserId();
        sessionId = users.get(0).getSessionId();

        //创建对象
        myCirclePresenter = new MyCirclePresenter(new CircleCall());

        //设置布局
        linearLayoutManager = new LinearLayoutManager(this);
        mycirXrecy.setLayoutManager(linearLayoutManager);
        //自定义适配器
        myCircleAdapter = new MyCircleAdapter();
        mycirXrecy.setAdapter(myCircleAdapter);
        mycirXrecy.setLoadingListener(this);

        mycirXrecy.refresh();
    }


    @Override
    public void onRefresh() {
        if (myCirclePresenter.isRunning()){
            mycirXrecy.refreshComplete();
            return;
        }
        myCirclePresenter.reqeust(true,userId,sessionId);
    }

    @Override
    public void onLoadMore() {
        if (myCirclePresenter.isRunning()){
            mycirXrecy.loadMoreComplete();
            return;
        }
        myCirclePresenter.reqeust(false,userId,sessionId);
    }

    @Override
    public void onClick(View v) {
        //创建对象
        deleteCirclePresenter = new DeleteCirclePresenter(new DeleteCircle());
        deleteCirclePresenter.reqeust(userId,sessionId,id);
      //  Toast.makeText(this, "aaa", Toast.LENGTH_SHORT).show();
    }

    private class CircleCall implements DataCall<Result<List<UserCircleBean>>> {
        @Override
        public void success(Result<List<UserCircleBean>> result) {
            mycirXrecy.refreshComplete();
            mycirXrecy.loadMoreComplete();
            if (result.getStatus().equals("0000")){
                List<UserCircleBean> result1 = result.getResult();
                id = result1.get(0).getId();
                //添加列表并刷新
                myCircleAdapter.addAll(result1);
                myCircleAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void fail(ApiException e) {
            mycirXrecy.refreshComplete();
            mycirXrecy.loadMoreComplete();
        }
    }

       class DeleteCircle implements DataCall<Result> {
        @Override
        public void success(Result result) {
            Toast.makeText(UserCircleActivity.this, ""+result.getMessage(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void fail(ApiException e) {

        }
    }
}
