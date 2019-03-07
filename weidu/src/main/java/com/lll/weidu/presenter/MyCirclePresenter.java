package com.lll.weidu.presenter;

import com.lll.weidu.core.BasePresenter;
import com.lll.weidu.core.DataCall;
import com.lll.weidu.core.IRequest;
import com.lll.weidu.http.NetWork;

import io.reactivex.Observable;

public class MyCirclePresenter extends BasePresenter {
    private int page=1;
    private boolean isRefresh=true;
    public MyCirclePresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        IRequest iRequest = NetWork.netWork().create(IRequest.class);
        isRefresh = (boolean)args[0];
        if (isRefresh){
            page = 1;
        }else{
            page++;
        }
        return iRequest.myCircle((long)args[1],(String) args[2],page,5);
    }
}
