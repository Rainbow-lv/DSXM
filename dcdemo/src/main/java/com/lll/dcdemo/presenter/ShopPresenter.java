package com.lll.dcdemo.presenter;

import com.lll.dcdemo.core.BasePresenter;
import com.lll.dcdemo.core.DataCall;
import com.lll.dcdemo.core.IRequest;
import com.lll.dcdemo.http.NetWork;

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
