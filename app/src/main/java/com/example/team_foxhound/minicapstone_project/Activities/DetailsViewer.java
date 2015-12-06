package com.example.team_foxhound.minicapstone_project.Activities;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.team_foxhound.minicapstone_project.R;

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
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFAE1B")));

        TextView textView = (TextView) findViewById(R.id.textView26);
        TextView textView2 = (TextView) findViewById(R.id.textView27);
        TextView textView3 = (TextView) findViewById(R.id.textView28);

       final Switch aSwitch = (Switch) findViewById(R.id.switch2);


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

        textView.setText(Integer.toString(waypoint));
        textView2.setText( Double.toString(totaldistance) + " KM");
        textView3.setText( Double.toString(CalculateCalories(totaldistance)) + " KCAL");


        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                if(buttonView.isChecked()) {

                    check = true;
                    aSwitch.setText("Female");

                    TextView textView = (TextView) findViewById(R.id.textView28);
                    textView.setText(Double.toString(CalculateCalories(totaldistance)) + " KCAL");


                }
                else {


                    check = false;
                    aSwitch.setText("Male");

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
