package com.yang.dm.ui.info;

import android.view.View;

import com.yang.dm.R;
import com.yang.dm.base.DmBaseFragment;
import com.zhangyue.we.x2c.ano.Xml;

/**
 * Describe: 资讯
 * Created by Yang on 2019/1/3.
 */
@Xml(layouts = "fragment_info")
public class InfoFragment extends DmBaseFragment {
    @Override
    protected int bindLayout() {
        return R.layout.fragment_info;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }
}
