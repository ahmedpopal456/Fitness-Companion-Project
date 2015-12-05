package com.example.team_foxhound.minicapstone_project.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.team_foxhound.minicapstone_project.R;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import persistence.MainHandler;

public class DetailsViewer extends AppCompatActivity {

   static int weight =0;
   static int height =0;
   static int age =0;
    boolean check =false;
    double speed = 8.5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_viewer);


        Bundle extras = getIntent().getExtras();
        final int waypoint = extras.getInt("waypoints");
        final double totaldistance = extras.getDouble("totaldistance");
        final String username3 = extras.getString("username1");

        TextView textView = (TextView) findViewById(R.id.textView26);
        TextView textView2 = (TextView) findViewById(R.id.textView27);
        TextView textView3 = (TextView) findViewById(R.id.textView28);

        ToggleButton toggleButton = (ToggleButton) findViewById(R.id.toggleButton2);


        MainHandler handler = new MainHandler(this);
        SQLiteDatabase db = handler.getReadableDatabase();


        Cursor cursor = db.rawQuery("SELECT * FROM " + "fitness ", null);
        startManagingCursor(cursor);

        while (cursor.moveToNext()) {

            if (cursor.getString(0).equals(username3)) {

                age = cursor.getInt(1);
                height = cursor.getInt(2);
                weight = cursor.getInt(3);

            }
        }

        textView.setText("Number of Waypoints : " + Integer.toString(waypoint));
        textView2.setText("Total Distance : " + Double.toString(totaldistance) + " KM");
        textView3.setText("Total Calories : " + Double.toString(CalculateCalories(totaldistance)) + " KCAL");


        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                if(buttonView.isChecked()) {

                    check = true;

                    TextView textView = (TextView) findViewById(R.id.textView28);
                    textView.setText("Total Calories : " + Double.toString(CalculateCalories(totaldistance)) + " KCAL");


                }
                else {


                    check = false;
                    TextView textView = (TextView) findViewById(R.id.textView28);
                    textView.setText("Total Calories : " + Double.toString(CalculateCalories(totaldistance)) + " KCAL");


                }
            }


        });

    }


        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_details_viewer, menu);
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


    public double CalculateCalories(double total) {

        double BMR=0;

        if (!check) {

            BMR = (6.25 * weight) + (5 * height) - (6.76 * age) + 66;    // for male

        }

        else
            {
                BMR = (4.35 * weight) + (1.85 * height) - (4.68 * age) + 655;   // for female

            }

        double time = (1/speed)*total; // in hours



return  (BMR * 1.375 * time);  // for a moderately fit person


    }
}
