package com.example.team_foxhound.minicapstone_project.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.team_foxhound.minicapstone_project.Activities.MapsActivity;
import com.example.team_foxhound.minicapstone_project.R;

import java.util.ArrayList;

public class MainHub extends AppCompatActivity  {



    private ActionBarDrawerToggle actionBarDrawerToggle;
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_hub);
        Bundle extras = getIntent().getExtras();;
        final String username = extras.getString("username");

//==========================================================================================
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.drawerlist);
        ArrayList<String> listArray = new ArrayList<String>();
        listArray.add("Fitness Home");
        listArray.add("MonitorNow");
        listArray.add("MyProgress");
        listArray.add("PredictNow");
        listArray.add("Profile");
        listArray.add("Settings");
        listArray.add("Log Out");

        drawerList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, listArray);
        drawerList.setAdapter(adapter);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.opendrawer, R.string.closedrawer);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);


        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        fragmentManager = getSupportFragmentManager();

//            loadSelection(0);

        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                switch(position) {

                    case 0: {

                        drawerLayout.closeDrawer(drawerList);
                        break;
                    }
                    case 1:
                           Intent newActivity1 = new Intent(MainHub.this, MainActivityHeartBeat.class);
                           newActivity1.putExtra("username3",username);
//                           newActivity1.putExtra("targethb", targethb);
                           startActivity(newActivity1);
                           break;

                    case 3:

                        Intent newActivity2 = new Intent(MainHub.this, MapsActivity.class);
                        newActivity2.putExtra("username3",username);
                        startActivity(newActivity2);
                        drawerLayout.closeDrawer(drawerList);
                        break;

                    case 2:

                        Intent intent  = new Intent(MainHub.this, ProgressView.class);
                        intent.putExtra("username2", username);
                        startActivity(intent);
                        break;

                    case 4:
//                            Intent newActivity3 = new Intent(this, karaiskaki.class);
//                            startActivity(newActivity3);
//                            break;
                    case 5:
//                            Intent newActivity4 = new Intent(this, reservetickets.class);
//                            startActivity(newActivity4);
//                            break;

                    case 6:

                        exit();
                        break;

                }

            }
        });

        //==========================================================================================

    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }else if (id == android.R.id.home){
            if (drawerLayout.isDrawerOpen(drawerList)){
                drawerLayout.closeDrawer(drawerList);
            }else{
                drawerLayout.openDrawer(drawerList);
            }
        }

        return super.onOptionsItemSelected(item);
    }



    public boolean onexit() {


        exit();

            return true;
    }



    protected void exit() {



        AlertDialog alertbox = new AlertDialog.Builder(this)

                .setMessage("Do you want to sign out of the application?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {
                        finish();
                    }
                })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {

                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                })
                .show();

    }



}