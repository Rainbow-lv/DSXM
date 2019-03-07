package com.lll.weidu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lll.weidu.adapter.UserFootAdapter;
import com.lll.weidu.bean.Result;
import com.lll.weidu.bean.User;
import com.lll.weidu.bean.UserFootBean;
import com.lll.weidu.core.DataCall;
import com.lll.weidu.core.exception.ApiException;
import com.lll.weidu.presenter.UserFootPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserFootActivity extends AppCompatActivity implements XRecyclerView.LoadingListener {

    @BindView(R.id.foot_recycler)
    XRecyclerView footRecycler;
    private UserFootPresenter userFootPresenter;
    private DaoSession daoSession;
    private UserDao userDao;
    private List<User> users;
    private long userId;
    private String sessionId;
    private GridLayoutManager gridLayoutManager;
    private UserFootAdapter userFootAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_foot);
        ButterKnife.bind(this);
        //创建对象
        userFootPresenter = new UserFootPresenter(new FootCall());
        daoSession = DaoMaster.newDevSession(this, UserDao.TABLENAME);
        userDao = daoSession.getUserDao();
        users = userDao.loadAll();
        userId = users.get(0).getUserId();
        sessionId = users.get(0).getSessionId();
        //创建布局对象
        gridLayoutManager = new GridLayoutManager(this,2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        //自定义适配器
        userFootAdapter = new UserFootAdapter();
        footRecycler.setLoadingListener(this);
        footRecycler.refresh();
    }

    @Override
    public void onRefresh() {
        if (userFootPresenter.isRunning()){
            footRecycler.refreshComplete();
            return;
        }
        userFootPresenter.reqeust(true,userId,sessionId);
    }

    @Override
    public void onLoadMore() {
        if (userFootPresenter.isRunning()){
            footRecycler.loadMoreComplete();
            return;
        }
        userFootPresenter.reqeust(false,userId,sessionId);
    }

    private class FootCall implements DataCall<Result<List<UserFootBean>>> {
        @Override
        public void success(Result<List<UserFootBean>> result) {
            footRecycler.refreshComplete();
            footRecycler.loadMoreComplete();
            List<UserFootBean> result1 = result.getResult();
            footRecycler.setLayoutManager(gridLayoutManager);
            //添加列表
            userFootAdapter.AddAll(result1);
            footRecycler.setAdapter(userFootAdapter);
            //刷新适配器
            userFootAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException e) {
            footRecycler.refreshComplete();
            footRecycler.loadMoreComplete();
        }
    }
}
