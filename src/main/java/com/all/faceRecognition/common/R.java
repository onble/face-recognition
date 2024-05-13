package com.all.faceRecognition.common;

import java.util.HashMap;

/**
 * 统一数据格式的封装
 */
public class R extends HashMap<String, Object> {

    public static R newInstance(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("message", msg);
        r.put("data", new HashMap<String, Object>());
        return r;
    }

    // 设置成功
    public static R ok() {
        R r = new R();
        r.put("code", 200);
        r.put("message", "ok");
        r.put("data", new HashMap<String, Object>());
        return r;
    }

    // 设置失败
    public static R error() {
        R r = new R();
        r.put("code", 500);
        r.put("message", "error");
        r.put("data", new HashMap<String, Object>());
        return r;
    }

    // 设置data
    public R setData(String key, Object value) {
        if (containsKey("data")) {
            HashMap<String, Object> data = (HashMap<String, Object>) get("data");
            data.put(key, value);
        } else {
            HashMap<String, Object> data = new HashMap<>();
            data.put(key, value);
            put("data", data);
        }
        return this;
    }

    // 设置message
    public R setMessage(String message) {
        put("message", message);
        return this;
    }

}
