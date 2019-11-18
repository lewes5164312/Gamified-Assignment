package com.example.gamifiedassignment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class FakeCountryData {

    public static ArrayList<Country> getAllCountries() {
        return new ArrayList<Country>((List) Arrays.asList(items.values().toArray()));
    }

    private static final HashMap<Integer, Country> items = new HashMap<>();

    static {
        items.put(1, new Country(
                "Australia"
        ));

        items.put(2, new Country(
                "NZ"
        ));


    }
}
