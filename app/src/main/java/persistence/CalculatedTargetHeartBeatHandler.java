package persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Nitesh on 11-Nov-15.
 */
public class CalculatedTargetHeartBeatHandler extends SQLiteOpenHelper implements com.example.team_foxhound.minicapstone_project.Interfaces.CalculatedTargetHeartBeatHandler {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "fitnessDB.db";
    private static final String FITNESS_TABLE_NAME = "calculatedtargetheartbeat";
    private static final String KEY_WORD = "username";
    private static final String HEART_BEAT ="heartbeat";
    private static final String FITNESS_TABLE_CREATE = "CREATE TABLE "
            + FITNESS_TABLE_NAME
            + " ("
            + KEY_WORD + " TEXT PRIMARY KEY,"
            + HEART_BEAT + " INTEGER"
            + ");";


    public CalculatedTargetHeartBeatHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(FITNESS_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void putCalculatedTargetHeartBeat(int heartbeat,SQLiteDatabase db ){

        ContentValues values = new ContentValues();
        values.put(HEART_BEAT,heartbeat);
        db.insert(FITNESS_TABLE_NAME, null, values);
        db.close();

    }
}
