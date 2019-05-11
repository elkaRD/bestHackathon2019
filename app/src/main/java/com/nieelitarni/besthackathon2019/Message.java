package com.nieelitarni.besthackathon2019;

import java.util.Date;

public class Message {
    private User author;
    private String content;
    private Date time;

    public Message(String c) {
        author = AppManager.getInstance().getMe();
        content = c;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    //
    public String getHeader() {
        String header;
        if (author.equals(AppManager.getInstance().getMe()))
            header = "Me";
        else
            header = author.getName();
        header += DateHandler.getTimeAgo(time);
        return header;
    }
}
