package com.nieelitarni.besthackathon2019;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GitHub {
    public void sendRequest() throws Exception{
        //URL url = new URL("https://api.github.com/repos/" + repoOwner + "/" + repoName + "/commits");
        URL url = new URL("");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while((inputLine = in.readLine()) != null){
            response.append(inputLine);
        }
        in.close();

        JSONObject js = new JSONObject(response.toString());
        System.out.println(js.toString());
    }
}
