package com.nieelitarni.besthackathon2019;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Task {
    private int id;
    private String title;
    private String description;
    private TaskStatus status;
    private Messenger messenger;
    private LocalDateTime createDate;
    private ArrayList<User> users;
    private ArrayList<Commit> commits;
    private int score;


}
