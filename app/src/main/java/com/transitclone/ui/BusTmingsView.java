package com.transitclone.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flipboard.bottomsheet.commons.BottomSheetFragment;
import com.google.android.gms.maps.model.LatLng;
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter;
import com.transitclone.R;
import com.transitclone.items.EditPackItem;
import com.transitclone.responses.direction.GoogleDirectionResponse;
import com.transitclone.responses.direction.Route;
import com.transitclone.responses.direction.RouteDecode;
import com.transitclone.responses.direction.Steps;
import com.transitclone.widgets.VerticalSpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


@SuppressLint("ValidFragment")
public class BusTmingsView extends BottomSheetFragment {

    Unbinder unbinder;

    @BindView(R.id.recyclerViewItems)
    RecyclerView recyclerViewItems;

    FastItemAdapter<EditPackItem> editPackItemFastItemAdapter;

    public BusTmingsView() {

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_bus_timings, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        editPackItemFastItemAdapter = new FastItemAdapter<>();
        recyclerViewItems.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewItems.setHasFixedSize(true);
        recyclerViewItems.setItemAnimator(new DefaultItemAnimator());
        recyclerViewItems.getItemAnimator().setAddDuration(500);
        recyclerViewItems.getItemAnimator().setRemoveDuration(500);
        recyclerViewItems.addItemDecoration(new VerticalSpaceItemDecoration(10));
        recyclerViewItems.setAdapter(editPackItemFastItemAdapter);

        //restore selections (this has to be done after the items were added
        editPackItemFastItemAdapter.withSavedInstanceState(savedInstanceState);
        editPackItemFastItemAdapter.add(new EditPackItem());
        editPackItemFastItemAdapter.add(new EditPackItem());
        editPackItemFastItemAdapter.add(new EditPackItem());
        editPackItemFastItemAdapter.add(new EditPackItem());
        editPackItemFastItemAdapter.add(new EditPackItem());
        editPackItemFastItemAdapter.add(new EditPackItem());
        editPackItemFastItemAdapter.add(new EditPackItem());


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
