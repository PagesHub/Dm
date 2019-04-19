package com.yang.sdk.utils;

import android.content.Context;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Describe:
 * Created by Yang on 2019/4/15.
 */
public class SdkInPuFilter implements InputFilter {

    private Context mContext;
    private String mTips;
    private Pattern mPattern;
    /**
     * 最大数字
     */
    private double MAX_VALUE = 500;
    private double MIN_VALUE = 5;
    /**
     * 小数点后的数字的位数
     */
    private int POINTER_LENGTH = 2;
    private static final String POINTER = ".";

    public SdkInPuFilter(Context context, double maxValue ,double minValue, String tips,int length) {
        this.mContext = context;
        this.MAX_VALUE = maxValue;
        this.MIN_VALUE = minValue;
        mTips = "";
        this.mTips = tips;
        this.POINTER_LENGTH=length;
        mPattern = Pattern.compile("([0-9]|\\.)*");//用于匹配输入的是0-9 .  这几个数字和字符
    }

    /**
     * @param source 新输入的字符串
     * @param start  新输入的字符串起始下标，一般为0
     * @param end    新输入的字符串终点下标，一般为source长度-1
     * @param dest   输入之前文本框内容
     * @param dstart 原内容起始坐标，一般为0
     * @param dend   原内容终点坐标，一般为dest长度-1
     * @return
     */
    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        String sourceText = source.toString();
        String destText = dest.toString();
        //验证删除等按键
        if (TextUtils.isEmpty(sourceText)) {
            if (dstart == 0 && destText.indexOf(POINTER) == 1) {//保证小数点不在第一个位置
                return "0";
            }
            return "";
        }
        Matcher matcher = mPattern.matcher(source);
        if (destText.contains(POINTER)) {//已经输入小数点的情况下，只能输入数字
            if (!matcher.matches()) {
                return "";
            } else {
                if (POINTER.contentEquals(source)) {//只能输入一个小数点
                    return "";
                }
            }
            //验证小数点精度，保证小数点后只能输入两位
            int index = destText.indexOf(POINTER);
            int length = destText.trim().length() - index;
            if (length > POINTER_LENGTH && dstart > index) {
                return "";
            }
        } else {//没有输入小数点的情况下，只能输入小数点和数字，但首位不能输入小数点和0
            if (!matcher.matches()) {
                return "";
            } else {
                if ((POINTER.contentEquals(source)) && dstart == 0) {//第一个位置输入小数点的情况
                    return "0.";
                } else if ("0".contentEquals(source) && dstart == 0) {//用于修复能输入多位0
                    return "0.";
                }
            }
        }
        String first = destText.substring(0, dstart);
        String second = destText.substring(dstart, destText.length());
        String sum = first + sourceText + second;
        //验证输入金额的大小
        //这里得到输入完之后需要计算的金额  如果这个金额大于了事先设定的金额,那么久直接返回  不需要加入输入的字符
        if (String.valueOf(MIN_VALUE).length() >= sum.length()) {
            return dest.subSequence(dstart, dend) + sourceText;
        }
        double sumText = Double.parseDouble(sum);
        if (sumText > MAX_VALUE || sumText < MIN_VALUE) {
            if (!mTips.isEmpty())
              //TODO  ToastUtils.show(mTips);
            return dest.subSequence(dstart, dend);
        }
        //如果输入的金额小于事先规定的金额
        return dest.subSequence(dstart, dend) + sourceText;
    }
}
