package com.example.a1.appwelcome170523;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by ljt on 2017/5/23.
 *  所有的Activity都必须继承这个类，实现以下三个抽象方法
 * initVariables()  初始化数据变量
 * initViews(saveDInstanceState)  初始化视图变量
 * loadData();  加载数据
 */

public abstract class BaseActivity extends Activity {

    /*
    * 有个小坑，注意onCreate()中是一个参数，如果你调两个参数的方法，
    * 程序不会报错，但是会灰屏，你看不到想要的效果。
    *
    * */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("aaa------>>>>>>>>>>");
        initVariables();
        initViews(savedInstanceState);
        loadData();
        System.out.println("aaa------>>>>>>>>>>B22a222");
    }
    /*
    * 初始化变量
    * */
    protected  abstract  void initVariables();

    /*
    * 初始化控件
    * */
    protected  abstract  void initViews(Bundle saveInstanceState);

    /*
    * 动态加载数据（如网络接口拿下来的数据）
    * */
    protected  abstract  void loadData();


}
