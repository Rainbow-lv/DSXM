package com.lll.weidu.core;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.google.gson.Gson;
import com.lll.weidu.DaoMaster;
import com.lll.weidu.UserDao;
import com.lll.weidu.bean.User;
import com.lll.weidu.untils.LogUtils;

import java.util.List;

import butterknife.ButterKnife;

//import com.umeng.analytics.MobclickAgent;

/**
 * @author dingtao
 * @date 2018/12/29 14:00
 * qq:1940870847
 */
public abstract class WDActivity extends AppCompatActivity {
    Gson mGson=new Gson();
    public final static int PHOTO = 0;// 相册选取
    public final static int CAMERA = 1;// 拍照
    public Dialog mLoadDialog;// 加载框

    /**
     * 记录处于前台的Activity
     */
    private static WDActivity mForegroundActivity = null;

    public User LOGIN_USER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //查询登录用户，方便每个页面使用
        UserDao userDao = DaoMaster.newDevSession(this,UserDao.TABLENAME).getUserDao();
        List<User> userInfos = userDao.queryBuilder().where(UserDao.Properties.Status.eq(1)).list();
        if (userInfos!=null&&userInfos.size()>0){
            LOGIN_USER = userInfos.get(0);//读取第一项
            LogUtils.i("用户信息"+mGson.toJson(LOGIN_USER));
        }
        //打印堆栈ID
        LogUtils.e("getTaskId = " + getTaskId());
        initLoad();
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initView();
    }

    protected abstract void initView();

    /**
     * 设置layoutId
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 清除数据
     */
    protected abstract void destoryData();

    /**
     * @param mActivity 传送Activity的
     */
    public void intent(Class mActivity) {
        Intent intent = new Intent(this, mActivity);
        startActivity(intent);
    }

    /**
     * @param mActivity 传送Activity的
     * @param bundle
     */
    public void intent(Class mActivity, Bundle bundle) {
        Intent intent = new Intent(this, mActivity);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 初始化加载框
     */
    private void initLoad() {
        mLoadDialog = new ProgressDialog(this);// 加载框
        mLoadDialog.setCanceledOnTouchOutside(false);
        mLoadDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode,
                                 KeyEvent event) {
                if (mLoadDialog.isShowing() && keyCode == KeyEvent.KEYCODE_BACK) {
                    cancelLoadDialog();
                    mLoadDialog.cancel();
                }
                return false;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destoryData();
    }

    //取消操作：请求或者其他
    public void cancelLoadDialog() {

    }

    @Override
    protected void onStart() {
        super.onStart();
        mForegroundActivity = this;
    }

    /**
     * 获取当前处于前台的activity
     */
    public static WDActivity getForegroundActivity() {
        return mForegroundActivity;
    }

    /**
     * 得到图片的路径
     *
     * @param fileName
     * @param requestCode
     * @param data
     * @return
     */
    public String getFilePath(String fileName, int requestCode, Intent data) {
        if (requestCode == CAMERA) {
            return fileName;
        } else if (requestCode == PHOTO) {
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
//    @Override
//    public void onResume() {
//        super.onResume();
//        MobclickAgent.onResume(this);
//    }
//    @Override
//    public void onPause() {
//        super.onPause();
//        MobclickAgent.onPause(this);
//    }
}
