package com.example.weaver.todolist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.weaver.todolist.R;
import com.example.weaver.todolist.models.Todo;
import com.example.weaver.todolist.utils.UIUtils;

import java.util.List;

/**
 * Created by Weaver on 2016/9/5.
 */
public class TodoListAdapter extends RecyclerView.Adapter{

    private MainActivity activity;
    private List<Todo> data;

    public TodoListAdapter(MainActivity activity, List<Todo> data) {
        this.activity = activity;
        this.data = data;
    }

    private boolean onBind;

    /*
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Viewholder vh;
        if (convertView == null){
            //Log.d("To do List","new position: " + position);
            convertView = activity.getLayoutInflater().inflate(R.layout.todo_list_item,parent, false);

            vh = new Viewholder();
            vh.todoText = (TextView) convertView.findViewById(R.id.to_do_list_text);
            vh.doneCheckbox = (CheckBox) convertView.findViewById(R.id.todo_list_item_check);
            convertView.setTag(vh);
        }
        else{
            vh = (Viewholder) convertView.getTag();
        }
        //Log.d("To do list","position: " + position);
        //Todo todo = data.get(position);
        //vh.todoText.setText(todo.text);

        final Todo todo = (Todo) getItem(position);
        vh.todoText.setText(todo.text);
        vh.doneCheckbox.setChecked(todo.done);
        UIUtils.setTextViewStrikeThrough(vh.todoText, todo.done);

        vh.doneCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                activity.updateTodo(position, isChecked);
            }
        });

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, TodoEditActivity.class);
                intent.putExtra(TodoEditActivity.KEY_TODO, todo);
                activity.startActivityForResult(intent, MainActivity.REQ_CODE_TODO_EDIT);
            }
        });

        return convertView;
    }
    */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View view = LayoutInflater.from(activity).inflate(R.layout.todo_list_item, parent, false);
        return new TodoViewHolder(view, activity);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        final Todo todo = data.get(position);
        TodoViewHolder vh = ((TodoViewHolder) viewHolder);
        onBind = true;
        vh.todoText.setText(todo.text);
        vh.doneCheckbox.setChecked(todo.done);
        UIUtils.setTextViewStrikeThrough(vh.todoText, todo.done);
        onBind = false;
        /*
        vh.doneCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                activity.updateTodo(position, isChecked);
            }
        });
        */
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, TodoEditActivity.class);
                intent.putExtra(TodoEditActivity.KEY_TODO, todo);
                activity.startActivityForResult(intent, MainActivity.REQ_CODE_TODO_EDIT);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private class TodoViewHolder extends RecyclerView.ViewHolder{
        TextView todoText;
        CheckBox doneCheckbox;

        public TodoViewHolder(View view, final MainActivity activity) {
            super(view);
            todoText = (TextView) view.findViewById(R.id.to_do_list_text);
            doneCheckbox = (CheckBox) view.findViewById(R.id.todo_list_item_check);
            doneCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (!onBind) {
                        activity.updateTodo(getLayoutPosition(), isChecked);
                    }
                }
            });
        }

    }

}
