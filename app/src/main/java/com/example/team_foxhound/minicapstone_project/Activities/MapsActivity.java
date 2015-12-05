package com.example.team_foxhound.minicapstone_project.Activities;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
      import android.app.AlertDialog;
        import android.content.Context;
      import android.content.DialogInterface;
        import android.content.Intent;
import android.location.Location;
import android.widget.CompoundButton;
import android.widget.Toast;
        import android.widget.ToggleButton;

import com.example.team_foxhound.minicapstone_project.R;
        import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.OnMapReadyCallback;
        import com.google.android.gms.maps.model.Marker;

        import java.text.DecimalFormat;
//        import persistence.HbHandler;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    ArrayList<LatLng> checkpoints;

    int counter = 0;
    int counter2 =0;
    boolean hasstarted =false;
    boolean directions=false;
    static Marker marker1;
    static Marker marker2;

    LatLng myposition;
    static LatLng startposition;
    static LatLng endposition;

    static boolean check;
    static int waypoint=1;
    static double totaldistance=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mMap = mapFragment.getMap();
        Button btnDraw = (Button)findViewById(R.id.btn_draw);
//        Button btnReset = (Button) findViewById(R.id.btn_reset);
        checkpoints = new ArrayList<LatLng>();

        Bundle extras = getIntent().getExtras();;
        final String username2 = extras.getString("username3");



        ToggleButton toggleButton = (ToggleButton) findViewById(R.id.toggleButton);

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                if (buttonView.isChecked()) {

                    check = true;
                    Toast.makeText(getApplicationContext(), "Please Set your Destination", Toast.LENGTH_LONG).show();

                    if (counter == 0) {

//                       if(marker1.equals(null) && marker2.equals(null)){
//
//                           marker1.remove();
//                           marker2.remove();
//                       }


                        startposition = new LatLng(myposition.latitude, myposition.longitude);
                        endposition = new LatLng(myposition.latitude+0.002, myposition.longitude);

                        marker1 = mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)).position(startposition).draggable(true).title("Start"));
                        marker2 = mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)).position(endposition).draggable(true).title("End"));

                        counter++;
                    }

                } else {

//                    Toast.makeText(getApplicationContext(), "Recording Stopped", Toast.LENGTH_LONG).show();


                    check = false;
                    AlertDialog alertbox = new AlertDialog.Builder(MapsActivity.this)

                            .setMessage("Do you want to get details of your course?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                                // do something when the button is clicked
                                public void onClick(DialogInterface arg0, int arg1) {

                                    double totaldistancetemp = totaldistance;
                                    int waypointtemp = waypoint-1;

                                    if(directions==true) {

                                        Intent intent = new Intent(MapsActivity.this, DetailsViewer.class);
                                        intent.putExtra("totaldistance", totaldistancetemp);
                                        intent.putExtra("waypoints", waypointtemp);
                                        intent.putExtra("username1", username2);
                                        startActivity(intent);


                                        counter--;
                                        counter2 = 0;
                                        waypoint = 1;
                                        checkpoints.clear();
                                        mMap.clear();
                                        directions = false;
                                        totaldistance = 0;
                                    }

                                    // cout distance

                                    else {
                                        Toast.makeText(getApplicationContext(), "A Route has not been Established", Toast.LENGTH_LONG).show();
                                        counter--;
                                        counter2 = 0;
                                        waypoint = 1;
                                        checkpoints.clear();
                                        mMap.clear();
                                        directions = false;
                                        totaldistance = 0;
                                    }
                                }
                            })

                            .setNegativeButton("No", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface arg0, int arg1) {

                                    // do  nothing

                                    if (counter == 1) {

                                        mMap.clear();
                                        checkpoints.clear();
                                        totaldistance=0;
                                        waypoint = 1;
                                        directions=false;
                                        counter--;
                                    }

                                }

                            })
                            .show();

                }


            }

        });



        btnDraw.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Checks, whether start and end locations are captured

                // Getting URL to the Google Directions API

                if (check) {
                    String url = getDirectionsUrl(startposition, endposition);

                    DownloadTask downloadTask = new DownloadTask();

                    // Start downloading json data from Google Directions API
                    downloadTask.execute(url);
                } else {

                    Toast.makeText(getApplicationContext(), "Please Set a Course ", Toast.LENGTH_LONG).show();

                }
            }
        });



    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {


if(!hasstarted) {

    AlertDialog alertbox = new AlertDialog.Builder(MapsActivity.this)

            .setMessage("Do you want FitnessCompanion to use your Current Location?")
            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                // do something when the button is clicked
                public void onClick(DialogInterface arg0, int arg1) {


                    if (isNetworkAvailable()) {
                        mMap.setMyLocationEnabled(true);
                        Toast.makeText(getApplicationContext(), "Location Enabled", Toast.LENGTH_LONG).show();
                        hasstarted= true;


                    } else {


                        Toast.makeText(getApplicationContext(), "Please Connect to the Internet Before", Toast.LENGTH_LONG).show();
                        startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
                        finish();

                    }


                }
            })

            .setNegativeButton("No", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface arg0, int arg1) {

                    // do  nothing
                    hasstarted= true;
                    LatLng latLng = new LatLng(mMap.getCameraPosition().target.latitude,mMap.getCameraPosition().target.longitude);
                    CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 4);
                    mMap.animateCamera(cameraUpdate);
                }

            })
            .show();


}
        mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {


                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                LatLng latLng = new LatLng(latitude, longitude);
                myposition = new LatLng(latitude, longitude);

                if (counter2 == 0) {

                    CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(myposition, 15);
                    mMap.animateCamera(cameraUpdate);
                    counter2++;
                }

            }
        });




        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {

            }

            @Override
            public void onMarkerDrag(Marker marker) {


                if (marker1.equals(marker)) {

                    startposition = marker.getPosition();
                }

                if (marker2.equals(marker)) {

                    endposition = marker.getPosition();
                }

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {

            }

        });



        mMap.setOnMapLongClickListener(new OnMapLongClickListener() {

            @Override
            public void onMapLongClick(LatLng latLng) {

                if (check && waypoint < 5) {
//
                    mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)).position(latLng).title("WayPoint "+ waypoint));
                    checkpoints.add(latLng);
                    waypoint++;
                }
            }
        });



    }

    public double CalculationByDistance(LatLng StartP, LatLng EndP) {

        int Radius=6371;//radius of earth in Km
        double lat1 = StartP.latitude;
        double lat2 = EndP.latitude;
        double lon1 = StartP.longitude;
        double lon2 = EndP.longitude;
        double dLat = Math.toRadians(lat2-lat1);
        double dLon = Math.toRadians(lon2-lon1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon/2) * Math.sin(dLon/2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double valueResult= Radius*c;
        double km=valueResult/1;
        DecimalFormat newFormat = new DecimalFormat("####");
        int kmInDec =  Integer.valueOf(newFormat.format(km));
        double meter=valueResult%1000;
        int  meterInDec= Integer.valueOf(newFormat.format(meter));
        return Radius * c;

    }



   private String getDirectionsUrl(LatLng origin,LatLng dest){

       directions = true;
       // Origin of route
       String str_origin = "origin="+origin.latitude+","+origin.longitude;

       // Destination of route
       String str_dest = "destination="+dest.latitude+","+dest.longitude;

       // Sensor enabled
       String sensor = "sensor=false";

       // Waypoints
       String waypoints = "";

      for(int i=0; i < checkpoints.size();i++){
          LatLng point  = (LatLng) checkpoints.get(i);
          if(i==0){
              waypoints = "waypoints=";}
          waypoints += point.latitude + "," + point.longitude + "|";
      }

       // Building the parameters to the web service
       String parameters = str_origin+"&"+str_dest+"&"+sensor+"&"+waypoints;

       // Output format
       String output = "json";

       // Building the url to the web service
       String url = "https://maps.googleapis.com/maps/api/directions/"+output+"?"+parameters;

       return url;
   }


   private String downloadUrl(String strUrl) throws IOException{
       String data = "";
       InputStream iStream = null;
       HttpURLConnection urlConnection = null;
       try{
           URL url = new URL(strUrl);

           // Creating an http connection to communicate with url
           urlConnection = (HttpURLConnection) url.openConnection();

           // Connecting to url
           urlConnection.connect();

           // Reading data from url
           iStream = urlConnection.getInputStream();

           BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

           StringBuffer sb  = new StringBuffer();

           String line = "";
           while( ( line = br.readLine())  != null){
               sb.append(line);
           }

           data = sb.toString();

           br.close();

       }catch(Exception e){

           Log.d("Exception downloading", e.toString());

       }finally{

           iStream.close();
           urlConnection.disconnect();
       }
       return data;
   }


   // Fetches data from url passed
   private class DownloadTask extends AsyncTask<String, Void, String>{

       // Downloading data in non-ui thread
       @Override
       protected String doInBackground(String... url) {

           // For storing data from web service

           String data = "";

           try{
               // Fetching the data from web service
               data = downloadUrl(url[0]);
           }catch(Exception e){
               Log.d("Background Task",e.toString());
           }
           return data;
       }

       // Executes in UI thread, after the execution of
       // doInBackground()
       @Override
       protected void onPostExecute(String result) {
           super.onPostExecute(result);

           ParserTask parserTask = new ParserTask();

           // Invokes the thread for parsing the JSON data
           parserTask.execute(result);
       }
   }

   private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String,String>>> >{

       // Parsing the data in non-ui thread
       @Override
       protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

           JSONObject jObject;
           List<List<HashMap<String, String>>> routes = null;

           try{
               jObject = new JSONObject(jsonData[0]);
               directionsrequest parser = new directionsrequest();

               // Starts parsing data
               routes = parser.parse(jObject);
           }catch(Exception e){
               e.printStackTrace();
           }
           return routes;
       }

       // Executes in UI thread, after the parsing process
       @Override
       protected void onPostExecute(List<List<HashMap<String, String>>> result) {

           ArrayList<LatLng> points = null;
           PolylineOptions lineOptions = null;

           // Traversing through all the routes
           for(int i=0;i<result.size();i++){
               points = new ArrayList<LatLng>();
               lineOptions = new PolylineOptions();

               // Fetching i-th route
               List<HashMap<String, String>> path = result.get(i);

               // Fetching all the points in i-th route
               for(int j=0;j<path.size();j++){

                   HashMap<String,String> point = path.get(j);

                   double lat = Double.parseDouble(point.get("lat"));
                   double lng = Double.parseDouble(point.get("lng"));
                   LatLng position = new LatLng(lat, lng);

                   points.add(position);
               }

               // Adding all the points in the route to LineOptions
               lineOptions.addAll(points);
               lineOptions.width(3);
               lineOptions.color(Color.RED);
           }

           // Drawing polyline in the Google Map for the i-th route
           mMap.addPolyline(lineOptions);


         for(int k=0; k<points.size()-1;k++) {

          totaldistance = CalculationByDistance(points.get(k), points.get(k+1))+ totaldistance;

          }

       }
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

        finish();


    }



    public boolean hasActiveInternetConnection(Context context) {


        if (isNetworkAvailable()) {
            try {
                HttpURLConnection urlc = (HttpURLConnection) (new URL("http://www.google.com").openConnection());
                urlc.setRequestProperty("User-Agent", "Test");
                urlc.setRequestProperty("Connection", "close");
                urlc.setConnectTimeout(1500);
                urlc.connect();
                return (urlc.getResponseCode() == 200);
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(),  "Error checking internet connection", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext(),  "No network available!", Toast.LENGTH_LONG).show();
        }
        return false;
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }
    }
