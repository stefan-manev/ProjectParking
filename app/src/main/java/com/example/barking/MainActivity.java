package com.example.barking;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.barking.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {

//    public String[] cities = new String[]{"Berovo", "Bitola", "Debar", "Gevgelija", "Kavadarci", "Kichevo",
//            "Krushevo", "Ohrid", "Prilep", "Skopje", "Struga", "Strumica", "Shtip", "Tetovo", "Veles"};
//
//    public Double[] lats = new Double[]{41.7061, 41.0297, 41.5198, 41.1452, 41.4329, 41.5129, 41.3706, 41.1231, 41.3441, 41.9981,
//            41.1778, 41.4378, 41.7464, 42.0069, 41.7165};
//    public Double[] lngs = new Double[]{22.8552, 21.3292, 20.5289, 22.4997, 22.0089, 20.9525, 21.2502, 20.8016, 21.5528, 21.4254,
//            20.6783, 22.6427, 22.1997, 20.9715, 21.7723};

    DBhelper dBhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);

//        for (int i = 0; i < 15; i++){
//            CityModel city = new CityModel(i, cities[i], lats[i], lngs[i]);
//            Boolean cityWasInserted = dBhelper.addOneCity(city);
//        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}