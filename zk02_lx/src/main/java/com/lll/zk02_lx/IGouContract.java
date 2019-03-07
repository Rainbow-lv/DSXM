package com.lll.zk02_lx;



public class IGouContract {
    //v层
    public interface IGouView{
        public void gouData();
    }
    //p层
    public interface IGouPresenter<IGouView>{
        //绑定
        public void attach(IGouView iGouView);

        //解绑
        public void deach(IGouView iGouView);

        //传值
        public void requestData();

    }
    //m层
    public interface IGouModel{
        public void containListModel(CallBack callBack);
        //接口
        public interface CallBack{
            public void responseData();
        }
    }
}
