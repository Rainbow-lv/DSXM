package com.lll.weidu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.lll.weidu.bean.Result;
import com.lll.weidu.core.DataCall;
import com.lll.weidu.core.exception.ApiException;
import com.lll.weidu.presenter.RegPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegActivity extends AppCompatActivity {

    @BindView(R.id.edit_reg_phone)
    EditText editregphone;
    @BindView(R.id.edit_reg_yz)
    EditText editregyz;
    @BindView(R.id.edit_reg_pwd)
    EditText editregpwd;
    @BindView(R.id.btn_getsafe)
    Button btnGetsafe;
    @BindView(R.id.img_reg_eye)
    ImageView imgregEye;
    @BindView(R.id.reg_login)
    Button btnLoginReg;
    @BindView(R.id.btn_zc)
    Button btnzc;
    private RegPresenter regPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        ButterKnife.bind(this);
        //创建对象
        regPresenter = new RegPresenter(new RegCall());

    }

    @OnClick({R.id.btn_getsafe,R.id.img_reg_eye,R.id.reg_login,R.id.btn_zc})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_getsafe:
                editregyz.setText("12345");
                break;
            case R.id.img_reg_eye:
                if(editregpwd.getInputType() == (InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)) {
                    editregpwd.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);

                }
                else {
                    editregpwd.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

                }
                break;
            case R.id.reg_login:
                finish();
                break;
            case R.id.btn_zc:
                String regphone = editregphone.getText().toString();
                String regpwd = editregpwd.getText().toString();
                regPresenter.reqeust(regphone,regpwd);
                break;
        }
    }

    private class RegCall implements DataCall<Result> {
        @Override
        public void success(Result result) {
            Toast.makeText(RegActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void fail(ApiException e) {
            Toast.makeText(RegActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
}
