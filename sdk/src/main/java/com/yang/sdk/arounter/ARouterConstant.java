package com.yang.sdk.arounter;

/**
 * Describe: ARouter常量
 * Created by Yang on 2019/4/17.
 */
public interface ARouterConstant {
    interface MainPath {
        //flutter交互界面
        String ACTIVITY_MAIN_FLUTTER = "/main/FlutterActivity";
    }

    interface GankPath {
        String MODULE_GANK_PROVIDER = "/gank/main";
        //干货集中营界面
        String ACTIVITY_GANK_FLUTTER = "/gank/FlutterActivity";
    }

    interface ChatPath {
        //跳转到聊天主界面
        String ACTIVITY_CHAT_MAIN = "/chat/ChatMainActivity";
    }

    interface KotlinPath {
        //跳转到玩安卓主界面 kotlin
        String ACTIVITY_KOTLIN_MAIN = "/kotlin/KtMainActivity";
    }
}
