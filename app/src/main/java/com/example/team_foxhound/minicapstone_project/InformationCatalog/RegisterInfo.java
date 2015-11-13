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
import android.widget.Toast;

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


                    int counter=0;

                    // First Name Validation
                    editText4 = (EditText) findViewById(R.id.editText6);
                    if(null!=editText4.getText().toString() && editText4.getText().toString().length()>0){
                        //CONTINUE
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "First Name is Empty. Please Enter a Valid Name", Toast.LENGTH_LONG).show();
                        counter = counter+1;
                    }

                    // Last Name Validation
                    editText5=(EditText) findViewById(R.id.editText7);
                    if(null!=editText5.getText().toString()&&editText5.getText().toString().length()>0){
                        //CONTINUE
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Last Name is Empty.  Please Enter a Valid Name", Toast.LENGTH_LONG).show();
                        counter = counter+1;
                    }


                 // Username Validation
                    editText6=(EditText) findViewById(R.id.editText8);
                    if(((editText6.getText().toString()!=""))&&((editText6.getText().toString().length()>6) && (editText6.getText().toString().length()<11))){
                        //CONTINUE
                    }
                    else if((!(editText6.getText().toString().isEmpty()))&&((editText6.getText().toString().length()>=11) ||(editText6.getText().toString().length()<=6)) ){
                        Toast.makeText(getApplicationContext(), "Username is Invalid. Please enter a 7 - 10 alphanumerical digit.", Toast.LENGTH_LONG).show();
                        counter = counter+1;
                    }

                    else{ Toast.makeText(getApplicationContext(), "Username is empty. Please Enter a Valid Username", Toast.LENGTH_LONG).show();
                        counter = counter+1; }



                    //  Password Validation
                    editText7=(EditText) findViewById(R.id.editText9); //Password
                    editText8=(EditText) findViewById(R.id.editText10); // Confirm Password

                    if(((editText7.getText().toString()!="")&&((editText7.getText().toString().length()>6) && (editText7.getText().toString().length()<11)))
                            &&((editText8.getText().toString()!="")&&((editText8.getText().toString().length()>6) && (editText8.getText().toString().length()<11)))
                            && (editText7.getText().toString().equals(editText8.getText().toString()))){

                        //CONTINUE
                    }
                    else if(((editText7.getText().toString()!="")&&((editText7.getText().toString().length()>=6) && (editText7.getText().toString().length()<=11)))
                            &&((editText8.getText().toString() != "")&&((editText8.getText().toString().length()>=6) && (editText8.getText().toString().length()<=11)))
                            && !(editText7.getText().toString().equals(editText8.getText().toString())) ){

                        Toast.makeText(getApplicationContext(), "Entered Passwords are not Equivalent. Please Verify.", Toast.LENGTH_LONG).show();
                        counter = counter+1;
                    }
                    else if(((editText7.getText().toString().length()<=6) || (editText7.getText().toString().length()>=11))
                            ||((editText8.getText().toString().length()<=6) || (editText8.getText().toString().length()>=11))){

                        Toast.makeText(getApplicationContext(), "Password is Invalid. Please enter a 7 - 10 alphanumerical digit.", Toast.LENGTH_LONG).show();
                        counter = counter+1;
                    }

                    // Only if there are no errors, can we go to next page

                    if (counter==0){

                        Intent intent = new Intent(RegisterInfo.this, UserInfoActivity.class);
                        startActivity(intent);

                    }

                }
            });
  }


    // GETTER FUNCTIONS
    public String getfirstname(){ return editText4.getText().toString();}
    public String getlastname(){ return editText5.getText().toString();}
    public String getusername() {return editText6.getText().toString();}
    public String getpassword(){return editText7.getText().toString();}
    public String getpassword2(){return editText8.getText().toString();}



}