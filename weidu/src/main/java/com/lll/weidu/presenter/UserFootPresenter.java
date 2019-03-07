package com.lll.weidu.presenter;

import com.lll.weidu.core.BasePresenter;
import com.lll.weidu.core.DataCall;
import com.lll.weidu.core.IRequest;
import com.lll.weidu.http.NetWork;

import io.reactivex.Observable;

public class UserFootPresenter extends BasePresenter {

    int page = 1;
    private boolean refresh;

    public UserFootPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        IRequest iRequest = NetWork.netWork().create(IRequest.class);
        refresh = (boolean) args[0];
        if (refresh){
            page = 1;
        }else{
            page++;
        }
        return iRequest.foot((long)args[1],(String) args[2],page,5);
    }
    public boolean refresh() {
        return refresh;
    }
}
