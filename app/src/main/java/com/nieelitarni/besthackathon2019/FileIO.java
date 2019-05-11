package com.nieelitarni.besthackathon2019;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

public class FileIO
{
    public static void save(Context context, String fileName, String json)
    {
        try {
            FileWriter file = new FileWriter("/data/" + context.getPackageName() + "/" + fileName);
            file.write(json);
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String read(Context context, String fileName) {
        String result = null;
        try {
            File f = new File("/data/" + context.getPackageName() + "/" + fileName);
            FileInputStream is = new FileInputStream(f);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            result = new String(buffer);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}