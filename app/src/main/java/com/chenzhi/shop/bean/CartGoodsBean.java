package com.chenzhi.shop.bean;

import java.util.List;

/**
 * Created by chenzhi on 2016/7/15 0015.
 * 这是封装的购物车信息
 */
public class CartGoodsBean {

    /**
     * code : 200
     * list : [{"amount":1,"cid":1,"color":0,"color_name":"黑色","config":"8G","cost":3000,"iid":0,"img_url":"/img/test4.jpg","name":"三星（SAMSUNG） Galaxy NOTE N5100 8英寸 四核","second_title":"全网首发","type":-1},{"amount":1,"cid":2,"color":0,"color_name":"白色","config":"16G","cost":3100,"iid":0,"img_url":"/img/test3.jpg","name":"iPhone phone","second_title":"全网首发","type":-1},{"amount":1,"cid":3,"color":0,"color_name":"白色","config":"16G","cost":538,"iid":0,"img_url":"/img/test1.jpg","name":"oppo phone","second_title":"全网首发","type":-1},{"amount":1,"cid":4,"color":0,"color_name":"绿色","config":"16G","cost":5232,"iid":0,"img_url":"/img/test2.jpg","name":"小米4 phone","second_title":"全网首发","type":-1},{"amount":1,"cid":5,"color":0,"color_name":"黑色","config":"16G","cost":4322,"iid":0,"name":"海尔 phone","second_title":"全网首发","type":-1}]
     * msg : 获取成功
     */

    private int code;
    private String msg;

    /**
     * amount : 1
     * cid : 1
     * color : 0
     * color_name : 黑色
     * config : 8G
     * cost : 3000
     * iid : 0
     * img_url : /img/test4.jpg
     * name : 三星（SAMSUNG） Galaxy NOTE N5100 8英寸 四核
     * second_title : 全网首发
     * type : -1
     */

    private List<ListBean> list;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        private int amount;
        private int cid;
        private int color;
        private String color_name;
        private String config;
        private int cost;
        private int iid;
        private String img_url;
        private String name;
        private String second_title;
        private int type;

        private boolean state;

        public boolean isState() {
            return state;
        }

        public void setState(boolean state) {
            this.state = state;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getCid() {
            return cid;
        }

        public void setCid(int cid) {
            this.cid = cid;
        }

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }

        public String getColor_name() {
            return color_name;
        }

        public void setColor_name(String color_name) {
            this.color_name = color_name;
        }

        public String getConfig() {
            return config;
        }

        public void setConfig(String config) {
            this.config = config;
        }

        public int getCost() {
            return cost;
        }

        public void setCost(int cost) {
            this.cost = cost;
        }

        public int getIid() {
            return iid;
        }

        public void setIid(int iid) {
            this.iid = iid;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSecond_title() {
            return second_title;
        }

        public void setSecond_title(String second_title) {
            this.second_title = second_title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
