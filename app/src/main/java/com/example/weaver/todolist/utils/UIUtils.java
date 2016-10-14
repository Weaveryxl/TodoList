package com.example.weaver.todolist.utils;

import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.widget.TextView;

/**
 * Created by Weaver on 2016/10/8.
 */
public class UIUtils {
    public static void setTextViewStrikeThrough(@NonNull TextView tv, boolean strikeThrough) {
        if (strikeThrough) {
            tv.setPaintFlags(tv.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
        else{
            tv.setPaintFlags(tv.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }
}
