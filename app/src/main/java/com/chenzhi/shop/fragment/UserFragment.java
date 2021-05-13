package com.chenzhi.shop.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.chenzhi.shop.R;
import com.chenzhi.shop.activity.LoginActivity;

/**
 * Created by chenzhi on 2016/7/7 0007.
 */
public class UserFragment extends BaseFragment{

    private View view;
    private Button loginButton;
    private ImageView icon;
    private ImageView changed_icon;
    private TextView username;
    private TextView userlevel;

    @Override
    public View getView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.frag_user,null);
        return view;
    }

    @Override
    public void findView() {
        loginButton = (Button) view.findViewById(R.id.userLogin);//登录按钮
        //用户头像
        icon = (ImageView) view.findViewById(R.id.user_frag_icon);
        //更改用户头像
        changed_icon = (ImageView) view.findViewById(R.id.changed_icon);
        //用户名
        username = (TextView) view.findViewById(R.id.username);
        //用户等级
        userlevel = (TextView) view.findViewById(R.id.userlevel);
    }

    @Override
    public void initData() {
        SharedPreferences sp = getActivity().getSharedPreferences("userLogin", Context.MODE_PRIVATE);
        boolean auto = sp.getBoolean("auto", false);
        String name = sp.getString("name", "");
        if (auto||!name.equals("")){
            setData(name);
        }
    }

    @Override
    public void setData() {
        //跳转到用户登录界面
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });
    }

    @Override
    public void setListener() {

    }

    private void setData(String name){

        loginButton.setVisibility(View.GONE);

        icon.setVisibility(View.VISIBLE);
        changed_icon.setVisibility(View.VISIBLE);
        username.setVisibility(View.VISIBLE);
        userlevel.setVisibility(View.VISIBLE);
        if (!name.equals("")){
            username.setText(name);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sp = getActivity().getSharedPreferences("userLogin", Context.MODE_PRIVATE);
        boolean auto = sp.getBoolean("auto", false);
        String name = sp.getString("name", "");
        if (auto||!name.equals("")){
            setData(name);
        }
    }
}
