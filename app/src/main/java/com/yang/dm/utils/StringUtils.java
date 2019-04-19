package com.yang.dm.utils;

import org.jsoup.Jsoup;

/**
 * Describe:
 * Created by Yang on 2019/1/30.
 */
public class StringUtils {
    /**
     * @param html
     * @return
     */
    public static String stripHtml(String html) {
        return Jsoup.parse(html).text();
    }
}
