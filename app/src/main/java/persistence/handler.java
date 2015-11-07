package persistence;//package persistence;
//
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.team_foxhound.minicapstone_project.Interfaces.InterfaceHandler;

//* Created by Nitesh on 19-Oct-15.

public class Handler extends SQLiteOpenHelper implements InterfaceHandler {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "fitnessDB.db";
    private static final String FITNESS_TABLE_NAME = "fitness";
    private static final String KEY_WORD = "username";
    private static final String AGE ="age";
    private static final String WEIGHT="weight";
    private static final String HEIGHT="height";
    private static final String KEY_DEFINITION = "";
    private static final String FITNESS_TABLE_CREATE = "CREATE TABLE "
            + FITNESS_TABLE_NAME
            + " ("
            + KEY_WORD + " TEXT PRIMARY KEY"
            + AGE + " INTEGER"
            + WEIGHT + " INTEGER"
            + HEIGHT + " INTEGER"
            + KEY_DEFINITION + " User Credentials"+");";


    public Handler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(FITNESS_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    SQLiteDatabase db = this.getWritableDatabase();
    public void putUserInfo(int age, int weight, int height){
        ContentValues values = new ContentValues();
        values.put(AGE,age);
        values.put(HEIGHT,height);
        values.put(WEIGHT,weight);
        db.insert(FITNESS_TABLE_NAME, null, values);
        db.close();

    }
//    public void putUserCredentials(){
//
//    }
//    public void putCalculatedTargetHeartBeat(){
//
//    }
}


