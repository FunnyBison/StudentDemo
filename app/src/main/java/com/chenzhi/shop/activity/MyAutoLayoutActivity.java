package com.chenzhi.shop.activity;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.View;

import com.chenantao.autolayout.AutoCardView;
import com.chenantao.autolayout.AutoFrameLayout;
import com.chenantao.autolayout.AutoLinearLayout;
import com.chenantao.autolayout.AutoRelativeLayout;
import com.chenzhi.shop.utils.StyleUtils;

/**
 * Created by chenzhi on 2016/7/7 0007.
 *
 * 这是所有Activity的父类
 */
public class MyAutoLayoutActivity extends FragmentActivity {
    private static final String LAYOUT_LINEARLAYOUT = "LinearLayout";
    private static final String LAYOUT_FRAMELAYOUT = "FrameLayout";
    private static final String LAYOUT_RELATIVELAYOUT = "RelativeLayout";

    private static final String CARD_VIEW = "android.support.v7.widget.CardView";

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs)
    {
        StyleUtils.initSystemBar(this);
        //设置状态栏是否沉浸
        StyleUtils.setStyle(this);

        View view = null;
        if (name.equals(LAYOUT_FRAMELAYOUT))
        {
            view = new AutoFrameLayout(context, attrs);
        }
        if (name.equals(LAYOUT_LINEARLAYOUT))
        {
            view = new AutoLinearLayout(context, attrs);
        }
        if (name.equals(LAYOUT_RELATIVELAYOUT))
        {
            view = new AutoRelativeLayout(context, attrs);
        }
        if (name.equals(CARD_VIEW))
        {
            view = new AutoCardView(context, attrs);
        }
        if (view != null) return view;
        return super.onCreateView(name, context, attrs);
    }

}

