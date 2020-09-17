package com.example.learning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioGroup;
import android.widget.ToggleButton;

// AndroidManifest.xml 配置文件介绍
/*

  本质: AndroidManifest.xml是整个应用的主配置清单文件
  包含：该应用的包名、版本号、组件、权限
  作用: 记录该应用的相关的配置信息
 */
/*
  全局篇
  1： 应用的包名和版本信息的管理
      package = "com.example.test"
      android:versionCode = "1"
      android:versionName = "1.0"
  2: 控制android版本信息(可以支持的最低版本、你期望的系统版本)
     android:minSdkVersion = "8"
     android:targetSdkVersion = "16"

  组件篇
    其属性可以设置:
       图标:   android:icon
       标题:   android:label
       主题样式: android:theme
    Activity 页面：启动一个没有在清单中定义的Activity会抛出异常
    Service  启动一个后台服务的处理
    Content Provider 用来管理数据库访问以及程序内和程序间共享的
    Broadcast Receiver 广播接收者里头的intent-filter匹配过滤

  权限篇
    1：使用系统权限
     <uses-permission> 申请权限
     声明了哪些是由你定义的权限，而这些权限是应用程序正常执行所必需的。在安装程序的时，你设定的所有权限将会告诉给用户，
     由他们来决定同意与否。对很多本地Android服务来说，权限都是必需的，特别是那些需要付费或者有安全问题的服务(例如，拨号、接收SMS
     或者有安全问题的服务)

    2：自定义权限
    自定义权限可以自定义命名，来保护android的某些重要组件

 */

 /*
  注意事项
  1.每个组件都必须包含android:name这个属性，推荐用全名称(包名.类名),
    intent-filter(过滤器)可以选写
  2.四大组件中除了BroadcastReceiver可以使用代码声明注册以外，其他组件必须要在Manifest
    文件中进行声明配置，否则会报错
  */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private AutoCompleteTextView acTextView;
    private String[] itemData = { "beijing1", "beijing2", "beijing3", "beijing4", "shanghai5", "shanghai6", "shanghai7", "shanghai8"};


    private MultiAutoCompleteTextView macTextView;

    private Button loginButton;
    private ImageButton imageButton;

    private ToggleButton tb;
    private ImageView img;

    private CheckBox checkBox;

    private RadioGroup rg;
    private RadioGroup rg2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 将布局xml文件引入到activity当中
        setContentView(R.layout.activity_main);

        // AutoCompleteTextView使用
        /*
         * 第一步:初始化控件
         * 第二步:需要一个适配器
         * 第三步:初始化数据源---这数据源去匹配文本框中输入的内容
         * 第四步:将adpter与当前AutoCompleteTextView绑定
         */
        acTextView = findViewById(R.id.autoCompleteTextView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, itemData);
        acTextView.setAdapter(adapter);

        // MultiAutoCompleteTextView使用
        /*
         * 第一步:初始化控件
         * 第二步:需要一个适配器
         * 第三步:初始化数据源---这数据源去匹配文本框中输入的内容
         * 第四步:将adpter与当前MultiAutoCompleteTextView绑定
         * 第五步:设置分隔符
         */
        macTextView = findViewById(R.id.multiAutoCompleteTextView);
        macTextView.setAdapter(adapter);
        macTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());


        /*
         * Button -- 可以设置文本内容一个按钮
         * ImageButton -- 不可以设置文本内容，可以设置background和src
         * 都拥有onclick事件
         * 监听事件实现的几种写法
         * 1.匿名内部类的实现
         * 2.独立类的实现
         * 3.实现接口的方式来实现
         */
        loginButton = findViewById(R.id.button);
        // 通过匿名内部类实现
//        loginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // 在当前onClick方法中监听点击Button的动作
//                System.out.println("我的button被点击了");
//            }
//        });
        // 独立类实现
        loginButton.setOnClickListener(listener);

        // 接口方式实现
        // 1.类 implements View.OnClickListener
        imageButton = findViewById(R.id.imageButton);
        imageButton.setOnClickListener(this);


        tb = findViewById(R.id.toggleButton);
        img = findViewById(R.id.imageView2);
        tb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                tb.setChecked(isChecked);
                img.setBackgroundResource(isChecked?R.mipmap.on:R.mipmap.off);
            }
        });


        checkBox = findViewById(R.id.checkBox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                checkBox.setChecked(isChecked);
                Log.i("tag"," checkBox被点击了"+checkBox.getText().toString());
            }
        });

        rg = findViewById(R.id.radioGroup);
        rg2 = findViewById(R.id.radioGroup2);

        rg2.setOnCheckedChangeListener(groupListener);
        rg.setOnCheckedChangeListener(groupListener);

    }

    // 接口方式实现，点击会回调到这里
    @Override
    public void onClick(View v) {

        Log.i("tag","接口方式：我的imageButton被点击了");
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            System.out.println("独立类实现的监听事件:我的button被点击了");
            Log.i("tag","独立类实现的监听事件:我的button被点击了");
        }
    };

    RadioGroup.OnCheckedChangeListener groupListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

            // 页面跳转
            Intent it = new Intent();

            switch (checkedId) {
                case R.id.radioButton1:
                    Log.i("tag"," RadioGroup的线性布局被点击了"+checkedId);
                    it.setClass(MainActivity.this,layoutActivity.class);
                    MainActivity.this.startActivity(it);
                    break;
                case R.id.radioButton2:
                    Log.i("tag"," RadioGroup的相对布局被点击了"+checkedId);
                    it.setClass(MainActivity.this,relativeActivity.class);
                    MainActivity.this.startActivity(it);
                    break;
                case R.id.radioButton3:
                    Log.i("tag"," RadioGroup的帧布局被点击了"+checkedId);
                    it.setClass(MainActivity.this,frameActivity.class);
                    MainActivity.this.startActivity(it);
                    break;
                case R.id.radioButton4:
                    Log.i("tag"," RadioGroup的绝对布局被点击了"+checkedId);
                    it.setClass(MainActivity.this,absoluteActivity.class);
                    MainActivity.this.startActivity(it);
                    break;
                case R.id.radioButton5:
                    Log.i("tag"," RadioGroup的表格布局被点击了"+checkedId);
                    it.setClass(MainActivity.this,tableActivity.class);
                    MainActivity.this.startActivity(it);
                    break;
                case R.id.radioButton21:
                    Log.i("tag"," RadioGroup的生命周期被点击了"+checkedId);
                    it.setClass(MainActivity.this,activityActivity.class);
                    MainActivity.this.startActivity(it);
                    break;
                case R.id.radioButton22:
                    Log.i("tag"," RadioGroup的IntentFirst被点击了"+checkedId);

                    it.setAction("aaa.bbb.ccc");
                    it.addCategory("android.intent.category.DEFAULT");
                    MainActivity.this.startActivity(it);  // 页面跳转
                    break;
                case R.id.radioButton23:
                    Log.i("tag"," RadioGroup的IntentSecond被点击了"+checkedId);
                    it.setClass(MainActivity.this,activityActivity.class);
                    MainActivity.this.startActivity(it);
                    break;
                case R.id.radioButton24:
                    Log.i("tag"," RadioGroup的被点击了"+checkedId);
                    it.setClass(MainActivity.this,tableActivity.class);
                    MainActivity.this.startActivity(it);
                    break;
                case R.id.radioButton25:
                    Log.i("tag"," RadioGroup的被点击了"+checkedId);
                    it.setClass(MainActivity.this,tableActivity.class);
                    MainActivity.this.startActivity(it);
                    break;
            }

        }
    };

}
