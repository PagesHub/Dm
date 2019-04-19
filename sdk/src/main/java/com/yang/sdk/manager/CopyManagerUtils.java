package com.yang.sdk.manager;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;


/**
 * Describe:
 * Created by Yang on 2019/4/15.
 */
public class CopyManagerUtils {

    public static void copyManager(Context context, String info) {
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        assert cm != null;
        cm.setPrimaryClip(ClipData.newPlainText("复制", info));
        //TODO  Toast
    }
}
