package com.nieelitarni.besthackathon2019;

import java.util.ArrayList;

public class AppManager {
    //singleton implementation start
    private AppManager() {

    }

    private static AppManager instance = null;

    public static AppManager getInstance() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }

    //singleton implementation finish
    private ArrayList<Task> tasks;
    private ArrayList<User> users;
    private ArrayList<Commit> commits;

    private String repoOwner;
    private String repoName;

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<Commit> getCommits() {
        return commits;
    }

    public void execute() {

    }

    public void saveToFile(){

    }
    public void readFromFile(){

    }
    public void addTask(Integer id, String title, String description) {
        Task task = new Task(id, title, description);
    }
}
