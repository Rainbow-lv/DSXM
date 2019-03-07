package com.lll.zk02_lx.presenter;

import com.lll.zk02_lx.core.BasePresenter;
import com.lll.zk02_lx.core.DataCall;
import com.lll.zk02_lx.core.IRequest;
import com.lll.zk02_lx.http.NetWork;

import io.reactivex.Observable;

public class BannerPresenter extends BasePresenter {
    public BannerPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        IRequest iLogin = NetWork.netWork().create(IRequest.class);
        return iLogin.banner();
    }
}
