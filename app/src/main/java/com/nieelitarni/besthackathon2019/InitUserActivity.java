package com.nieelitarni.besthackathon2019;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class InitUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init_user);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("Set up your account");
    }

    public void onClickSave(View view)
    {
        EditText email = findViewById(R.id.editTextEmail);
        AppManager.getInstance().addMeUser(email.getText().toString(), Role.Backend);
        AppManager.getInstance().execute();
        finish();
    }
}
