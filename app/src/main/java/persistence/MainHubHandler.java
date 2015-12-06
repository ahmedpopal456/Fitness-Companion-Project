package persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.team_foxhound.minicapstone_project.Activities.MainHub;

/**
 * Created by Ahmed on 11/20/2015.
 */
public class MainHubHandler extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "mhh.db";
    public static final String TABLE_NAME = "mainhub";
    public static final String TEXT="text";

    // private static final String KEY_DEFINITION = "";
    public static final String FITNESS_TABLE_CREATE = "CREATE TABLE "
            + TABLE_NAME
            + " ("
            + TEXT + " TEXT"
            + ");";


    public MainHubHandler(Context context) {

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

    public void putString(String string,SQLiteDatabase db){

        ContentValues values = new ContentValues();
        values.put(TEXT,string);
        db.insert(TABLE_NAME, null, values);
        db.close();

    }

    public void deletetable(SQLiteDatabase db){

        db.execSQL("DELETE FROM " + TABLE_NAME);
        db.close();
    }


}



