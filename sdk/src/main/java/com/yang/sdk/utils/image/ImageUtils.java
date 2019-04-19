package com.yang.sdk.utils.image;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;

import com.yang.sdk.utils.DateUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Describe:  Image相关助手类
 * Created by Yang on 2019/4/15.
 */
public class ImageUtils {

    /**
     * 图片文件名称
     *
     * @return
     */
    public static String imageName() {
        return DateUtils.getNowTime() + ".jpg";
    }

    /**
     * 保存图片到本地并更新图库
     *
     * @param bmp
     * @param context
     * @return
     */
    public static boolean saveImageToGallery(String fileName, Bitmap bmp, Context context) {
        File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsoluteFile();//注意小米手机必须这样获得public绝对路径
        String paThName = "/DICM";
        File appDir = new File(file, paThName);
        if (!appDir.exists()) {
            appDir.mkdirs();
        }
        File currentFile = new File(appDir, fileName);
        if (!currentFile.isFile()) {
            FileOutputStream outputStream = null;
            boolean isSuccess = false;
            try {
                file.createNewFile();
                outputStream = new FileOutputStream(currentFile);
                bmp.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                // 最后通知图库更新
                Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                Uri uri = Uri.fromFile(file);
                intent.setData(uri);
                context.sendBroadcast(intent);
                isSuccess = true;
            } catch (Exception e1) {
                e1.printStackTrace();
            } finally {
                if (outputStream != null) {
                    try {
                        outputStream.flush();
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return isSuccess;
        }
        return false;
    }

    /**
     * 保存图片到本地并更新图库
     *
     * @param bmp
     * @param context
     * @return
     */
    public static void saveImageToGallery(Context context, Bitmap bmp) {// 首先保存图片
        File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsoluteFile();//注意小米手机必须这样获得public绝对路径
        String paThName = "/DICM";
        File appDir = new File(file, paThName);
        if (!appDir.exists()) {
            appDir.mkdirs();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File currentFile = new File(appDir, fileName);

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
        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(new File(currentFile.getPath()))));
    }

    /**
     * Uri转Bitmap
     *
     * @param uri
     * @param context
     * @return
     * @throws IOException
     */
    public static Bitmap UriToBitmap(Uri uri, Context context) throws IOException {
        InputStream inputStream = context.getContentResolver().openInputStream(uri);
        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, options);
        inputStream.close();
        return bitmap;
    }

    /***
     * Drawable 转换为 Bitmap
     *
     * @param drawable
     * @return
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {

        Bitmap bitmap = Bitmap.createBitmap(

                drawable.getIntrinsicWidth(),

                drawable.getIntrinsicHeight(),

                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888

                        : Bitmap.Config.RGB_565);

        Canvas canvas = new Canvas(bitmap);

        //canvas.setBitmap(bitmap);

        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());

        drawable.draw(canvas);

        return bitmap;
    }

    /**
     * 根据Uri获取具体的路径
     *
     * @param context
     * @param uri
     * @return
     */
    public static String getRealFilePath(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    /**
     * 按比例压缩图片
     *
     * @param path
     * @param w
     * @param h
     * @return
     */
    public static Bitmap getBitmap(String path, int w, int h) {
        Bitmap bit = null;
        BitmapFactory.Options opts = new BitmapFactory.Options();
        // opts.inJustDecodeBounds = true
        // ,设置该属性为true，不会真的返回一个Bitmap给你，它仅仅会把它的宽，高取回来给你，
        // 这样就不会占用太多的内存，也就不会那么频繁的发生OOM了
        opts.inJustDecodeBounds = true;
        bit = BitmapFactory.decodeFile(path, opts);
        int test = 1;
        if (opts.outWidth > opts.outHeight) {
            if (opts.outWidth >= w)
                test = opts.outWidth / w;
            opts.inSampleSize = test; // 限制处理后的图片最大宽为w*2
        } else {
            if (opts.outHeight >= h)
                test = opts.outHeight / h;
            opts.inSampleSize = test; // 限制处理后的图片最大高为h*2
        }
        opts.inJustDecodeBounds = false;
        bit = BitmapFactory.decodeFile(path, opts);
        return bit;
    }

    //计算图片的缩放值
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }

    /**
     * 把bitmap转换成String
     */
    public static String bitmapToBs64(String filePath) {
        Bitmap bm = getSmallBitmaps(filePath);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        //1.5M的压缩后在100Kb以内，测试得值,压缩后的大小=94486字节,压缩后的大小=74473字节
        //这里的JPEG 如果换成PNG，那么压缩的就有600kB这样
        bm.compress(Bitmap.CompressFormat.JPEG, 60, baos);
        byte[] b = baos.toByteArray();
        Log.d("d", "压缩后的大小=" + b.length);
        return Base64.encodeToString(b, Base64.DEFAULT);
    }

    /**
     * 根据路径获得图片并压缩，返回bitmap用于显示
     */
    public static Bitmap getSmallBitmaps(String filePath) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize2(options, 1080, 1920);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(filePath, options);
    }

    /**
     * 计算图片的缩放值
     */
    public static int calculateInSampleSize2(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }

    /**
     * 对Bitmap裁剪，使其变成圆形
     */
    public static Bitmap getCroppedBitmap(Bitmap bmp, int radius) {
        Bitmap sbmp;
        if (bmp.getWidth() != radius || bmp.getHeight() != radius)
            sbmp = Bitmap.createScaledBitmap(bmp, radius, radius, false);
        else
            sbmp = bmp;

        Bitmap output = Bitmap.createBitmap(sbmp.getWidth(), sbmp.getHeight(), Bitmap.Config.ARGB_8888);
        final Rect rect = new Rect(0, 0, sbmp.getWidth(), sbmp.getHeight());

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        paint.setColor(Color.parseColor("#BAB399"));

        Canvas c = new Canvas(output);
        c.drawARGB(0, 0, 0, 0);
        c.drawCircle(sbmp.getWidth() / 2 + 0.7f, sbmp.getHeight() / 2 + 0.7f, sbmp.getWidth() / 2 + 0.1f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        c.drawBitmap(sbmp, rect, rect, paint);

        return output;
    }

    /**
     * 把bitmap转换成String
     */
    public static String bitmapToBase64(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        //1.5M的压缩后在100Kb以内，测试得值,压缩后的大小=94486字节,压缩后的大小=74473字节
        //这里的JPEG 如果换成PNG，那么压缩的就有600kB这样
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        Log.d("d", "压缩后的大小=" + b.length);
        return Base64.encodeToString(b, Base64.DEFAULT);
    }
}
