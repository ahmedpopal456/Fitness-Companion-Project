package persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ahmed on 11/20/2015.
 */
public class HBInfoHandler extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "hbinfo.db";
    public static final String TABLE_NAME = "heartbeatinfo";
    public static final String KEY_WORD = "username";
    public static final String HEART_BEAT_AVERAGE= "heartbeat_ave";
    public static final String ON_TARGET= "ontarget";
    public static final String DATE= "date";


    // private static final String KEY_DEFINITION = "";
    public static final String FITNESS_TABLE = "CREATE TABLE "
            + TABLE_NAME
            + " ("
            + KEY_WORD + " TEXT,"
            + HEART_BEAT_AVERAGE + " DOUBLE,"
            + ON_TARGET + " DOUBLE,"
            + DATE + " TEXT"
            + ");";


    public HBInfoHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(FITNESS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "   + FITNESS_TABLE);
        onCreate(db);

    }

    public void putHb(double averageHb,double ontarget,String user,String date, SQLiteDatabase db){

        ContentValues values = new ContentValues();
        values.put(KEY_WORD,user);
        values.put(HEART_BEAT_AVERAGE,averageHb);
        values.put(ON_TARGET,ontarget);
        values.put(DATE,date);
        db.insert(TABLE_NAME, null, values);
        db.close();

    }


//    public void putUserCredentials(){
//
//    }
//    public void putCalculatedTargetHeartBeat(){
//
//    }
}


