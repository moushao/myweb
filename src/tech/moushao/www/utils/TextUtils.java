package tech.moushao.www.utils;

public class TextUtils {
    public static boolean isEmpty(String var) {
        if (var == null || var.length() <= 0) {
            return true;
        }
        return false;
    }
}
