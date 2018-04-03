package com.transitclone;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBufferResponse;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.PlaceFilter;
import com.google.android.gms.location.places.PlaceLikelihood;
import com.google.android.gms.location.places.PlaceLikelihoodBufferResponse;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.transitclone.adapters.PlaceAutocompleteAdapter;
import com.transitclone.responses.direction.GoogleDirectionResponse;
import com.transitclone.responses.direction.Route;
import com.transitclone.responses.direction.RouteDecode;
import com.transitclone.responses.direction.Steps;
import com.transitclone.ui.BusTmingsView;
import com.transitclone.widgets.ClearableAutoCompleteTextView;
import com.transitclone.widgets.WayOverView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;


@RuntimePermissions
public class MapsActivity extends BaseActivity implements OnMapReadyCallback {

    private static final String TAG = MapsActivity.class.getSimpleName();
    @BindView(R.id.autoCompleteFromLocation)
    ClearableAutoCompleteTextView autoCompleteFromLocation;
    @BindView(R.id.autoCompleteToLocation)
    ClearableAutoCompleteTextView autoCompleteToLocation;
    @BindView(R.id.btnRideNow)
    Button btnRideNow;
    @BindView(R.id.bottomLayout)
    LinearLayout bottomLayout;

    private GoogleMap mMap;
    private SupportMapFragment mapFragment;
    private PlaceAutocompleteAdapter mAdapter;

    protected GeoDataClient mGeoDataClient;
    protected PlaceDetectionClient mPlaceDetectionClient;

    private FusedLocationProviderClient mFusedLocationClient;
    private String destination, origin;
    private LatLng destinationLatLng, originLatLng;

    CompositeDisposable compositeDisposable;

    ArrayList<LatLng>  latLngBlueLine = new ArrayList<>();

    ArrayList<LatLng>  latLngRedLine = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        latLngBlueLine.add(new LatLng(18.530886503530553, -69.86536502838135));
        latLngBlueLine.add(new LatLng(18.52437593323263, -69.86725330352783));
        latLngBlueLine.add(new LatLng(18.51798719516208, -69.86888408660889));
        latLngBlueLine.add(new LatLng(18.51021458700509, -69.86995697021484));



        latLngRedLine.add(new LatLng(18.48634518387883, -69.88742351531982));
        latLngRedLine.add(new LatLng(18.485409061076645, -69.87622261047363));
        latLngRedLine.add(new LatLng(18.484574686443203, -69.86570835113525));
        latLngRedLine.add(new LatLng(18.484147322252994, -69.85671758651733));
        latLngRedLine.add(new LatLng(18.48146100865814, -69.84131097793579));
        latLngRedLine.add(new LatLng(18.477736731539444, -69.83659029006958));
        latLngRedLine.add(new LatLng(18.473116887403993, -69.83328580856323));
        latLngRedLine.add(new LatLng(18.469677363157366, -69.83006715774536));
        latLngRedLine.add(new LatLng(18.465098007869905, -69.81927394866943));
        latLngRedLine.add(new LatLng(18.46462988911034, -69.80144262313843));
        latLngRedLine.add(new LatLng(18.466054594407176, -69.78545665740967));
        latLngRedLine.add(new LatLng(18.463998944413785, -69.76586580276489));
        latLngRedLine.add(new LatLng(18.46344941004591, -69.7456955909729));
        latLngRedLine.add(new LatLng(18.457485831338943, -69.71945285797119));
        latLngRedLine.add(new LatLng(18.455755744135708, -69.70434665679932));
        latLngRedLine.add(new LatLng(18.45343536451456, -69.6933388710022));
        latLngRedLine.add(new LatLng(18.450178638472323, -69.6774172782898));
        latLngRedLine.add(new LatLng(18.448611317050847, -69.65291261672974));
        latLngRedLine.add(new LatLng(18.447817473471485, -69.63469505310059));
        latLngRedLine.add(new LatLng(18.454392016052385, -69.62057590484619));
        latLngRedLine.add(new LatLng(18.454453078735533, -69.60617780685425));
        latLngRedLine.add(new LatLng(18.451053889665268, -69.60587739944458));
        latLngRedLine.add(new LatLng(18.448692736697673, -69.6057915687561));
        latLngRedLine.add(new LatLng(18.447471437942504, -69.60757255554199));




        compositeDisposable = new CompositeDisposable();
        // Construct a GeoDataClient for the Google Places API for Android.
        mGeoDataClient = Places.getGeoDataClient(this, null);


        // Construct a PlaceDetectionClient.
        mPlaceDetectionClient = Places.getPlaceDetectionClient(this, null);


        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        setContentView(R.layout.activity_maps);
        ButterKnife.bind(this);

        // StatusBarUtil.setTransparent(this);
        MapsActivityPermissionsDispatcher.getCurrentLocationWithPermissionCheck(this);

        autoCompleteFromLocation.setOnItemClickListener(provideAutocompleteClickListener(autoCompleteFromLocation));
        autoCompleteToLocation.setOnItemClickListener(provideAutocompleteClickListener(autoCompleteToLocation));


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


    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        // mMap.setMinZoomPreference(10.0f);
        // mMap.setMaxZoomPreference(14.0f);


        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(18.481746, -69.930539);


       // mMap.addMarker(new MarkerOptions().position(sydney).title("Santo Domingo"));
        //    mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        // Updates the location and zoom of the mMapView
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(sydney, 10);
        mMap.animateCamera(cameraUpdate);






   /*     PolylineOptions line=
                new PolylineOptions().add(new LatLng(18.538210598712347, -69.86326217651367),
                        new LatLng(18.530886503530553, -69.86536502838135),
                        new LatLng(18.52437593323263, -69.86725330352783),
                        new LatLng(18.51798719516208, -69.86888408660889),
        new LatLng(18.51021458700509, -69.86995697021484),
        new LatLng(18.504557859643054, -69.88321781158447))
                        .width(5).color(Color.RED);

        mMap.addPolyline(line);*/
     /*   setRoute1("18.48301276691192,-69.89019691944122", "18.48634518387883,-69.61042642593384");

        setRoute1("18.538210598712347,-69.86326217651367", "18.504557859643054,-69.88321781158447");*/


        int blue = Color.rgb(0, 0, 255);

        int red = Color.rgb(255, 0, 0);


              for (int i = 0; i < latLngRedLine.size() - 1; i++) {
                        mMap.addMarker(new MarkerOptions().position(latLngRedLine.get(i))
                                //.title(results.getName())
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bus_stop_blue)));
                    }

        for (int i = 0; i < latLngBlueLine.size() - 1; i++) {



            mMap.addMarker(new MarkerOptions().position(latLngBlueLine.get(i))
                    //.title(results.getName())
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_bus_stop_red)));
        }


        setRoute1(red, "18.48301276691192,-69.89019691944122", "18.48634518387883,-69.61042642593384");
        setRoute1(blue, "18.538210598712347,-69.86326217651367", "18.504557859643054,-69.88321781158447");

    }

    @SuppressLint("MissingPermission")
    @NeedsPermission({Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION})
    void getCurrentLocation() {
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        List<String> filters = new ArrayList<>();
        // filters.add(String.valueOf(Place.TYPE_BUS_STATION));
        filters.add(String.valueOf(Place.TYPE_TRANSIT_STATION));
        // filters.add(String.valueOf(Place.TYPE_TRAIN_STATION));
        PlaceFilter placeFilter = new PlaceFilter(false, filters);

        Task<PlaceLikelihoodBufferResponse> placeLikelihoodBufferResponseTask = mPlaceDetectionClient.getCurrentPlace(placeFilter);
        placeLikelihoodBufferResponseTask.addOnCompleteListener(new OnCompleteListener<PlaceLikelihoodBufferResponse>() {
            @Override
            public void onComplete(@NonNull Task<PlaceLikelihoodBufferResponse> task) {

                task.addOnCompleteListener(new OnCompleteListener<PlaceLikelihoodBufferResponse>() {
                    @Override
                    public void onComplete(@NonNull Task<PlaceLikelihoodBufferResponse> task) {
                        if (task.isSuccessful() && task.getResult() != null) {
                            PlaceLikelihoodBufferResponse likelyPlaces = task.getResult();
                            int M_MAX_ENTRIES = likelyPlaces.getCount();

                            String[] mLikelyPlaceNames;
                            String[] mLikelyPlaceAddresses;
                            String[] mLikelyPlaceAttributions;
                            LatLng[] mLikelyPlaceLatLngs;
                            // Set the count, handling cases where less than 5 entries are returned.
                            int count;
                            if (likelyPlaces.getCount() < M_MAX_ENTRIES) {
                                count = likelyPlaces.getCount();
                            } else {
                                count = M_MAX_ENTRIES;
                            }

                            int i = 0;
                            mLikelyPlaceNames = new String[count];
                            mLikelyPlaceAddresses = new String[count];
                            mLikelyPlaceAttributions = new String[count];
                            mLikelyPlaceLatLngs = new LatLng[count];

                            for (PlaceLikelihood placeLikelihood : likelyPlaces) {
                                // Build a list of likely places to show the user.
                                mLikelyPlaceNames[i] = (String) placeLikelihood.getPlace().getName();
                                mLikelyPlaceAddresses[i] = (String) placeLikelihood.getPlace()
                                        .getAddress();
                                mLikelyPlaceAttributions[i] = (String) placeLikelihood.getPlace()
                                        .getAttributions();
                                mLikelyPlaceLatLngs[i] = placeLikelihood.getPlace().getLatLng();

                                Log.e("PLACES", mLikelyPlaceNames[i]);
                                i++;
                                if (i > (count - 1)) {
                                    break;
                                }
                            }

                            // Release the place likelihood buffer, to avoid memory leaks.
                            likelyPlaces.release();

                            // Show a dialog offering the user the list of likely places, and add a
                            // marker at the selected place.
                            //  openPlacesDialog();

                        } else {
                            Log.e(TAG, "Exception: %s", task.getException());
                        }
                    }
                });
         /*       for (PlaceLikelihood placeLikelihood : likelyPlaces) {

                    mMap.addMarker(new MarkerOptions()
                            .position(placeLikelihood.getPlace().getLatLng())
                            .title((String) placeLikelihood.getPlace().getName())
                            .snippet((String) placeLikelihood.getPlace().getPhoneNumber()));
                }
                likelyPlaces.release();*/
            }
        });

      /*  mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {


                        // Add a marker in Sydney and move the camera
                        LatLng sydney = new LatLng(18.481746, -69.930539);
                        // Got last known location. In some rare situations this can be null.
                        if (sydney != null) {
                            //LatLngBounds CURRENT_LOCATION_LAT_LNG = new LatLngBounds(new LatLng(location.getLatitude(), location.getLongitude()), new LatLng(location.getLatitude(), location.getLongitude()));
                            mAdapter = new PlaceAutocompleteAdapter(MapsActivity.this, mGeoDataClient, new LatLngBounds(sydney, sydney), null);
                            autoCompleteFromLocation.setAdapter(mAdapter);
                            autoCompleteToLocation.setAdapter(mAdapter);

                            compositeDisposable.add(apiService.getBuStops(sydney.latitude + "," + sydney.longitude, 50000 + "", "transit_station", true, "AIzaSyA5jFLDhBw7b6zEZ-Z5j9x5a9DL7OkxHHY").subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<NearBySearchResponse>() {
                                @Override
                                public void accept(NearBySearchResponse googleDirectionResponse) throws Exception {


                                    BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.ic_station);
                                    Bitmap b = bitmapdraw.getBitmap();
                              *//*      Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);*//*
                                    mMap.clear();
                                    if (googleDirectionResponse.getResults().size() > 0 && googleDirectionResponse.getStatus().equalsIgnoreCase("OK")) {

                                        for (Results results : googleDirectionResponse.getResults()) {


                                            //LatLng sydney = new LatLng(results.getGeometry().getLocation().getLat(), results.getGeometry().getLocation().getLng());
                                            mMap.addMarker(new MarkerOptions().position(new LatLng(results.getGeometry().getLocation().getLat(), results.getGeometry().getLocation().getLng()))
                                                    .title(results.getName()).icon(BitmapDescriptorFactory.fromBitmap(createBitmapFromShape(R.drawable.map_stop_icon))));
                                        }

                                        for (int i = 0; i < googleDirectionResponse.getResults().size() - 1; i++) {
                                            setRoute(googleDirectionResponse.getResults().get(i).getGeometry().getLocation().getLat() + "," + googleDirectionResponse.getResults().get(i).getGeometry().getLocation().getLng(),
                                                    googleDirectionResponse.getResults().get(i++).getGeometry().getLocation().getLat() + "," + googleDirectionResponse.getResults().get(i++).getGeometry().getLocation().getLng());
                                        }


                                    }

                                    //  mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {
                                    throwable.printStackTrace();

                                }
                            }));
                            // Logic to handle location object
                        }
                    }
                });*/


    }

    private Bitmap createBitmapFromShape(@DrawableRes int drawable) {
        int px = getApplicationContext().getResources()
                .getDimensionPixelSize(R.dimen.bikeshare_small_marker_size);

        Bitmap bitmap = Bitmap.createBitmap(px, px, Bitmap.Config.ARGB_8888);

        Canvas c = new Canvas(bitmap);
        Drawable shape = ContextCompat.getDrawable(getApplicationContext(), drawable);
        shape.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());
        shape.draw(c);

        return bitmap;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MapsActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @OnShowRationale({Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION})
    void getCurrentLocationOnShowRationale(final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setMessage(R.string.permission_location_rationale)
                .setPositiveButton(R.string.button_allow, (dialog, button) -> request.proceed())
                .setNegativeButton(R.string.button_deny, (dialog, button) -> request.cancel())
                .show();
    }

    @OnPermissionDenied({Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION})
    void getCurrentLocationOnPermDenied() {
        Snackbar.make(findViewById(android.R.id.content), "Allow us to get exact pick-up location", Snackbar.LENGTH_LONG)
                .setAction("OK", view -> {

                    Intent intent = new Intent();
                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri = Uri.fromParts("package", getPackageName(), null);
                    intent.setData(uri);
                    startActivity(intent);
                    //   UserPhoneNoActivityPermissionsDispatcher.getOtpWithPermissionCheck(this);
                }).show();
    }

    @OnNeverAskAgain({Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION})
    void getCurrentLocationOnNeverAskAgain() {

        Snackbar.make(findViewById(android.R.id.content), "Allow us to get exact pick-up location", Snackbar.LENGTH_LONG)
                .setAction("OK", view -> {
                    /*Snackbar snackbar1 = Snackbar.make(dummyLayoutForSnackbar, "Message is restored!", Snackbar.LENGTH_SHORT);
                    snackbar1.show();*/
                    Intent intent = new Intent();
                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri = Uri.fromParts("package", getPackageName(), null);
                    intent.setData(uri);
                    startActivity(intent);
                    //   UserPhoneNoActivityPermissionsDispatcher.getOtpWithPermissionCheck(this);
                }).show();
    }

    private AdapterView.OnItemClickListener provideAutocompleteClickListener(ClearableAutoCompleteTextView autoCompleteTextView) {

        /**
         * Listener that handles selections from suggestions from the AutoCompleteTextView that
         * displays Place suggestions.
         * Gets the place id of the selected item and issues a request to the Places Geo Data Client
         * to retrieve more details about the place.
         *
         * @see GeoDataClient#getPlaceById(String...)
         */
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            /*
             Retrieve the place ID of the selected item from the Adapter.
             The adapter stores each Place suggestion in a AutocompletePrediction from which we
             read the place ID and title.
              */
                final AutocompletePrediction item = mAdapter.getItem(position);
                final String placeId = item.getPlaceId();
                final CharSequence primaryText = item.getPrimaryText(null);
                autoCompleteTextView.setText(primaryText + "," + item.getSecondaryText(new StyleSpan(Typeface.BOLD)));
                if (autoCompleteTextView.equals(autoCompleteToLocation)) {
                    //  autoCompleteToLocation.setTag(R.id.AUTOCOMPLETE_TAG_AUTOCOMPLETE_PREDICTION, item);
                    destination = "place_id:" + item.getPlaceId();

                }
                if (autoCompleteTextView.equals(autoCompleteFromLocation)) {
                    //  autoCompleteFromLocation.setTag(R.id.AUTOCOMPLETE_TAG_AUTOCOMPLETE_PREDICTION, item);
                    origin = "place_id:" + item.getPlaceId();
                }
                Log.i(TAG, "Autocomplete item selected: " + primaryText);

            /*
             Issue a request to the Places Geo Data Client to retrieve a Place object with
             additional details about the place.
              */
                Task<PlaceBufferResponse> placeResult = mGeoDataClient.getPlaceById(placeId);

                placeResult.addOnCompleteListener(new OnCompleteListener<PlaceBufferResponse>() {
                    @Override
                    public void onComplete(@NonNull Task<PlaceBufferResponse> task) {
                        try {
                            PlaceBufferResponse places = task.getResult();

                            // Get the Place object from the buffer.
                            final Place place = places.get(0);


                            if (autoCompleteTextView.equals(autoCompleteToLocation)) {
                                destinationLatLng = place.getLatLng();
                                //  autoCompleteToLocation.setTag(R.id.AUTOCOMPLETE_TAG_PLACE_BUFFER_RESPONSE, place.freeze());


                            }
                            if (autoCompleteTextView.equals(autoCompleteFromLocation)) {
                                originLatLng = place.getLatLng();
                                //autoCompleteFromLocation.setTag(R.id.AUTOCOMPLETE_TAG_PLACE_BUFFER_RESPONSE, place.freeze());
                            }

                        /*    if (place.getLatLng() != null) {
                                // Updates the location and zoom of the mMapView
                                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(place.getLatLng(), 18);
                                map.animateCamera(cameraUpdate);
                            }*/




               /* // Format details of the place for display and show it in a TextView.
                mPlaceDetailsText.setText(formatPlaceDetails(getResources(), place.getName(),
                        place.getId(), place.getAddress(), place.getPhoneNumber(),
                        place.getWebsiteUri()));*/

                            // Display the third party attributions if set.
                            final CharSequence thirdPartyAttribution = places.getAttributions();


                            Log.i(TAG, "Place details received: " + place.getName());
                            place.freeze();
                            places.release();
                        } catch (RuntimeRemoteException e) {
                            // Request did not complete successfully
                            Log.e(TAG, "Place query did not complete.", e);
                            return;
                        }
                    }
                });

                Log.i(TAG, "Called getPlaceById to get Place details for " + placeId);
            }
        };
    }

    @OnClick(R.id.btnRideNow)
    public void onViewClicked() {
        mMap.clear();
        setRoute("18.48301276691192,-69.89019691944122", "18.48634518387883,-69.61042642593384");
    }

    @OnClick(R.id.btnTimings)
    public void onbtnTimingsViewClicked() {
        BusTmingsView orderRideReviewFragment = new BusTmingsView();
        orderRideReviewFragment.show(getSupportFragmentManager(), R.id.rideBottomSheet);


    }


    private void setRoute(@NonNull String origin, @NonNull String dest) {
    /*    Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();*/


        //  ApiService apiService1 = retrofit.create(ApiService.class);


        compositeDisposable.add(apiService.getWayPoints(origin, dest, "transit", BuildConfig.DIRECTION_GOOGLE_SERVER_KEY).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<GoogleDirectionResponse>() {
            @Override
            public void accept(GoogleDirectionResponse googleDirectionResponse) throws Exception {

                BitmapDescriptorFactory.fromResource(R.drawable.ub__partner_funnel_helix_pin);
                MarkerOptions markerOptionsStart = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(createBitmapFromShape(R.drawable.map_stop_icon)));
                MarkerOptions markerOptionsEnd = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(createBitmapFromShape(R.drawable.bike_marker_small)));

                Log.i("zacharia", "inside on success" + googleDirectionResponse.getRoutes().size());
                ArrayList<LatLng> routelist = new ArrayList<LatLng>();
                if (googleDirectionResponse.getRoutes().size() > 0) {
                    ArrayList<LatLng> decodelist;
                    Route routeA = googleDirectionResponse.getRoutes().get(0);
                    Log.i("zacharia", "Legs length : " + routeA.getLegs().size());
                    if (routeA.getLegs().size() > 0) {
                        List<Steps> steps = routeA.getLegs().get(0).getSteps();
                        Log.i("zacharia", "Steps size :" + steps.size());
                        Steps step;
                        com.transitclone.responses.direction.Location location;
                        String polyline;

                        for (int i = 0; i < steps.size(); i++) {
                            step = steps.get(i);
                            location = step.getStart_location();
                            routelist.add(new LatLng(location.getLat(), location.getLng()));
                            Log.i("zacharia", "Start Location :" + location.getLat() + ", " + location.getLng());
                            polyline = step.getPolyline().getPoints();
                            decodelist = RouteDecode.decodePoly(polyline);
                            routelist.addAll(decodelist);
                            location = step.getEnd_location();
                            routelist.add(new LatLng(location.getLat(), location.getLng()));
                            Log.i("zacharia", "End Location :" + location.getLat() + ", " + location.getLng());
                            Log.d("Lat", steps.get(i).getDistance().getText());
                        }
                    }
                }
                Log.i("zacharia", "routelist size : " + routelist.size());
                if (routelist.size() > 0) {
                    Random rnd = new Random();
                    int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                    //    view.setBackgroundColor(color);

                    /*PolylineOptions rectLine = new PolylineOptions().width(10).color(
                            ContextCompat.getColor(MapsActivity.this, R.color.colorPrimary));*/

                    PolylineOptions rectLine = new PolylineOptions().width(8).color(color);

                    for (int i = 0; i < routelist.size(); i++) {
                        rectLine.add(routelist.get(i));
                    }

                    //  mMap.clear();
                    // Adding route on the map
                    mMap.addPolyline(rectLine);
                    markerOptionsStart.position(routelist.get(0));
                    markerOptionsEnd.position(routelist.get(routelist.size() - 1));
                    //  markerOptions.draggable(true);
                    mMap.addMarker(markerOptionsStart);
                    mMap.addMarker(markerOptionsEnd);
                    zoomRoute(mMap, routelist);
                }

                WayOverView orderRideReviewFragment = new WayOverView(googleDirectionResponse);
                orderRideReviewFragment.show(getSupportFragmentManager(), R.id.rideBottomSheet);


            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                throwable.printStackTrace();
            }
        }));
    }

    private void setRoute1(int color, @NonNull String origin, @NonNull String dest) {
    /*    Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();*/


        //  ApiService apiService1 = retrofit.create(ApiService.class);


        compositeDisposable.add(apiService.getWayPoints(origin, dest, "driving", BuildConfig.DIRECTION_GOOGLE_SERVER_KEY).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<GoogleDirectionResponse>() {
            @Override
            public void accept(GoogleDirectionResponse googleDirectionResponse) throws Exception {

                BitmapDescriptorFactory.fromResource(R.drawable.ub__partner_funnel_helix_pin);
                MarkerOptions markerOptionsStart = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(createBitmapFromShape(R.drawable.map_stop_icon)));
                MarkerOptions markerOptionsEnd = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(createBitmapFromShape(R.drawable.bike_marker_small)));

                Log.i("zacharia", "inside on success" + googleDirectionResponse.getRoutes().size());
                ArrayList<LatLng> routelist = new ArrayList<LatLng>();
                if (googleDirectionResponse.getRoutes().size() > 0) {
                    ArrayList<LatLng> decodelist;
                    Route routeA = googleDirectionResponse.getRoutes().get(0);
                    Log.i("zacharia", "Legs length : " + routeA.getLegs().size());
                    if (routeA.getLegs().size() > 0) {
                        List<Steps> steps = routeA.getLegs().get(0).getSteps();
                        Log.i("zacharia", "Steps size :" + steps.size());
                        Steps step;
                        com.transitclone.responses.direction.Location location;
                        String polyline;

                        for (int i = 0; i < steps.size(); i++) {
                            step = steps.get(i);
                            location = step.getStart_location();
                            routelist.add(new LatLng(location.getLat(), location.getLng()));
                            Log.i("zacharia", "Start Location :" + location.getLat() + ", " + location.getLng());
                            polyline = step.getPolyline().getPoints();
                            decodelist = RouteDecode.decodePoly(polyline);
                            routelist.addAll(decodelist);
                            location = step.getEnd_location();

                            routelist.add(new LatLng(location.getLat(), location.getLng()));
                            Log.i("zacharia", "End Location :" + location.getLat() + ", " + location.getLng());
                            Log.d("Lat", steps.get(i).getDistance().getText());
                        }
                    }
                }
                Log.i("zacharia", "routelist size : " + routelist.size());
                if (routelist.size() > 0) {
                    // Random rnd = new Random();

                    //    view.setBackgroundColor(color);

                    /*PolylineOptions rectLine = new PolylineOptions().width(10).color(
                            ContextCompat.getColor(MapsActivity.this, R.color.colorPrimary));*/

                    PolylineOptions rectLine = new PolylineOptions().width(8).color(color);

                    for (int i = 0; i < routelist.size(); i++) {
                        rectLine.add(routelist.get(i));
                    }

                    //  mMap.clear();
                    // Adding route on the map
                    mMap.addPolyline(rectLine);


                    markerOptionsStart.position(routelist.get(0));
                    markerOptionsEnd.position(routelist.get(routelist.size() - 1));
                    //  markerOptions.draggable(true);

                    mMap.addMarker(markerOptionsStart);
                    mMap.addMarker(markerOptionsEnd);
                    zoomRoute(mMap, routelist);
                }

              /*  WayOverView orderRideReviewFragment = new WayOverView(googleDirectionResponse);
                orderRideReviewFragment.show(getSupportFragmentManager(), R.id.rideBottomSheet);*/


            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                throwable.printStackTrace();
            }
        }));
    }


    /**
     * Zooms a Route (given a List of LalLng) at the greatest possible zoom level.
     *
     * @param googleMap:      instance of GoogleMap
     * @param lstLatLngRoute: list of LatLng forming Route
     */
    public void zoomRoute(GoogleMap googleMap, List<LatLng> lstLatLngRoute) {

        if (googleMap == null || lstLatLngRoute == null || lstLatLngRoute.isEmpty()) return;

        LatLngBounds.Builder boundsBuilder = new LatLngBounds.Builder();
        for (LatLng latLngPoint : lstLatLngRoute)
            boundsBuilder.include(latLngPoint);

        int routePadding = 100;
        LatLngBounds latLngBounds = boundsBuilder.build();
        googleMap.setPadding(routePadding, routePadding, routePadding, routePadding);
        googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, routePadding));
    }

}
