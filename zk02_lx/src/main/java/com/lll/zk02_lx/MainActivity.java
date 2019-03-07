package com.lll.zk02_lx;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_tiao)
    TextView tvTiao;
    int count = 3;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (count <0){
                startActivity(new Intent(MainActivity.this,HomeActivity.class));
                finish();
                return;
            }
            tvTiao.setText(count-- +"秒");
            handler.sendEmptyMessageDelayed(100,1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //发送
        handler.sendEmptyMessage(100);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeMessages(100);
    }
}
