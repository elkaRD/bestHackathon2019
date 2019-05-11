package com.nieelitarni.besthackathon2019;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

//TODO: remove this class and activity;
// new one -> MessagesActivity

public class TaskMessagesActivity extends AppCompatActivity {
    private Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_messages);

//        inflate(R.layout.activity_tasks_messages, this, false);
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.addNewTask);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                createNewTask();
//            }
//        });

        Bundle extras = getIntent().getExtras();
        String taskId = extras.getString(TasksFragment.INTENT_PARAM);
        task = AppManager.getInstance().getTaskById(taskId);
    }
}
