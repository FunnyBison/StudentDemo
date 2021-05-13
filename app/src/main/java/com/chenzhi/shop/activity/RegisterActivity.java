package com.chenzhi.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.chenzhi.shop.R;
import com.chenzhi.shop.net.HttpUrl;
import com.chenzhi.shop.ui.LoginEditText;
import com.okhttplib.CacheLevel;
import com.okhttplib.CallbackOk;
import com.okhttplib.HttpInfo;
import com.okhttplib.OkHttpUtil;

import org.json.JSONObject;

import java.io.IOException;

public class RegisterActivity extends MyAutoLayoutActivity{


    private LoginEditText name;
    private LoginEditText phone;
    private LoginEditText pass;
    private LoginEditText passAgain;
    private CheckBox agree;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        find();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String names = name.getText().toString().trim();
                String phones = phone.getText().toString().trim();
                String passw = pass.getText().toString().trim();
                String agaginpass = passAgain.getText().toString().trim();

                if (names.equals("")){
                    Toast.makeText(RegisterActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
                }else if (phones.equals("")){
                    Toast.makeText(RegisterActivity.this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                }else if (passw.equals("")){
                    Toast.makeText(RegisterActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                }else if (agaginpass.equals("")){
                    Toast.makeText(RegisterActivity.this, "请输入确认密码", Toast.LENGTH_SHORT).show();
                }else if(!passw.equals(agaginpass)){
                    Toast.makeText(RegisterActivity.this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
                }else{
                    getData(names,passw,phones);
                }

            }
        });

    }
    /**
     * 定义找控件的方法
     */
    private void find(){
        TextView title = (TextView) findViewById(R.id.inTitle);//标题
        title.setText("普通用户注册");//设置标题

        //用户名
        name = (LoginEditText) findViewById(R.id.regist_name);
        //手机号
        phone = (LoginEditText) findViewById(R.id.regist_phone);
        //密码
        pass = (LoginEditText) findViewById(R.id.regist_pwd);
        //确认密码
        passAgain = (LoginEditText) findViewById(R.id.regist_again_pwd);
        //同意条款
        agree = (CheckBox) findViewById(R.id.regist_agree);
        //提交
        submit = (Button) findViewById(R.id.regist_submit);
    }
    /**
     * 定义请求数据的方法
     */
    private void getData(final String name, final String pass,String phones) {
        OkHttpUtil.Builder().setCacheLevel(CacheLevel.FIRST_LEVEL).setConnectTimeout(25).build(this).doGetAsync(
                HttpInfo.Builder().setUrl(HttpUrl.registerUrl + "username=" + name + "&password=" + pass+"&phone="+phones).build(),
                new CallbackOk() {
                    @Override
                    public void onResponse(HttpInfo info) throws IOException {
                        try {
                            if (info.isSuccessful()) {
                                String result = info.getRetDetail();//获取数据
                                JSONObject jsonObject = new JSONObject(result);
                                int code = jsonObject.getInt("code");
                                if (code==200){
                                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                    intent.putExtra("name",name);
                                    intent.putExtra("pass",pass);
                                    setResult(2002,intent);
                                    finish();
                                }else{
                                    Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(RegisterActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
