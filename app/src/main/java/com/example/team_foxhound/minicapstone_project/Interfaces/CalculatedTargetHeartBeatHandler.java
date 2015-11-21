package com.example.team_foxhound.minicapstone_project.Interfaces;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Nitesh on 11-Nov-15.
 */
public interface CalculatedTargetHeartBeatHandler {
    public void putCalculatedTargetHeartBeat(int heartbeat,SQLiteDatabase db );
}
