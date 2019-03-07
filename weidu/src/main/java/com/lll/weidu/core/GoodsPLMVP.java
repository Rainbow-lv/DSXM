package com.lll.weidu.core;


import com.lll.weidu.bean.Result;

import java.io.File;

/**
 * Created by mrl
 * on 2019/3/1 16:47
 * QQ:1452674298
 * Describe:
 */
public interface GoodsPLMVP {
     //V层
         public interface IView{

             public void showData(Result result);
         }

         //P层
         public interface IPresenter<IView>{

             public void attachView(IView view);
             public void disAttachView(IView view);
             public void requestData(String oid, int comid, String s, File file);
         }

         //M层
         public interface IModel{

             public void containData(String oid, int comid, String s, File file, CallBack callBack);

             public interface CallBack{

                 public void callBack(Result result);

             }
         }
}
