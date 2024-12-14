package com.lxq.Tools;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class TimeTools {
    //获取当前时间
    public String getTime() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }

    //获取年月日
    public String getTimeYMD() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }


    //通过当前时间 加上时间长度 获取一段时间
    public List<String> getTimeArr(String time, Integer length) {
        List<String> timeList = new ArrayList<>();
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = simpleDateFormat.parse(time);
            for (int i = 0; i < length; i++) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                cal.add(Calendar.DATE, i);
                String timeString = simpleDateFormat.format(cal.getTime());
                timeList.add(timeString);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timeList;

    }
}
