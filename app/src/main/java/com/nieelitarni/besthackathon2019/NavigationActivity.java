package com.nieelitarni.besthackathon2019;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class NavigationActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private static NavigationActivity currentInstance = null;
    private final static int REQUEST_INIT = 0;

    public static IRefreshable toResfresh = null;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment fragment = new ProgressFragment();

            switch (item.getItemId()) {
                case R.id.navigation_progress:
                    fragment = new ProgressFragment();
                    break;
                case R.id.navigation_tasks:
                    fragment = new TasksFragment();
                    break;
                case R.id.navigation_users:
                    fragment = new UsersFragment();
                    break;
                case R.id.navigation_commits:
                    fragment = new CommitsFragment();
                    break;
            }
            replaceFragment(fragment);

            return true;
        }
    };

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameContainer, fragment);
        fragmentTransaction.addToBackStack(fragment.toString());
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        currentInstance = this;

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //AppManager.getInstance().setContext(this);
        //launchInitActivity();
        if (!AppManager.getInstance().setContext(this))
        {
            launchInitActivity();
        }
        else
            AppManager.getInstance().execute();

//        Firebase.write("my_debug_key", "my_debug_msg");
//        Firebase.read("my_debug_key");
        Firebase.read(AppManager.getInstance().getRepoName());
    }

    private void launchInitActivity()
    {
        Intent myIntent = new Intent(this, InitUserActivity.class);
        startActivityForResult(myIntent, REQUEST_INIT);
    }

    public static NavigationActivity getCurrentInstance() {
        return currentInstance;
    }

    public void dataChanged() {
        refreshCurrentScreen();
    }

    public void refreshCurrentScreen()
    {
        if (toResfresh != null)
        {
            toResfresh.refreshScreen();
        }
    }
}
