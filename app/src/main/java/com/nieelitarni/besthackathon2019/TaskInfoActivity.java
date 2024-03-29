package com.nieelitarni.besthackathon2019;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TaskInfoActivity extends AppCompatActivity implements IRefreshable
{

    private Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_info);

        NavigationActivity.toResfresh = this;

        Bundle extras = getIntent().getExtras();
        String taskId = extras.getString(TasksFragment.INTENT_PARAM);
        task = AppManager.getInstance().getTaskById(taskId);

        setTitle("Details about " + task.getTitle());

        TextView title = findViewById(R.id.textViewTitle);
        title.setText(task.getTitle());

        TextView id = findViewById(R.id.textViewId);
        id.setText("ID: " + task.getId());

        TextView date = findViewById(R.id.textViewDate);
        date.setText("Created " + DateHandler.getTimeAgo(task.getCreateDate()));

        TextView users = findViewById(R.id.textViewUsers);
        users.setText("Contributors: " + task.getUsers().size());

        TextView details = findViewById(R.id.textViewDetails);
        details.setText("Details: " + task.getDescription());
    }

    public void onClickMarkAsDone(View view)
    {
//        task.setStatus(TaskStatus.Completed);
        AppManager.getInstance().markTaskAsDone(task);
        finish();
    }

    @Override
    public void refreshScreen()
    {
        //TODO: refresh this screen by re-launching activity
    }
}
