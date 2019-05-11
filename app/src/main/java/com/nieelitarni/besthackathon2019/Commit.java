package com.nieelitarni.besthackathon2019;

import java.time.LocalDateTime;
import java.util.Date;

public class Commit {

    public Commit(String hash, String author, Date time, String content, Task task) {
        this.hash = hash;
        this.author = author;
        this.time = time;
        this.content = content;
        this.task = task;
    }

    private String hash;
    private String author;
    private Date time;
    private String content;
    private Task task;


    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
    //////////////
}
