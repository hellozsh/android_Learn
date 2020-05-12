package com.example.learning;

import android.app.Activity;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class activityActivity extends Activity {


    /* 依次顺序
     onCreate(); 创建
     onStart(); 运行
     onResume(); 获取焦点 此时页面开始运行了状态Running
     onPause(); 失去焦点(比如弹窗出现当前页面不再最顶端就是暂停状态)
     onStop(); 停止状态(比如新的activity弹窗覆盖了他或者按键home去到后台运行就是停止状态)
     onDestroy(); 销毁 此时页面关闭了
     onRestart(); // 会重新回到onStart()状态
     */

     /*
      Activity的四种状态
      -- 活动状态(running),Activity处于页面最顶端，获取焦点
      -- 暂停状态(Paused),Activity失去焦点，但对用户可见
      -- 停止状态(Stopped),Activity被完全遮挡，但保留所有状态和成员信息
      -- 非活动状态(Killed)，Activity被停止
      */
     /*
      Activity 是一个应用程序组件，提供用户与程序交互的界面
      Android四大组件
      -- Activity
      -- Service
      -- BroadcastReceiver
      -- Content Provider
      */

     /*
     Activity如何创建使用
     -- 继承Android的Activity类
     -- 重写方法
     -- 设置显示布局
     -- 在AndroidManifest文件中，注册Activity
      */

     /*
      Intent
      Intent来协助完成Android各个组件之间的通讯
      1> startActivity(intent)
      2> 带返回值方法：startActivityForResult(intent,requestCode);
         onActivityResult(int requestCode,int resultCode,Intent data)
         setResult(resultCode,data);
      */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 将布局xml文件引入到activity当中
        setContentView(R.layout.layout_relative);
    }

}
