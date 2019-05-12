package com.nieelitarni.besthackathon2019;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import java.util.ArrayList;

public class TaskUsersActivity extends AppCompatActivity implements IRefreshable
{
    private Task task;
    private ArrayList<View> displayedItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_users);

        NavigationActivity.toResfresh = this;


        Bundle extras = getIntent().getExtras();
        String taskId = extras.getString(TasksFragment.INTENT_PARAM);
        task = AppManager.getInstance().getTaskById(taskId);

        setTitle("Assigned users to " + task.getTitle());

        updateItemsList();
    }

    private void updateItemsList()
    {
        for (View toRemove : displayedItems)
            ((ViewGroup) toRemove.getParent()).removeView(toRemove);

        displayedItems.clear();

        ViewGroup tasksViewGroup = (ViewGroup) findViewById(R.id.itemsLayout);

        ArrayList<User> users = task.getUsers();

        for (User user : users)
        {
            View child = LayoutInflater.from(this).inflate(R.layout.item_task_user, null);

            TextView name = child.findViewById(R.id.textViewUserName);
            name.setText(user.getName());

            tasksViewGroup.addView(child);
            displayedItems.add(child);

            ScaleAnimation anim = new ScaleAnimation(0,1,0,1);
            anim.setDuration(200);
            anim.setFillAfter(true);
            child.startAnimation(anim);
        }
    }

    @Override
    public void refreshScreen()
    {
        updateItemsList();
    }
}
