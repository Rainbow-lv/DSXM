package com.lll.weidu;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lll.weidu.adapter.ImageAdapter02;
import com.lll.weidu.bean.Result;
import com.lll.weidu.bean.User;
import com.lll.weidu.core.DataCall;
import com.lll.weidu.core.exception.ApiException;
import com.lll.weidu.presenter.PublishCirclePresenter;
import com.lll.weidu.presenter.ShopCommPresenter;
import com.lll.weidu.untils.UIUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GoCommActivity extends AppCompatActivity {


    @BindView(R.id.pinglun_headerpic)
    ImageView pinglunHeaderpic;
    @BindView(R.id.pinglin_name)
    TextView pinglinName;
    @BindView(R.id.pinglun_price)
    TextView pinglunPrice;
    @BindView(R.id.pinglun_one)
    RelativeLayout pinglunOne;
    @BindView(R.id.pinglun_ed)
    EditText pinglunEd;
    @BindView(R.id.pinglun_two)
    RelativeLayout pinglunTwo;
    @BindView(R.id.pinglun_recycle)
    RecyclerView pinglunRecycle;
    @BindView(R.id.pinglun_rd)
    RadioButton pinglunRd;
    @BindView(R.id.pinglun_btn_tb)
    Button pinglunBtnTb;
    private ShopCommPresenter shopCommPresenter;
    private int id;
    private String orderId;
    private String sessionId;
    private long userId;
    //    private ImageAdapter imageAdapter;
    public final static int CAMERA = 1;// 拍照
    private List<Object> list1 = new ArrayList<>();
    private ImageAdapter02 imageAdapter02;
    private PublishCirclePresenter publishCirclePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_comm);
        ButterKnife.bind(this);
        //获取对象
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        pinglinName.setText(name);
        String price = intent.getStringExtra("price");
        pinglunPrice.setText("￥" + price);
        String image = intent.getStringExtra("image");
        Glide.with(this).load(image).into(pinglunHeaderpic);
        id = intent.getIntExtra("id", 0);
        orderId = intent.getStringExtra("orderId");
        //创建对象
        shopCommPresenter = new ShopCommPresenter(new ShopCommCall());

        //  查询数据库
        DaoSession daoSession = DaoMaster.newDevSession(this, UserDao.TABLENAME);
        UserDao userDao = daoSession.getUserDao();
        List<User> users = userDao.loadAll();

        for (int i = 0; i < users.size(); i++) {
            sessionId = users.get(0).getSessionId();
            userId = users.get(0).getUserId();
        }
        //设置适配器
//        imageAdapter = new ImageAdapter();
        imageAdapter02 = new ImageAdapter02(list1, this);
        imageAdapter02.setSign(1);
        list1.add(R.drawable.camerablue);
        //设置布局
        pinglunRecycle.setLayoutManager(new GridLayoutManager(this, 3));
        pinglunRecycle.setAdapter(imageAdapter02);
    }

    //    @OnClick(R.id.pinglun_btn_tb)
//    public void onViewClicked() {
//        shopCommPresenter.reqeust(userId, sessionId, id,orderId,pinglunEd.getText().toString(),list1);
//    }
    @OnClick({R.id.pinglun_rd, R.id.pinglun_btn_tb})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.pinglun_rd:
                //同步圈子
                publishCirclePresenter = new PublishCirclePresenter(new PublisCall());
                publishCirclePresenter.reqeust(userId,sessionId,id,pinglunEd.getText().toString(),list1);
                break;
            case R.id.pinglun_btn_tb:
                shopCommPresenter.reqeust(userId, sessionId, id,orderId,pinglunEd.getText().toString(),list1);
                //发表
                break;
        }
    }

    //获取图片路径
    public String getFilePath(String fileName, int requestCode, Intent data) {
        if (requestCode == CAMERA) {
            return fileName;
        } else if (requestCode == 101) {
            Uri uri = data.getData();
            String[] proj = {MediaStore.Images.Media.DATA};
            Cursor actualimagecursor = managedQuery(uri, proj, null, null, null);
            int actual_image_column_index = actualimagecursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            actualimagecursor.moveToFirst();
            String img_path = actualimagecursor
                    .getString(actual_image_column_index);
            // 4.0以上平台会自动关闭cursor,所以加上版本判断,OK
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH)
                actualimagecursor.close();
            return img_path;
        }
        return null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101) {//resultcode是setResult里面设置的code值
            String filePath = getFilePath(null, requestCode, data);
            list1.add(filePath);
            imageAdapter02.notifyDataSetChanged();
        }
    }


    private class ShopCommCall implements DataCall<Result> {
        @Override
        public void success(Result result) {
            if (result.getStatus().equals("0000")) {
                Toast.makeText(GoCommActivity.this, "" + result.getMessage(), Toast.LENGTH_SHORT).show();
            } else {
                UIUtils.showToastSafe(result.getStatus() + "  " + result.getMessage());
            }
        }

        @Override
        public void fail(ApiException e) {

        }
    }

    //同步圈子
    private class PublisCall implements DataCall<Result> {
        @Override
        public void success(Result result) {
            Toast.makeText(GoCommActivity.this, "同步成功", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void fail(ApiException e) {

        }
    }
}
