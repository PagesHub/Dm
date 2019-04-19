package com.yang.sdk.loader;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.yang.sdk.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

/**
 * Describe:
 * Created by Yang on 2019/1/18.
 */
public class GlideUtils {
    private String TAG = "GlideUtils";

    /**
     * 借助内部类 实现线程安全的单例模式
     * 属于懒汉式单例，因为Java机制规定，内部类SingletonHolder只有在getInstance()
     * 方法第一次调用的时候才会被加载（实现了lazy），而且其加载过程是线程安全的。
     * 内部类加载的时候实例化一次instance。
     */
    private GlideUtils() {
    }

    private static class GlideUtilsHolder {
        private final static GlideUtils INSTANCE = new GlideUtils();
    }

    public static GlideUtils getInstance() {
        return GlideUtilsHolder.INSTANCE;
    }

    /**
     * Glide 加载 简单判空封装 防止异步加载数据时调用Glide 抛出异常
     *
     * @param context
     * @param url        加载图片的url地址  String
     * @param imageView  加载图片的ImageView 控件
     * @param defaultImg 图片展示错误的本地图片 id
     */
    public void glideLoad(@Nullable Context context, @NonNull String url, @NonNull ImageView imageView, int defaultImg) {
        if (context != null) {
            Glide.with(context).load(url).apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .override(Target.SIZE_ORIGINAL)
                    .placeholder(ContextCompat.getDrawable(context, defaultImg == 0 ? R.drawable.shape_empty_bg : defaultImg))
                    .error(ContextCompat.getDrawable(context, defaultImg == 0 ? R.drawable.shape_empty_bg : defaultImg))
                    .fallback(ContextCompat.getDrawable(context, defaultImg == 0 ? R.drawable.shape_empty_bg : defaultImg)))
                    .into(imageView);
        } else {
            Log.e(TAG, "Picture loading failed,Context is null");
        }
    }

    /**
     * @param activity   当前activity
     * @param url        加载图片的url地址  String
     * @param imageView  加载图片的ImageView 控件
     * @param defaultImg 图片展示错误的本地图片 id
     */
    public void glideLoad(@NonNull Activity activity, @NonNull String url, @NonNull ImageView imageView, int defaultImg) {
        if (!activity.isDestroyed()) {
            Glide.with(activity).load(url).apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .override(Target.SIZE_ORIGINAL)
                    .placeholder(ContextCompat.getDrawable(activity, defaultImg == 0 ? R.drawable.shape_empty_bg : defaultImg))
                    .error(ContextCompat.getDrawable(activity, defaultImg == 0 ? R.drawable.shape_empty_bg : defaultImg))
                    .fallback(ContextCompat.getDrawable(activity, defaultImg == 0 ? R.drawable.shape_empty_bg : defaultImg)))
                    .into(imageView);
        } else {
            Log.e(TAG, "Picture loading failed,Activity is Destroyed");
        }
    }

    /**
     * @param fragment   当前Fragment(V4)
     * @param url        加载图片的url地址  String
     * @param imageView  加载图片的ImageView 控件
     * @param defaultImg 图片展示错误的本地图片 id
     */
    public void glideLoad(@NonNull Fragment fragment, @NonNull String url, @NonNull ImageView imageView, int defaultImg) {
        if (fragment.getActivity() != null) {
            Glide.with(fragment).load(url).apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .override(Target.SIZE_ORIGINAL)
                    .placeholder(ContextCompat.getDrawable(fragment.getActivity(), defaultImg == 0 ? R.drawable.shape_empty_bg : defaultImg))
                    .error(ContextCompat.getDrawable(fragment.getActivity(), defaultImg == 0 ? R.drawable.shape_empty_bg : defaultImg))
                    .fallback(ContextCompat.getDrawable(fragment.getActivity(), defaultImg == 0 ? R.drawable.shape_empty_bg : defaultImg)))
                    .into(imageView);
        } else {
            Log.e(TAG, "Picture loading failed,Fragment is null");
        }
    }
}
