package com.lll.yuekao_moni.core;


import com.lll.yuekao_moni.bean.Result;

public interface DataCall<T> {
    void success(T data);
    void fail(Result result);
}
