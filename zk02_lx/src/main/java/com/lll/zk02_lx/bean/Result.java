package com.lll.zk02_lx.bean;

public class Result<T> {
//"result"
//        "message": "登录成功",
//        "status": "0000"
    private String message;
    private String status;
    private T result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
