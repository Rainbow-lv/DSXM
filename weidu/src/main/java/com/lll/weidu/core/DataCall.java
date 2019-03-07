package com.lll.weidu.core;

import com.lll.weidu.core.exception.ApiException;

public interface DataCall<T> {
    void success(T result);
    void fail(ApiException e);
}
