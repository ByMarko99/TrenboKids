package com.example.trenbokids;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Mapa extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnCameraMoveListener, GoogleMap.OnMapClickListener  {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    MapView mapView;
    GoogleMap map;
    private ImageView arnieMonke;

    private LocationCallback locationCallback;
    Marker marker1;
    private static final String TAG = Mapa.class.getSimpleName();
    private CameraPosition cameraPosition;
    private AlertDialog.Builder dialogBuilder;

    // The entry point to the Places API.
    //private PlacesClient placesClient;

    // The entry point to the Fused Location Provider.
    private FusedLocationProviderClient fusedLocationProviderClient;

    private Location lastKnownLocation;
    ImageView walter;
    private TextView textScore;
    private int score = 0;
    private static final String KEY_CAMERA_POSITION = "camera_position";
    private static final String KEY_LOCATION = "location";
    private FusedLocationProviderClient fusedLocationClient;
    LatLng here;
    private int screenWidth;
    private int[] glowColors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.MAGENTA};



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        arnieMonke = findViewById(R.id.imageMonkey);
        textScore = findViewById(R.id.textScore);
        textScore.setVisibility(View.GONE);

        if (savedInstanceState != null) {
            lastKnownLocation = savedInstanceState.getParcelable(KEY_LOCATION);
            cameraPosition = savedInstanceState.getParcelable(KEY_CAMERA_POSITION);
        }


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != //Comprueba solo si tiene write, no hace falta mas, y lo pide sino junto al read
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 0);
        }


        // Gets the MapView from the XML layout and creates it

        mapView = (MapView) findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);


        mapView.getMapAsync(this);



        screenWidth = getResources().getDisplayMetrics().widthPixels;


        arnieMonke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Increment the score and update the text view
                score++;
                textScore.setText("+" + score);
                textScore.setVisibility(View.VISIBLE);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        textScore.setVisibility(View.GONE);


                    }
                }, 1000);

            }
        });
        startAnimation();


        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Cooking tren...");
        progressDialog.show();
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {

                        if (location == null) {

                            // I forgor que hace💀💀
                            buildAlertMessageNoGps();
                        }else {
                            here = new LatLng(location.getLatitude(), location.getLongitude());
                            // float zoomLevel = 16.5f;
                            CameraPosition cameraPosition = new CameraPosition.Builder().
                                    target(here).
                                    tilt(60).
                                    zoom(19).
                                    bearing(0).
                                    build();

                            map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                            marker1 =  map.addMarker(new MarkerOptions().position(here).title("Hemen zaude").icon(BitmapDescriptorFactory.fromResource(R.raw.arnie)));
                            //  map.moveCamera(CameraUpdateFactory.newLatLngZoom(here, zoomLevel));
                  // Crea un looper en el que pide la la localizacion y luego ejecuta lo de arriba para poner el marker
                            // AAAAA reaches here after returning null? gets location enters locationcallback forever???
                        }

                    }
                });





            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                   progressDialog.dismiss();

                }
            }, 4000);
        }





    private void startAnimation() {
      Random random = new Random();

        // Create a horizontal movement animation
        ObjectAnimator moveAnimation = ObjectAnimator.ofFloat(arnieMonke, "translationX", 0f, screenWidth - arnieMonke.getWidth());
        moveAnimation.setDuration(3000);
        moveAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        moveAnimation.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                // Reverse the horizontal movement animation
                moveAnimation.reverse();
            }
        });

        // Create a vertical jump animation
        ObjectAnimator jumpAnimation = ObjectAnimator.ofFloat(arnieMonke, "translationY", 0f, -400f);
        jumpAnimation.setDuration(1000);
        jumpAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        jumpAnimation.setRepeatCount(ObjectAnimator.INFINITE);
        jumpAnimation.setRepeatMode(ObjectAnimator.REVERSE);

        // Create a backflip animation
        ObjectAnimator backflipAnimation = ObjectAnimator.ofFloat(arnieMonke, "rotationY", 0f, 360f);
        backflipAnimation.setDuration(1000);
        backflipAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        backflipAnimation.setRepeatCount(ObjectAnimator.INFINITE);

        // Create a glowing animation with different colors
        ValueAnimator glowAnimation = ValueAnimator.ofFloat(1f, 0.5f, 1f);
        glowAnimation.setDuration(2000);
        glowAnimation.setRepeatCount(ObjectAnimator.INFINITE);
        glowAnimation.setRepeatMode(ObjectAnimator.REVERSE);
        glowAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                int color = glowColors[random.nextInt(glowColors.length)];
                arnieMonke.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
                arnieMonke.setAlpha(value);
            }
        });

        // Create an AnimatorSet to combine the animations
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(moveAnimation, jumpAnimation, backflipAnimation, glowAnimation);

        // Start the animation
        animatorSet.start();
    }



    /*  @SuppressLint("MissingPermission")
      public void getLocation(){
          fusedLocationClient = LocationServices.getFusedLocationProviderClient(getContext());

          fusedLocationClient.getLastLocation()
                  .addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                      @Override
                      public void onSuccess(Location location) {

                          if (location != null) {


                              try {
                                  LatLng here = new LatLng(location.getLatitude(), location.getLongitude());
                                  map.addMarker(new MarkerOptions().position(here).title("Hemen zaude"));
                              }catch (Exception e){

                              }


                          }


                      }
                  });
      }*/



    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Zure GPS-a desaktibatuta dago, aktibatu NOW")
                .setCancelable(false);


        ((Activity)this).runOnUiThread(new Runnable() { // Hilo no puede sacar popups por eso se le pone que ejecute el hilo en el principal grafico
            public void run() {
                final AlertDialog alert = builder.create();
                alert.show();            }
        });


    }




       /*
       //in old Api Needs to call MapsInitializer before doing any CameraUpdateFactory call
        try {
            MapsInitializer.initialize(this.getActivity());
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
       */

    // Updates the location and zoom of the MapView
        /*CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(43.1, -87.9), 10);
        map.animateCamera(cameraUpdate);*/






    public void onMapReady(final GoogleMap map) {
        this.map = map;

        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = map.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.style));

            if (!success) {
                Log.e(TAG, "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "Can't find style. Error: ", e);
        }


    }

    public void onMapReady2 (final  GoogleMap map) {

        this.map = map;

        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = map.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.style));

            if (!success) {
                Log.e(TAG, "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "Can't find style. Error: ", e);
        }
    }


    // Clase para añadir marcadores onclick
    public void onMapClick(LatLng point) {
     /*   map.clear();
        map.addMarker(new MarkerOptions()
                .position(point)
                .title("You are here")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        //

        getAddress(point.latitude, point.longitude);
*/
    }





    /**
     *
     */
    //TODO nothing mar submarine brum brum
 /*   public String getAddress(double lat, double lng) {
        Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
            Address obj = addresses.get(0);
            String add = obj.getAddressLine(0);
          //  add = add + "\n" + obj.getCountryName();
          //  add = add + "\n" + obj.getCountryCode();
          //  add = add + "\n" + obj.getAdminArea();
         //   add = add + "\n" + obj.getPostalCode();
            //   add = add + "\n" + obj.getSubAdminArea();

            Log.v("IGA", "Address" + add);
            // Toast.makeText(this, "Address=>" + add,
            // Toast.LENGTH_SHORT).show();
            return add;

            // TennisAppActivity.showDialog(add);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return "";

    } */

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();


    }



    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();

    }

    public void onPause2() {
        super.onPause();
        if (fusedLocationClient != null) {
            fusedLocationClient.removeLocationUpdates(locationCallback);
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }


    @Override
    public void onCameraMove() {

    }



}