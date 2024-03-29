package com.nieelitarni.besthackathon2019;

import com.google.gson.Gson;

import java.util.ArrayList;

public class Messenger {
    private ArrayList<Message> messages;

    public Messenger() {
        messages = new ArrayList<>();
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    //
    public void addMessage(String msg) {
        messages.add(new Message(msg));
        AppManager.getInstance().sendToDatabase();
    }
}
