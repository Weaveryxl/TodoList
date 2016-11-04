package com.example.weaver.todolist;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;

import com.example.weaver.todolist.models.Todo;
import com.example.weaver.todolist.utils.AlarmUtils;
import com.example.weaver.todolist.utils.ModelUtils;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int REQ_CODE_TODO_EDIT = 100;

    private static final String TODOS = "todos";

    private static final String NOTIFICATIONS = "notifications";

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

        loadData();

        adapter = new TodoListAdapter(this, todos);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.main_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        //setupUI(mockData());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE_TODO_EDIT && resultCode == Activity.RESULT_OK) {
            String todoId = data.getStringExtra(TodoEditActivity.KEY_TODO_ID);
            if(todoId != null) {
                deleteTodo(todoId);
            } else {
                Todo todo = data.getParcelableExtra(TodoEditActivity.KEY_TODO);
                updateTodo(todo);
            }
        }
    }

    private void updateTodo(Todo todo) {
        boolean found = false;
        for (int i = 0; i < todos.size(); i++) {
            Todo item = todos.get(i);
            if (TextUtils.equals(item.id, todo.id)) {
                found = true;
                todos.set(i, todo);
                break;
            }
        }

        if (!found) {
            todos.add(todo);
        }

        adapter.notifyDataSetChanged();
        ModelUtils.save(this, TODOS, todos);
        ModelUtils.save(this, NOTIFICATIONS, AlarmUtils.hashMap);
    }

    public void updateTodo(int index, boolean done) {
        todos.get(index).done = done;

        adapter.notifyDataSetChanged();
        ModelUtils.save(this, TODOS, todos);
        ModelUtils.save(this, NOTIFICATIONS, AlarmUtils.hashMap);
    }

    private void deleteTodo(@NonNull String todoId) {
        for (int i = 0; i < todos.size(); i++) {
            Todo item = todos.get(i);
            if (TextUtils.equals(item.id, todoId)) {
                todos.remove(i);
                break;
            }
        }

        adapter.notifyDataSetChanged();
        ModelUtils.save(this, TODOS, todos);
        ModelUtils.save(this, NOTIFICATIONS, AlarmUtils.hashMap);
    }

    private void loadData() {
        todos = ModelUtils.read(this, TODOS, new TypeToken<List<Todo>>(){});
        AlarmUtils.hashMap = ModelUtils.read(this, NOTIFICATIONS, new TypeToken<HashMap<Integer, Integer>>(){});
        if (todos == null) {
            todos = new ArrayList<>();
        }
        if (AlarmUtils.hashMap == null) {
            AlarmUtils.hashMap = new HashMap<Integer, Integer>();
        }
    }

    /*
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
*/
}
