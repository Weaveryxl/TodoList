package com.example.weaver.todolist.utils;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.weaver.todolist.R;
import com.example.weaver.todolist.models.Todo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int REQ_CODE_TODO_EDIT = 100;

    private static final String TODOS = "todos";

    private TodoListAdapter adapter;
    private List<Todo> todos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,TodoEditActivity.class);
                startActivityForResult(intent, REQ_CODE_TODO_EDIT);
            }
        });

        setupUI(mockData());
    }

    private void setupUI(@NonNull List<Todo> todos){
        ListView listView = (ListView) findViewById(R.id.list_view);
        TodoListAdapter adapter = new TodoListAdapter(this, todos);
        listView.setAdapter(adapter);
    }

    @NonNull
    private List<Todo> mockData(){
        List<Todo> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++){
            list.add(new Todo("todo" + i));
        }
        return list;
    }
}
