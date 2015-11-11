package com.example.team_foxhound.minicapstone_project.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.team_foxhound.minicapstone_project.InformationCatalog.RegisterInfo;
import com.example.team_foxhound.minicapstone_project.R;

public class UserInfoActivity extends AppCompatActivity {
        RegisterInfo registerInfo;
        public EditText editText;
        public EditText editText2;
        public EditText editText3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_info, menu);
//        editText = (EditText)findViewById(R.id.editText2);
//        editText2 = (EditText)findViewById(R.id.editText4);
//        editText3 = (EditText)findViewById(R.id.editText5);

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


    public void setAge(View v){
           editText = (EditText)findViewById(R.id.editText2);

            registerInfo.setAge(editText);
        }
    public void setHeight(View v){
        editText2 = (EditText)findViewById(R.id.editText4);
        registerInfo.setHeight(editText2);
    }
    public void setWeight(View v){
        editText3 = (EditText)findViewById(R.id.editText5);
        registerInfo.setWeight(editText3);
    }
    public void setGoogle(View v){
        Intent intent = new Intent(UserInfoActivity.this,MapsActivity.class);
        startActivity(intent);
    }
    public void setNext(View v){
        Intent intent = new Intent(UserInfoActivity.this,MainActivity.class);
        startActivity(intent);
    }
}
