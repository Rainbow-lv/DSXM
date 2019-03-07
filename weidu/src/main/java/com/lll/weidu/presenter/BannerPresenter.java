package com.lll.weidu.presenter;


import com.lll.weidu.core.BasePresenter;
import com.lll.weidu.core.DataCall;
import com.lll.weidu.core.IRequest;
import com.lll.weidu.http.NetWork;

import java.util.List;

import io.reactivex.Observable;

public class BannerPresenter extends BasePresenter {
    public BannerPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        IRequest iLogin = NetWork.netWork().create(IRequest.class);
        return iLogin.bann();
    }
}
