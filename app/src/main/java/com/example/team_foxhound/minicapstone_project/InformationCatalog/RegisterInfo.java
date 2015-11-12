package com.example.team_foxhound.minicapstone_project.InformationCatalog;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;

import com.example.team_foxhound.minicapstone_project.Activities.UserInfoActivity;
import com.example.team_foxhound.minicapstone_project.R;



public class RegisterInfo extends UserInfoActivity {

    public static String fNAME;
    public static EditText editText4;
    public static EditText editText5;
    public static EditText editText6;
    public static EditText editText7;
    public static EditText editText8;
    public static Button CLICK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_info);

       CLICK = (Button) findViewById(R.id.button6);
       CLICK.setOnClickListener(

            new View.OnClickListener() {

                public void onClick(View view) {

                    editText5=(EditText) findViewById(R.id.editText7);
                    editText6=(EditText) findViewById(R.id.editText8);
                    editText7=(EditText) findViewById(R.id.editText9);
                    editText4 = (EditText) findViewById(R.id.editText6);

                }
            });
  }



    // GETTER FUNCTIONS
    public String getfirstname(){ return editText4.getText().toString();}
    public String getlastname(){ return editText5.getText().toString();}
    public String getusername() {return editText6.getText().toString();}
    public String getpassword(){return editText7.getText().toString();}
    public String getpassword2(){return editText8.getText().toString();}


    // SETTING NEXT BUTTON
    public void setNextButton(View v){

        Intent intent = new Intent(RegisterInfo.this, UserInfoActivity.class);
        startActivity(intent);
    }


}