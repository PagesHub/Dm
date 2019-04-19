package com.yang.dm.ui.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yang.dm.R;
import com.yang.dm.mvp.model.ContentBean;
import com.yang.sdk.loader.GlideUtils;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * Describe:
 * Created by Yang on 2019/2/22.
 */
public class VideoDisClassifyAdapter extends BaseQuickAdapter<ContentBean.DataBean, BaseViewHolder> {

    public VideoDisClassifyAdapter(@Nullable List<ContentBean.DataBean> data) {
        super(R.layout.videos_discover_classify, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ContentBean.DataBean item) {
        GlideUtils.getInstance().glideLoad(mContext, item.getIcon(), helper.getView(R.id.imv_classify), R.mipmap.common_empty_square);
        helper.setText(R.id.txv_classify_title, item.getTitle().replace("#", ""));
        helper.setText(R.id.txv_classify_description, item.getDescription());
    }
}
