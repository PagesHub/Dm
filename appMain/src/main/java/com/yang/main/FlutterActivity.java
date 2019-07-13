package com.yang.main;


import android.view.View;
import android.widget.FrameLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yang.main.base.MBaseActivity;
import com.yang.sdk.arounter.ARouterConstant;
import com.yang.sdk.arounter.ARouterUtils;

import io.flutter.facade.Flutter;
import io.flutter.view.FlutterView;

@Route(path = ARouterConstant.MainPath.ACTIVITY_MAIN_FLUTTER)
public class FlutterActivity extends MBaseActivity {

    @Override
    protected int bindLayout() {
        return R.layout.activity_flutter;
    }

    @Override
    protected void initView() {
        ARouterUtils.injectActivity(this);

        FlutterView flutterView = Flutter.createView(this, getLifecycle(), "route1");
        final FrameLayout layout = findViewById(R.id.flutter_container);
        layout.addView(flutterView);

        final FlutterView.FirstFrameListener[] listeners = new FlutterView.FirstFrameListener[1];
        listeners[0] = () -> layout.setVisibility(View.VISIBLE);
        flutterView.addFirstFrameListener(listeners[0]);
    }
}
