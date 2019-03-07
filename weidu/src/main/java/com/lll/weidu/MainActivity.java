package com.lll.weidu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.lll.weidu.app.MyApp;
import com.lll.weidu.bean.Result;
import com.lll.weidu.bean.User;
import com.lll.weidu.core.DataCall;
import com.lll.weidu.core.exception.ApiException;
import com.lll.weidu.presenter.LoginPresenter;
import com.lll.weidu.untils.UIUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.img_phone)
    ImageView imgPhone;
    @BindView(R.id.edit_phone)
    EditText editPhone;
    @BindView(R.id.img_pwd)
    ImageView imgPwd;
    @BindView(R.id.edit_pwd)
    EditText editPwd;
    @BindView(R.id.login_eye)
    ImageView loginEye;
    @BindView(R.id.ch_box)
    CheckBox chBox;
    @BindView(R.id.login_soonreg)
    Button loginSoonreg;
    @BindView(R.id.btn_login)
    Button btnLogin;
    private LoginPresenter loginPresenter;
    private DaoSession daoSession;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        loginPresenter = new LoginPresenter(new LoginCall());
        boolean remPas = MyApp.getShare().getBoolean("remPas",true);
        if (remPas){
            chBox.setChecked(true);
            editPhone.setText(MyApp.getShare().getString("mobile",""));
            editPwd.setText(MyApp.getShare().getString("pas",""));
        }
        daoSession = DaoMaster.newDevSession(this, UserDao.TABLENAME);
        userDao = daoSession.getUserDao();
    }

    @OnClick({R.id.login_eye, R.id.ch_box, R.id.login_soonreg, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_eye:
                //显示隐藏密码
                if (editPwd.getInputType() == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)) {
                    editPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

                } else {
                    editPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

                }
                break;
            case R.id.ch_box:
                //记住密码
                MyApp.getShare().edit()
                        .putBoolean("remPas",chBox.isChecked()).commit();
                break;
            case R.id.login_soonreg:
                //快速注册
                startActivity(new Intent(MainActivity.this, RegActivity.class));
                break;
            case R.id.btn_login:
                //登录
                String phone = editPhone.getText().toString();
                String pwd = editPwd.getText().toString();
                if (TextUtils.isEmpty(phone)){
                    Toast.makeText(this,"请输入正确的手机号",Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(pwd)){
                    Toast.makeText(this,"请输入密码",Toast.LENGTH_LONG).show();
                    return;
                }
                if (chBox.isChecked()){
                    MyApp.getShare().edit().putString("mobile",phone)
                            .putString("pas",pwd).commit();
                }
                loginPresenter.reqeust(phone,pwd);
                break;
        }
    }

    private class LoginCall implements DataCall<Result<User>> {
        @Override
        public void success(Result<User> result) {
            if (result.getStatus().equals("0000")){
                boolean checked = chBox.isChecked();
                if (checked){
                    String phone = editPhone.getText().toString();
                    String pwd = editPwd.getText().toString();
                    result.getResult().setStatus(1);
                    UserDao userInfoDao = DaoMaster.newDevSession(getBaseContext(), UserDao.TABLENAME).getUserDao();
                    userInfoDao.insertOrReplace(result.getResult());//保存用户数据
                }else {
                    userDao.deleteAll();
                }
                Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }else {
                UIUtils.showToastSafe(result.getStatus()+"  "+result.getMessage());
            }
        }

        @Override
        public void fail(ApiException e) {
            UIUtils.showToastSafe(e.getCode()+"  "+e.getDisplayMessage());
        }
    }
}
