package com.lll.dcdemo.core;




import com.lll.dcdemo.bean.Result;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public abstract class BasePresenter {
    private DataCall dataCall;
    private Observable observable;

    public BasePresenter(DataCall dataCall) {
        this.dataCall = dataCall;
    }

    protected abstract Observable observable(Object... args);

    public void reqeust(Object... args) {
        observable = observable(args);
        observable.subscribeOn(Schedulers.newThread())//将请求调度到子线程上
               .observeOn(AndroidSchedulers.mainThread())//观察响应结果，把响应结果调度到主线程中处理
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




    public void unBind() {
        dataCall = null;
    }
}
