package com.chenzhi.shop.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by chenzhi on 2016/7/16 0016.
 */
public class SearchGoodsBean implements Serializable{

    /**
     * list : [{"img_url":"/img/test4.jpg","others":[{"color":1,"color_name":"黑色","config":"8G","cid":1}],"config_des":"8G WCDMA","iid":1,"color_name":"黑色","describ":"/img/info1.png","type":0,"cid":1,"cost":3000,"amount":50,"color":1,"name":"三星（SAMSUNG） Galaxy NOTE N5100 8英寸 四核","config":"8G","second_title":"全网首发"}]
     * code : 200
     * msg : 查询成功
     */

    private int code;
    private String msg;
    /**
     * img_url : /img/test4.jpg
     * others : [{"color":1,"color_name":"黑色","config":"8G","cid":1}]
     * config_des : 8G WCDMA
     * iid : 1
     * color_name : 黑色
     * describ : /img/info1.png
     * type : 0
     * cid : 1
     * cost : 3000
     * amount : 50
     * color : 1
     * name : 三星（SAMSUNG） Galaxy NOTE N5100 8英寸 四核
     * config : 8G
     * second_title : 全网首发
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

    public static class ListBean  implements Serializable{
        private String img_url;
        private String config_des;
        private int iid;
        private String color_name;
        private String describ;
        private int type;
        private int cid;
        private int cost;
        private int amount;
        private int color;
        private String name;
        private String config;
        private String second_title;
        /**
         * color : 1
         * color_name : 黑色
         * config : 8G
         * cid : 1
         */

        private List<OthersBean> others;

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public String getConfig_des() {
            return config_des;
        }

        public void setConfig_des(String config_des) {
            this.config_des = config_des;
        }

        public int getIid() {
            return iid;
        }

        public void setIid(int iid) {
            this.iid = iid;
        }

        public String getColor_name() {
            return color_name;
        }

        public void setColor_name(String color_name) {
            this.color_name = color_name;
        }

        public String getDescrib() {
            return describ;
        }

        public void setDescrib(String describ) {
            this.describ = describ;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getCid() {
            return cid;
        }

        public void setCid(int cid) {
            this.cid = cid;
        }

        public int getCost() {
            return cost;
        }

        public void setCost(int cost) {
            this.cost = cost;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getConfig() {
            return config;
        }

        public void setConfig(String config) {
            this.config = config;
        }

        public String getSecond_title() {
            return second_title;
        }

        public void setSecond_title(String second_title) {
            this.second_title = second_title;
        }

        public List<OthersBean> getOthers() {
            return others;
        }

        public void setOthers(List<OthersBean> others) {
            this.others = others;
        }

        public static class OthersBean  implements Serializable{
            private int color;
            private String color_name;
            private String config;
            private int cid;

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

            public int getCid() {
                return cid;
            }

            public void setCid(int cid) {
                this.cid = cid;
            }
        }
    }
}
