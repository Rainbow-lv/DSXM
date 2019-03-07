package com.lll.yuekao_moni.presenter;

import com.lll.yuekao_moni.core.BasePresenter;
import com.lll.yuekao_moni.core.DataCall;
import com.lll.yuekao_moni.core.IRequest;
import com.lll.yuekao_moni.http.NetWork;

import io.reactivex.Observable;

public class ShopPresenter extends BasePresenter {
    public ShopPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        IRequest iRequest = NetWork.netWork().create(IRequest.class);
        return iRequest.shopCar();
    }
}
