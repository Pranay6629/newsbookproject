package com.monu.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.monu.newsapp.adapter.ViewPagerAdapter;
import com.monu.newsapp.fragment.EntertainFragment;
import com.monu.newsapp.fragment.HealthFragment;
import com.monu.newsapp.fragment.SportsFragment;
import com.monu.newsapp.fragment.TechFragment;

public class NewsAppActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_app);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        getTab();

        viewPager.setCurrentItem(0);
    }





    public void getTab(){
        final ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        new Handler().post(new Runnable() {
            @Override
            public void run() {

                viewPagerAdapter.addFragment(TechFragment.getInstance(), "Tech");
                viewPagerAdapter.addFragment(HealthFragment.getInstance(), "Health");
                viewPagerAdapter.addFragment(SportsFragment.getInstance(), "Sports");
                viewPagerAdapter.addFragment(EntertainFragment.getInstance(), "Entertainment");

                viewPager.setAdapter(viewPagerAdapter);
                tabLayout.setupWithViewPager(viewPager);
            }
        });
    }
}