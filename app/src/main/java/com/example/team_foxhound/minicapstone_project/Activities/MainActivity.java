package com.example.team_foxhound.minicapstone_project.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.team_foxhound.minicapstone_project.InformationCatalog.RegisterInfo;
import com.example.team_foxhound.minicapstone_project.R;
import com.example.team_foxhound.minicapstone_project.UserManagement.SuperUser;

public class MainActivity extends AppCompatActivity {
    public Context contextnew;
    EditText editText;
    EditText editText3;
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

    public void setLogin(View v){
      Intent intent  = new Intent(MainActivity.this, MainActivityHeartBeat.class);
      startActivity(intent);
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
