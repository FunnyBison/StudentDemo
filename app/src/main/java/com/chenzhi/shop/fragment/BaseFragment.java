package com.chenzhi.shop.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

;

/**
 * Created by chenzhi on 2016/7/9.
 */
public abstract class BaseFragment extends Fragment {

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = getView(inflater);

        findView();

        initData();

        setData();

        setListener();

        return view;
    }


    /**
     * 抽象类的接口
     * @param inflater
     * @return
     */
    public abstract View getView(LayoutInflater inflater);
    /**
     * 找控件
     */
    public abstract void findView();
    /**
     * 准备数据
     */
    public abstract void initData();
    /**
     * 设置数据
     */
    public abstract void setData();
    /**
     * 设置监听
     */
    public abstract void setListener();
}
