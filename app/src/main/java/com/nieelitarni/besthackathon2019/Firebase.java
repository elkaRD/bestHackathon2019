package com.nieelitarni.besthackathon2019;

import android.util.Log;

//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import static android.content.ContentValues.TAG;

public final class Firebase
{

//    public static void write(JSONObject json, String userId)
//    {
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference(userId);
//
//        myRef.setValue(json.toString());
//    }
//
//    public static JSONObject read(String userId)
//    {
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference(userId);
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot)
//            {
//                String value = dataSnapshot.getValue(String.class);
//                Log.d(TAG, "Value is: " + value);
//
//                try {
//                    TaskManager.getInstance().onDataChanged(new JSONObject(value));
//                }
//                catch (JSONException e)
//                {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error)
//            {
//                Log.w(TAG, "Failed to read value.", error.toException());
//            }
//        });
//
//        return null;
//    }
}
