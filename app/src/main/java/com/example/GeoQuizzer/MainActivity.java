package com.example.GeoQuizzer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String URL_DATA = "https://www.ttnsw.com.au/home/files/2014/8375293875293jkwhjkfhyhh/countries3.json";

    //hashmap used to allow for easy retrival of a key CountryID when passing intents
    private static final HashMap<Integer, Country> countryList = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadRecyclerViewData();

        //set default fragment
        NoteFragment fragment = new NoteFragment();
        final FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frag_slot, fragment);
        fragmentTransaction.commit();


        BottomNavigationView navMenu = findViewById(R.id.bottomNavBar);

        navMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.nav_home) {
                    Fragment fragment = new NoteFragment();
                    swapFragment(fragment);
                    return true;
                } else if (menuItem.getItemId() == R.id.nav_learn) {
                    Fragment fragment = new LearnFragment();
                    swapFragment(fragment);
                    return true;
                } else if (menuItem.getItemId() == R.id.nav_quiz) {
                    Fragment fragment = new QuizFragment();
                    swapFragment(fragment);
                    return true;
                }
                return false;
            }
        });

    }

    //Swapfragment method From NYT demo
    private void swapFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frag_slot, fragment);
        fragmentTransaction.commit();
    }

    private void loadRecyclerViewData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Data...");
        progressDialog.show();

        // retrieve countires from external URL via JSON
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONArray array = jsonObject.getJSONArray("country");

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject o = array.getJSONObject(i);

                                countryList.put(i, new Country(
                                        i,
                                        o.getString("name"),
                                        o.getString("continent"),
                                        o.getString("area"),
                                        o.getString("pop"),
                                        o.getString("gdp"),
                                        o.getString("capital"),
                                        o.getString("primary_language"),
                                        o.getString("desc"),
                                        o.getString("flag_url")
                                ));


                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), volleyError.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public static ArrayList<Country> getAllCountries() {
        return new ArrayList<Country>((List) Arrays.asList(countryList.values().toArray()));
    }

    public static Country getCountryById(int itemID) {
        return countryList.get(itemID);
    }

    }

