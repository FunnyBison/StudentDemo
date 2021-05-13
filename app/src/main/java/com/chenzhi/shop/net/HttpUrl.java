package com.chenzhi.shop.net;

/**
 * Created by chenzhi on 2016/7/14 0014.
 */
public class HttpUrl {

    /**
     * 登录接口地址
     * 参数：username=chenzhi&password=123
     */
    public static String loginUrl="http://169.254.200.231:8080/ShopServer/myServlet?reqcode=101&";

    /**
     * 注册接口地址
     * 参数：username=chenzhi&password=1234&phone=15201320029
     */
    public static String registerUrl="http://169.254.200.231:8080/ShopServer/myServlet?reqcode=102&";

    /**
     * 分类接口地址
     */
    public static String sortUrl="http://169.254.200.231:8080/ShopServer/myServlet?reqcode=901";

    /**
     * 购物车接口
     */
    public static String cartUrl="http://169.254.200.231:8080/ShopServer/myServlet?reqcode=304&uid=1";

    /**
     * 搜索商品接口地址
     * type:2关键字
     * keywords：关键词
     */
    public static String searchUrl="http://169.254.200.231:8080/ShopServer/myServlet?reqcode=202&type=2&keywords=";
}
