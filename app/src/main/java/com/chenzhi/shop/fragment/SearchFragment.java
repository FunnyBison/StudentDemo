package com.chenzhi.shop.fragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.chenzhi.shop.R;
import com.chenzhi.shop.activity.SearchListActivity;
import com.chenzhi.shop.bean.SearchGoodsBean;
import com.chenzhi.shop.net.HttpUrl;
import com.chenzhi.shop.ui.KeywordsFlow;
import com.chenzhi.shop.ui.LoginEditText;
import com.chenzhi.shop.utils.GsonUtils;
import com.okhttplib.CacheLevel;
import com.okhttplib.CallbackOk;
import com.okhttplib.HttpInfo;
import com.okhttplib.OkHttpUtil;

import java.io.IOException;
import java.util.Random;

/**
 * Created by chenzhi on 2016/7/7 0007.
 */
public class SearchFragment  extends BaseFragment  implements View.OnClickListener {
    private View view;

    public String[] keywords = {
            "QQ", "Sodino", "APK", "GFW", "铅笔",
            "短信", "桌面精灵", "MacBook Pro", "平板电脑", "雅诗兰黛",
            "卡西欧 TR-100", "笔记本", "SPY Mouse", "Thinkpad E40", "捕鱼达人",
            "内存清理", "地图", "导航", "闹钟", "主题",
            "通讯录", "播放器", "CSDN leak", "安全", "3D",
            "美女", "天气", "4743G", "戴尔", "联想",
            "欧朋", "浏览器", "愤怒的小鸟", "mmShow", "网易公开课",
            "iciba", "油水关系", "网游App", "互联网", "365日历",
            "脸部识别", "Chrome", "Safari", "中国版Siri", "A5处理器",
            "iPhone4S", "摩托 ME525", "魅族 M9", "尼康 S2500"
    };
    private KeywordsFlow keywordsFlow;
    private Button changed;
    private LoginEditText search_text;
    private ImageButton search_btn;

    @Override
    public View getView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.frag_search,null);
        return view;
    }

    /**
     * 找控件
     */
    @Override
    public void findView() {
        keywordsFlow = (KeywordsFlow) view.findViewById(R.id.keywordsflow);
        changed = (Button) view.findViewById(R.id.changed);
        //搜索输入框
        search_text = (LoginEditText) view.findViewById(R.id.search_text);
        //搜索按钮
        search_btn = (ImageButton) view.findViewById(R.id.search_btn);

    }

    @Override
    public void initData() {
        keywordsFlow.setDuration(800l);
        keywordsFlow.setOnItemClickListener(this);//设置点击事件
        changed.setOnClickListener(this);
        // 添加
        feedKeywordsFlow(keywordsFlow, keywords);
        keywordsFlow.go2Show(KeywordsFlow.ANIMATION_IN);
    }

    @Override
    public void setData() {

    }

    @Override
    public void setListener() {
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String trim = search_text.getText().toString().trim();
                if (!trim.equals("")){
                    getData(trim);
                }else{
                    Toast.makeText(getActivity(), "请输入您要搜索的商品名称", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == changed) {
            keywordsFlow.rubKeywords();
            feedKeywordsFlow(keywordsFlow, keywords);
            keywordsFlow.go2Show(KeywordsFlow.ANIMATION_OUT);
        } else if (v instanceof TextView) {
            String keyword = ((TextView) v).getText().toString();
            Toast.makeText(getActivity(), "你点击的是"+keyword, Toast.LENGTH_SHORT).show();
        }
    }
    private static void feedKeywordsFlow(KeywordsFlow keywordsFlow, String[] arr) {
        Random random = new Random();
        for (int i = 0; i < KeywordsFlow.MAX; i++) {
            int ran = random.nextInt(arr.length);
            String tmp = arr[ran];
            keywordsFlow.feedKeyword(tmp);
        }
    }

    /**
     * 定义请求数据的方法
     */
    private void getData(String str) {
        OkHttpUtil.Builder().setCacheLevel(CacheLevel.FIRST_LEVEL).setConnectTimeout(25).build(this).doGetAsync(
                HttpInfo.Builder().setUrl(HttpUrl.searchUrl+str).build(),
                new CallbackOk() {
                    @Override
                    public void onResponse(HttpInfo info) throws IOException {
                        try {
                            if (info.isSuccessful()) {
                                String result = info.getRetDetail();//获取数据
                                SearchGoodsBean searchGoodsBean = GsonUtils.jsonToObject(result, SearchGoodsBean.class);

                                Intent intent = new Intent(getActivity(), SearchListActivity.class);
                                intent.putExtra("search",searchGoodsBean);
                                startActivity(intent);
                            } else {
                                Toast.makeText(getActivity(), "请求网络失败", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
