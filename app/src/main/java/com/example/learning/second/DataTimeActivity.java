package com.example.learning.second;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.learning.R;

import java.util.Calendar;

/*

 DatePicker 和 TimePicker2种实现动态输入日期和时间的功能

 DatePickerDialog 和 TimePickerDialog 2种实现动态输入日期和时间的对话框

 两组针对检测检测日期改变的监听器包含:
   OnDateChangedListener和OnTimeChangedListener()
   OnDateSetListener和OnTimeSetListener()


 */

public class DataTimeActivity  extends AppCompatActivity {

    private TimePicker timePicker;
    private DatePicker datePicker;
    private Calendar calendar;
    private int _year;
    private int _month;
    private int _day;
    private int _hour;
    private int _minute;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_time);


        // 获取日历对象
        calendar = Calendar.getInstance();
        // 获取年月日时分秒的信息
        _year = calendar.get(Calendar.YEAR);
        _month = calendar.get(Calendar.MONDAY) + 1;
        _day = calendar.get(Calendar.DAY_OF_MONTH);
        _hour = calendar.get(Calendar.HOUR_OF_DAY);
        _minute = calendar.get(Calendar.MINUTE);

        setTitle(_year+"-"+_month+"-"+_day+"-"+_hour+"-"+_minute);
        datePicker=(DatePicker)findViewById(R.id.date);
        timePicker=(TimePicker) findViewById(R.id.time);

        // dataPicker初始化
        datePicker.init(_year, _month-1, _day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                _year = year;
                _month = monthOfYear;
                _day = dayOfMonth;

                setTitle(_year+"-"+(_month+1)+"-"+_day+"-"+_hour+"-"+_minute);
            }
        });

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {

                _hour = hourOfDay;
                _minute = minute;

                setTitle(_year+"-"+(_month+1)+"-"+_day+"-"+_hour+"-"+_minute);
            }
        });

        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                setTitle(_year+"-"+(_month+1)+"-"+_day+"-"+_hour+"-"+_minute);
            }
        }, _year, _month, _day).show();


        new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener(){

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                setTitle(_year+"-"+(_month+1)+"-"+_day+"-"+_hour+"-"+_minute);
            }
        }, _hour, _minute, true);

    }


}
