package com.lll.weidu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.lll.weidu.bean.Result;
import com.lll.weidu.core.DataCall;
import com.lll.weidu.core.exception.ApiException;
import com.lll.weidu.presenter.UpdateNamePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdateNameActivity extends AppCompatActivity {

    @BindView(R.id.update_nick)
    EditText updateNick;
    @BindView(R.id.update_btnsur)
    Button updateBtnsur;
    private UpdateNamePresenter updateNamePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_name);
        ButterKnife.bind(this);
        //创建对象
        updateNamePresenter = new UpdateNamePresenter(new UpdateName());
    }

    @OnClick(R.id.update_btnsur)
    public void onViewClicked() {

    }

    private class UpdateName implements DataCall<Result> {
        @Override
        public void success(Result result) {
            String s = updateNick.getText().toString();
        }

        @Override
        public void fail(ApiException e) {

        }
    }
}
