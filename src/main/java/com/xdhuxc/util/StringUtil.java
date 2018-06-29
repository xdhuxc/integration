package com.xdhuxc.util;

/**
 * 字符串工具类
 * @author xdhuxc
 * @date 2018-06-28
 **/

public class StringUtil {

    /**
     * 判断字符串 s 是否有效
     * @param s 待检测的字符串
     * @return true，如果 s 为 null，或者 s 的长度为0
     */
    public static boolean isEmpty(String s) {
        if ( null == s || s.length() == 0) {
            return true;
        }
        return false;
    }



}
