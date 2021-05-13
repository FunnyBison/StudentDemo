package com.chenzhi.shop.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chenzhi.shop.R;
import com.chenzhi.shop.bean.SearchGoodsBean;

import java.util.List;

/**
 * Created by chenzhi on 2016/7/16 0016.
 */
public class SearchGoodsAdapter extends BaseAdapter{

    private List<SearchGoodsBean.ListBean> list;
    private Context context;

    public SearchGoodsAdapter(Context context, List<SearchGoodsBean.ListBean> list) {
        this.context=context;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=View.inflate(context, R.layout.search_goods,null);
        TextView goodsname = (TextView) view.findViewById(R.id.tv_items_child);
        ImageView id_iv_logo = (ImageView) view.findViewById(R.id.id_iv_logo);
        TextView tv_items_child_desc = (TextView) view.findViewById(R.id.tv_items_child_desc);
        TextView price = (TextView) view.findViewById(R.id.id_tv_discount_price);
        TextView oldprice = (TextView) view.findViewById(R.id.id_tv_price);

        Glide.with(context).
                load("http://169.254.200.231:8080/ShopServer"+list.get(position).
                        getImg_url()).placeholder(R.mipmap.dud).
                into(id_iv_logo);

        goodsname.setText(list.get(position).getName());
        tv_items_child_desc.setText(list.get(position).getColor_name()+"  "+list.get(position).getConfig()+"  "+list.get(position).getConfig_des());
        price.setText("现价：￥"+list.get(position).getCost());
        oldprice.setText("原价：￥"+list.get(position).getCost());
        oldprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);//添加删除线
        return view;
    }
}
