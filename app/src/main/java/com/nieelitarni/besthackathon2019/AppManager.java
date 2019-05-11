package com.nieelitarni.besthackathon2019;

import java.util.ArrayList;

public class AppManager {
    //singleton implementation start
    private AppManager() {
        tasks = new ArrayList<>();
        users= new ArrayList<>();
        commits = new ArrayList<>();
        repoOwner = "Quazan";
        repoName = "test";

        tasks.add(new Task(tasks.size(), "Task1", "do zrobienia"));
        tasks.add(new Task(tasks.size(), "Task2", "do zrobienia duuuuuuuzo"));
        users.add(new User(users.size(), "paolo21d", Role.Backend ));
        users.add(new User(users.size(), "robert", Role.Fronted ));
        users.add(new User(users.size(), "Quazan", Role.Tester ));
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
