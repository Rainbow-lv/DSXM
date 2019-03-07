package com.lll.weidu.presenter;


import com.lll.weidu.core.BasePresenter;
import com.lll.weidu.core.DataCall;
import com.lll.weidu.core.IRequest;
import com.lll.weidu.http.NetWork;

import io.reactivex.Observable;

public class AddressPreseter extends BasePresenter {
    public AddressPreseter(DataCall baseCall) {
        super(baseCall);
    }

    @Override
    protected Observable observable(Object... args) {
        IRequest iLogin = NetWork.netWork().create(IRequest.class);
        return iLogin.addresslist((int)args[0],(String)args[1]);
    }
}
