package com.yang.sdk.manager;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Describe:
 * Created by Yang on 2018/12/17.
 */
public class FontManager {
    /**
     * 设置view组的MONACO字体
     *
     * @param context context
     * @param root    ViewGroup
     * @param path    default MONACO.ttf"
     */
    public static void viewGroupFonts(Context context, @NonNull ViewGroup root, @Nullable String path) {
        if (path == null || path.isEmpty())
            path = "MONACO.ttf";
        Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/" + path);
        for (int i = 0; i < root.getChildCount(); i++) {
            View v = root.getChildAt(i);
            if (v instanceof TextView) {
                ((TextView) v).setTypeface(tf);
            } else if (v instanceof ViewGroup) {
                viewGroupFonts(context, (ViewGroup) v, path);
            }
        }
    }

    /**
     * 设置view组的MONACO字体
     *
     * @param context context
     * @param v       view
     * @param path    default "MONACO.ttf"
     */
    public static void viewFonts(Context context, @NonNull View v, @Nullable String path) {
        if (path == null || path.isEmpty())
            path = "MONACO.ttf";
        Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/" + path);
        if (v instanceof TextView) {
            ((TextView) v).setTypeface(tf);
        }
    }
}
