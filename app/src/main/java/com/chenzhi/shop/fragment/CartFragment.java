package com.chenzhi.shop.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.chenzhi.shop.R;
import com.chenzhi.shop.adapter.CartAdapter;
import com.chenzhi.shop.bean.CartGoodsBean;
import com.chenzhi.shop.net.HttpUrl;
import com.chenzhi.shop.utils.GsonUtils;
import com.okhttplib.CacheLevel;
import com.okhttplib.CallbackOk;
import com.okhttplib.HttpInfo;
import com.okhttplib.OkHttpUtil;

import java.io.IOException;
import java.util.List;

/**
 * Created by chenzhi on 2016/7/7 0007.
 */
public class CartFragment extends BaseFragment {

    private View view;
    private ListView listView;
    private List<CartGoodsBean.ListBean> list;
    public TextView id_tv_totalPrice;
    public CheckBox id_cb_select_all;
    private CartAdapter adapter;
    public Button id_tv_totalCount_jiesuan;
    private ImageView cart_empty;
    private TextView tv_cart;

    @Override
    public View getView(LayoutInflater inflater) {

        view = inflater.inflate(R.layout.frag_cart, null);
        return view;
    }

    @Override
    public void findView() {
        TextView title = (TextView) view.findViewById(R.id.inTitle);
        Button deleteButton = (Button) view.findViewById(R.id.del_btn);
        deleteButton.setVisibility(View.VISIBLE);
        title.setText("购物车");

        listView = (ListView) view.findViewById(R.id.list_cart);

        //合计价格
        id_tv_totalPrice = (TextView) view.findViewById(R.id.id_tv_totalPrice);
        //全选按钮
        id_cb_select_all = (CheckBox) view.findViewById(R.id.id_cb_select_all);
        //结算
        id_tv_totalCount_jiesuan = (Button) view.findViewById(R.id.id_tv_totalCount_jiesuan);

        cart_empty = (ImageView) view.findViewById(R.id.cart_empty);
        tv_cart = (TextView) view.findViewById(R.id.cart_empty_tv);

        SharedPreferences sp = getActivity().getSharedPreferences("userLogin", Context.MODE_PRIVATE);
        boolean auto = sp.getBoolean("auto", false);
        String name = sp.getString("name", "");
        if (!auto||name.equals("")){

            listView.setVisibility(View.GONE);

            cart_empty.setVisibility(View.VISIBLE);
            tv_cart.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void initData() {
        getData();//获取购物车商品信息
    }

    @Override
    public void setData() {


    }

    @Override
    public void setListener() {
        /**
         * 全选or全不选
         */
        id_cb_select_all.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // 全选
                    for (int i = 0; i < list.size(); i++) {
                        CartGoodsBean.ListBean goods = list.get(i);

                        goods.setState(true);

                    }
                    // 更新adapter
                    adapter.setSum(0);
                    adapter.notifyDataSetChanged();
                    id_cb_select_all.setText("全不选");
                } else {
                    // 全不选
                    for (int i = 0; i < list.size(); i++) {
                        CartGoodsBean.ListBean goods = list.get(i);

                        goods.setState(false);
                    }
                    //更新adapter
                    adapter.setSum(0);
                    adapter.notifyDataSetChanged();
                    id_cb_select_all.setText("全选");
                }
            }
        });
    }

    /**
     * 定义请求数据的方法
     */
    private void getData() {
        OkHttpUtil.Builder().setCacheLevel(CacheLevel.FIRST_LEVEL).setConnectTimeout(25).build(this).doGetAsync(
                HttpInfo.Builder().setUrl(HttpUrl.cartUrl).build(),
                new CallbackOk() {
                    @Override
                    public void onResponse(HttpInfo info) throws IOException {
                        if (info.isSuccessful()) {
                            String result = info.getRetDetail();//获取数据
                            CartGoodsBean cartGoodsBean = GsonUtils.jsonToObject(result, CartGoodsBean.class);
                            list = cartGoodsBean.getList();
                            listView.setAdapter(adapter = new CartAdapter(getActivity(), list));
                        }
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sp = getActivity().getSharedPreferences("userLogin", Context.MODE_PRIVATE);
        boolean auto = sp.getBoolean("auto", false);
        String name = sp.getString("name", "");
        if (auto||!name.equals("")){
            getData();//获取购物车商品信息
            listView.setVisibility(View.VISIBLE);

            cart_empty.setVisibility(View.GONE);
            tv_cart.setVisibility(View.GONE);
        }
    }
}
