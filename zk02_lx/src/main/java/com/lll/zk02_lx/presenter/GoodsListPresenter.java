package com.lll.zk02_lx.presenter;

import com.lll.zk02_lx.core.BasePresenter;
import com.lll.zk02_lx.core.DataCall;
import com.lll.zk02_lx.core.IRequest;
import com.lll.zk02_lx.http.NetWork;

import io.reactivex.Observable;

public class GoodsListPresenter extends BasePresenter {
    public GoodsListPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        IRequest iRequest = NetWork.netWork().create(IRequest.class);
        return iRequest.getlist();
    }
}
