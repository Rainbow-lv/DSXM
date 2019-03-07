package com.lll.weidu;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lll.weidu.adapter.ImageAdapter;
import com.lll.weidu.bean.Result;
import com.lll.weidu.bean.User;
import com.lll.weidu.bean.UserPresonBean;
import com.lll.weidu.core.DataCall;
import com.lll.weidu.core.exception.ApiException;
import com.lll.weidu.presenter.UpdateHeadPresenter;
import com.lll.weidu.presenter.UpdateNamePresenter;
import com.lll.weidu.presenter.UserPersonPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserPersonActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.user_headimage)
    SimpleDraweeView userHeadimage;
    @BindView(R.id.user_nicktv)
    TextView userNicktv;
    @BindView(R.id.user_pwdtv)
    TextView userPwdtv;
    @BindView(R.id.update_name)
    EditText updateName;
    @BindView(R.id.btn_sur)
    Button btnSur;
    @BindView(R.id.headimage_btn)
    Button headimageBtn;
    private UserPersonPresenter userPersonPresenter;
    private DaoSession daoSession;
    private UserDao userDao;
    private List<User> users;
    private long userId;
    private String sessionId;
    private PopupWindow pop;
    private UpdateHeadPresenter updateHeadPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_person);
        ButterKnife.bind(this);
        //创建对象
        userPersonPresenter = new UserPersonPresenter(new PersonCall());
        daoSession = DaoMaster.newDevSession(this, UserDao.TABLENAME);
        userDao = daoSession.getUserDao();
        users = userDao.loadAll();
        userId = users.get(0).getUserId();
        sessionId = users.get(0).getSessionId();
        userPersonPresenter.reqeust(userId, sessionId);

        View view = View.inflate(this, R.layout.pop_cream, null);
        //获取对象
        Button cream_bt = view.findViewById(R.id.cream_bt);
        Button phone_bt = view.findViewById(R.id.phone_bt);
        Button cancle_bt = view.findViewById(R.id.cancle_bt);
        //设置点击事件
        cream_bt.setOnClickListener(this);
        phone_bt.setOnClickListener(this);
        cancle_bt.setOnClickListener(this);
        //创建对象
        pop = new PopupWindow(view, ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        //设置背景
        pop.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        //设置外部点击可取消
        pop.setOutsideTouchable(true);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cream_bt:
                //相机
                // 第一步:跳转意图
                Intent intent = new Intent(
                        MediaStore.ACTION_IMAGE_CAPTURE);
                intent.addCategory("android.intent.category.DEFAULT");
                //进入相机的Activity
                startActivityForResult(intent, 0);
                break;
            case R.id.phone_bt:
                //相册
                // 设置相册的意图（权限）
                Intent intent1 = new Intent(Intent.ACTION_PICK);
                // 设置显式MIME数据类型
                intent1.setType("image/*");
                // 跳转回传
                startActivityForResult(intent1, 1);
                break;
            case R.id.cancle_bt:
                //取消
                pop.dismiss();
                break;
        }
    }

//    @OnClick({R.id.user_nicktv, R.id.btn_sur})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.user_nicktv:
//                updateName.setVisibility(View.VISIBLE);
//                btnSur.setVisibility(View.VISIBLE);
//                break;
//            case R.id.btn_sur:
//                //确认修改
//                //创建修改对象
//                String name = updateName.getText().toString();
//                UpdateNamePresenter updateNamePresenter = new UpdateNamePresenter(new UpdateName());
//                updateNamePresenter.reqeust(userId, sessionId, name);
//                break;
//        }
//    }

    @OnClick({R.id.user_nicktv, R.id.user_headimage, R.id.btn_sur})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.user_nicktv:
                //修改昵称
                updateName.setVisibility(View.VISIBLE);
                btnSur.setVisibility(View.VISIBLE);
                break;
            case R.id.user_headimage:
                //修改头像
                headimageBtn.setVisibility(View.VISIBLE);
                pop.showAsDropDown(userHeadimage);
                ImageAdapter imageAdapter = new ImageAdapter();
                //创建对象
                updateHeadPresenter = new UpdateHeadPresenter(new Updatehead());
             //   updateHeadPresenter.reqeust(userId,sessionId,imageAdapter.getList());
                break;
            case R.id.btn_sur:
                //确认修改
                //创建修改对象
                String name = updateName.getText().toString();
                UpdateNamePresenter updateNamePresenter = new UpdateNamePresenter(new UpdateName());
                updateNamePresenter.reqeust(userId, sessionId, name);
                break;
        }
    }


    private class PersonCall implements DataCall<Result<UserPresonBean>> {
        @Override
        public void success(Result<UserPresonBean> result) {
            UserPresonBean result1 = result.getResult();
            userHeadimage.setImageURI(result1.getHeadPic());
            userNicktv.setText(result1.getNickName());
            //userNicktv.notify();
            userPwdtv.setText(result1.getPhone());
        }

        @Override
        public void fail(ApiException e) {
            Toast.makeText(UserPersonActivity.this, "" + e.getCode(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0:
                Bitmap bitmap = data.getParcelableExtra("data");
                userHeadimage.setImageBitmap(bitmap);
                break;
            case 1:
                //得到图片路径
                Uri uri = data.getData();
                // 设置图片（相册获取图片完毕）
                userHeadimage.setImageURI(uri);
                break;
        }
    }

    //修改名称
    private class UpdateName implements DataCall<Result> {
        @Override
        public void success(Result result) {
            Toast.makeText(UserPersonActivity.this, "" + result.getMessage(), Toast.LENGTH_SHORT).show();
            updateName.setVisibility(View.GONE);
            btnSur.setVisibility(View.GONE);
        }

        @Override
        public void fail(ApiException e) {

        }
    }

    //上传头像
    private class Updatehead implements DataCall<Result> {
        @Override
        public void success(Result result) {
            Toast.makeText(UserPersonActivity.this, ""+result.getMessage(), Toast.LENGTH_SHORT).show();
            headimageBtn.setVisibility(View.GONE);
        }

        @Override
        public void fail(ApiException e) {

        }
    }
}
