package com.yang.sdk.arounter;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.DegradeService;

/**
 * Describe: ARouter路由降级处理
 * Created by Yang on 2019/4/17.
 */
@Route(path = DegradeServiceImpl.PATH)
public class DegradeServiceImpl implements DegradeService {

    static final String PATH = "/service/DegradeServiceImpl";

    @Override
    public void onLost(Context context, Postcard postcard) {
        if (context != null && postcard.getGroup().equals("activity")) {
//            Intent intent = new Intent(context, WebViewActivity.class);
//            intent.putExtra(Constants.URL, Constants.GITHUB);
//            intent.putExtra(Constants.TITLE, "github地址");
//            ActivityCompat.startActivity(context, intent, null);
        }
    }

    @Override
    public void init(Context context) {

    }
}
