package com.example.team_foxhound.minicapstone_project.Activities;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.CompoundButton;

import com.example.team_foxhound.minicapstone_project.InformationCatalog.RegisterInfo;
import com.example.team_foxhound.minicapstone_project.R;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

import persistence.HbHandler;
import persistence.MainHandler;
import persistence.userCredentialsHandler;
import zephyr.android.HxMBT.BTClient;
import zephyr.android.HxMBT.ZephyrProtocol;


public class MainActivityHeartBeat extends AppCompatActivity {

    HbHandler mainHandler1 = new HbHandler(this);

    static boolean record = false;
    static int keepingtrack;
    static int HBMAX1;  // HBMAX
    static int HBMAX2;  // HBMAX
    String username1;
    TextView textView;



    /** Called when the activity is first created. */
    BluetoothAdapter adapter = null;
    BTClient _bt;
    ZephyrProtocol _protocol;
    NewConnectedListener _NConnListener;
    private final int HEART_RATE = 0x100;
    private final int INSTANT_SPEED = 0x101;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Bundle extras = getIntent().getExtras();
        final String username = extras.getString("username3");
        username1 = username;
        keepingtrack =0;


        setContentView(R.layout.activity_main_activity_heart_beat);
        /*Sending a message to android that we are going to initiate a pairing request*/
        IntentFilter filter = new IntentFilter("android.bluetooth.device.action.PAIRING_REQUEST");
        /*Registering a new BTBroadcast receiver from the Main Activity context with pairing request event*/
        this.getApplicationContext().registerReceiver(new BTBroadcastReceiver(), filter);
        // Registering the BTBondReceiver in the application that the status of the receiver has changed to Paired
        IntentFilter filter2 = new IntentFilter("android.bluetooth.device.action.BOND_STATE_CHANGED");
        this.getApplicationContext().registerReceiver(new BTBondReceiver(), filter2);

        //Obtaining the handle to act on the CONNECT button
//        TextView tv = (TextView) findViewById(R.id.labelStatusMsg);
        String ErrorText = "Not Connected to HxM ! !";
//        tv.setText(ErrorText);

        Button btnConnect = (Button) findViewById(R.id.ButtonConnect);
        if (btnConnect != null) {
            btnConnect.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    String BhMacID = "00:07:80:9D:8A:E8";
                    //String BhMacID = "00:07:80:88:F6:BF";
                    adapter = BluetoothAdapter.getDefaultAdapter();

                    Set<BluetoothDevice> pairedDevices = adapter.getBondedDevices();

                    if (pairedDevices.size() > 0) {
                        for (BluetoothDevice device : pairedDevices) {
                            if (device.getName().startsWith("HXM")) {
                                BluetoothDevice btDevice = device;
                                BhMacID = btDevice.getAddress();
                                break;

                            }
                        }


                    }

                    //BhMacID = btDevice.getAddress();
                    BluetoothDevice Device = adapter.getRemoteDevice(BhMacID);
                    String DeviceName = Device.getName();
                    _bt = new BTClient(adapter, BhMacID);
                    _NConnListener = new NewConnectedListener(Newhandler, Newhandler);
                    _bt.addConnectedEventListener(_NConnListener);

                    TextView tv1 = (TextView) findViewById(R.id.labelHeartRate);
                    tv1.setText("000");

//                    tv1 = (EditText) findViewById(R.id.labelInstantSpeed);
//                    tv1.setText("0.0");

//                    ((TextView) findViewById(R.id.MACaddr)).setText(BhMacID);

                    //tv1 = 	(EditText)findViewById(R.id.labelSkinTemp);
                    //tv1.setText("0.0");

                    //tv1 = 	(EditText)findViewById(R.id.labelPosture);
                    //tv1.setText("000");

                    //tv1 = 	(EditText)findViewById(R.id.labelPeakAcc);
                    //tv1.setText("0.0");
                    if (_bt.IsConnected()) {
                        _bt.start();
                        TextView tv = (TextView) findViewById(R.id.textView3);
                        String ErrorText = "Connected to HxM " + DeviceName;
                        tv.setText(ErrorText);

                        //Reset all the values to 0s

                    } else {
                        TextView tv = (TextView) findViewById(R.id.textView3);
                        String ErrorText = "Unable to Connect !";
                        tv.setText(ErrorText);
                    }


                }
            });
        }
        /*Obtaining the handle to act on the DISCONNECT button*/
        Button btnDisconnect = (Button) findViewById(R.id.ButtonDisconnect);
        if (btnDisconnect != null) {
            btnDisconnect.setOnClickListener(new View.OnClickListener() {
                @Override
				/*Functionality to act if the button DISCONNECT is touched*/
                public void onClick(View v) {
                    // TODO Auto-generated method stub
					/*Reset the global variables*/
//                    TextView tv = (TextView) findViewById(R.id.labelStatusMsg);
                    String ErrorText = "Disconnected from HxM!";
//                    tv.setText(ErrorText);

					/*This disconnects listener from acting on received messages*/
                    _bt.removeConnectedEventListener(_NConnListener);
					/*Close the communication with the device & throw an exception if failure*/
                    _bt.Close();

                }
            });
        }

// Displaying Calculated HB MAX

        MainHandler handler = new MainHandler(this);
        SQLiteDatabase db = handler.getReadableDatabase();


        Cursor cursor = db.rawQuery("SELECT * FROM " + "fitness ", null);
        startManagingCursor(cursor);


        final String HBMAX;
        textView = (TextView) findViewById(R.id.textView18);
        while (cursor.moveToNext()) {

            if (cursor.getString(0).equals(username1)) {

               // HBMAX = cursor.getString(4);
                HBMAX1 = (int) (cursor.getDouble(4)*0.6);
                HBMAX2 = (int) (cursor.getDouble(4));



                textView.setText(Integer.toString(HBMAX1));

            }
        }


        final SeekBar sk=(SeekBar) findViewById(R.id.seekBar);
        sk.setMax(HBMAX2);
        sk.setProgress(HBMAX1);

        sk.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                HBMAX1 = progress;
                textView.setText(Integer.toString(progress));
//                Toast.makeText(getApplicationContext(), String.valueOf(progress), Toast.LENGTH_LONG).show();

            }
        });


//
//        //Toggle Music
//        final MediaPlayer mediaplayer = MediaPlayer.create(this,R.raw.lol);
//        final Switch mySwitch = (Switch) findViewById(R.id.switch1);
//        mySwitch.setChecked(false);
//        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                int counter = 0;
//                while (counter==0) {
//                    if (mySwitch.isChecked()) {
//                        mediaplayer.start();
//                        counter++;
//                    } else {
//                        mediaplayer.pause();
//                        counter--;
//                    }
//                }
//            }
//        });



//    }

    ToggleButton toggleButton = (ToggleButton) findViewById(R.id.toggleButton);

    toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


            if(buttonView.isChecked() == true){

                Toast.makeText(getApplicationContext(), "Start Recording", Toast.LENGTH_LONG).show();
                record = true;

            }

            else if(buttonView.isChecked() == false) {

                Toast.makeText(getApplicationContext(), "Recording Stopped", Toast.LENGTH_LONG).show();
                record = false;


                AlertDialog alertbox = new AlertDialog.Builder(MainActivityHeartBeat.this)

                        .setMessage("Do you want to view progress of this session?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                            // do something when the button is clicked
                            public void onClick(DialogInterface arg0, int arg1) {

                                Intent intent  = new Intent(MainActivityHeartBeat.this, Graph.class);
                                intent.putExtra("targethb", HBMAX1);
                                intent.putExtra("username1",username);
                                startActivity(intent);
                                finish();


                            }
                        })

                        .setNegativeButton("No", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface arg0, int arg1) {

                                HbHandler mainhandler = new HbHandler(MainActivityHeartBeat.this);
                                SQLiteDatabase database = mainhandler.getReadableDatabase();
                                mainhandler.deletetable(database);


                            }

                        })
                        .show();

            }


        }

    });
}




private class BTBondReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle b = intent.getExtras();
        BluetoothDevice device = adapter.getRemoteDevice(b.get("android.bluetooth.device.extra.DEVICE").toString());
        Log.d("Bond state", "BOND_STATED = " + device.getBondState());
    }
}
private class BTBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("BTIntent", intent.getAction());
        Bundle b = intent.getExtras();
        Log.d("BTIntent", b.get("android.bluetooth.device.extra.DEVICE").toString());
        Log.d("BTIntent", b.get("android.bluetooth.device.extra.PAIRING_VARIANT").toString());
        try {
            BluetoothDevice device = adapter.getRemoteDevice(b.get("android.bluetooth.device.extra.DEVICE").toString());
            Method m = BluetoothDevice.class.getMethod("convertPinToBytes", new Class[] {String.class} );
            byte[] pin = (byte[])m.invoke(device, "1234");
            m = device.getClass().getMethod("setPin", new Class[] {pin.getClass()});
            Object result = m.invoke(device, pin);
            Log.d("BTTest", result.toString());
        } catch (SecurityException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (NoSuchMethodException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}


final Handler Newhandler = new Handler(){
    public void handleMessage(Message msg)
    {

        TextView tv;
        switch (msg.what)
        {
            case HEART_RATE:

                String HeartRatetext = msg.getData().getString("HeartRate");
                tv = (TextView)findViewById(R.id.labelHeartRate);

//======================================================================================================== MAIN FUNCTIONALITY
                // VIBRATE WHEN HB SURPASSES TARGET HB (ONLY ONCE)
                if((absolute(Integer.valueOf(tv.getText().toString()).intValue()) > HBMAX1) && (keepingtrack==0)){

                    vibrate_main();
                    keepingtrack++;
                }

                // VIBRATE WHEN HB GOES BELOW TARGET HB (ONLY ONCE)
                if((absolute(Integer.valueOf(tv.getText().toString()).intValue()) < HBMAX1) && (keepingtrack ==1)) {

                    vibrate_main();
                    keepingtrack =0;

                }
//========================================================================================================  HB DATABASE STORING + GRAPH (SECONDARY FUNCTIONALITY)

                SQLiteDatabase database = mainHandler1.getWritableDatabase();

                if(record = true) {

                    mainHandler1.putHb(absolute(Integer.valueOf(tv.getText().toString()).intValue()), database);

                }


//                        try {
//                            Thread.sleep(500);                 // Wait for 500ms
//                        } catch (InterruptedException ex) {
//                            Thread.currentThread().interrupt();
//                        }
//                    }



                else {

                    // do nothing for now

                }


//========================================================================================================

                System.out.println("Heart Rate Info is " + HeartRatetext);

                if(Integer.toString(absolute(Integer.valueOf(HeartRatetext).intValue())).length()==3){tv.setText(Integer.toString(absolute(Integer.valueOf(HeartRatetext).intValue())));}
                if(Integer.toString(absolute(Integer.valueOf(HeartRatetext).intValue())).length()==2) {tv.setText("0"+ Integer.toString(absolute(Integer.valueOf(HeartRatetext).intValue())));}
                if(Integer.toString(absolute(Integer.valueOf(HeartRatetext).intValue())).length()==1) {tv.setText("00"+ Integer.toString(absolute(Integer.valueOf(HeartRatetext).intValue())));}
                break;

            case INSTANT_SPEED:

                String InstantSpeedtext = msg.getData().getString("InstantSpeed");
//                    tv = (EditText)findViewById(R.id.labelInstantSpeed);
//                    if (tv != null)tv.setText(InstantSpeedtext);
//
//                    break;

        }
    }

};




    public void vibrate_main() {


        Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vib.vibrate(2000);
    }


    public int absolute(int i) {

        if( i< 0) {

            i = i*-1;

            return i;
        }
        else {

            return i;
        }
    }




}
