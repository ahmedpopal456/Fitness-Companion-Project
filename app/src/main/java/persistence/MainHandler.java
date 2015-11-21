package persistence;//package persistence;
//
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.team_foxhound.minicapstone_project.Interfaces.InterfaceHandler;

//* Created by Nitesh on 19-Oct-15.

public class MainHandler extends SQLiteOpenHelper implements InterfaceHandler {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "fitnessdb.db";
    public static final String FITNESS_TABLE_NAME = "fitness";
    public static final String KEY_WORD = "username";
    public static final String AGE ="age";
    public static final String HBMAX ="hbmax";
    public static final String WEIGHT="weight";
    public static final String HEIGHT="height";
   // private static final String KEY_DEFINITION = "";
    public static final String FITNESS_TABLE_CREATE = "CREATE TABLE "
            + FITNESS_TABLE_NAME
            + " ("
            + KEY_WORD + " TEXT PRIMARY KEY,"
            + AGE + " INTEGER,"
            + WEIGHT + " INTEGER,"
            + HEIGHT + " INTEGER,"
            + HBMAX + " INTEGER"
            + ");";


    public MainHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(FITNESS_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "   + FITNESS_TABLE_CREATE);
        onCreate(db);

    }

    public void putUserInfo(int age, int weight, int height, String username,int hbmax,SQLiteDatabase db){
        ContentValues values = new ContentValues();
        values.put(KEY_WORD, username);
        values.put(AGE,age);
        values.put(HEIGHT,height);
        values.put(WEIGHT,weight);
        values.put(HBMAX,hbmax);
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


