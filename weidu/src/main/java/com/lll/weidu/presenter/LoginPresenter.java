package com.lll.weidu.presenter;

import com.lll.weidu.core.BasePresenter;
import com.lll.weidu.core.DataCall;
import com.lll.weidu.core.IRequest;
import com.lll.weidu.http.NetWork;

import io.reactivex.Observable;

public class LoginPresenter extends BasePresenter {
    public LoginPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        IRequest iRequest = NetWork.netWork().create(IRequest.class);
        return iRequest.login((String) args[0],(String) args[1]);
    }
}
