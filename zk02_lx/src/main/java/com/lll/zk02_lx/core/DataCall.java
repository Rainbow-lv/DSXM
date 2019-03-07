package com.lll.zk02_lx.core;


import com.lll.zk02_lx.bean.Result;

public interface DataCall<T> {
    void success(T result);
    void fail(Result result);
}
