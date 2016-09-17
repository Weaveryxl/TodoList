package com.example.weaver.todolist.utils;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.weaver.todolist.R;
import com.example.weaver.todolist.models.Todo;

import java.util.List;

/**
 * Created by Weaver on 2016/9/5.
 */
public class TodoListAdapter extends BaseAdapter{

    private Context context;
    private List<Todo> data;

    public TodoListAdapter(Context context, List<Todo> data){
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Viewholder vh;
        if (convertView == null){
            Log.d("To do List","new position: " + position);
            convertView = LayoutInflater.from(context).inflate(R.layout.todo_list_item,parent, false);
            vh = new Viewholder();
            vh.todoText = (TextView) convertView.findViewById(R.id.to_do_list_text);
            convertView.setTag(vh);

        }
        else{
            vh = (Viewholder) convertView.getTag();
        }
        Log.d("To do list","position: " + position);
        Todo todo = data.get(position);
        vh.todoText.setText(todo.text);
        return convertView;
    }

    private static class Viewholder{
        TextView todoText;
    }
}
