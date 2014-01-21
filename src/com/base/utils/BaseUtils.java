package com.base.utils;

/**
 * Created by kai.wang on 12/13/13.
 */
public class BaseUtils {

    /**
     * 是不是INT
     */
    public static boolean isInt(Object object) {
        if (object == null) {
            return false;
        }
        try {
            Integer.parseInt(object.toString());
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * 转换为INT
     */
    public static int toInt(Object object) {
        int r = 0;
        if (object == null) {
            return 0;
        }
        try {
            r = Integer.parseInt(object.toString());
        } catch (NumberFormatException e) {
            return 0;
        }
        return r;
    }
}
