package com.monu.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
//import android.widget.Toolbar;

import com.google.android.material.tabs.TabLayout;
import com.monu.newsapp.adapter.ViewPagersAdapter;
import com.monu.newsapp.fragment.BooksFragment;

public class BooksHomeActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_home);
        toolbar = (Toolbar)findViewById(R.id.booksapp_toolbar);
        setSupportActionBar(toolbar);
        viewPager = (ViewPager)findViewById(R.id.booksapp_viewpager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout)findViewById(R.id.booksapp_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagersAdapter adapter = new ViewPagersAdapter(getSupportFragmentManager());
        adapter.addBooksFragment(new BooksFragment(),"Books");
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }
}