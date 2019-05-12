package com.nieelitarni.besthackathon2019;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.ScaleAnimation;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class UsersFragment extends Fragment implements IRefreshable
{
    private View view;
    private ArrayList<View> displayedUsers = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_users, container, false);

        updateCommitsList();
        NavigationActivity.toResfresh = this;

        return view;
    }

    private void updateCommitsList()
    {
        for (View toRemove : displayedUsers)
            ((ViewGroup) toRemove.getParent()).removeView(toRemove);

        displayedUsers.clear();

        ViewGroup tasksViewGroup = (ViewGroup) view.findViewById(R.id.usersLayout);

        ArrayList<User> users = AppManager.getInstance().getUsers();

        for (User user : users)
        {
            View child = LayoutInflater.from(getActivity()).inflate(R.layout.item_user, null);

            TextView name = child.findViewById(R.id.textViewUserName);
            name.setText(user.getName());

            Random r = new Random();
            ProgressBar level = child.findViewById(R.id.progressBar);
            level.setProgress(r.nextInt(100), true);

            TextView done = child.findViewById(R.id.textViewUserDone);
            done.setText(Integer.toString(user.getDoneTasks().size()));

            TextView current = child.findViewById(R.id.textViewUserCurrent);
            current.setText(Integer.toString(user.getCurrentTasks().size()));

            tasksViewGroup.addView(child);
            displayedUsers.add(child);

            ScaleAnimation anim = new ScaleAnimation(0,1,0,1);
            anim.setDuration(200);
            anim.setFillAfter(true);
            child.startAnimation(anim);
        }
    }

    @Override
    public void refreshScreen()
    {
        updateCommitsList();
    }
}
