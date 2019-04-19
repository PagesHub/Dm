package com.yang.sdk.utils.rxUtils;

/**
 * Describe:RxBusBaseMessage数据
 * Created by Yang on 2018/12/19.
 */
public class RxBusMessage {
    private int code;
    private Object object;

    public RxBusMessage(int code, Object object) {
        this.code = code;
        this.object = object;
    }

    public int getCode() {
        return code;
    }

    public Object getObject() {
        return object;
    }
}
