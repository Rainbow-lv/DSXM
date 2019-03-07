package com.lll.weidu.presenter;

import com.lll.weidu.core.BasePresenter;
import com.lll.weidu.core.DataCall;
import com.lll.weidu.core.IRequest;
import com.lll.weidu.http.NetWork;

import java.io.File;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * @author dingtao
 * @date 2018/12/28 11:23
 * qq:1940870847
 */
public class ShopCommPresenter extends BasePresenter {

    public ShopCommPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
//        IRequest iRequest = NetWork.netWork().create(IRequest.class);
//
//        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
//        builder.addFormDataPart("commodityId", "1");
//        builder.addFormDataPart("orderid",(String)args[3]);
//        builder.addFormDataPart("content", (String)args[4]);
//        List<Object> list = (List<Object>) args[5];
//        if (list.size()>1) {
//            for (int i = 1; i < list.size(); i++) {
//                File file = new File((String) list.get(i));
//                builder.addFormDataPart("image", file.getName(),
//                        RequestBody.create(MediaType.parse("multipart/octet-stream"),
//                                file));
//            }
//        }
//        return iRequest.shopComm((Long) args[0],
//                (String)args[1],builder.build());
//    }
        IRequest iLogin = NetWork.netWork().create(IRequest.class);
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("content", (String)args[4]);
        builder.addFormDataPart("commodityId", String.valueOf((int) args[2]));
        builder.addFormDataPart("orderId",(String) args[3]);
        List<Object> list = (List<Object>) args[5];
        if (list.size()>1) {
            for (int i = 1; i < list.size(); i++) {
                File file = new File((String) list.get(i));
                builder.addFormDataPart("image", file.getName(),
                        RequestBody.create(MediaType.parse("multipart/octet-stream"),
                                file));
            }
        }
        return iLogin.shopComm((long)args[0],(String) args[1],builder.build());
    }

}
