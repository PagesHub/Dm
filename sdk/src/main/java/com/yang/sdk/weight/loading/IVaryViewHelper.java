package com.yang.sdk.weight.loading;

import android.content.Context;
import android.view.View;

/**
 * Describe:  加载布局接口
 * Created by Yang on 2019/4/15.
 */
public interface IVaryViewHelper {

    //获取当前布局
    View getCurrentLayout();

    //恢复布局
    void restoreView();

    //展示布局
    void showLayout(View view);

    //加载布局
    View inflate(int layoutId);

    //获取上下文context
    Context getContext();

    //获取View
    View getView();

}
