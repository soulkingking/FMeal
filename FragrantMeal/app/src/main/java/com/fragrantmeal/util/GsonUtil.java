package com.fragrantmeal.util;

import com.google.gson.Gson;

import java.lang.reflect.Type;


/**
 * json数据解析工具类
 */
public class GsonUtil {

    private static Gson gson = new Gson();

    public static String toJson(Object obj, Type type) {
        return gson.toJson(obj, type);
    }

    public static Object fromJson(String str,Type type){
        return gson.fromJson(str, type);
    }

    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }
}  
