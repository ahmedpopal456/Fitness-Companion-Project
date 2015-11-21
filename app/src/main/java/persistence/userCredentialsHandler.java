package persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.team_foxhound.minicapstone_project.Interfaces.UserPreferencesHandler;

/**
 * Created by Nitesh on 11-Nov-15.
 */
public class userCredentialsHandler extends SQLiteOpenHelper implements UserPreferencesHandler{

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "fitnessDB.db";
    private static final String FITNESS_TABLE_NAME = "usercredentials";
    private static final String KEY_WORD = "username";
    private static final String PASSWORD ="password";
    private static final String FIRST_NAME="fname";
    private static final String LAST_NAME="lname";
    private static final String KEY_DEFINITION = "";
    private static final String FITNESS_TABLE_CREATE = "CREATE TABLE "
            + FITNESS_TABLE_NAME
            + " ("
            + KEY_WORD + " TEXT PRIMARY KEY,"
            + PASSWORD + " TEXT,"
            + FIRST_NAME + " TEXT,"
            + LAST_NAME + " TEXT"
            +");";


    public userCredentialsHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(FITNESS_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void putUserCredentials(String username, String password, String fname, String lname, SQLiteDatabase db){
        ContentValues values = new ContentValues();
        values.put(KEY_WORD,username);
        values.put(PASSWORD,password);
        values.put(FIRST_NAME,fname);
        values.put(LAST_NAME,lname);
        db.insert(FITNESS_TABLE_NAME, null, values);
        db.close();

    }
}
