package com.nieelitarni.besthackathon2019;

import android.graphics.Color;
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

public class CommitsFragment extends Fragment implements IRefreshable
{
    private View view;
    private ArrayList<View> displayedCommits = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_commits, container, false);

        NavigationActivity.toResfresh = this;
        updateCommitsList();

        return view;
    }

    private void updateCommitsList()
    {
        for (View toRemove : displayedCommits)
            ((ViewGroup) toRemove.getParent()).removeView(toRemove);

        displayedCommits.clear();

        ViewGroup commitsViewGroup = (ViewGroup) view.findViewById(R.id.commitsLayout);
        //AppManager.getInstance().sendRequest();
        ArrayList<Commit> commits = AppManager.getInstance().getCommits();

        String color = "#eeeeee";

        for (Commit commit : commits)
        {
            View child = LayoutInflater.from(getActivity()).inflate(R.layout.item_commit, null);

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

            commitsViewGroup.addView(child);
            displayedCommits.add(child);

            ScaleAnimation anim = new ScaleAnimation(0,1,0,1);
            anim.setDuration(200);
            anim.setFillAfter(true);
            child.startAnimation(anim);
        }
    }

    public void refreshScreen()
    {
        updateCommitsList();
    }
}
