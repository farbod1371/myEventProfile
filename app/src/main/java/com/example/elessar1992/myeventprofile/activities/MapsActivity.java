package com.example.elessar1992.myeventprofile.activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.elessar1992.myeventprofile.R;
import com.example.elessar1992.myeventprofile.Services.FourSquareService;
import com.example.elessar1992.myeventprofile.model.FoursquareData.Explore;
import com.example.elessar1992.myeventprofile.model.FoursquareData.Price;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by elessar1992 on 1/22/18.
 */

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.OnConnectionFailedListener
{
    private AppCompatButton findButton;
    private AppCompatButton searchButton;
    private TextInputEditText textInputEditTextDescription;
    private TextInputLayout textInputLayoutDescription;



    private GoogleMap mMap;
    Marker mMarker;

    private static final String TAG = MapsActivity.class.getSimpleName();
    protected GeoDataClient mGeoDataClient;
    protected PlaceDetectionClient mPlaceDetectionClient;
    protected FusedLocationProviderClient mFusedLocationProviderClient;


    private final LatLng mDefaultLocation = new LatLng(-33.8523341, 151.2106085);
    private static final int DEFAULT_ZOOM = 15;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private boolean mLocationPermissionGranted;
    private Location mLastKnownLocation;

    String Client_ID = "GQYXXRBQP2UYHST3FL1IRFEQNLRBPXPQXORWT5JWWGU32XMW";
    String Client_Secret = "YPIX13O3ZY5HZ1QJZFU0EW0SP3XIIEN3JK5LNE4FC11OSUAH";
    String apiVersion = "20130815";
    List<LatLng> list = new ArrayList<>();
    List<String> nameList = new ArrayList<>();
    String ll;
    String query;
    String radius = "10000";
    MarkerOptions markerOptions = new MarkerOptions();
    String name;
    double lat;
    double lng;
    double rating;
    String photourl;
    String suffix;
    String prefix;
    Price price;
    ArrayList<Marker> markersToClear = new ArrayList<Marker>();

    List<String> descriptionList = new ArrayList<>();
    List<String> photoUrls = new ArrayList<>();
    List<Double> ratingList = new ArrayList<>();
    List<String> suffixList = new ArrayList<>();
    List<String> prefixList = new ArrayList<>();
    List<String> ratingAsStringList = new ArrayList<>();
    List<String> photourlList = new ArrayList<>();
    List<String> latlngAsStringList = new ArrayList<>();
    List<Price> priceList = new ArrayList<>();
    String description;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        getSupportActionBar().hide();

        textInputLayoutDescription = (TextInputLayout) findViewById(R.id.Description);
        textInputEditTextDescription = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        findButton = (AppCompatButton) findViewById(R.id.find);
        searchButton = (AppCompatButton) findViewById(R.id.search);

        // Construct a GeoDataClient.
        mGeoDataClient = Places.getGeoDataClient(this, null);
        // Construct a PlaceDetectionClient.
        mPlaceDetectionClient = Places.getPlaceDetectionClient(this, null);

        // Construct a FusedLocationProviderClient.
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        //Autocomplete Search engine
        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener()
        {
            @Override
            public void onPlaceSelected(Place place)
            {
                // TODO: Get info about the selected place.
                Log.i(TAG, "Place: " + place.getName());

                LatLng placelatlng = place.getLatLng();
                String name = (String) place.getName();
                mMarker = mMap.addMarker(new MarkerOptions().position(placelatlng).title(name).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
                mMarker.showInfoWindow();
                mMap.moveCamera(CameraUpdateFactory.newLatLng(placelatlng));


                String lat = Double.toString(placelatlng.latitude);
                String lng = Double.toString(placelatlng.longitude);

                ll = lat + ',' +lng;

                //build_retrofit_and_get_response("4d4b7104d754a06370d81259");


            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        findButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String value = textInputEditTextDescription.getText().toString().trim();
                clearList();
                if(value.isEmpty())
                {
                    textInputEditTextDescription.setError("its empty");
                }
                else
                {
                    // do something when the corky is clicked
                    build_retrofit_and_get_response(value);
                }
            }
        });




    }


    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        mMap = googleMap;
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        updateLocationUI();

        getDeviceLocation();



        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling

            return;
        }

        mMap.setMyLocationEnabled(true);


        UiSettings uiSettings = googleMap.getUiSettings();
        uiSettings.setCompassEnabled(true);
        uiSettings.setZoomControlsEnabled(true);
        uiSettings.isTiltGesturesEnabled();
        uiSettings.setRotateGesturesEnabled(true);

        mMap.setOnInfoWindowClickListener(
                new GoogleMap.OnInfoWindowClickListener()
                {
                    public void onInfoWindowClick(Marker marker)
                    {
                        for(int i = 0; i < list.size(); i++){
                            if(list.get(i) == marker.getTag())
                            {
                                Intent activity = new Intent(MapsActivity.this, EventActivity.class);
                                activity.putExtra("Name", nameList.get(i));
                                activity.putExtra("Rating", ratingAsStringList.get(i));
                                activity.putExtra("latlng", latlngAsStringList.get(i));
                                //activity.putExtra("photourl", photourlList.get(i));
                                //activity.putExtra("suffix", suffixList.get(i));
                                //activity.putExtra("prefix", prefixList.get(i));
                                startActivity(activity);
                            }
                        }
                    }
                });

    }


    private void getLocationPermission()
    {
        /*
     * Request location permission, so that we can get the location of the
     * device. The result of the permission request is handled by a callback,
     * onRequestPermissionsResult.
     */
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED)
        {
            mLocationPermissionGranted = true;
        }
        else
        {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                }
            }
        }
        updateLocationUI();
    }


    private void updateLocationUI()
    {
        if (mMap == null)
        {
            return;
        }
        try
        {
            if (mLocationPermissionGranted)
            {
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
            }
            else
            {
                mMap.setMyLocationEnabled(false);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                mLastKnownLocation = null;
                getLocationPermission();
            }
        }
        catch (SecurityException e)
        {
            Log.e("Exception: %s", e.getMessage());
        }
    }


    private void getDeviceLocation()
    {
    /*
     * Get the best and most recent location of the device, which may be null in rare
     * cases when a location is not available.
     */
        try
        {
            if (mLocationPermissionGranted)
            {
                Task locationResult = mFusedLocationProviderClient.getLastLocation();
                locationResult.addOnCompleteListener(this, new OnCompleteListener()
                {
                    @Override
                    public void onComplete(@NonNull Task task)
                    {
                        if (task.isSuccessful())
                        {
                            // Set the map's camera position to the current location of the device.
                            mLastKnownLocation = (Location) task.getResult();
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                    new LatLng(mLastKnownLocation.getLatitude(),
                                            mLastKnownLocation.getLongitude()), DEFAULT_ZOOM));

                            String lat = Double.toString(mLastKnownLocation.getLatitude());
                            String lng = Double.toString(mLastKnownLocation.getLongitude());

                            ll = lat + ',' +lng;
                        }
                        else
                        {
                            Log.d(TAG, "Current location is null. Using defaults.");
                            Log.e(TAG, "Exception: %s", task.getException());

                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mDefaultLocation, DEFAULT_ZOOM));
                            mMap.getUiSettings().setMyLocationButtonEnabled(false);
                        }
                    }

                });
            }
        }
        catch (SecurityException e)
        {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    private void build_retrofit_and_get_response(String query)
    {
        FourSquareService fourSquareService = FourSquareService.retrofit.create(FourSquareService.class);
        Call<Explore> call = fourSquareService.requestExplore(Client_ID, Client_Secret, apiVersion, ll, query, radius);
        call.enqueue(new Callback<Explore>() {
            @Override
            public void onResponse(Call<Explore> call, Response<Explore> response)
            {
                try
                {
                    Log.i(TAG, "onResponse:"+ response.body().getResponse().getGroups().get(0).getItems().size());
                    for (int i = 0; i < response.body().getResponse().getGroups().get(0).getItems().size(); i++)
                    {
                        //price = response.body().getResponse().getGroups().get(0).getItems().get(i).getVenue().getPrice();
                        if(response.body().getResponse().getGroups().get(0).getItems().get(i).getVenue().getRating() != null)
                        {
                            rating = response.body().getResponse().getGroups().get(0).getItems().get(i).getVenue().getRating();
                            String ratingAsString = String.valueOf(rating);
                            ratingAsStringList.add(ratingAsString);
                        }
                        //String ratingAsString = String.valueOf(rating);
                        lat = response.body().getResponse().getGroups().get(0).getItems().get(i).getVenue().getLocation().getLat();
                        lng = response.body().getResponse().getGroups().get(0).getItems().get(i).getVenue().getLocation().getLng();
                        name = response.body().getResponse().getGroups().get(0).getItems().get(i).getVenue().getName();
                        //photourl = response.body().getResponse().getGroups().get(0).getItems().get(i).getTips().get(0).getPhotourl();
                        //suffix = response.body().getResponse().getGroups().get(0).getItems().get(i).getTips().get(0).getPhoto().getSuffix();
                        //prefix = response.body().getResponse().getGroups().get(0).getItems().get(i).getTips().get(0).getPhoto().getPrefix();
                        LatLng latLng = new LatLng(lat, lng);
                        String latlngAsString = String.valueOf(latLng);
                        list.add(latLng);
                        nameList.add(name);
                        //ratingList.add(rating);
                        //ratingAsStringList.add(ratingAsString);
                        latlngAsStringList.add(latlngAsString);
                        //photourlList.add(photourl);
                        //suffixList.add(suffix);
                        //prefixList.add(prefix);
                        //priceList.add(price);
                    }
                    for(int i = 0; i < list.size(); i ++)
                    {
                        markerOptions.position(list.get(i));
                        markerOptions.title(nameList.get(i));
                        if(ratingAsStringList.get(i) == null)
                        {
                            markerOptions.snippet("Is not rated yet");
                        }
                        else
                        {
                            markerOptions.snippet("Rating is " + ratingAsStringList.get(i));
                        }

                        Marker marker;
                        marker = mMap.addMarker(markerOptions);
                        marker.setTag(markerOptions.getPosition());
                        markersToClear.add(marker);
                    }
                }
                catch (Exception e)
                {
                    Log.d("onResponse", "There is an error " + e.getMessage() );
                    e.printStackTrace();
                }
            }




            @Override
            public void onFailure(Call<Explore> call, Throwable t) {
                Log.d("onFailure", t.toString());
            }
        });
    }

    public void clearList()
    {
        list.clear();
        nameList.clear();
        ratingAsStringList.clear();
        ratingList.clear();
        latlngAsStringList.clear();
        //photourlList.clear();
        //prefixList.clear();
        //suffixList.clear();
        //priceList.clear();
        for (Marker marker : markersToClear)
        {
            marker.remove();
        }
        markersToClear.clear();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult)
    {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
        Toast.makeText(this, "Google Play Services error.", Toast.LENGTH_SHORT).show();
    }
}