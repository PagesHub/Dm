package com.yang.dm.ui.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yang.dm.R;
import com.yang.dm.mvp.model.ReadClassifyChild;
import com.yang.sdk.loader.GlideUtils;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Describe: HomePager RecyclerView adapter
 * Created by Yang on 2019/1/23.
 */
public class NewsPagerItemAdapter extends BaseQuickAdapter<ReadClassifyChild, BaseViewHolder> {

    public NewsPagerItemAdapter(@Nullable List<ReadClassifyChild> data) {
        super(R.layout.newspager_item_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ReadClassifyChild item) {
        GlideUtils.getInstance().glideLoad(mContext,item.getIcon(),helper.getView(R.id.imv_icon),R.mipmap.common_empty_square);
        helper.setText(R.id.txv_title,item.getTitle());
    }

    @Override
    public void onViewAttachedToWindow(BaseViewHolder holder) {
        super.onViewAttachedToWindow(holder);
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull BaseViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
    }
}
