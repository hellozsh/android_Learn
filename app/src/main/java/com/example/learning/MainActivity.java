package com.example.learning;

import androidx.appcompat.app.AppCompatActivity;

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
//        loginButton.setOnClickListener(listener);

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
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.radioButton1:
                        Log.i("tag"," RadioGroup的篮球被点击了"+checkedId);
                        break;
                    case R.id.radioButton2:
                        Log.i("tag"," RadioGroup的足球被点击了"+checkedId);
                        break;
                    case R.id.radioButton3:
                        Log.i("tag"," RadioGroup的羽毛球被点击了"+checkedId);
                        break;
                }

            }
        });

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
}
