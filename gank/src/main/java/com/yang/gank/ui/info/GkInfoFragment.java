package com.yang.gank.ui.info;

import android.view.View;

import com.yang.gank.R;
import com.yang.gank.base.GankFragment;
import com.yang.gank.mvp.contract.InfoContract;
import com.zhangyue.we.x2c.ano.Xml;

/**
 * Describe: 资讯
 * @author Created by Yang on 2019/1/3.
 */
@Xml(layouts = "fragment_gk_info")
public class GkInfoFragment extends GankFragment implements InfoContract.View{

    @Override
    protected int bindLayout() {
        return R.layout.fragment_gk_info;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }
}
