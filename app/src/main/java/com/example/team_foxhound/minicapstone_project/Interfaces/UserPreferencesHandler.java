package com.example.team_foxhound.minicapstone_project.Interfaces;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Nitesh on 11-Nov-15.
 */
public interface UserPreferencesHandler {
    public void putUserCredentials(String username, String password, String fname, String lname,SQLiteDatabase db);
}
