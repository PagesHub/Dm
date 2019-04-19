package com.yang.sdk.utils;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Describe:  杂项助手类
 * Created by Yang on 2019/4/15.
 */
public class OtherUtils {

    private static final String TAG = "SDK_Sample.Util";
    private static Intent intent;


    /**
     * 打开网络设置界面
     */
    @SuppressLint("ObsoleteSdkInt")
    public static void setNetworkMethod(Context mContext) {
        if (android.os.Build.VERSION.SDK_INT > 13) {
            intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
        } else {
            intent = new Intent();
            ComponentName component = new ComponentName("com.android.settings", "com.android.settings.WirelessSettings");
            intent.setComponent(component);
            intent.setAction("android.intent.action.VIEW");
        }
        mContext.startActivity(intent);
    }

    /**
     * 检查手机上是否安装了指定的软件
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean isInstalled(Context context, String packageName) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        if (packageInfos != null) {
            for (int i = 0; i < packageInfos.size(); i++) {
                String pkName = packageInfos.get(i).packageName;
                if (pkName.equals(packageName)) return true;
            }
        }
        return false;
    }

    /**
     * 保存电话号码到通讯录
     *
     * @param context
     * @param name    姓名
     * @param phone   电话
     */
    public static void saveContact(Context context, String name, String phone) {
        int newContactId;

        //保存电话薄
        // 1.向raw_contact表里面添加联系人的id
        ContentResolver resolver = context.getContentResolver();
        // 获取raw_contact表对应的uri
        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
        // 必须知道最后一条联系人的id是多少
        Cursor cursor = resolver.query(uri, new String[]{"_id"}, null, null, null);
        assert cursor != null;
        if (cursor.getCount() == 0) {//当通讯录没有联系人的时候要判断  否则可能会闪退(不知道有没有用)
            newContactId = 1;
        } else {
            cursor.moveToLast();
            int lastContactId = cursor.getInt(0);
            newContactId = lastContactId + 1;
        }
        //插入一个联系人id
        ContentValues values = new ContentValues();
        values.put("contact_id", newContactId);
        resolver.insert(uri, values);

        // 2. 使用添加的联系人的id向data表里面添加数据
        Uri dataUri = Uri.parse("content://com.android.contacts/data");
        // 电话数据
        ContentValues phoneValues = new ContentValues();
        phoneValues.put("data1", phone);
        phoneValues.put("mimetype", "vnd.android.cursor.item/phone_v2");
        phoneValues.put("raw_contact_id", newContactId);
        resolver.insert(dataUri, phoneValues);

        // 姓名数据
        ContentValues nameValues = new ContentValues();
        nameValues.put("data1", name);//手机号码集合获得的手机号
        nameValues.put("mimetype", "vnd.android.cursor.item/name");
        nameValues.put("raw_contact_id", newContactId);
        resolver.insert(dataUri, nameValues);
        cursor.close();
    }

    /**
     * 打电话
     *
     * @param context
     * @param phoneNumber
     */
    public static void callPhone(Context context, String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri uri = Uri.parse("tel:" + phoneNumber);
        intent.setData(uri);
        context.startActivity(intent);
    }

    public static void sendMessage(Context context, String phoneNumber) {
        Uri uri = Uri.parse("smsto:" + phoneNumber);
        Intent sendIntent = new Intent(Intent.ACTION_VIEW, uri);
        context.startActivity(sendIntent);
    }

    /**
     * MD5加密
     *
     * @param content
     * @return
     */
    public static String md5(String content) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(content.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("NoSuchAlgorithmException", e);
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) {
                hex.append("0");
            }
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }

    /**
     * 安装apk
     *
     * @param context 上下文
     * @param path    文件路径
     */
    public static void installAPK(Context context, String path) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(path)), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }
}
