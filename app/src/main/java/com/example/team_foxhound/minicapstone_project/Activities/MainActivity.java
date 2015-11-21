package com.example.team_foxhound.minicapstone_project.Activities;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.team_foxhound.minicapstone_project.InformationCatalog.RegisterInfo;
import com.example.team_foxhound.minicapstone_project.R;
import com.example.team_foxhound.minicapstone_project.UserManagement.SuperUser;

import persistence.userCredentialsHandler;

public class MainActivity extends AppCompatActivity {
    public Context contextnew;
    public static EditText editText; // password
    public static EditText editText3; // username


    SuperUser superUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contextnew=getApplicationContext();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void setLogin(View v) {

        // SET EDIT BOXES
        editText = (EditText) findViewById(R.id.editText);
        editText3 = (EditText) findViewById(R.id.editText3);

        // GET READY TO READ FROM DB
        userCredentialsHandler handler = new userCredentialsHandler(this);
        SQLiteDatabase db = handler.getReadableDatabase();


        Cursor cursor = db.rawQuery("SELECT * FROM " + "usercredentials ", null);
        startManagingCursor(cursor);
        int counter = 0;

        while (cursor.moveToNext()) {


            String readUsername;
            String readPassword;

            readUsername = cursor.getString(0);
            readPassword = cursor.getString(1);

            if ((editText.getText().toString().equals(readPassword)) && (editText3.getText().toString().equals(readUsername))) {


                Intent intent = new Intent(MainActivity.this, MainActivityHeartBeat.class);
                intent.putExtra("username",readUsername);
                startActivity(intent);
                counter++;

            }

        }

        if (counter == 0) {
            Toast.makeText(getApplicationContext(), "Username and/or Password does not exist or is wrong.", Toast.LENGTH_LONG).show();
        }
    }

    public void setSignUP(View v){
        Intent intent  = new Intent(MainActivity.this, RegisterInfo.class);
        startActivity(intent);
    }
    public void setUsername(View v){
        editText = (EditText) findViewById(R.id.editText3);
        superUser.setUsername(editText);
    }
    public void setPassword(View v) {
        editText3 = (EditText) findViewById((R.id.editText3));
        superUser.setPassword(editText3);
    }

}
