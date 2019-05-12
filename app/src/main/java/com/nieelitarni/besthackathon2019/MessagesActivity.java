package com.nieelitarni.besthackathon2019;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.ScaleAnimation;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MessagesActivity extends AppCompatActivity implements IRefreshable
{
    private Task task;
    private ArrayList<View> displayedItems = new ArrayList<>();
    private EditText inputMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationActivity.toResfresh = this;

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
////                        .setAction("Action", null).show();
//
//
//            }
//        });

        Bundle extras = getIntent().getExtras();
        String taskId = extras.getString(TasksFragment.INTENT_PARAM);
        task = AppManager.getInstance().getTaskById(taskId);

        inputMsg = findViewById(R.id.editTextMsg);

        updateItemsList();
    }

    private void updateItemsList()
    {
        for (View toRemove : displayedItems)
            ((ViewGroup) toRemove.getParent()).removeView(toRemove);

        displayedItems.clear();

        ViewGroup itemsViewGroup = (ViewGroup) findViewById(R.id.itemsLayout);

        ArrayList<Message> messages = task.getMessages();

        for (Message message : messages)
        {
            View child = null;
            if (AppManager.getInstance().isMe(message.getAuthor()))
                child = LayoutInflater.from(this).inflate(R.layout.item_task_message_my, null);
            else
                child = LayoutInflater.from(this).inflate(R.layout.item_task_message, null);

            TextView msg = child.findViewById(R.id.textViewMsg);
            msg.setText(message.getContent());

            TextView header = child.findViewById(R.id.textViewHeader);
            header.setText(message.getHeader());

            itemsViewGroup.addView(child);
            displayedItems.add(child);

            ScaleAnimation anim = new ScaleAnimation(0,1,0,1);
            anim.setDuration(200);
            anim.setFillAfter(true);
            child.startAnimation(anim);
        }
    }

    public void onClickSend(View view)
    {
        String typedMsg = inputMsg.getText().toString();

        if (typedMsg == null || typedMsg.equals("")) return;

        AppManager.getInstance().addMessageToTask(task, typedMsg);
        inputMsg.setText("");
    }

    @Override
    public void refreshScreen()
    {
        updateItemsList();
    }
}
