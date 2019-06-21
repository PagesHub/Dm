package com.yang.main;


import com.yang.main.base.MBaseActivity;
import com.zhangyue.we.x2c.ano.Xml;

/**
 * Describe: java文件说明
 * Created by Yang on 2019/6/20  17:42
 */
@Xml(layouts = {"R.layout.activity_main"})
public class MainHomeActivity extends MBaseActivity {

    @Override
    protected int bindLayout() {
        return R.layout.activity_main_home;
    }

    @Override
    protected void initView() {

    }
}
