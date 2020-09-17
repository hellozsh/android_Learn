package com.example.learning.second;

/*
  数据适配器:
      作用: 把复杂的数据(数组、链表、数据库、集合等)填充在制定屏幕上

      ArrayAdapter(数组适配器): 用于绑定格式单一的数据
      数据源: 可以是集合或数组

      SimpleAdapter(简单适配器): 用于绑定格式复杂的数据
      数据源: 只能是特定范泛型的集合

      数据适配器是连接数据源和视图界面的桥梁

      实现过程: 新建适配器 -> 添加数据源到适配器 -> 视图加载适配器

  监听器
       OnItemClickListener: 可以处理视图中单个条目的点击事件
       OnScrollListener: 检测滚动的变化，可以用于视图在滚动中加载数据
       监听器是程序和用户(或系统)交互的桥梁
 */

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.learning.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListviewActivity extends Activity implements OnItemClickListener, OnScrollListener {

    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private SimpleAdapter simpleAdapter;
    private ArrayList<Map<String, Object>> dataList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_layout);

        listView = findViewById(R.id.listView1);

        // ----------------------- 第一种Adapter
        // 1.新建一个数据适配器
        String[] arr_data = {"慕课网1", "慕课网2", "慕课网3", "慕课网4", "慕课网5", "慕课网6" };
        // ArrayAdapter(上下文, 当前listView加载的每一个列表项对应的布局文件, 数据源)
        // 2.适配器加载数据源
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr_data);
        // 2.视图(listView)加载适配器
//        listView.setAdapter(arrayAdapter);


        // ----------------------- 第二种Adapter
        // SimpleAdapter(上下文, 元素继承map的list， )
        /*
            第一个参数: 上下文
            第二个参数: 元素继承map的list
                      每个map都会去对应listview列表中的一行
                      每个map中的键必须包含所有在from中所制定的键
            第三个参数: 列表项的布局文件ID
            第四个参数:  frome: Map中的建名
            第5个参数: to绑定数据视图中的ID，与from成对应关系
         */
        dataList = new ArrayList<Map<String, Object>>();
        getData();
        simpleAdapter = new SimpleAdapter(this, dataList, R.layout.listview_layout_item, new String[]{"pic","text"}, new int[]{R.id.list_pic, R.id.list_text});
        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener(this);
        listView.setOnScrollListener(this);
    }

    private ArrayList<Map<String, Object>> getData() {

        for (int i = 0; i<20; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("pic", R.mipmap.ic_launcher);
            map.put("text","木块"+i);

            dataList.add(map);
        }

        return dataList;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

       String text = listView.getItemAtPosition(position) + "";
        Toast.makeText(this, "position="+position+" text="+text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

        switch (scrollState) {
            case SCROLL_STATE_FLING:
                Log.i("ListView", "用户在手指离开屏幕之前，由于用力滑了一下，视图仍以惯性继续滑动");
                break;
            case SCROLL_STATE_IDLE:
                Log.i("ListView", "视图已经停止滑动");
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("pic", R.mipmap.ic_launcher);
                map.put("text", "增加项-下拉刷新");
                dataList.add(map);
                simpleAdapter.notifyDataSetChanged();
                break;
            case SCROLL_STATE_TOUCH_SCROLL:
                Log.i("ListView", "手指没有离开屏幕，视图正在滑动");
                break;
        }

    }
}
















