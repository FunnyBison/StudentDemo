package com.chenzhi.shop.fragment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.chenzhi.shop.R;
import com.chenzhi.shop.activity.HomeActivity;
import com.chenzhi.shop.adapter.BannerAdapter;
import com.chenzhi.shop.ui.SnapUpCountDownTimerView;

import java.util.ArrayList;

import jp.co.recruit_lifestyle.android.widget.WaveSwipeRefreshLayout;

/**
 * Created by chenzhi on 2016/7/7 0007.
 */
public class HomeFragment extends BaseFragment implements WaveSwipeRefreshLayout.OnRefreshListener{

    private View view;
    private ViewPager viewPager;
    private LinearLayout linear;
    private ArrayList<ImageView> imageList;
    private SnapUpCountDownTimerView rushBuyCountDownTimerView;
    private WaveSwipeRefreshLayout main_swipe;
    private Button searchButton;
    private int[] ImageUrl = new int[]{
            R.mipmap.image01,
            R.mipmap.image02,
            R.mipmap.image03,
            R.mipmap.image04,
            R.mipmap.image05,
            R.mipmap.image06,
            R.mipmap.image07,
            R.mipmap.image08
    };

    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0) {
                //拿到当前的页面索引值
                int currentItem = viewPager.getCurrentItem();
                currentItem++;
                //设置索引值
                viewPager.setCurrentItem(currentItem);

                handler.sendEmptyMessageDelayed(0, 3000);
            }
        }

        ;
    };

    /**
     * 找控件
     */
    @Override
    public void findView() {
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        linear = (LinearLayout) view.findViewById(R.id.linear);

        //搜索按钮
        searchButton = (Button) view.findViewById(R.id.search_btn);

        TextView text = (TextView) view.findViewById(R.id.text);
        text.setTypeface(Typeface.DEFAULT_BOLD);//设置字体加粗

        rushBuyCountDownTimerView = (SnapUpCountDownTimerView) view.findViewById(R.id.RushBuyCountDownTimerView);
        rushBuyCountDownTimerView.setTime(0, 55, 3);//设置倒计时
        rushBuyCountDownTimerView.start();//开启倒计时

        main_swipe = (WaveSwipeRefreshLayout) view.findViewById(R.id.main_swipes);
        main_swipe.setWaveColor(Color.argb(100,255,0,0));
        main_swipe.setOnRefreshListener(this);
        main_swipe.setColorSchemeColors(Color.WHITE, Color.WHITE);

        TextView text2 = (TextView) view.findViewById(R.id.text2);
        text2.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);//添加删除线

    }

    @Override
    public View getView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.frag_home, null);
        return view;
    }

    @Override
    public void initData() {
        //初始化小圆点
        initDots();
    }

    @Override
    public void setData() {
        //设置适配器
        viewPager.setAdapter(new BannerAdapter(getActivity(), ImageUrl));

        //设置当前页面的一个条目
        viewPager.setCurrentItem(imageList.size() * 3000);

        //为viewPager设置监听
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < imageList.size(); i++) {
                    if (i == position % ImageUrl.length) {
                        imageList.get(i).setImageResource(R.drawable.share_dots);
                    } else {
                        imageList.get(i).setImageResource(R.drawable.share_dotss);
                    }
                }
            }
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });

        handler.sendEmptyMessageDelayed(0, 3000);
    }

    @Override
    public void setListener() {
        //跳转到搜索界面
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeActivity homeActivity = (HomeActivity) getActivity();
                //得到Fragment的管理器
                Fragment searchFragment = homeActivity.getSupportFragmentManager().findFragmentByTag("search");
                FragmentTransaction transaction = homeActivity.getSupportFragmentManager().beginTransaction();
                //添加到任务栈
                transaction.show(searchFragment);
                transaction.hide(HomeFragment.this);
                transaction.commit();
            }
        });
    }
    /**
     * 初始化小圆点
     */
    private void initDots() {
        imageList = new ArrayList<ImageView>();
        imageList.clear();//清空集合中的元素
        linear.removeAllViews();//移除布局中的控件
        for (int i = 0; i < ImageUrl.length; i++) {
            //创建一个ImageView控件
            ImageView imageView = new ImageView(getActivity());
            if (i == 0) {
                //点亮的小圆点
                imageView.setImageResource(R.drawable.share_dots);
            } else {
                //默认的小圆点
                imageView.setImageResource(R.drawable.share_dotss);
            }

            WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);

            int width = wm.getDefaultDisplay().getWidth();//屏幕宽度

            LayoutParams params = new LayoutParams(width / ImageUrl.length, 10);
            params.setMargins(0, 0, 5, 0);
            //将圆点添加到集合中
            imageList.add(imageView);
            //将圆点添加到布局中
            linear.addView(imageView, params);
        }
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
