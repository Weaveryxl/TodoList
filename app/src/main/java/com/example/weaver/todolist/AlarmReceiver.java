package com.example.weaver.todolist;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.example.weaver.todolist.models.Todo;

/**
 * Created by Weaver on 2016/10/14.
 */
public class AlarmReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        //final int notificationId = 100;
        Todo todo = intent.getParcelableExtra(TodoEditActivity.KEY_TODO);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(todo.text)
                .setContentText(todo.text);

        int notificationId = todo.int_id;

        Intent resultIntent = new Intent(context, TodoEditActivity.class);
        resultIntent.putExtra(TodoEditActivity.KEY_TODO, todo);
        resultIntent.putExtra(TodoEditActivity.KEY_NOTIFICATION_ID, notificationId);

        PendingIntent resultPendingIntent = PendingIntent.getActivity(context, todo.int_id,
                                                                        resultIntent,
                                                                        PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentIntent(resultPendingIntent);

        NotificationManager nm = (NotificationManager) context.getSystemService(
                Context.NOTIFICATION_SERVICE);
        nm.notify(notificationId, builder.build());
    }
}
