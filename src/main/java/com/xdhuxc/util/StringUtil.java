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
     * @return true，如果 s 为 null，或者 s 的长度为0。
     */
    public static boolean isEmpty(String s) {
        if ( null == s || s.length() == 0) {
            return true;
        }
        return false;
    }

    /**
     * 判断字符串 s 是否有效
     * @param s 待检测的字符串
     * @return true，如果字符串 s 不为 null，并且 s 的长度不为 0。
     */
    public static boolean isNotEmpty(String s) {
        return !StringUtil.isEmpty(s);
    }

    /**
     * 判断字符串 s 是否有效
     * @param s 待检测的字符串
     * @return true，如果字符串 s 为 null，或者 s 的长度为 0，或者 s 全为空格。
     */
    public static boolean isBlank(String s) {
        int sLength;
        if ( null == s || (sLength = s.length()) == 0 ) {
            return true;
        }
        // 字符串 s 中有一个不为空格，则说明 s 不为空。
        for (int i = 0; i < sLength; i++) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断字符串 s 是否有效
     * @param s 待检测的字符串
     * @return true，如果字符串 s 不为 null，且 s 的长度不为 0，并且 s 不全为 空格。
     */
    public static boolean isNotBlank(String s) {
        return !StringUtil.isBlank(s);
    }

}
