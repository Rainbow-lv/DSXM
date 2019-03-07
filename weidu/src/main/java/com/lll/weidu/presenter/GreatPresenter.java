package com.lll.weidu.presenter;

import com.lll.weidu.core.BasePresenter;
import com.lll.weidu.core.DataCall;
import com.lll.weidu.core.IRequest;
import com.lll.weidu.http.NetWork;


import io.reactivex.Observable;

public class GreatPresenter extends BasePresenter {
    public GreatPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        IRequest iLogin = NetWork.netWork().create(IRequest.class);
        return iLogin.addCircleGreat((String)args[0],(String)args[1],(long)args[2]);
    }
}
