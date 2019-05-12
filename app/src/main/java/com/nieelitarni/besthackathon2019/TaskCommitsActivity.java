package com.nieelitarni.besthackathon2019;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import java.util.ArrayList;

public class TaskCommitsActivity extends AppCompatActivity implements IRefreshable
{
    private Task task;
    private ArrayList<View> displayedItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_commits);

        NavigationActivity.toResfresh = this;

        Bundle extras = getIntent().getExtras();
        String taskId = extras.getString(TasksFragment.INTENT_PARAM);
        task = AppManager.getInstance().getTaskById(taskId);

        setTitle("Commits for " + task.getTitle());

        updateItemsList();
    }

    private void updateItemsList()
    {
        for (View toRemove : displayedItems)
            ((ViewGroup) toRemove.getParent()).removeView(toRemove);

        displayedItems.clear();

        ViewGroup itemsViewGroup = (ViewGroup) findViewById(R.id.itemsLayout);

        ArrayList<Commit> commits = AppManager.getInstance().getCommits();

        String color = "#eeeeee";

        for (Commit commit : commits)
        {
            if (!commit.getTask().getId().equals(task.getId())) continue;

            View child = LayoutInflater.from(this).inflate(R.layout.item_task_commit, null);

            TextView name = child.findViewById(R.id.textViewDetails);
            name.setText(commit.getContent());
            name.setBackgroundColor(Color.parseColor(color));

            TextView hash = child.findViewById(R.id.textViewHash);
            hash.setText(commit.getHash());
            hash.setBackgroundColor(Color.parseColor(color));

            TextView timeAgo = child.findViewById(R.id.textViewDate);
            timeAgo.setText(DateHandler.getTimeAgo(commit.getTime()));
            timeAgo.setBackgroundColor(Color.parseColor(color));

            TextView author = child.findViewById(R.id.textViewAuthor);
            author.setText(commit.getAuthor());
            author.setBackgroundColor(Color.parseColor(color));

            itemsViewGroup.addView(child);
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
