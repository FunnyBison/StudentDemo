package com.chenzhi.shop.utils;

import com.google.gson.Gson;

/**
 * 饿汉式
 */
public class GsonUtils {


//    私有化静态，声明本类对象
    private static GsonUtils instance = null;

//    私有化构造函数
    private GsonUtils(){

    }

    public static GsonUtils getInstance(){
        return instance;
    }

    public static <T> T jsonToObject(String json,Class<T> t){
        if (json == null){
            return null;
        }
        return new Gson().fromJson(json,t);
    }

}
