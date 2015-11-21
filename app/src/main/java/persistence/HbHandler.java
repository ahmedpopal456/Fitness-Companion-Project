package persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ahmed on 11/20/2015.
 */
public class HbHandler extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "hb.db";
    public static final String TABLE_NAME = "heartbeat";
    // public static final String KEY_WORD = "username";
    public static final String HEART_BEAT="heartbeat_val";

    // private static final String KEY_DEFINITION = "";
    public static final String FITNESS_TABLE_CREATE = "CREATE TABLE "
            + TABLE_NAME
            + " ("
            + HEART_BEAT + " INTEGER"
            + ");";


    public HbHandler(Context context) {
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

    public void putHb(int Hb,SQLiteDatabase db){

        ContentValues values = new ContentValues();
        values.put(HEART_BEAT,Hb);
        db.insert(TABLE_NAME, null, values);
        db.close();

    }

    public void deletetable(SQLiteDatabase db){

        db.execSQL("DELETE FROM " + TABLE_NAME);
        db.close();
    }


//    public void putUserCredentials(){
//
//    }
//    public void putCalculatedTargetHeartBeat(){
//
//    }
}



