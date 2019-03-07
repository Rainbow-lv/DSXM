package com.lll.weidu.presenter;

import com.lll.weidu.core.BasePresenter;
import com.lll.weidu.core.DataCall;
import com.lll.weidu.core.IRequest;
import com.lll.weidu.http.NetWork;

import io.reactivex.Observable;

public class AddAdressPresenter extends BasePresenter {
    public AddAdressPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        IRequest iRequest = NetWork.netWork().create(IRequest.class);
        return iRequest.addAddrss((int)args[0],(String)args[1],(String) args[2],(String)args[3],(String)args[4],(String)args[5]);
    }
}
