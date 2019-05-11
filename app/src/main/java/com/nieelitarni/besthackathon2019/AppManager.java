package com.nieelitarni.besthackathon2019;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AppManager {
    //singleton implementation start
    private AppManager() {
        tasks = new ArrayList<>();
        users = new ArrayList<>();
        commits = new ArrayList<>();
        repoOwner = "Quazan";
        repoName = "test";

        tasks.add(new Task(tasks.size(), "Task1", "do zrobienia"));
        tasks.add(new Task(tasks.size(), "Task2", "do zrobienia duuuuuuuzo"));
        users.add(new User(users.size(), "paolo21d", Role.Backend));
        users.add(new User(users.size(), "robert", Role.Fronted));
        users.add(new User(users.size(), "Quazan", Role.Tester));
        getTaskById(0).addUser(getUserByName("paolo21d"));
        getTaskById(1).addUser(getUserByName("paolo21d"));
        getTaskById(1).addUser(getUserByName("Quazan"));

        saveToFile();
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

    public void saveToFile() {
        String path = new File("applicationData.json").getAbsolutePath();
        Gson json = new Gson();
        String response = json.toJson(this);

        try {
            FileWriter myWriter = new FileWriter(path);
            myWriter.write(response);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFromFile() {
        String path = new File("applicationData.json").getAbsolutePath();
        String data = "";
        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data += myReader.nextLine();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();

        }

        Gson json = new Gson();
        AppManager tmp = json.fromJson(data, AppManager.class);
        this.users = tmp.users;
        this.tasks = tmp.tasks;
        this.commits = tmp.commits;
    }

    //Task methods
    public boolean addTask(Integer id, String title, String description) {
        for (Task t : tasks) {
            if (t.getTitle().equals(title))
                return false;
        }
        Task task = new Task(id, title, description);
        tasks.add(task);
        return true;
    }

    public Task getTaskById(Integer id) {
        for (Task t : tasks) {
            if (t.getId().equals(id))
                return t;
        }
        return null;
    }

    public Task getTaskByTitle(String title) {
        for (Task t : tasks) {
            if (t.getTitle().equals(title))
                return t;
        }
        return null;
    }

    //User methods
    public boolean addUser(Integer id, String name, Role role) {
        for (User u : users) {
            if (u.getName().equals(name))
                return false;
        }
        User user = new User(id, name, role);
        users.add(user);
        return true;
    }

    public User getUserById(Integer id) {
        for (User u : users) {
            if (u.getId().equals(id))
                return u;
        }
        return null;
    }

    public User getUserByName(String name) {
        for (User u : users) {
            if (u.getName().equals(name))
                return u;
        }
        return null;
    }
}
