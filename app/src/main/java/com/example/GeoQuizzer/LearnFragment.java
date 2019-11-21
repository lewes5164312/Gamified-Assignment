package com.example.GeoQuizzer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class LearnFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_learn, container, false);

        recyclerView = view.findViewById(R.id.rv_learn);
        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        LearnAdapter countryAdapter = new LearnAdapter(MainActivity.getAllCountries());

        recyclerView.setAdapter(countryAdapter);

        GridLayoutManager gridLayoutManager =
                new GridLayoutManager( getContext(), 2);

        recyclerView.setLayoutManager(gridLayoutManager);

        return view;
    }


}
