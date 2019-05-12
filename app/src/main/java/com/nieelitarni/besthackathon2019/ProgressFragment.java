package com.nieelitarni.besthackathon2019;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ProgressFragment extends Fragment implements IRefreshable
{
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_progress, container, false);

        NavigationActivity.toResfresh = this;

        TextView title = view.findViewById(R.id.textViewTitle);
        title.setText("Repo name: " + AppManager.getInstance().getRepoName());

        TextView owner = view.findViewById(R.id.textViewOwner);
        owner.setText("Repo owner: " + AppManager.getInstance().getRepoOwner());

        String usersCounter = Integer.toString(AppManager.getInstance().getUsers().size());
        TextView users = view.findViewById(R.id.textViewUsers);
        users.setText(usersCounter + " users");

        String commitsCounter = Integer.toString(AppManager.getInstance().getCommits().size());
        TextView commits = view.findViewById(R.id.textViewCommits);
        commits.setText(commitsCounter + " commits");

        return view;
    }

    @Override
    public void refreshScreen()
    {
        //TODO: refresh this screen by re-launching activity
    }
}
