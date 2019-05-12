package com.nieelitarni.besthackathon2019;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class NewTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("Add new task");

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                addNewTask();
            }
        });
    }

    private void addNewTask()
    {
        EditText editName = findViewById(R.id.editTextName);
        EditText editDetails = findViewById(R.id.editTextDetails);

        String name = editName.getText().toString();
        String details = editDetails.getText().toString();

        Intent data = new Intent();
        String text = "";
        data.setData(Uri.parse(text));
        AppManager.getInstance().addTask(name, details);
        setResult(RESULT_OK, data);
        finish();
    }
}
