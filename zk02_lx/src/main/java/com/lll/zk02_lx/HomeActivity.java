package com.lll.zk02_lx;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.lll.zk02_lx.fragment.Frag_car;
import com.lll.zk02_lx.fragment.Frag_home;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.fragment)
    FrameLayout fragment;
    @BindView(R.id.radio01)
    RadioButton radio01;
    @BindView(R.id.radio02)
    RadioButton radio02;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.lay)
    LinearLayout lay;
    @BindView(R.id.drawer)
    DrawerLayout drawer;
    @BindView(R.id.img_menu)
    ImageView imgMenu;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    ArrayList<String> list = new ArrayList<>();
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private Frag_home frag_home;
    private Frag_car frag_car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        //获取管理者
        manager = getSupportFragmentManager();
        //开启事务
        transaction = manager.beginTransaction();
        initData();
        //添加Fragment
        frag_home = new Frag_home();
        frag_car = new Frag_car();
        transaction.add(R.id.fragment,frag_home).show(frag_home);
        transaction.add(R.id.fragment,frag_car).hide(frag_car);
        //提交
        transaction.commit();
        //设置第一个选中
        radioGroup.check(radioGroup.getChildAt(0).getId());
        //切换按钮改变页面状态
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction transaction1 = manager.beginTransaction();
                switch (checkedId){
                    case R.id.radio01:
                        transaction1.show(frag_home).hide(frag_car);
                        drawer.closeDrawers();
                        break;
                    case R.id.radio02:
                        transaction1.show(frag_car).hide(frag_home);
                        drawer.closeDrawers();
                        break;
                }
                transaction1.commit();
            }
        });
    }

    private void initData() {
        list.add("首页");
        list.add("购物车");
        final ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(HomeActivity.this,android.R.layout.simple_list_item_1,list);
        lv.setAdapter(mAdapter);
        //设置点击监听
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentTransaction transaction2 = manager.beginTransaction();
                switch (position){
                    case 0:
                        transaction2.show(frag_home).hide(frag_car);
                        radioGroup.check(radioGroup.getChildAt(position).getId());
                        break;
                    case 1:
                        transaction2.show(frag_car).hide(frag_home);
                        radioGroup.check(radioGroup.getChildAt(position).getId());
                        break;
                }
                transaction2.commit();
            }
        });
    }

    @OnClick(R.id.img_menu)
    public void onViewClicked() {
    }
}
