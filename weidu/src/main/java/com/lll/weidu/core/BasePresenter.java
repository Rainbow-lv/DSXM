package com.lll.weidu.core;

import com.lll.weidu.bean.Result;
import com.lll.weidu.core.exception.CustomException;
import com.lll.weidu.core.exception.ResponseTransformer;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public abstract class BasePresenter {
    private DataCall dataCall;
    private boolean running;
    public BasePresenter(DataCall dataCall) {
        this.dataCall = dataCall;
    }
    protected abstract Observable observable(Object... args);
    public void reqeust(Object... args) {
        if (running)
            return;
        running = true;
        observable(args)
                .compose(ResponseTransformer.handleResult())
                .compose(new ObservableTransformer() {
                    @Override
                    public ObservableSource apply(Observable upstream) {
                        return upstream.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                })
                .subscribe(new Consumer<Result>() {
                    @Override
                    public void accept(Result result) throws Exception {
                        running = false;
                        dataCall.success(result);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        running = false;
                        // 处理异常
//                        UIUtils.showToastSafe("请求失败");
                        dataCall.fail(CustomException.handleException(throwable));
                    }
                });
    }
    public boolean isRunning() {
        return running;
    }
    public void unBind() {
        dataCall = null;
    }
}
