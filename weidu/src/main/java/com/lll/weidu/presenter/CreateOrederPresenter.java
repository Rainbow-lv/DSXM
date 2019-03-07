package com.lll.weidu.presenter;

import com.lll.weidu.core.BasePresenter;
import com.lll.weidu.core.DataCall;
import com.lll.weidu.core.IRequest;
import com.lll.weidu.http.NetWork;

import io.reactivex.Observable;

public class CreateOrederPresenter extends BasePresenter {
    public CreateOrederPresenter(DataCall baseCall) {
        super(baseCall);
    }

    @Override
    protected Observable observable(Object... args) {
        IRequest iLogin = NetWork.netWork().create(IRequest.class);
        return iLogin.createOreder((long)args[0],(String) args[1],(String) args[2],(double) args[3],(int) args[4]);
    }
}
