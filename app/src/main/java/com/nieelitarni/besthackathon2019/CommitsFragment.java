package com.nieelitarni.besthackathon2019;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.ScaleAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CommitsFragment extends Fragment
{
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_commits, container, false);

        return view;
    }

    private ArrayList<View> displayedCommits = new ArrayList<>();

    private void fillCommitsList()
    {
        for (View toRemove : displayedCommits)
            ((ViewGroup) toRemove.getParent()).removeView(toRemove);

        displayedCommits.clear();

        ViewGroup tasksViewGroup = (ViewGroup) view.findViewById(R.id.commitsLayout);

        ArrayList<Commit> commits = AppManager.getInstance().getCommits();

        for (Commit commit : commits)
        {

        }
    }
}
