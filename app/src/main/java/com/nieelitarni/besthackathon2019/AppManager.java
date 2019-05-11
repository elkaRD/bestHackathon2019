package com.nieelitarni.besthackathon2019;

import java.util.ArrayList;

public class AppManager {
    private ArrayList<Task> tasks;
    private ArrayList<User> users;
    private ArrayList<Commit> commits;

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<Commit> getCommits() {
        return commits;
    }
}
