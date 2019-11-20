package com.example.gamifiedassignment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class LearnDetailActivity extends AppCompatActivity {

    private TextView nameTV;
    private TextView areaTV;
    private TextView popTV;
    private TextView gdpTV;
    private TextView capitalTV;
    private TextView languageTV;
    private TextView descTV;
    private ImageView imageIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.country_detail);

        Intent intent = getIntent();

        int countryID = intent.getIntExtra("CountryID",0);

        Country country = MainActivity.getCountryById(countryID);

        nameTV = findViewById(R.id.country_name_tv);
        areaTV = findViewById(R.id.country_area_tv);
        popTV = findViewById(R.id.country_pop_tv);
        gdpTV = findViewById(R.id.country_gdp_tv);
        capitalTV = findViewById(R.id.country_capital_tv);
        languageTV = findViewById(R.id.country_language_tv);
        descTV = findViewById(R.id.country_desc_tv);
        imageIV = findViewById(R.id.country_image_iv);

        String imageURL = "https://www.ttnsw.com.au/home/files/2014/8375293875293jkwhjkfhyhh/" + country.getFlag_url();
        Glide.with(this).load(imageURL).into(imageIV);

        nameTV.setText(country.getName());
        areaTV.setText(country.getArea() + " km2");
        popTV.setText(country.getPop());
        gdpTV.setText("$" + country.getGdp() + " billion");
        capitalTV.setText(country.getCapital());
        languageTV.setText(country.getPrimary_language());
        descTV.setText(country.getDesc());
    }
}