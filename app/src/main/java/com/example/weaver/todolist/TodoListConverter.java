package com.example.weaver.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.weaver.todolist.models.Todo;

import java.util.List;

/**
 * Created by Weaver on 2016/9/5.
 */
public class TodoListConverter {

    private Context context;
    private List<Todo> data;

    public TodoListConverter(Context context, List<Todo> data){
        this.context = context;
        this.data = data;
    }

    public View getView(int position){
        Todo todo = data.get(position);

        View view = LayoutInflater.from(context).inflate(R.layout.todo_list_item, null);
        ((TextView) view.findViewById(R.id.to_do_list_text)).setText(todo.text);
        return view;
    }
}
