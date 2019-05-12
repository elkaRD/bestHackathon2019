package com.nieelitarni.besthackathon2019;

import java.util.ArrayList;

public class User {
    private String id;
    private String name;
    private Integer score;
    private Role role;
    private ArrayList<Task> currentTasks;
    private ArrayList<Task> doneTasks;

    public transient int number1;
    public transient int number2;

    public User(String id, String name, Role role) {
        this.id = id;
        this.name = name;
        this.score = 0;
        this.role = role;
        currentTasks = new ArrayList<>();
        doneTasks = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public ArrayList<Task> getCurrentTasks() {
        return currentTasks;
    }

    public ArrayList<Task> getDoneTasks() {
        return doneTasks;
    }

    ///

}
