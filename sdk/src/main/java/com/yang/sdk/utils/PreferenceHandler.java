package com.yang.sdk.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.yang.sdk.dagger.BaseContext;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Describe: Key-Value 属性存储
 * Created by Yang on 2018/12/20.
 */
@Singleton
public class PreferenceHandler {

    private static final String PREFS_NAME = "config";
    private static SharedPreferences settings;

    @Inject
    public PreferenceHandler(@BaseContext Context context) {
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    /**
     * 获取boolean值默认是false
     *
     * @param key
     * @return
     */
    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    private boolean getBoolean(String key, boolean defValue) {
        return settings.getBoolean(key, defValue);
    }

    /**
     * 设置Boolean值
     *
     * @param key
     * @param value
     */
    public void setBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    /**
     * 获取int值默认是0
     *
     * @param key
     * @return
     */
    public int getInt(String key) {
        return this.getInt(key, 0);
    }

    private int getInt(String key, int defValue) {
        return settings.getInt(key, defValue);
    }

    /**
     * 设置int值
     *
     * @param key
     * @param value
     */
    public void setInt(String key, int value) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    /**
     * 获取String值
     *
     * @param key
     * @return
     */
    public String getString(String key) {
        return this.getString(key, "");
    }

    private String getString(String key, String defValue) {
        return settings.getString(key, defValue);
    }

    /**
     * 设置String值
     *
     * @param key
     * @param value
     */
    public void setString(String key, String value) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * 清除属性
     */
    public void clearConfig() {
        SharedPreferences.Editor editor = settings.edit();
        editor.clear();
        editor.apply();
    }
}
