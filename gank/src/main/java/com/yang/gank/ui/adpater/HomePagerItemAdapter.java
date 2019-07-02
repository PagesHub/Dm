package com.yang.gank.ui.adpater;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yang.gank.R;
import com.yang.gank.mvp.model.GanHuo;
import com.youth.banner.Banner;

/**
 * Describe: HomePager RecyclerView adapter
 * Created by Yang on 2019/7/2  12:01
 */
public class HomePagerItemAdapter extends BaseQuickAdapter<GanHuo, BaseViewHolder> {
    private Banner mBanner;

    public HomePagerItemAdapter() {
        super(R.layout.fragment_gk_home_pager_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, GanHuo item) {
        helper.setText(R.id.txv_title, item.getDesc());
        helper.setText(R.id.txv_name, item.getWho());
    }
}
