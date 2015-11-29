package com.example.team_foxhound.minicapstone_project.Activities;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.team_foxhound.minicapstone_project.R;

import junit.framework.TestCase;

import persistence.HBInfoHandler;

public class ProgressView extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_view);

        Bundle extras = getIntent().getExtras();
        String username = extras.getString("username2");


        TextView textView1 = (TextView) findViewById(R.id.textView25);
        textView1.setText("For " +username + ":");

        //    ===============================================================================================

        TableLayout tblLayout = (TableLayout) findViewById(R.id.tablelayout);

        HBInfoHandler hbInfoHandler = new HBInfoHandler(this);
        SQLiteDatabase database = hbInfoHandler.getWritableDatabase();


        Cursor cursor = database.rawQuery("SELECT * FROM " + "heartbeatinfo", null);
        int i = 0;

        while ((cursor.moveToNext()) && (database.getMaximumSize() > 0)) {

//            TableRow row = (TableRow) tblLayout.getChildAt(0);
//            TextView textView = (TextView)row.getChildAt(0);
//            TextView textView2 = (TextView)row.getChildAt(1);
//            TextView textView3 = (TextView)row.getChildAt(2);
//
//            textView.setText(cursor.getString(3));
//            textView2.setText(cursor.getString(1));
//            textView3.setText(cursor.getString(2));

           if ((username.equals(cursor.getString(0))) && i <= 12) {

               TableRow row = (TableRow) tblLayout.getChildAt(i); // Here get row id depending on number of rows
               TextView textView = (TextView) row.getChildAt(0);

               while (textView.getText().toString().isEmpty()) {

                   TextView textView2 = (TextView)row.getChildAt(0);
                   TextView textView3 = (TextView)row.getChildAt(1);
                   TextView textView4 = (TextView)row.getChildAt(2);

                   textView2.setText(cursor.getString(3));
                   textView3.setText(cursor.getString(1));
                   textView4.setText(cursor.getString(2));

               }

                i++;
           }

     }

 }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_progress_view, menu);
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
}
