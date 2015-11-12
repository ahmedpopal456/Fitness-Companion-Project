package com.example.team_foxhound.minicapstone_project.InformationCatalog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.team_foxhound.minicapstone_project.Activities.UserInfoActivity;
import com.example.team_foxhound.minicapstone_project.R;

public class RegisterInfo extends UserInfoActivity {
    public EditText editText4;
    public EditText editText5;
    public EditText editText6;
    public EditText editText7;
    public EditText editText8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_info);
    }


    // SETTER FUNCTIONS

    public void setfirstname(View v){

        editText4=(EditText)findViewById(R.id.editText6);
    }
    public void setlastname(View v){
    }
    public void setusername(View v){
    }
    public void setpassword(View v){
    }
    public void setpassword2(View v){
    }

    //GETTER FUNCTIONS
    public String getfirstname(){

        return editText4.getText().toString();
    }
    public String getlastname(){
        return editText5.toString();
    }
    public String getusername(){
        return editText6.toString();
    }
    public String getpassword(){
        return editText7.toString();
    }
    public String getpassword2(){
        return editText8.toString();
    }

    public void setNextButton(View v){
       editText4=(EditText)findViewById(R.id.editText6);
        editText5=(EditText) findViewById(R.id.editText7);
        editText6=(EditText) findViewById(R.id.editText8);
        editText8=(EditText) findViewById(R.id.editText10);
        Intent intent = new Intent(RegisterInfo.this,UserInfoActivity.class);
        startActivity(intent);

    }
}
