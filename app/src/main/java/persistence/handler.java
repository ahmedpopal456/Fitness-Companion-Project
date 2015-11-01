//package persistence;
//
//import android.content.Context;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
///**
// * Created by Nitesh on 19-Oct-15.
// */
//public class handler extends SQLiteOpenHelper{
//    private static final int DATABASE_VERSION = 2;
//    private static final String DATABASE_NAME = "fitnessDB" ;
//    private static final String FITNESS_TABLE_NAME = "fitness";
//    private static final String KEY_WORD = "" ;
//    private static final String KEY_DEFINITION = "";
//    private static final String FITNESS_TABLE_CREATE =
//            "CREATE TABLE " + FITNESS_TABLE_NAME + " (" +
//                    KEY_WORD + " User Name " +
//                    KEY_DEFINITION + " User Credentials);";
//
//
//    handler(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        db.execSQL(FITNESS_TABLE_CREATE);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//
//    }
//
//}
