package com.lll.yuekao_moni;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lll.yuekao_moni.view.FlowLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FlowActivity extends AppCompatActivity {

    @BindView(R.id.edit)
    EditText edit;
    @BindView(R.id.btn_search)
    Button btnSearch;
    @BindView(R.id.flow_layout)
    FlowLayout flowLayout;
    @BindView(R.id.text01)
    Button text01;
    @BindView(R.id.text02)
    Button text02;
    @BindView(R.id.text03)
    Button text03;
    @BindView(R.id.text04)
    Button text04;
    @BindView(R.id.text05)
    Button text05;
    @BindView(R.id.btn_clear)
    Button btnClear;
    @BindView(R.id.tv_cont)
    TextView tvCont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btn_search)
    public void onViewClicked() {
        String editName = edit.getText().toString();

        tvCont.setText(editName);
    }
}
