package com.lll.zk02_lx.core;

import com.lll.zk02_lx.bean.Result;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public abstract class BasePresenter {
    DataCall dataCall;
    public BasePresenter(DataCall dataCall){
        this.dataCall = dataCall;
    }
    protected abstract Observable observable(Object... args);
    public void request(Object...args){
        observable().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Result>() {
                    @Override
                    public void accept(Result result) throws Exception {
                        dataCall.success(result);
                    }
                }, new Consumer<Result>() {
                    @Override
                    public void accept(Result result) throws Exception {
                        dataCall.fail(result);
                    }
                });
    }
}
