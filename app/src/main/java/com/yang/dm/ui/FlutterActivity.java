package com.yang.dm.ui;

import android.view.View;
import android.widget.FrameLayout;

import com.yang.dm.R;
import com.yang.dm.base.DmBaseActivity;
import com.zhangyue.we.x2c.ano.Xml;

import io.flutter.facade.Flutter;
import io.flutter.view.FlutterView;


/**
 * Describe: flutter跳转界面
 * Created by Yang on 2019/3/26.
 */
@Xml(layouts = "activity_flutter")
public class FlutterActivity extends DmBaseActivity {
    @Override
    protected int bindLayout() {
        return R.layout.activity_flutter;
    }

    @Override
    protected void initView() {
        FlutterView flutterView = Flutter.createView(this, getLifecycle(), "route1");
        final FrameLayout layout = findViewById(R.id.flutter_container);
        layout.addView(flutterView);

        final FlutterView.FirstFrameListener[] listeners = new FlutterView.FirstFrameListener[1];
        listeners[0] = () -> layout.setVisibility(View.VISIBLE);
        flutterView.addFirstFrameListener(listeners[0]);
    }
}
