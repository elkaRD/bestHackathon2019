package com.nieelitarni.besthackathon2019;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.TimeZone;

public class AppManager {
    private transient Context context;

    public boolean setContext(Context context) {
        this.context = context;
        if (!FileIO.doesExist(context, "myUser.bin")) { //pierwsze uruchomienie
            return false;
        }
        return true;
    }

    //singleton implementation start
    private static transient AppManager instance = null;

    private AppManager() {
        tasks = new ArrayList<>();
        users = new ArrayList<>();
        commits = new ArrayList<>();
        repoOwner = "Quazan";
        repoName = "test";


        tasks.add(new Task("0", "Task1", "do zrobienia"));
        tasks.add(new Task("1", "Task2", "do zrobienia duuuuuuuzo"));
        me = new User(users.size(), "localUser", Role.Backend);
        users.add(me);
        users.add(new User(users.size(), "paolo21d", Role.Backend));
        users.add(new User(users.size(), "robert", Role.Fronted));
        users.add(new User(users.size(), "Quazan", Role.Tester));
        getTaskById("0").addUser(getUserByName("paolo21d"));
        getTaskById("1").addUser(getUserByName("paolo21d"));
        getTaskById("1").addUser(getUserByName("Quazan"));

        //saveToFile();

    }

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
    private User me;

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<Commit> getCommits() {
        return commits;
    }

    private void setNewCommitsList(ArrayList<Commit> newCommitsList){
        commits = newCommitsList;
    }


    public void execute() {
        saveToFile();
        tasks = null;
        users = null;
        readFromFile();
        System.out.println("Test");

        try {
            sendRequest();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void saveToFile() {
        String path = new File("applicationData.json").getAbsolutePath();
        Gson json = new Gson();
        String response = json.toJson(instance);

        FileIO.save(context, "appData.json", response);
    }

    public void readFromFile() {
        String path = new File("applicationData.json").getAbsolutePath();
        String data = FileIO.read(context, "appData.json");

        Gson json = new Gson();
        AppManager tmp = json.fromJson(data, AppManager.class);
        this.users = tmp.users;
        this.tasks = tmp.tasks;
        this.commits = tmp.commits;
        this.repoName = tmp.repoName;
        this.repoOwner = tmp.repoOwner;
        this.me = tmp.me;
    }

    //Task methods
    public boolean addTask(String id, String title, String description) {
        for (Task t : tasks) {
            if (t.getTitle().equals(title))
                return false;
        }
        Task task = new Task(id, title, description);
        tasks.add(task);
        return true;
    }

    public Task getTaskById(String id) {
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

    public void assignToTask(Task t) {
        t.addUser(me);
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

    public void addMeUser(String name, Role role){
        me = new User(users.size(), name, role);
        users.add(me);
    }

    //GitHub methods
    private void sendRequest() {
        if (context == null) {
            System.out.println("context jest nullem");
            return;
        }
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);
        String url ="https://api.github.com/repos/Quazan/test/commits";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        response = "{\"commits_list\":"+response+"}";
                        JSONObject js = null;
                        JSONArray jsonArray = null;
                        try {
                            js = new JSONObject(response);
                            jsonArray = js.getJSONArray("commits_list");

                            ArrayList<Commit> newCommitsList = new ArrayList<>();

                            for(int i = 0; i < jsonArray.length(); i++){
                                String hash = null;
                                Task task = null;
                                String author = null;
                                JSONObject jso = jsonArray.getJSONObject(i);
                                hash = jso.getString("sha");
                                jso = jso.getJSONObject("commit");
                                String message = jso.getString("message");

                                jso = jso.getJSONObject("committer");
                                author = jso.getString("name");

                                String dt = jso.getString("date");

                                dt = dt.replace("T", " ");
                                dt = dt.replace("Z"," GMT+00:00" );
                                @SuppressLint("SimpleDateFormat") DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
                                formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
                                Date time = formatter.parse(dt);

                                String[] splited = message.split(" ");

                                for(String str: splited){
                                    if(str.startsWith("#T") || str.startsWith("#t")){
                                        String s = str.substring(2);
                                        task = getTaskById(s);
                                        Commit commit = new Commit(hash, author, time, message, task);
                                        newCommitsList.add(commit);
                                    }
                                }
                            }

                            setNewCommitsList(newCommitsList);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
