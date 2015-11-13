package com.example.team_foxhound.minicapstone_project.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.team_foxhound.minicapstone_project.InformationCatalog.RegisterInfo;
import com.example.team_foxhound.minicapstone_project.R;
import com.example.team_foxhound.minicapstone_project.UserManagement.SubUser;

import org.w3c.dom.Text;

public class RegistrationConfirmation extends RegisterInfo {


    TextView fname;
    TextView lname;
    TextView username;
    TextView password;
    TextView age;
    TextView weight;
    TextView height;
    SubUser subUser=  new SubUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_confirmation);

         fname = (TextView)findViewById(R.id.textView9);
         lname = (TextView)findViewById(R.id.textView10);
         username = (TextView)findViewById(R.id.textView13);
         password = (TextView)findViewById(R.id.textView14);
         age = (TextView)findViewById(R.id.textView15);
         height = (TextView)findViewById(R.id.textView16);
         weight = (TextView)findViewById(R.id.textView17);

        fname.setText("The First Name entered:  "+getfirstname());
        lname.setText("The Last Name entered:  "+getlastname());
        username.setText("The Username entered:  "+getusername());
        password.setText("The Password entered:  " + getpassword());

       age.setText("The Age entered:  " + getAge());
       height.setText("The Height entered:  " + getHeight());
       weight.setText("The Weight entered:  " + getWeight());


  }



public void createUser(View v) {

    subUser.setfname(subUser, getfirstname());
    subUser.setlname(subUser, getlastname());
    subUser.setusername(subUser, getusername());
    subUser.setpassword(subUser, getpassword());

    Intent intent = new Intent(RegistrationConfirmation.this, MainActivity.class);
    startActivity(intent);



}


}