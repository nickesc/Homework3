package com.example.homework3;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LocationFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private JSONArray results;
    private List<Location> locations = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.fragment_location,container,false);
        recyclerView=view.findViewById(R.id.rv_location);

        try {
            results = new JSONObject(new String(getArguments().getByteArray("response"))).getJSONArray("results");

            for (int i=0;i<results.length();i++){

                Location tempLocation= new Location(results.getJSONObject(i));

                this.locations.add(tempLocation);
            }
            LocationAdapter locationAdapter = new LocationAdapter(locations);
            recyclerView.setAdapter(locationAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
            return view;
        } catch (JSONException e) {
            e.printStackTrace();
        }




        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
