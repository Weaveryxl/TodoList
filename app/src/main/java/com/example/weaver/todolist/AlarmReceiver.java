package com.example.weaver.todolist;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Weaver on 2016/10/14.
 */
public class AlarmReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Alarm!", Toast.LENGTH_LONG).show();
    }
}
