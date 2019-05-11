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

public class UsersFragment extends Fragment
{
    private View view;
    private ArrayList<View> displayedUsers = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_users, container, false);

        updateCommitsList();

        return view;
    }

    private void updateCommitsList()
    {
        for (View toRemove : displayedUsers)
            ((ViewGroup) toRemove.getParent()).removeView(toRemove);

        displayedUsers.clear();

        ViewGroup tasksViewGroup = (ViewGroup) view.findViewById(R.id.tasksLayout);

        ArrayList<User> users = AppManager.getInstance().getUsers();

        for (User user : users)
        {
            View child = LayoutInflater.from(getActivity()).inflate(R.layout.item_commit, null);

            TextView name = child.findViewById(R.id.textViewName);
            name.setText(user.getName());

            TextView done = child.findViewById(R.id.textViewUsers);
            done.setText(user.getDoneTasks().size());

            TextView current = child.findViewById(R.id.textViewDate);
            current.setText(user.getCurrentTasks().size());

            tasksViewGroup.addView(child);
            displayedUsers.add(child);

            ScaleAnimation anim = new ScaleAnimation(0,1,0,1);
            anim.setDuration(200);
            anim.setFillAfter(true);
            child.startAnimation(anim);
        }
    }
}
