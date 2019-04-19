package com.yang.sdk.loader;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import androidx.annotation.NonNull;


/**
 * Describe: 图片保存工具类
 * Created by Yang on 2019/1/12.
 */
public class DownLoadImageService implements Runnable {
    private String mUrl;
    private Context mContext;
    private ImageDownLoadCallBack callBack;
    private File currentFile;

    /**
     * @param context
     * @param url
     * @param callBack
     */
    public DownLoadImageService(@NonNull Context context, @NonNull String url, @NonNull ImageDownLoadCallBack callBack) {
        this.mUrl = url;
        this.callBack = callBack;
        this.mContext = context;
    }

    @Override
    public void run() {
        Bitmap bitmap = null;
        try {
            bitmap = Glide.with(mContext).asBitmap()
                    .load(mUrl)
                    .submit(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .get();
            if (bitmap != null) {// 在这里执行图片保存方法
                saveImageToGallery(bitmap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bitmap != null && currentFile.exists()) {
                callBack.onDownLoadSuccess(bitmap);
            } else {
                callBack.onDownLoadFailed();
            }
        }
    }

    /**
     * 保存图片
     *
     * @param bmp
     */
    private void saveImageToGallery(Bitmap bmp) {// 首先保存图片
        File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsoluteFile();//注意小米手机必须这样获得public绝对路径
        String paThName = "/DICM";
        File appDir = new File(file, paThName);
        if (!appDir.exists()) {
            appDir.mkdirs();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        currentFile = new File(appDir, fileName);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(currentFile);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        /**
         *
         最后通知图库更新
         */
        mContext.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(new File(currentFile.getPath()))));
    }

    /**
     * 图片保存结果监听
     */
    public interface ImageDownLoadCallBack {
        /**
         * 图片保存成功
         *
         * @param bitmap
         */
        void onDownLoadSuccess(Bitmap bitmap);

        /**
         * 图片保存失败
         */
        void onDownLoadFailed();
    }
}
