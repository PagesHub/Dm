package com.yang.sdk.utils;

import android.util.Log;

/**
 * Describe:Log助手类
 * Created by Yang on 2019/4/15.
 */
public class LogUtils {
    private static final String TAG = "Debug";

    // 开发调试状态
    public static boolean debug = true;


    public static void setDebug(boolean debug) {
        LogUtils.debug = debug;
    }

    public static void dLog(String msg) {
        if (debug)
            Log.d(TAG, msg == null ? "Null" : msg);
    }

    public static void dLog(String tag, String msg) {
        if (debug)
            Log.d(tag, msg == null ? "Null" : msg);
    }

    public static void eLog(String msg) {
        if (debug)
            Log.e(TAG, msg == null ? "Null" : msg);
    }

    public static void eLog(String tag, String msg) {
        if (debug)
            Log.e(tag, msg == null ? "Null" : msg);
    }

    public static void iLog(String msg) {
        if (debug)
            Log.i(TAG, msg == null ? "Null" : msg);
    }

    public static void iLog(String tag, String msg) {
        if (debug)
            Log.i(tag, msg == null ? "Null" : msg);
    }
}
