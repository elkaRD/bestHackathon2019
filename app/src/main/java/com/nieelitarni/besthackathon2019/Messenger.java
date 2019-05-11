package com.nieelitarni.besthackathon2019;

import java.util.ArrayList;

public class Messenger {
    private ArrayList<Message> messages;
    public Messenger(){
        messages = new ArrayList<>();
    }
    public ArrayList<Message> getMessages() {
        return messages;
    }
}
