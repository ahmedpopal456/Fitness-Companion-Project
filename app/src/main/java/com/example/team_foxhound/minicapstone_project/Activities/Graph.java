package com.example.team_foxhound.minicapstone_project.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.team_foxhound.minicapstone_project.R;
//import com.google.android.gms.fitness.data.DataPoint;
//import com.jjoe64.*;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import persistence.HBInfoHandler;
import persistence.HbHandler;
import persistence.userCredentialsHandler;

public class Graph extends AppCompatActivity  {



    RelativeLayout R1;
    int i =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        Bundle extras = getIntent().getExtras();
        int targethb = extras.getInt("targethb");
        String username = extras.getString("username1");


//============================================================================================================== SHOW GRAPH WITH THE REAL-TIME VALUES TAKEN IN EARLIER
        // Get current time of the day
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        Calendar cal = Calendar.getInstance();

        // Set graph, its title, parameters
        GraphView graph = (GraphView) findViewById(R.id.graph);
//        graph.setLayoutParams()
     //   graph.setLayoutParams(new GridLayout.LayoutParams());

        graph.setTitle("Heartbeat Progress With Time" + " On " + dateFormat.format(cal.getTime()));

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0.0);

        graph.getViewport().setScrollable(true);
        graph.getViewport().setScalable(true);
        graph.getViewport().setBackgroundColor(Color.WHITE);




        // Set the graph to be of type line series
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>();
        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<DataPoint>();
        LineGraphSeries<DataPoint> series3 = new LineGraphSeries<DataPoint>();
        LineGraphSeries<DataPoint> series4 = new LineGraphSeries<DataPoint>();

       // Set series' colors
        series.setColor(Color.BLUE);
        series2.setColor(Color.GREEN);
        series3.setColor(Color.RED);
        series4.setColor(Color.RED);

        series.setTitle("Current HB");
        series2.setTitle("Target HB");
        series3.setTitle("HB");
        series4.setTitle("Range");

        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.BOTTOM);


        // Set series'thickness
        series.setThickness(5);
        series2.setThickness(5);
        series3.setThickness(5);
        series4.setThickness(5);



        // Initialize handler and database
        HbHandler mainhandler = new HbHandler(this);



// FOR TESTING PURPOSES

//     SQLiteDatabase database = mainhandler.getWritableDatabase();
//     mainhandler.putHb(10,database);
//     mainhandler.putHb(11,database);

// STOP TESTING


        SQLiteDatabase database = mainhandler.getReadableDatabase();

        // Set cursor to read from DB
        Cursor cursor = database.rawQuery("SELECT * FROM " + "heartbeat ", null);
        startManagingCursor(cursor);

        double counter =0.0;     // counter for time
        double counter2 = 0.0;  // counter for comparison
        int counter1=0;         // how much data was collected
        double averageHB =0;   // add all the data in it

        TextView textView = (TextView) findViewById(R.id.textView22);
        TextView textView2 = (TextView) findViewById(R.id.textView23);

        while (cursor.moveToNext()&&database.getMaximumSize()!=0) {
//
            double number = cursor.getDouble(0);
            averageHB = averageHB + number;
//
            series.appendData(new DataPoint(counter, number), true, 10000);
            series2.appendData(new DataPoint(counter, targethb),true,10000);
            series3.appendData(new DataPoint(counter, targethb*1.20),true,10000);
            series4.appendData(new DataPoint(counter, targethb*0.8),true,10000);
//
            counter = counter + 0.5;
            counter1++;


            if((number>(targethb*0.8)) && (number < (targethb*1.2))){

                counter2 = counter2 +0.5;
            }
        }

        //series.appendData(new DataPoint(1,1),true, 10000);
        graph.addSeries(series);
        graph.addSeries(series2);
        graph.addSeries(series3);
        graph.addSeries(series4);


        textView.setText(Double.toString(averageHB/counter1));
        textView2.setText((Double.toString(counter2/counter))+" %");



        cursor.close();
        database.close();
        mainhandler.close();
//

//==============================================================================================================    STORE GRAPH SCREENSHOT


        Button but = (Button) findViewById(R.id.button5);
        but.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                R1 = (RelativeLayout) findViewById(R.id.Relative);
                View v1 = R1.getRootView();
                v1.setDrawingCacheEnabled(true);
                Bitmap bm = v1.getDrawingCache();



                String filename = "screenshot" + i + ".jpg";
                File directory = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Screenshots");
                directory.mkdirs();


                File file = new File(directory, filename);

                while (file.exists()) {
                    i++;
                    filename = "screenshot" + i + ".jpg";
                    file = new File(directory, filename);
                }

                if (!file.exists()) {

                    try {

                        FileOutputStream fOut = new FileOutputStream(file, true);
                        bm.compress(Bitmap.CompressFormat.PNG, 85, fOut);
                        fOut.flush();
                        fOut.close();
                        Toast.makeText(Graph.this, "File exported to /sdcard/ScreenShots/screenshot" + i + ".jpg", Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }



            }


        });

        //============================================================================================================== STORE DETAILS IN DATABASE

         HBInfoHandler mainhandler_main = new HBInfoHandler(this);
         SQLiteDatabase database_new = mainhandler_main.getReadableDatabase();
         mainhandler_main.putHb((averageHB/counter1),(counter2/counter),username,dateFormat.format(cal.getTime()),database_new);
        Toast.makeText(getApplicationContext(), "Details of this session have been saved", Toast.LENGTH_LONG).show();


        graph.destroyDrawingCache();

        //==============================================================================================================


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_graph, menu);
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


    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitByBackKey();

            //moveTaskToBack(false);

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }



    protected void exitByBackKey() {

        AlertDialog alertbox = new AlertDialog.Builder(this)

                .setMessage("Do you want to return to the heartbeat monitoring screen ? Please save before going back")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                            // do something when the button is clicked
                            public void onClick(DialogInterface arg0, int arg1) {


                                HbHandler mainhandler = new HbHandler(Graph.this);
                                SQLiteDatabase database = mainhandler.getReadableDatabase();
                                mainhandler.deletetable(database);

                                finish();

                            }
                        }

                )

                .

                        setNegativeButton("No", new DialogInterface.OnClickListener() {

                                    // do something when the button is clicked
                                    public void onClick(DialogInterface arg0, int arg1) {

                                        //delete info from database

                                    }
                                }

                        )
                .

                        show();

    }

}

