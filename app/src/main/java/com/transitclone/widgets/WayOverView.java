package com.transitclone.widgets;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flipboard.bottomsheet.commons.BottomSheetFragment;
import com.google.android.gms.maps.model.LatLng;
import com.transitclone.R;
import com.transitclone.responses.direction.GoogleDirectionResponse;
import com.transitclone.responses.direction.Route;
import com.transitclone.responses.direction.RouteDecode;
import com.transitclone.responses.direction.Steps;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


@SuppressLint("ValidFragment")
public class WayOverView extends BottomSheetFragment {

    Unbinder unbinder;

    GoogleDirectionResponse googleDirectionResponse;

    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;

    public WayOverView(GoogleDirectionResponse googleDirectionResponse) {
        this.googleDirectionResponse = googleDirectionResponse;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_bottomsheet, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<LatLng> routelist = new ArrayList<LatLng>();
        if (googleDirectionResponse.getRoutes().size() > 0) {
            ArrayList<LatLng> decodelist;
            Route routeA = googleDirectionResponse.getRoutes().get(0);
            Log.i("Leg", "Legs length : " + routeA.getLegs().size());
            if (routeA.getLegs().size() > 0) {


                List<Steps> steps = routeA.getLegs().get(0).getSteps();
                Log.i("Steps", "Steps size :" + steps.size());
                Steps step;
                com.transitclone.responses.direction.Location location;
                String polyline;

                for (int i = 0; i < steps.size(); i++) {

                    View child = getLayoutInflater().inflate(R.layout.child, null);
                    TextView tvStationName = child.findViewById(R.id.tvStationName);

                    TextView tvType = child.findViewById(R.id.tvType);
                    tvStationName.setText(steps.get(i).getHtml_instructions() + "");
                    tvType.setText(steps.get(i).getTravel_mode() + "");

                    step = steps.get(i);
                    location = step.getStart_location();
                    routelist.add(new LatLng(location.getLat(), location.getLng()));
                    Log.i("step", "Start Location :" + location.getLat() + ", " + location.getLng());
                    polyline = step.getPolyline().getPoints();
                    decodelist = RouteDecode.decodePoly(polyline);
                    routelist.addAll(decodelist);
                    location = step.getEnd_location();
                    routelist.add(new LatLng(location.getLat(), location.getLng()));
                    Log.i("step", "End Location :" + location.getLat() + ", " + location.getLng());
                    Log.d("Lat", steps.get(i).getDistance().getText());

                    linearLayout.addView(child);
                }
            }


        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
