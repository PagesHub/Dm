package com.yang.sdk.app;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import com.blankj.utilcode.util.LogUtils;
import com.tencent.smtt.sdk.QbSdk;
import com.yang.sdk.R;

import androidx.annotation.Nullable;

/**
 * Describe:
 * Created by Yang on 2019/4/15.
 */
public class InitializeService extends IntentService {
    private static final String ACTION_INIT_WHEN_APP_CREATE = "com.niqu.wallet.service.action.INIT";

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public InitializeService() {
        super("InitializeService");
    }

    /**
     * 启动IntentService
     *
     * @param context
     */
    public static void start(Context context) {
        Intent intent = new Intent(context, InitializeService.class);
        intent.setAction(ACTION_INIT_WHEN_APP_CREATE);
        if (Build.VERSION.SDK_INT >= 26) {
            context.startForegroundService(intent);
        } else {
            context.startService(intent);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            if (ACTION_INIT_WHEN_APP_CREATE.equals(intent.getAction())) {
                performInit();
            }
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("Ln", "Ln-App", NotificationManager.IMPORTANCE_MIN);
            channel.enableVibration(false);//去除振动
            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            if (manager != null) manager.createNotificationChannel(channel);
            Notification.Builder builder = new Notification.Builder(getApplicationContext(), "Ln")
                    .setContentTitle("正在后台运行")
                    .setSmallIcon(R.drawable.shape_empty_bg);
            startForeground(1, builder.build());//id must not be 0,即禁止是0
        }
    }

    /**
     * 执行app初始化工作
     */
    private void performInit() {
        initTbs();        //初始化X5内核
        initUtils();

    }

    /**
     * 初始化X5内核\
     * 搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
     */
    private void initTbs() {
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
            @Override
            public void onViewInitFinished(boolean arg0) { //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
            }

            @Override
            public void onCoreInitFinished() {
            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(), cb);
    }

    /**
     * 初始化utils工具类
     */
    private void initUtils() {
        LogUtils.Config config = LogUtils.getConfig();
        //边框开关，设置打开
        config.setBorderSwitch(true);
        //logcat 是否打印，设置打印
        config.setConsoleSwitch(true);
        //设置打印日志总开关，线上时关闭
        config.setLogSwitch(true);
    }
}
