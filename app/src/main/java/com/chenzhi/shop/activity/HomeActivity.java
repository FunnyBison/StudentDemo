package com.chenzhi.shop.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.chenzhi.shop.R;
import com.chenzhi.shop.fragment.CartFragment;
import com.chenzhi.shop.fragment.HomeFragment;
import com.chenzhi.shop.fragment.SearchFragment;
import com.chenzhi.shop.fragment.SortFragment;
import com.chenzhi.shop.fragment.UserFragment;

public class HomeActivity extends MyAutoLayoutActivity {
    private FragmentManager manager;
    private HomeFragment homeFrag;
    private SearchFragment searchFrag;
    private SortFragment sortFrag;
    public CartFragment cartFrag;
    public UserFragment userFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //这是我修改的信息
        //柏亮嘎嘎嘎

        RadioGroup group = (RadioGroup) findViewById(R.id.group);

        //获取FragmentManager对象
        manager = getSupportFragmentManager();
        //开启事务
        FragmentTransaction transaction = manager.beginTransaction();
        //加载fragment
        transaction.add(R.id.content, homeFrag = new HomeFragment(), "home");
        transaction.add(R.id.content, searchFrag = new SearchFragment(), "search");
        transaction.add(R.id.content, sortFrag = new SortFragment(), "sort");
        transaction.add(R.id.content, cartFrag = new CartFragment(), "cart");
        transaction.add(R.id.content, userFrag = new UserFragment(), "user");

        transaction.hide(searchFrag);
        transaction.hide(sortFrag);
        transaction.hide(cartFrag);
        transaction.hide(userFrag);
        //提交事务
        transaction.commit();

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.home:
                        switchTag("home");//显示首页页面
                        break;
                    case R.id.search:
                        switchTag("search");//显示搜索页面
                        //startActivity(new Intent(HomeActivity.this, CloudTags.class));
                        break;
                    case R.id.sort:
                        switchTag("sort");//显示分类页面
                        break;
                    case R.id.cart:
                        switchTag("cart");//显示购物车页面
                        break;
                    case R.id.user:
                        switchTag("user");//显示个人中心页面
                        break;
                }
            }
        });


    }

    /**
     * 切换fragment页面
     * 隐藏/显示
     * 相比replace而言：页面切换更流畅
     *
     * @param tag
     */
    private void switchTag(String tag) {
        //开启事务
        FragmentTransaction transaction = manager.beginTransaction();
        if ("home".equals(tag)) {
            transaction.show(homeFrag);//显示

            transaction.hide(searchFrag);//隐藏
            transaction.hide(sortFrag);
            transaction.hide(cartFrag);
            transaction.hide(userFrag);
        } else if ("search".equals(tag)) {
            transaction.show(searchFrag);//显示

            transaction.hide(homeFrag);//隐藏
            transaction.hide(sortFrag);
            transaction.hide(cartFrag);
            transaction.hide(userFrag);
        } else if ("sort".equals(tag)) {
            transaction.show(sortFrag);//显示

            transaction.hide(searchFrag);//隐藏
            transaction.hide(homeFrag);
            transaction.hide(cartFrag);
            transaction.hide(userFrag);
        } else if ("cart".equals(tag)) {
            transaction.show(cartFrag);//显示

            transaction.hide(searchFrag);//隐藏
            transaction.hide(homeFrag);
            transaction.hide(sortFrag);
            transaction.hide(userFrag);
        } else if ("user".equals(tag)) {
            transaction.show(userFrag);//显示

            transaction.hide(searchFrag);//隐藏
            transaction.hide(homeFrag);
            transaction.hide(cartFrag);
            transaction.hide(sortFrag);
        }
        //提交事务
        transaction.commit();
    }

    /**
     * 设置点击退出程序显示吐司提醒，
     * 再一次点击就退出程序的功能
     */

    private int mBackKeyPressedTimes = 0;

    @Override
    public void onBackPressed() {
        if (mBackKeyPressedTimes == 0) {
            Toast.makeText(this, "再按一次返回桌面", Toast.LENGTH_SHORT).show();
            mBackKeyPressedTimes = 1;
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        mBackKeyPressedTimes = 0;
                    }
                }
            }.start();
            return;
        } else {
            this.finish();
        }
        super.onBackPressed();
    }
}
