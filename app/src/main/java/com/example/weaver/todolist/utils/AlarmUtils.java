package com.example.weaver.todolist.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.example.weaver.todolist.AlarmReceiver;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Weaver on 2016/10/13.
 */
public class AlarmUtils {

    public static void setAlarm(@NonNull Context context, @NonNull Date date) {
        Calendar c = Calendar.getInstance();
        if (date.compareTo(c.getTime()) < 0) {
            return;
        }

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(context, AlarmReceiver.class);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(context,0,
                                                                intent,
                                                                PendingIntent.FLAG_UPDATE_CURRENT);
        //What is broadcast? what's the different with service and activity?
        alarmManager.set(AlarmManager.RTC_WAKEUP,
                            date.getTime(),
                            alarmIntent);
    }
}
