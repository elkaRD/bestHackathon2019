package com.nieelitarni.besthackathon2019;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Task {
    private Integer id;
    private String title;
    private String description;

    private TaskStatus status;
    private Integer score;
    private Date createDate;

    private Messenger messenger;
    private ArrayList<User> users;
    private ArrayList<Commit> commits;

    public Task(Integer id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
        status = TaskStatus.Created;
        createDate = Calendar.getInstance().getTime();
        score = 1;
        messenger = new Messenger();
        users = new ArrayList<>();
        commits = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public ArrayList<Commit> getCommits() {
        return commits;
    }

    public ArrayList<User> getUsers() {
        return users;
    }
    //////////////////
    public ArrayList<Message> getMessages(){
        return messenger.getMessages();
    }

    public void addUser(User user) {
        users.add(user);
    }
}
