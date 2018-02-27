package tech.moushao.www.utils;

import java.util.List;

import net.sf.json.JSONObject;

import tech.moushao.www.bean.Article;
import tech.moushao.www.bean.ResponseBean;
import tech.moushao.www.bean.User;

public class JsonUtils {
    public static String getJsonString(String code, String message, Object ob) {
        ResponseBean rb = new ResponseBean(code, message, ob);
        return getString(rb);

    }

    public static String getJsonString(String code, String message) {
        ResponseBean rb = new ResponseBean(code, message);
        return getString(rb);
    }

    public static String getString(Object b) {
        JSONObject json = JSONObject.fromObject(b);
        return json.toString();
    }
}
