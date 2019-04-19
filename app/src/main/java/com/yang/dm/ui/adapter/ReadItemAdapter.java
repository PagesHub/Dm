package com.yang.dm.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yang.dm.R;
import com.yang.dm.mvp.model.Read;
import com.yang.dm.utils.StringUtils;
import com.yang.sdk.utils.DateUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Describe:
 * Created by Yang on 2019/1/29.
 */
public class ReadItemAdapter extends BaseQuickAdapter<Read, BaseViewHolder> {

    public ReadItemAdapter() {
        super(R.layout.readinfo_item_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, Read item) {
        helper.setText(R.id.txv_title, item.getTitle());
        String content;
        if (item.getContent() != null && !item.getContent().equals("")) {
            content = StringUtils.stripHtml(item.getContent());
        } else {
            if (item.getSite().getCat_en().equalsIgnoreCase("android") || item.getSite().getCat_en().equalsIgnoreCase("iOS"))
                if (item.getRaw().split("content':").length <= 1)
                    content = item.getUrl();
                else
                    content = StringUtils.stripHtml(item.getRaw().split("content':")[1]).replace("\\n", "").replace("'","");
            else {
                Matcher matcher = Pattern.compile(".+summary.+content.+'(.+)'.+direction.+").matcher(item.getRaw());
                String html = matcher.find() ? matcher.group(1) : "";
                content = (html != null ? StringUtils.stripHtml(html) : "").replace("\\n", "").replace("'","");
            }
        }
        helper.setText(R.id.txv_content, content);
        helper.setText(R.id.txv_time, DateUtils.formatTime(DateUtils.dealDateFormat(item.getCreated_at()), "yyyy/MM/DD HH:SS:mm"));

    }
}
