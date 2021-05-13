package com.chenzhi.shop.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.chenzhi.shop.R;
import com.chenzhi.shop.net.HttpUrl;
import com.chenzhi.shop.ui.LoginEditText;
import com.chenzhi.shop.ui.RoundImageView;
import com.okhttplib.CacheLevel;
import com.okhttplib.CallbackOk;
import com.okhttplib.HttpInfo;
import com.okhttplib.OkHttpUtil;

import org.json.JSONObject;

import java.io.IOException;


public class LoginActivity extends MyAutoLayoutActivity {

    private LoginEditText username;
    private LoginEditText password;
    private CheckBox autoLogin;
    private Button login;
    private TextView findPsw;
    private RoundImageView userIcon;
    private String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //找控件
        find();

        //设置监听
        setListener();
    }

    /**
     * 定义设置监听的方法
     */
    private void setListener() {

        //用户登录
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String name = username.getText().toString().trim();
                    String pass = password.getText().toString().trim();

                    if (name.equals("")) {
                        Toast.makeText(LoginActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                    } else if (pass.equals("")) {
                        Toast.makeText(LoginActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    } else {
                        boolean checked = autoLogin.isChecked();
                        getData(name,pass);
                        SharedPreferences sp = getSharedPreferences("userLogin", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("name",name);
                        editor.putBoolean("auto",checked);
                        editor.commit();
                        finish();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 定义请求数据的方法
     */
    private void getData(final String name, String pass) {
        OkHttpUtil.Builder().setCacheLevel(CacheLevel.FIRST_LEVEL).setConnectTimeout(25).build(this).doGetAsync(
                HttpInfo.Builder().setUrl(HttpUrl.loginUrl + "username=" + name + "&password=" + pass).build(),
                new CallbackOk() {
                    @Override
                    public void onResponse(HttpInfo info) throws IOException {
                        try {
                            if (info.isSuccessful()) {
                                result = info.getRetDetail();//获取数据
                                JSONObject jsonObject = new JSONObject(result);
                                int code = jsonObject.getInt("code");
                                if (code==200){
                                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(LoginActivity.this, "不存在的用户", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    /**
     * 跳转到注册界面
     */
    public void register(View view) {
        startActivityForResult(new Intent(LoginActivity.this, RegisterActivity.class),2002);
    }

    /**
     * 定义找控件的方法
     */
    private void find() {
        TextView title = (TextView) findViewById(R.id.inTitle);//标题
        title.setText("登录");//设置标题
        //用户头像
        userIcon = (RoundImageView) findViewById(R.id.userIcon);
        //用户名
        username = (LoginEditText) findViewById(R.id.login_username);
        //密码
        password = (LoginEditText) findViewById(R.id.login_password);
        //自动登录
        autoLogin = (CheckBox) findViewById(R.id.auto_login);
        //登录
        login = (Button) findViewById(R.id.login);
        //找回密码
        findPsw = (TextView) findViewById(R.id.find_password);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==2002&&data!=null){
            String name = data.getStringExtra("name");
            String pass = data.getStringExtra("pass");
            username.setText(name);
            password.setText(pass);
        }
    }
}
