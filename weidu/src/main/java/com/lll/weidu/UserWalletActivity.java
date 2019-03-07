package com.lll.weidu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.TextView;

import com.lll.weidu.adapter.UserWalletAdapter;
import com.lll.weidu.bean.Result;
import com.lll.weidu.bean.User;
import com.lll.weidu.bean.UserWallet;
import com.lll.weidu.bean.WalletBean;
import com.lll.weidu.core.DataCall;
import com.lll.weidu.core.exception.ApiException;
import com.lll.weidu.presenter.WalletPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserWalletActivity extends AppCompatActivity {

    @BindView(R.id.money_text)
    TextView moneyText;
    @BindView(R.id.mallet_recycle)
    RecyclerView malletRecycle;
    private UserWalletAdapter myWalletAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_wallet);
        ButterKnife.bind(this);
        DaoSession daoSession = DaoMaster.newDevSession(this, UserDao.TABLENAME);
        UserDao userDao = daoSession.getUserDao();
        List<User> list = userDao.loadAll();
        long userId = list.get(0).getUserId();
        String sessionId = list.get(0).getSessionId();
        new WalletPresenter(new MyCall()).reqeust((int)userId,sessionId,1,10);
        myWalletAdapter = new UserWalletAdapter();
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        malletRecycle.setLayoutManager(manager);
        malletRecycle.setAdapter(myWalletAdapter);

    }
    class MyCall implements DataCall<Result<UserWallet>> {

        @Override
        public void success(Result<UserWallet> result) {
            UserWallet result1 = result.getResult();
            double balance = result1.getBalance();
            moneyText.setText(balance+"");
            List<WalletBean> detailList = result1.getDetailList();
            myWalletAdapter.addList(detailList);
            myWalletAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException e) {

        }
    }
}
