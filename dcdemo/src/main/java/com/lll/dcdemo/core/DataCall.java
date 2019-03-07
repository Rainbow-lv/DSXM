package com.lll.dcdemo.core;


import com.lll.dcdemo.bean.Result;


public interface DataCall<T> {
    void success(T data);
    void fail(Result result);
}
