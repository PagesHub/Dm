package com.yang.dm.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yang.dm.R;
import com.yang.dm.mvp.model.VideoDetails;
import com.yang.sdk.loader.GlideUtils;
import com.yang.sdk.utils.DateUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

/**
 * Describe: HomePager RecyclerView adapter
 * Created by Yang on 2019/1/23.
 */
public class VideoDetailsItemAdapter extends BaseQuickAdapter<VideoDetails, BaseViewHolder> {
    private JzvdStd mJzvdStd;

    public VideoDetailsItemAdapter() {
        super(R.layout.videos_details_item_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, VideoDetails item) {
        mJzvdStd=helper.getView(R.id.videoPlayer);
        helper.setText(R.id.txv_title, item.getData().getHeader().getTitle());
        helper.setText(R.id.txv_name, item.getData().getContent().getData().getAuthor()==null?"":item.getData().getContent().getData().getAuthor().getName());
        helper.setText(R.id.txv_provider, String.format(mContext.getString(R.string.video_provider), item.getData().getContent().getData().getProvider().getName()));
        helper.setVisible(R.id.provider_avatar, !item.getData().getContent().getData().getProvider().getIcon().isEmpty());
        GlideUtils.getInstance().glideLoad(mContext, item.getData().getContent().getData().getProvider().getIcon(), helper.getView(R.id.provider_avatar), 0);
        GlideUtils.getInstance().glideLoad(mContext, item.getData().getContent().getData().getAuthor().getIcon(), helper.getView(R.id.author_avatar), 0);
        helper.setText(R.id.txv_time, DateUtils.formatTime(String.valueOf(item.getData().getHeader().getTime() / 1000), "yyyy/MM/dd HH:mm:ss"));
        helper.setText(R.id.txv_content, item.getData().getContent().getData().getDescription());


        mJzvdStd.setUp(item.getData().getContent().getData().getPlayUrl(),item.getData().getHeader().getTitle(),JzvdStd.SCREEN_NORMAL);
        GlideUtils.getInstance().glideLoad(mContext, item.getData().getContent().getData().getCover().getFeed(), mJzvdStd.thumbImageView, 0);
        Jzvd.setVideoImageDisplayType(Jzvd.VIDEO_IMAGE_DISPLAY_TYPE_FILL_SCROP);

    }

    @Override
    public void onViewAttachedToWindow(BaseViewHolder holder) {
        super.onViewAttachedToWindow(holder);
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull BaseViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
    }


    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
    }

}
