package com.yang.sdk.http.okgo;

import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Types;
import com.lzy.okgo.convert.Converter;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;

import okhttp3.Response;

/**
 * Describe:  使用Gson做解析
 * Created by Yang on 2019/4/15.
 */
public class GsonConvert <T> implements Converter<T> {

    private Class<T> clazz;

    public GsonConvert() {

    }

    GsonConvert(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T convertResponse(Response response) throws Throwable {
        if (response.code() == 401)
            throw new IllegalStateException("401");
        else if (response.code() == 500)
            throw new IllegalStateException("500");
        if (clazz == null) {
            //反射获取带泛型的class
            Type superclass = getClass().getGenericSuperclass();
            if (superclass instanceof Class)
                throw new RuntimeException("Missing type parameter.");
            //获取所有泛型
            ParameterizedType parameterizedType = (ParameterizedType) superclass;
            //将泛型转为type
            Type mType = $Gson$Types.canonicalize(Objects.requireNonNull(parameterizedType).getActualTypeArguments()[0]);
            return parseType(response, mType);
        } else {
            return parseClass(response, clazz);
        }
    }

    private T parseClass(Response response, Class<T> clazz) throws Exception {
        Gson gson = new Gson();
        T t = gson.fromJson(Objects.requireNonNull(response.body()).string(), clazz);
        return t;
    }

    private T parseType(Response response, Type mType) throws Exception {
        Gson gson = new Gson();
        T t = gson.fromJson(Objects.requireNonNull(response.body()).string(), mType);
        return t;
    }
}
