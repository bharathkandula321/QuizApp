package com.example.quizapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;
import com.google.gson.JsonSerializer;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.Queue;
 import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import  com.example.quizapp.ResultActivity;

public class DataStoring {


    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    private Context context;
    private static Gson gson;
    private static final String key = "quizapp";
    private static DataStoring singleTonInstance = null;
    public static final String PREF_NAME  = "quizapp";
    private static final int PRIVATE_MODE = 0;
    Queue<UserData> q;

    public static DataStoring getInstance(Context context) {

        if (singleTonInstance == null) {

            singleTonInstance = new DataStoring(context);
        }
        return singleTonInstance;
    }

    DataStoring(Context context) {

        super();
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME ,PRIVATE_MODE);
        gson = new Gson();
        editor = sharedPreferences.edit();
        editor.commit();
    }


    public static void storeData(Queue<UserData> objectArray) throws IOException {

        /*ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(objectArray);
        oos.flush();
        byte[] base64str = Base64.encode(bos.toByteArray(), Base64.DEFAULT);
        editor.putString(PREF_NAME , String.valueOf(base64str));
        editor.commit();
        editor.apply();*/

        editor.putString(key, gson.toJson(objectArray));
        editor.commit();
        editor.apply();
    }

    public Queue<UserData> getData() {

        String resp = sharedPreferences.getString(key, "");
        //Gson gson = new Gson();
        java.lang.reflect.Type type = new TypeToken<Queue<UserData>>() {
        }.getType();

        if (gson.fromJson(resp, type) == null) {

            return null;
        } else {

            return gson.fromJson(resp, type);
        }
    }

    void data1(String userName,int score) throws IOException {

        q = getData();
        if (q != null  ) {

            if (q.size() < 9) {

                q.add(new UserData(userName,score));
                storeData(q);
            }
            else {

                q.remove();
                q.add(new UserData(userName,score));
                storeData(q);
            }


        }
        else {

            q = new LinkedList<UserData>();
            q.add(new UserData(userName,score));
            storeData(q);
        }
    }
}
