package com.yang.sdk.http.okgo;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okrx2.adapter.ObservableBody;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Describe:
 * Created by Yang on 2019/4/15.
 */
public class OkgoUtils {

    public OkgoUtils() {
    }
    /**
     * get请求方式
     *
     * @param url 请求url
     * @return
     */
    public static <T> Observable<T> get(String url) {
        return get(url, null, "", null, "");
    }

    public static <T> Observable<T> get(String url, Class<T> clazz, String tag) {
        return get(url, null, "", clazz, "");
    }


    /**
     * get请求方式
     *
     * @param url    请求url
     * @param params 请求参数
     * @param header 请求头
     * @param tag    tag标记
     * @return Observable对象
     */
    public static <T> Observable<T> get(String url, HttpParams params, String header, Class<T> clazz, String tag) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.put("Authorization", header);
        return OkGo.<T>get(url)
                .tag(tag)                           //请求tag，用于取消对应的请求
                .headers(httpHeaders)               //请求头
                .params(params)                      //请求参数
                .converter(new GsonConvert<T>(clazz))     //作用就是将服务端的数据做解析转换
                .adapt(new ObservableBody<T>())    //将网络请求的Call<T>对象，适配成我们需要的Observable<T>对象。
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * post 请求方式
     *
     * @param url    请求url
     * @param params 请求参数
     * @param header 请求头
     * @param clazz  实体类
     * @param tag    tag标记
     * @return Observable对象
     */
    public static <T> Observable<T> post(String url, HttpParams params, String header, Class<T> clazz, String tag) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.put("Authorization", header);
        return OkGo.<T>post(url)
                .tag(tag)
                .headers(httpHeaders)
                .params(params)
                .converter(new GsonConvert<>(clazz))
                .adapt(new ObservableBody<T>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
