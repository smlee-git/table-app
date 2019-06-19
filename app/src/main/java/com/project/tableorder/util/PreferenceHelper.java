package com.project.tableorder.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceHelper {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private static PreferenceHelper instance;

    public PreferenceHelper(Context context) {
        this.pref = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
        this.editor = pref.edit();
    }

    public static PreferenceHelper getInstance(Context context) {
        if (instance == null) {
            instance = new PreferenceHelper(context);
        }
        return instance;
    }

    public String getStringPreference(String key, String defaultValue) {
        return pref.getString(key, defaultValue);
    }

    public void setStringPreference(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }
}
