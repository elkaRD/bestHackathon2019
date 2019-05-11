package com.nieelitarni.besthackathon2019;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.ScaleAnimation;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class TasksFragment extends Fragment implements View.OnClickListener
{
    private View view;
    private ArrayList<View> displayedTasks = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_tasks, container, false);

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

            TextView name = child.findViewById(R.id.textViewName);
            name.setText(task.getTitle());

            TextView users = child.findViewById(R.id.textViewAssignedUsers);
            users.setText(Integer.toString(task.getUsers().size()));

            TextView messages = child.findViewById(R.id.textViewMessages);
            messages.setText(Integer.toString(task.getMessages().size()));

            ImageButton assignMe = child.findViewById(R.id.imageButtonAdd);
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
        AppManager.getInstance().assignToTask(task);
        updateCommitsList();
        Snackbar.make(view, "Assigned to task", Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();
    }
}
