package com.all.officeSystem.common;

import java.util.HashMap;

/**
 * 统一数据格式的封装
 */
public class R extends HashMap<String, Object> {

    public static R newInstance(int code, String msg){
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    // 设置成功
    public static R ok(){
        R r = new R();
        r.put("code", 200);
        r.put("msg", "ok");
        return r;
    }

    // 设置失败
    public static R error(){
        R r = new R();
        r.put("code", 500);
        r.put("mag", "error");
        return r;
    }

    // 设置data
    public R setData(String key, Object value){
        put(key, value);
        return this;
    }
}
