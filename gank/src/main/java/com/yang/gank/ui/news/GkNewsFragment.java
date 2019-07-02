package com.yang.gank.ui.news;

import com.yang.gank.R;
import com.yang.gank.base.GankFragment;
import com.yang.gank.mvp.contract.NewsContract;
import com.zhangyue.we.x2c.ano.Xml;

/**
 * Describe: java文件说明
 * Created by Yang on 2019/7/1  14:56
 */
@Xml(layouts = "fragment_gk_news")
public class GkNewsFragment extends GankFragment implements NewsContract.View {

    @Override
    protected int bindLayout() {
        return R.layout.fragment_gk_news;
    }

    @Override
    protected void initView() {

    }
    @Override
    public void setReadClassify() {

    }
}
