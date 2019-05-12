package com.nieelitarni.besthackathon2019;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class TasksFragment extends Fragment implements View.OnClickListener, IRefreshable
{
    private View view;
    private ArrayList<View> displayedTasks = new ArrayList<>();

    private final static int BUTTON_USERS = 0;
    private final static int BUTTON_MESSAGES = 1;
    private final static int BUTTON_COMMITS = 2;
    private final static int BUTTON_ADD = 3;
    private final static int BUTTON_INFO = 4;

    private final static int REQUEST_USERS = 0;
    private final static int REQUEST_MESSAGES = 1;
    private final static int REQUEST_COMMITS = 2;
    private final static int REQUEST_INFO = 3;
    private final static int REQUEST_NEW_TASK = 3;

    public final static String INTENT_PARAM = "task";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_tasks, container, false);

        Button newTask = view.findViewById(R.id.buttonAdd);
        newTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getActivity(), NewTaskActivity.class);
                startActivityForResult(myIntent, REQUEST_NEW_TASK);
            }
        });

        NavigationActivity.toResfresh = this;

        updateCommitsList();

        return view;
    }

    private void updateCommitsList()
    {
        for (View toRemove : displayedTasks)
            ((ViewGroup) toRemove.getParent()).removeView(toRemove);

        displayedTasks.clear();

        ViewGroup tasksViewGroup = (ViewGroup) view.findViewById(R.id.tasksLayout);

        ArrayList<Task> tasks = AppManager.getInstance().getTasks();

        for (Task task : tasks)
        {
            View child = LayoutInflater.from(getActivity()).inflate(R.layout.item_task, null);

            TextView name = child.findViewById(R.id.buttonName);
            name.setText(task.getTitle());
            name.setTag(R.string.tag_task_fragment_type, BUTTON_INFO);
            name.setTag(R.string.tag_task_fragment_add, task);
            name.setOnClickListener(this);

            TextView users = child.findViewById(R.id.textViewAssignedUsers);
            users.setText(Integer.toString(task.getUsers().size()));

            TextView messages = child.findViewById(R.id.textViewMessages);
            messages.setText(Integer.toString(task.getMessages().size()));

            TextView commits = child.findViewById(R.id.textViewCommits);
            commits.setText(Integer.toString(task.getCommits().size()));

            ImageButton assigned = child.findViewById(R.id.imageButtonAssignedUsers);
            assigned.setTag(R.string.tag_task_fragment_type, BUTTON_USERS);
            assigned.setTag(R.string.tag_task_fragment_add, task);
            assigned.setOnClickListener(this);

            ImageButton messagesButton = child.findViewById(R.id.imageButtonMessages);
            messagesButton.setTag(R.string.tag_task_fragment_type, BUTTON_MESSAGES);
            messagesButton.setTag(R.string.tag_task_fragment_add, task);
            messagesButton.setOnClickListener(this);

            ImageButton commitsButton = child.findViewById(R.id.imageButtonCommits);
            commitsButton.setTag(R.string.tag_task_fragment_type, BUTTON_COMMITS);
            commitsButton.setTag(R.string.tag_task_fragment_add, task);
            commitsButton.setOnClickListener(this);

            ImageButton assignMe = child.findViewById(R.id.imageButtonAdd);
            assignMe.setTag(R.string.tag_task_fragment_type, BUTTON_ADD);
            assignMe.setTag(R.string.tag_task_fragment_add, task);
            assignMe.setOnClickListener(this);

            tasksViewGroup.addView(child);
            displayedTasks.add(child);

            ScaleAnimation anim = new ScaleAnimation(0,1,0,1);
            anim.setDuration(200);
            anim.setFillAfter(true);
            child.startAnimation(anim);
        }
    }

    @Override
    public void onClick(View v)
    {
        Task task = (Task) v.getTag(R.string.tag_task_fragment_add);
        int buttonClicked = (int) v.getTag(R.string.tag_task_fragment_type);

        if (buttonClicked == BUTTON_ADD) {
            clickedAdd(task);
        }
        else if (buttonClicked == BUTTON_USERS) {
            clickedUsers(task);
        }
        else if (buttonClicked == BUTTON_MESSAGES) {
            clickedMessages(task);
        }
        else if (buttonClicked == BUTTON_COMMITS) {
            clickedCommits(task);
        }
        else if (buttonClicked == BUTTON_INFO) {
            clickedInfo(task);
        }
    }

    private void clickedInfo(Task task)
    {
        Intent myIntent = new Intent(getActivity(), TaskInfoActivity.class);
        myIntent.putExtra(INTENT_PARAM, task.getId());
        startActivityForResult(myIntent, REQUEST_INFO);
    }

    private void clickedUsers(Task task)
    {
        Intent myIntent = new Intent(getActivity(), TaskUsersActivity.class);
        myIntent.putExtra(INTENT_PARAM, task.getId());
        startActivityForResult(myIntent, REQUEST_USERS);
    }

    private void clickedMessages(Task task)
    {
        Intent myIntent = new Intent(getActivity(), MessagesActivity.class);
        myIntent.putExtra(INTENT_PARAM, task.getId());
        startActivityForResult(myIntent, REQUEST_MESSAGES);
    }

    private void clickedCommits(Task task)
    {
        Intent myIntent = new Intent(getActivity(), TaskCommitsActivity.class);
        myIntent.putExtra(INTENT_PARAM, task.getId());
        startActivityForResult(myIntent, REQUEST_COMMITS);
    }

    private void clickedAdd(Task task)
    {
        AppManager.getInstance().assignToTask(task);
        updateCommitsList();
        Snackbar.make(view, "Assigned to task", Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();
    }

    @Override
    public void refreshScreen()
    {
        updateCommitsList();
    }
}
