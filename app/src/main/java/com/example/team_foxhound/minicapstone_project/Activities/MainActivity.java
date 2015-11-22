package com.example.team_foxhound.minicapstone_project.Activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
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
    public static EditText editText; // password
    public static EditText editText3; // username


    SuperUser superUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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



    // Check ON LOGIN information
    public void setLogin(View v) {

        // SET EDIT BOXES
        editText = (EditText) findViewById(R.id.editText);
        editText3 = (EditText) findViewById(R.id.editText3);

        // GET READY TO READ FROM DB
        userCredentialsHandler handler = new userCredentialsHandler(this);
        SQLiteDatabase db = handler.getReadableDatabase();

        // SET CURSORS TO READ FROM DB
        Cursor cursor = db.rawQuery("SELECT * FROM " + "usercredentials ", null);
        startManagingCursor(cursor);
        int counter = 0;

        while (cursor.moveToNext()) {


            String readUsername;
            String readPassword;

            readUsername = cursor.getString(0);
            readPassword = cursor.getString(1);

            if ((editText.getText().toString().equals(readPassword)) && (editText3.getText().toString().equals(readUsername))) { // IF PASSWORD AND USERNAME ARE STORED


                Intent intent = new Intent(MainActivity.this, MainActivityHeartBeat.class);        // MOVE TO NEXT PAGE
                intent.putExtra("username", readUsername);                                          // SEND USER INFO WITH THE INTENT
               // intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);                                 // REMOVING THE DEFAULT ANIMATION
//                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
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

    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitByBackKey();

            //moveTaskToBack(false);

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }



    protected void exitByBackKey() {

        AlertDialog alertbox = new AlertDialog.Builder(this)

                .setMessage("Do you want to exit application?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {
                        finish();
                    }
                })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {

                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                })
                .show();

    }

}
