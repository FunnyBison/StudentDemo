package com.chenzhi.shop.fragment;

import android.graphics.Color;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.chenzhi.shop.R;
import com.chenzhi.shop.adapter.ExpandableAdapter;
import com.chenzhi.shop.net.HttpUrl;
import com.okhttplib.CacheLevel;
import com.okhttplib.CallbackOk;
import com.okhttplib.HttpInfo;
import com.okhttplib.OkHttpUtil;

import java.io.IOException;

import jp.co.recruit_lifestyle.android.widget.WaveSwipeRefreshLayout;

/**
 * Created by chenzhi on 2016/7/7 0007.
 */
public class SortFragment extends BaseFragment  implements WaveSwipeRefreshLayout.OnRefreshListener{

    private View view;
    private WaveSwipeRefreshLayout main_swipe;

    @Override
    public View getView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.frag_sort,null);
        return view;
    }

    @Override
    public void findView() {
        TextView title = (TextView) view.findViewById(R.id.inTitle);
        title.setText("分类");

        ExpandableListView ev = (ExpandableListView) view.findViewById(R.id.sort_list);
        ExpandableAdapter adapter = new ExpandableAdapter(getActivity());
        ev.setGroupIndicator(null);
        ev.setAdapter(adapter);

        main_swipe = (WaveSwipeRefreshLayout) view.findViewById(R.id.main_swipes);
        main_swipe.setWaveColor(Color.argb(100,255,0,0));
        main_swipe.setOnRefreshListener(this);
        main_swipe.setColorSchemeColors(Color.WHITE, Color.WHITE);
    }

    @Override
    public void initData() {

    }

    @Override
    public void setData() {

    }

    @Override
    public void setListener() {

    }
    /**
     * 定义请求数据的方法
     */
    private void getData() {
        OkHttpUtil.Builder().setCacheLevel(CacheLevel.FIRST_LEVEL).setConnectTimeout(25).build(this).doGetAsync(
                HttpInfo.Builder().setUrl(HttpUrl.sortUrl).build(),
                new CallbackOk() {
                    @Override
                    public void onResponse(HttpInfo info) throws IOException {
                        try {
                            if (info.isSuccessful()) {
                                String result = info.getRetDetail();//获取数据

                            } else {
                                Toast.makeText(getActivity(), "请求网络失败", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
    /**
     * 刷新
     */
    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 更新が終了したらインジケータ非表示
                main_swipe.setRefreshing(false);
                Toast.makeText(getActivity(), "已经是最新数据了哦", Toast.LENGTH_SHORT).show();
            }
        }, 3000);
    }
}
