package com.example.team_foxhound.minicapstone_project.Activities;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.team_foxhound.minicapstone_project.InformationCatalog.RegisterInfo;
import com.example.team_foxhound.minicapstone_project.R;
import com.example.team_foxhound.minicapstone_project.UserManagement.SubUser;

public class RegistrationConfirmation extends RegisterInfo {
    SubUser subUser;
    TextView editTexttest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_confirmation);

        showfirstname(editTexttest);


    }

   public void showfirstname(TextView v){

      v = (TextView)findViewById(R.id.textView9);
       v.setText("First Name is :" +getfirstname());
   }
}
