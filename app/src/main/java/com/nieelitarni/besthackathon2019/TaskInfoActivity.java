package com.nieelitarni.besthackathon2019;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TaskInfoActivity extends AppCompatActivity {

    private Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_info);

        Bundle extras = getIntent().getExtras();
        String taskId = extras.getString(TasksFragment.INTENT_PARAM);
        task = AppManager.getInstance().getTaskById(taskId);
    }
}