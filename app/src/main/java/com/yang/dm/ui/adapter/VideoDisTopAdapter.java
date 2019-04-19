package com.yang.dm.ui.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.yang.dm.R;
import com.yang.dm.mvp.model.ContentBean;
import com.yang.dm.mvp.model.TagsBean;
import com.yang.dm.mvp.model.VideoDiscovery;
import com.yang.sdk.loader.GlideUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Describe:
 * Created by Yang on 2019/2/22.
 */
public class VideoDisTopAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    public static final int TYPE_LEVEL_0 = 0;
    public static final int TYPE_LEVEL_1 = 1;

    public VideoDisTopAdapter() {
        super(null);
        addItemType(TYPE_LEVEL_0, R.layout.videos_discover_top);
        addItemType(TYPE_LEVEL_1, R.layout.videos_discover_top_chlid);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {
        switch (helper.getItemViewType()) {
            case TYPE_LEVEL_0:
                ContentBean.DataBean dataBean = (ContentBean.DataBean) item;
                helper.setText(R.id.txv_title, dataBean.getTitle());
                break;
            case TYPE_LEVEL_1:
                TagsBean tagsBean = (TagsBean) item;
                helper.setText(R.id.txv_tag_name, tagsBean.getName());
                GlideUtils.getInstance().glideLoad(mContext, tagsBean.getHeaderImage(), helper.getView(R.id.imv_top_pic), R.mipmap.common_empty_square);
                break;
        }
    }
    /**
     * @param data
     * @return
     */
    public List<MultiItemEntity> getHotAuthorData(List<VideoDiscovery> data) {
        List<MultiItemEntity> res = new ArrayList<>();
        ContentBean.DataBean bean = data.get(13).getData();
        for (VideoDiscovery.DataBeanX.ItemListBean itemListBean : bean.getItemList()) {
            ContentBean.DataBean dataBean = itemListBean.getData();
            for (TagsBean tagsBean : dataBean.getTags()) {
                dataBean.addSubItem(tagsBean);
            }
            res.add(dataBean);
        }
        return res;
    }

    /**
     * @param data
     * @return
     */
    public List<MultiItemEntity> getExpandableData(List<VideoDiscovery> data) {
        List<MultiItemEntity> res = new ArrayList<>();
        for (VideoDiscovery discovery : data) {
            ContentBean.DataBean dataBean = null;
            if (discovery.getData().getTags() == null && discovery.getData().getContent() == null)
                continue;
            if (discovery.getData().getTags() != null)
                dataBean = discovery.getData();
            else if (discovery.getData().getContent() != null)
                dataBean = discovery.getData().getContent().getData();
            assert dataBean != null;
            List<TagsBean> tags = dataBean.getTags();
            for (int i = 0; i < tags.size(); i++) {
                dataBean.addSubItem(tags.get(i));
            }
            res.add(dataBean);
        }
        return res;
    }
}
