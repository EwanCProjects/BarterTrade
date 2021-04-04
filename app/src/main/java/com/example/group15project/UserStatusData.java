package com.example.group15project;

import android.content.Context;
import android.content.SharedPreferences;


import com.google.gson.Gson;

import static android.content.Context.MODE_PRIVATE;

public class UserStatusData {
    /**
     * get user Email
     */
    public static String getEmail(Context context) {
        return getUserPreferenceData("email", context);
    }

    /**
     * get userId
     */
    public static String getUserID(Context context) {
        if(!getUserPreferenceData("email", context).equals("")){
            return getUserPreferenceData("email", context).replace(".", ";");
        }
        return null;
    }

    public static String getUserPreferenceData(String key, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                "UserPreferences", MODE_PRIVATE);

        return sharedPreferences.getString(key, "");
    }


    public static FilterPreferences getUserFilterPrefs(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                "UserPreferences", MODE_PRIVATE);

        Gson gson = new Gson();
        String json = sharedPreferences.getString("UserPrefs", null);

        FilterPreferences userPrefs = null;

        if(json != null){
            userPrefs = gson.fromJson(json, FilterPreferences.class);
        }

        return userPrefs;
    }

}
