package com.lll.yuekao_moni;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.lll.yuekao_moni.fragment.Frag_01;
import com.lll.yuekao_moni.fragment.Frag_02;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.fragment)
    FrameLayout fragment;
    @BindView(R.id.radio01)
    RadioButton radio01;
    @BindView(R.id.radio02)
    RadioButton radio02;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private Frag_01 frag_01;
    private Frag_02 frag_02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //获取事务管理
        manager = getSupportFragmentManager();
        //开启事务
        transaction = manager.beginTransaction();
        //添加Fragment
        frag_01 = new Frag_01();
        frag_02 = new Frag_02();
        transaction.add(R.id.fragment,frag_01).show(frag_01);
        transaction.add(R.id.fragment,frag_02).hide(frag_02);
        //提交
        transaction.commit();
        //默认选中第一个
        radioGroup.check(radioGroup.getChildAt(0).getId());
        //切换按钮改变页面状态
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction transaction1 = manager.beginTransaction();
                switch (checkedId){
                    case R.id.radio01:
                        transaction1.show(frag_01).hide(frag_02);
                        break;
                    case R.id.radio02:
                        transaction1.show(frag_02).hide(frag_01);
                        break;
                }
                transaction1.commit();
            }
        });
    }

}
