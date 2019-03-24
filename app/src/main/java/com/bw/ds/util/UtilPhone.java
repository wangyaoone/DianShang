package com.bw.ds.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Auther: 12547
 * @Date: 2019/3/20 10:15:35
 * @Description:
 */
public class UtilPhone {
    /**
     * 手机号号段校验，
     第1位：1；
     第2位：{3、4、5、6、7、8}任意数字；
     第3—11位：0—9任意数字
     * @param value
     * @return
     */
    public static boolean isTelPhoneNumber(String value) {
        if (value != null && value.length() == 11) {
            Pattern pattern = Pattern.compile("^1[3|4|5|6|7|8][0-9]\\d{8}$");
            Matcher matcher = pattern.matcher(value);
            return matcher.matches();
        }
        return false;
    }
}
