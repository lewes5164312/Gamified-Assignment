package com.example.gamifiedassignment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class LearnFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_learn, container, false);

        final LearnAdapter learnAdapter = new LearnAdapter();

        recyclerView = view.findViewById(R.id.rv_learn);
        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        LearnAdapter countryAdapter = new LearnAdapter();
        countryAdapter.setData(FakeCountryData.getAllCountries());
        recyclerView.setAdapter(countryAdapter);
        return view;
    }


}
