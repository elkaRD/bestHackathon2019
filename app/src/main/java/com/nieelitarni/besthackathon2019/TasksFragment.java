package com.nieelitarni.besthackathon2019;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import java.util.ArrayList;

public class TasksFragment extends Fragment
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
            users.setText(task.getUsers().size());

            TextView messages = child.findViewById(R.id.textViewMessages);
            messages.setText(task.getMessages().size());

            tasksViewGroup.addView(child);
            displayedTasks.add(child);

            ScaleAnimation anim = new ScaleAnimation(0,1,0,1);
            anim.setDuration(200);
            anim.setFillAfter(true);
            child.startAnimation(anim);
        }
    }
}
