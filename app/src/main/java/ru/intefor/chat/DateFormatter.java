package ru.intefor.chat;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");

    public static long getDate(){
        Date date = new Date();
        return date.getTime();
    }

    public static String dateStr(long ts){
        String s = dateFormat.format(ts);
        return s;
    }
}
