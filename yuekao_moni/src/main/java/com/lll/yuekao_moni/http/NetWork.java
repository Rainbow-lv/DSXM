package com.lll.yuekao_moni.http;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetWork {
    String baseurl = "http://172.17.8.101:8081/";
//   String baseurl = "http://mobile.bwstudent.com/";

    private static NetWork netWork;
    private Retrofit retrofit;
    public static NetWork netWork(){
        if (netWork == null){
            netWork = new NetWork();
        }
        return netWork;
    }
    //单例模式
    private NetWork(){
        init();
    }

    private void init() {
        //设置拦截器
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //网络请求
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
//                .connectTimeout(10,TimeUnit.SECONDS)
//                .readTimeout(10,TimeUnit.SECONDS)
//                .writeTimeout(10,TimeUnit.SECONDS)
                .build();
        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(baseurl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public <T> T create(final Class<T> service){
        return retrofit.create(service);
    }
}
