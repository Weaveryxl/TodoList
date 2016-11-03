package com.example.weaver.todolist.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Weaver on 2016/9/4.
 */
public class Todo implements Parcelable{

    public String id;

    public String text;

    public boolean done;

    public Date remindDate;

    public int int_id;

    public Todo(String text, Date remindDate, int int_id){
        this.id = UUID.randomUUID().toString();
        this.text = text;
        this.done = false;
        this.remindDate = remindDate;
        this.int_id = int_id;
    }

    protected Todo(Parcel in) {
        id = in.readString();
        text = in.readString();
        done = in.readByte() != 0;

        long date = in.readLong();
        remindDate = date == 0 ? null : new Date(date);
        int_id = in.readInt();
    }

    public static final Creator<Todo> CREATOR = new Creator<Todo>() {
        @Override
        public Todo createFromParcel(Parcel source) {
            return new Todo(source);
        }

        @Override
        public Todo[] newArray(int size) {
            return new Todo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(text);
        dest.writeByte((byte) (done ? 1 : 0));
        dest.writeLong(remindDate != null ? remindDate.getTime() : 0);
        dest.writeInt(int_id);
    }
}
