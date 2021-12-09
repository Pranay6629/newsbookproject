package com.monu.newsapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.Toast;

import com.monu.newsapp.Client;
import com.monu.newsapp.R;
import com.monu.newsapp.adapter.DataAdapter;
import com.monu.newsapp.parameter.Articles;
import com.monu.newsapp.parameter.Headline;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TechFragment extends Fragment {
    RecyclerView recyclerView;
    DataAdapter dataAdapter;
    final String API_KEY = "eaf54c76cffa486b801fec2976a21a4e";
    Button refreshButton;
    List<Articles> articles = new ArrayList<>();


    public TechFragment() {
        // Required empty public constructor
    }

    public static TechFragment getInstance(){
        TechFragment techFragment = new TechFragment();
        return techFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_tech, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        refreshButton = view.findViewById(R.id.refresh);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        final String country = "in";
        String category = "technology";
        fetchJSON(country, category, API_KEY);
        

        return view;
    }

    private void fetchJSON(String country, String category, String api_key) {
        Call<Headline> call = Client.getInstance().getApi().getHeadlines(country, category, api_key);
        call.enqueue(new Callback<Headline>() {
            @Override
            public void onResponse(Call<Headline> call, Response<Headline> response) {
                if (response.isSuccessful() && response.body().getArticles() != null){
                    articles.clear();
                    articles = response.body().getArticles();
                    dataAdapter = new DataAdapter(getContext(), articles);
                    recyclerView.setAdapter(dataAdapter);
                }
            }

            @Override
            public void onFailure(Call<Headline> call, Throwable t) {
                Toast.makeText(getContext(), "CHECK YOUR INTERNET CONNECTION", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private String getCountry() {
        Locale locale = Locale.getDefault();
        String country = locale.getCountry();
        return country.toLowerCase();
    }
}