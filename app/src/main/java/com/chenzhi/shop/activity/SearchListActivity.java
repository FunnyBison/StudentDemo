package com.chenzhi.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.chenzhi.shop.R;
import com.chenzhi.shop.adapter.SearchGoodsAdapter;
import com.chenzhi.shop.bean.SearchGoodsBean;

import java.util.List;

public class SearchListActivity extends MyAutoLayoutActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list);

        //找控件
        find();

        //得到传过来的值
        Intent intent = getIntent();
        SearchGoodsBean search = (SearchGoodsBean) intent.getSerializableExtra("search");
        List<SearchGoodsBean.ListBean> list=search.getList();

        if (list.size()<=0){
            //温馨提示
            Toast.makeText(this, "很抱歉，没有搜索到该商品！", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, list.get(0).getName(), Toast.LENGTH_SHORT).show();
            listView.setAdapter(new SearchGoodsAdapter(this,list));
        }

    }
    /**
     * 定义找控件的方法
     */
    private void find(){
        TextView title = (TextView) findViewById(R.id.inTitle);//标题
        title.setText("搜索结果");//设置标题
        //商品列表
        listView = (ListView) findViewById(R.id.searchList);
    }
}
