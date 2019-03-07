package com.lll.weidu.presenter;

import com.lll.weidu.core.BasePresenter;
import com.lll.weidu.core.DataCall;
import com.lll.weidu.core.IRequest;
import com.lll.weidu.http.NetWork;

import io.reactivex.Observable;

public class UserNewPrsenter extends BasePresenter {
    public UserNewPrsenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        IRequest iLogin = NetWork.netWork().create(IRequest.class);
        return iLogin.addAddrss((long)args[0],(String) args[1],(String)args[2],(String)args[3],(String)args[4],(String)args[5]);
    }
}
