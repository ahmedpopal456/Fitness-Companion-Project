package com.example.team_foxhound.minicapstone_project.Activities;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.team_foxhound.minicapstone_project.R;

import persistence.HBInfoHandler;
import persistence.MainHandler;
import persistence.userCredentialsHandler;

public class ProfileView extends AppCompatActivity {

    String Firstname;
    String Lastname;
    static int age;
    static int weight;
    static int height;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);

        Bundle extras = getIntent().getExtras();
        final String username = extras.getString("username2");

        TextView tv = (TextView) findViewById(R.id.textView29);
        TextView tv1 = (TextView) findViewById(R.id.textView30);
        TextView tv2 = (TextView) findViewById(R.id.textView31);
        TextView tv3 = (TextView) findViewById(R.id.textView32);
        TextView tv4 = (TextView) findViewById(R.id.textView33);


        // GET READY TO READ FROM DB
        userCredentialsHandler handler = new userCredentialsHandler(this);
        SQLiteDatabase db = handler.getReadableDatabase();

        // SET CURSORS TO READ FROM DB
        Cursor cursor = db.rawQuery("SELECT * FROM " + "usercredentials ", null);
        startManagingCursor(cursor);

        while (cursor.moveToNext()) {


            if (cursor.getString(0).equals(username)) {


                Firstname= cursor.getString(2);
                Lastname= cursor.getString(3);

            }
        }

        handler.close();
        cursor.close();
        db.close();


        // GET READY TO READ FROM DB
        MainHandler handler2 = new MainHandler(this);
        SQLiteDatabase db2 = handler2.getReadableDatabase();

        // SET CURSORS TO READ FROM DB
        Cursor cursor2 = db2.rawQuery("SELECT * FROM " + "fitness ", null);
        startManagingCursor(cursor);

        while (cursor2.moveToNext()) {


            if (cursor2.getString(0).equals(username)) {


                age = cursor2.getInt(1);
                weight = cursor2.getInt(2);
                height = cursor2.getInt(3);

            }
        }




        tv.setText("First Name : " + Firstname);
        tv1.setText("Last Name : " + Lastname);
        tv2.setText("Age : " + Integer.toString(age));
        tv3.setText("Weight : " + Integer.toString(weight)+ " lbs");
        tv4.setText("Height : " + Integer.toString(height)+ " cm");




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
