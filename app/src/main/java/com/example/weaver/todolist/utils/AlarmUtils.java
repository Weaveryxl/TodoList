package com.example.weaver.todolist.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.example.weaver.todolist.AlarmReceiver;
import com.example.weaver.todolist.TodoEditActivity;
import com.example.weaver.todolist.models.Todo;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Weaver on 2016/10/13.
 */


public class AlarmUtils {

    public static HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();

    public static void setAlarm(@NonNull Context context, @NonNull Todo todo) {
        Calendar c = Calendar.getInstance();
        if (todo.remindDate.compareTo(c.getTime()) < 0) {
            return;
        }

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra(TodoEditActivity.KEY_TODO, todo);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(context,todo.int_id,
                                                                intent,
                                                                PendingIntent.FLAG_UPDATE_CURRENT);
        //What is broadcast? what's the different with service and activity?
        alarmManager.set(AlarmManager.RTC_WAKEUP,
                            todo.remindDate.getTime(),
                            alarmIntent);
    }
}
