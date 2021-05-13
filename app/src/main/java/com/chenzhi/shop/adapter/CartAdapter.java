package com.chenzhi.shop.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chenzhi.shop.R;
import com.chenzhi.shop.activity.HomeActivity;
import com.chenzhi.shop.bean.CartGoodsBean;

import java.util.List;

/**
 * Created by chenzhi on 2016/7/14 0014.
 */
public class CartAdapter extends BaseAdapter {

    private Context context;
    private List<CartGoodsBean.ListBean> list;
    private int sum;
    private int count=1;
    private int price;
    private int sums;

    public void setSum(int sum) {
        this.sum = sum;
    }

    public CartAdapter(Context context, List<CartGoodsBean.ListBean> list) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        View view=null;
        if (convertView==null){
            view= View.inflate(context,R.layout.child_layout,null);//找到布局文件
            holder=new ViewHolder();
            //找控件
            holder.goodsPic= (ImageView) view.findViewById(R.id.id_iv_logo);
            holder.add= (ImageView) view.findViewById(R.id.iv_add);
            holder.elect_child= (CheckBox) view.findViewById(R.id.id_cb_select_child);
            holder.reduce= (ImageView) view.findViewById(R.id.reduce);
            holder.goodsname= (TextView) view.findViewById(R.id.tv_items_child);
            holder.price= (TextView) view.findViewById(R.id.id_tv_discount_price);
            holder.oldprice= (TextView) view.findViewById(R.id.id_tv_price);
            holder.info= (TextView) view.findViewById(R.id.tv_items_child_desc);
            holder.num= (TextView) view.findViewById(R.id.goodsnum);

            view.setTag(holder);

        }else{
            view=convertView;

            holder = (ViewHolder) view.getTag();
        }



        //获取商品价格
        final int price1=list.get(position).getCost();

        HomeActivity activity= (HomeActivity) context;
        final TextView id_tv_totalPrice = activity.cartFrag.id_tv_totalPrice;
        final Button id_tv_totalCount_jiesuan = activity.cartFrag.id_tv_totalCount_jiesuan;


        price=price1;
        if (holder.elect_child.isChecked()){
            sum+=price*list.get(position).getAmount();
        }
        id_tv_totalPrice.setText("合计：￥"+sum+"元");
        /*
		 * 合计核心算法
		 */
        //为多选框添加监听

        holder.elect_child.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //得到原来的合计价格
                price=price1;
                if (isChecked){
                    list.get(position).setState(true);
                    sum+=price*list.get(position).getAmount();
                }else{
                    list.get(position).setState(false);
                    sum-=price*list.get(position).getAmount();
                }
                id_tv_totalPrice.setText("合计:"+sum+"元");
            }
        });

        /**
         * 商品加
         */
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count=list.get(position).getAmount();
                count++;
                list.get(position).setAmount(count);
                //count++;
                holder.num.setText(list.get(position).getAmount()+"");
                id_tv_totalPrice.setText("合计:"+sum+"元");
            }
        });
        /**
         * 商品减
         */
        holder.reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count>1){
                    count=list.get(position).getAmount();
                    count--;
                    list.get(position).setAmount(count);
                    holder.num.setText(list.get(position).getAmount()+"");
                    id_tv_totalPrice.setText("合计:"+sum+"元");
                }else{
                    Toast.makeText(context, "至少选择一件商品哦", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //为控件赋值
        Glide.with(context).
                load("http://169.254.200.231:8080/ShopServer"+list.get(position).
                        getImg_url()).placeholder(R.mipmap.dud).
                into(holder.goodsPic);
        holder.goodsname.setText(list.get(position).getName());
        holder.info.setText(list.get(position).getColor_name()+"   "+list.get(position).getConfig());
        holder.price.setText("现价：￥"+list.get(position).getCost());
        holder.oldprice.setText("原价：￥"+list.get(position).getCost());
        holder.oldprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);//添加删除线
        holder.num.setText(list.get(position).getAmount()+"");

        holder.elect_child.setChecked(list.get(position).isState());

        /**
         * 结算按钮
         */
        id_tv_totalCount_jiesuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sum<=0){
                    Toast.makeText(context, "您还没有选择商品哦", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "商品总价" + sum, Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    class ViewHolder{
        ImageView goodsPic;//商品图片
        ImageView add;//加号
        ImageView reduce;//减号
        CheckBox elect_child;//选择
        TextView goodsname;//商品名称
        TextView price;//商品价格
        TextView oldprice;//旧价格
        TextView info;//商品规格
        TextView num;//商品数量

    }
}
