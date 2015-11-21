package com.example.team_foxhound.minicapstone_project.Activities;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.team_foxhound.minicapstone_project.AlgorithmGenerator.AlgorithmGenerator;
import com.example.team_foxhound.minicapstone_project.InformationCatalog.RegisterInfo;
import com.example.team_foxhound.minicapstone_project.R;
import com.example.team_foxhound.minicapstone_project.UserManagement.SubUser;

import persistence.MainHandler;
import persistence.userCredentialsHandler;

public class RegistrationConfirmation extends RegisterInfo {

    public static Button CLICK2;
    public static Button CLICK3;
    TextView fname;
    TextView lname;
    TextView username;
    TextView password;
    TextView age;
    TextView weight;
    TextView height;

    MainHandler mainHandler = new MainHandler(this);
    userCredentialsHandler handler_1 = new userCredentialsHandler(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);                                        // SETTING UP PAGE TO SHOW ALL OF THE
        setContentView(R.layout.activity_registration_confirmation);          // USER'S ENTERED INFORMATION
        // IF THE USER WISHES TO CHANGE
        fname = (TextView) findViewById(R.id.textView9);                         // HIS/HER INFO, HE/SHE CAN GO BACK
        lname = (TextView) findViewById(R.id.textView10);
        username = (TextView) findViewById(R.id.textView13);
        password = (TextView) findViewById(R.id.textView14);
        age = (TextView) findViewById(R.id.textView15);
        height = (TextView) findViewById(R.id.textView16);
        weight = (TextView) findViewById(R.id.textView17);

        fname.setText("The First Name entered:  " + getfirstname());
        lname.setText("The Last Name entered:  " + getlastname());
        username.setText("The Username entered:  " + getusername());
        password.setText("The Password entered:  " + getpassword());

        age.setText("The Age entered:  " + getAge());
        height.setText("The Height entered:  " + getHeight());
        weight.setText("The Weight entered:  " + getWeight());


        CLICK2 = (Button) findViewById(R.id.button8);
        CLICK3 = (Button) findViewById(R.id.button4);
        CLICK2.setOnClickListener(

                new View.OnClickListener() {

                    public void onClick(View view) {


                        SubUser subUser = new SubUser();
                        AlgorithmGenerator algorithm = new AlgorithmGenerator();


                        // Setting user's credentials
                        subUser.setfname(subUser, getfirstname());
                        subUser.setlname(subUser, getlastname());
                        subUser.setusername(subUser, getusername());
                        subUser.setpassword(subUser, getpassword());

                        // Setting user's information
                        subUser.setAge(getAge(), subUser);
                        subUser.setHeight(getHeight(), subUser);
                        subUser.setWeight(getWeight(), subUser);

                        SQLiteDatabase database = mainHandler.getWritableDatabase();
                        SQLiteDatabase database1 = handler_1.getWritableDatabase();

                        handler_1.putUserCredentials(subUser.getusername(), subUser.getpassword(), subUser.getfirstname(), subUser.getlastname(), database1);
                        mainHandler.putUserInfo(getAge(), subUser.getWeight(), subUser.getHeight(), subUser.getusername(), algorithm.CalculateHBmax(getAge()), database);


                        // User created
                        Toast.makeText(getApplicationContext(), "User has been created", Toast.LENGTH_LONG).show();


                        // Intent for the next activity                                                     //USER REGISTERED, GO BACK TO MAIN ACTIVITY


//                        Intent intent = new Intent(RegistrationConfirmation.this, MainActivity.class);
//                        startActivity(intent);
                        finish();


                    }


                });


        CLICK3.setOnClickListener(

                new View.OnClickListener() {

                    public void onClick(View view) {

                        Intent intent = new Intent(RegistrationConfirmation.this, RegisterInfo.class);
                        startActivity(intent);
                        finish();

                    }
                });


    }
}