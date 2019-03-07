package com.lll.weidu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lll.weidu.DaoMaster;
import com.lll.weidu.DaoSession;
import com.lll.weidu.MainActivity;
import com.lll.weidu.R;
import com.lll.weidu.UserAddressActivity;
import com.lll.weidu.UserCircleActivity;
import com.lll.weidu.UserDao;
import com.lll.weidu.UserFootActivity;
import com.lll.weidu.UserPersonActivity;
import com.lll.weidu.UserWalletActivity;
import com.lll.weidu.bean.Result;
import com.lll.weidu.bean.User;
import com.lll.weidu.bean.UserPresonBean;
import com.lll.weidu.core.DataCall;
import com.lll.weidu.core.WDFragment;
import com.lll.weidu.core.exception.ApiException;
import com.lll.weidu.presenter.UserPersonPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class Frag_05 extends WDFragment {
    @BindView(R.id.person_name)
    TextView personName;
    @BindView(R.id.imageinfor)
    ImageView imageinfor;
    @BindView(R.id.user_person)
    TextView userPerson;
    @BindView(R.id.imagecircle)
    ImageView imagecircle;
    @BindView(R.id.user_circle)
    TextView userCircle;
    @BindView(R.id.imagefoot)
    ImageView imagefoot;
    @BindView(R.id.user_foot)
    TextView userFoot;
    @BindView(R.id.walletimage)
    ImageView walletimage;
    @BindView(R.id.user_wallet)
    TextView userWallet;
    @BindView(R.id.addressimage)
    ImageView addressimage;
    @BindView(R.id.user_address)
    TextView userAddress;
    @BindView(R.id.logout_btn)
    Button logoutBtn;
    @BindView(R.id.head_image)
    SimpleDraweeView headImage;
    Unbinder unbinder;
    private UserPersonPresenter userPersonPresenter;
    private long userId;
    private String sessionId;

//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.frag_05, container, false);
//        unbinder = ButterKnife.bind(this, view);
//        DaoSession daoSession = DaoMaster.newDevSession(getContext(), UserDao.TABLENAME);
//        UserDao userDao = daoSession.getUserDao();
//        List<User> users = userDao.loadAll();
//        userId = users.get(0).getUserId();
//        sessionId = users.get(0).getSessionId();
//        userPersonPresenter = new UserPersonPresenter(new PersonCall());
//        userPersonPresenter.reqeust(userId, sessionId);
//        return view;
//    }

//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        unbinder.unbind();
//    }

    @Override
    public String getPageName() {
        return "退出登录";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frag_05;
    }

    @Override
    protected void initView() {
        DaoSession daoSession = DaoMaster.newDevSession(getContext(), UserDao.TABLENAME);
        UserDao userDao = daoSession.getUserDao();
        List<User> users = userDao.loadAll();
        userId = users.get(0).getUserId();
        sessionId = users.get(0).getSessionId();
        userPersonPresenter = new UserPersonPresenter(new PersonCall());
        userPersonPresenter.reqeust(userId, sessionId);
    }

    @OnClick({R.id.user_person, R.id.user_circle, R.id.user_foot, R.id.user_wallet, R.id.user_address, R.id.logout_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.user_person:
                startActivity(new Intent(getContext(),UserPersonActivity.class));
                break;
            case R.id.user_circle:
                startActivity(new Intent(getContext(),UserCircleActivity.class));
                break;
            case R.id.user_foot:
                startActivity(new Intent(getContext(),UserFootActivity.class));
                break;
            case R.id.user_wallet:
                startActivity(new Intent(getContext(),UserWalletActivity.class));
                break;
            case R.id.user_address:
                startActivity(new Intent(getContext(),UserAddressActivity.class));
                break;
            case R.id.logout_btn:
                UserDao userInfoDao = DaoMaster.newDevSession(getActivity(), UserDao.TABLENAME).getUserDao();
                userInfoDao.delete(LOGIN_USER);//Intent清除栈FLAG_ACTIVITY_CLEAR_TASK会把当前栈内所有Activity清空；
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);//重点
                startActivity(intent);//重点
                break;
        }
    }

    private class PersonCall implements DataCall<Result<UserPresonBean>> {
        @Override
        public void success(Result<UserPresonBean> result) {
            UserPresonBean result1 = result.getResult();
            personName.setText(result1.getNickName());
            headImage.setImageURI(result1.getHeadPic());
        }

        @Override
        public void fail(ApiException e) {

        }
    }
}
