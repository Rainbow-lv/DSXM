package com.lll.weidu.presenter;

import com.lll.weidu.core.BasePresenter;
import com.lll.weidu.core.DataCall;
import com.lll.weidu.core.IRequest;
import com.lll.weidu.http.NetWork;

import io.reactivex.Observable;

public class SearchPresenter extends BasePresenter {
    private int page=1;
    private boolean isRefresh=true;
    public SearchPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        IRequest iRequest = NetWork.netWork().create(IRequest.class);
        isRefresh = (boolean)args[0];
        if (isRefresh){
            page = 1;
        }else{
            page++;
        }
        return iRequest.search((String) args[1],page,(int)args[2]);
    }
    public boolean isRefresh() {
        return isRefresh;
    }
}
