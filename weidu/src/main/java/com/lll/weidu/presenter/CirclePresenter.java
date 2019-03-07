package com.lll.weidu.presenter;

import com.lll.weidu.bean.CircleBean;
import com.lll.weidu.bean.Result;
import com.lll.weidu.core.BasePresenter;
import com.lll.weidu.core.DataCall;
import com.lll.weidu.core.IRequest;
import com.lll.weidu.http.NetWork;


import java.util.List;

import io.reactivex.Observable;

public class CirclePresenter extends BasePresenter {
    private int page=1;
    public CirclePresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable<Result<List<CircleBean>>> observable(Object... args) {
        IRequest iLogin = NetWork.netWork().create(IRequest.class);
        boolean refresh = (boolean)args[0];
        if (refresh){
            page = 1;
        }else{
            page++;
        }
        return iLogin.circle((long)args[1],(String)args[2],page,10);
    }
}
