package com.example.team_foxhound.minicapstone_project.InformationCatalog;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.team_foxhound.minicapstone_project.Activities.UserInfoActivity;
import com.example.team_foxhound.minicapstone_project.R;
import com.example.team_foxhound.minicapstone_project.UserManagement.SubUser;

public class RegisterInfo extends UserInfoActivity {
    SubUser subUser;
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
    public void setNextButton(View v){
        Intent intent = new Intent(RegisterInfo.this,UserInfoActivity.class);
        startActivity(intent);
    }
    public void setfirstname(View v){
        editText4=(EditText)findViewById(R.id.editText6);
        subUser.setfname(editText4.toString());
    }

}
