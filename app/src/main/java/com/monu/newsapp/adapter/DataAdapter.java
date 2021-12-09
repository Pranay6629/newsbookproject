package com.monu.newsapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.monu.newsapp.NewsInDetailActivity;
import com.monu.newsapp.R;
import com.monu.newsapp.parameter.Articles;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataHolder> {

    Context context;
    List<Articles> articles;


    public DataAdapter(Context context, List<Articles> articles) {
        this.context = context;
        this.articles = articles;
    }

    @NonNull
    @Override
    public DataHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {


        SharedPreferences preferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        boolean firstStart= preferences.getBoolean("firstStart", true);
        if (firstStart){
            createTableOnFirstStart();
        }


        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.list_item, parent, false);
        DataHolder holder = new DataHolder(listItem);
        return holder;

    }



    @Override
    public void onBindViewHolder(@NonNull final DataHolder holder, final int position) {


        final Articles art =  articles.get(position);

        String url = art.getUrl();
        holder.newsTitle.setText(art.getTitle());
        holder.newsAuthor.setText(art.getAuthor());
        holder.newsDesp.setText(art.getDescription());
        String imageURL = art.getUrlToImage();
         Picasso.with(context).load(imageURL).into(holder.newsImage);



        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewsInDetailActivity.class);
                intent.putExtra("url", art.getUrl());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class DataHolder extends RecyclerView.ViewHolder {
        TextView newsTitle, newsDesp, newsAuthor;
        ImageView newsImage;
        CardView cardView;
        Button favBnt;
        public DataHolder(@NonNull View itemView) {
            super(itemView);
            newsTitle = itemView.findViewById(R.id.title);
            newsDesp = itemView.findViewById(R.id.discripton);
            newsAuthor = itemView.findViewById(R.id.author);
            newsImage =itemView.findViewById(R.id.imageView);
            cardView = itemView.findViewById(R.id.cardView);



        }


    }

    private void createTableOnFirstStart() {

        SharedPreferences preferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("firstStart", false);
        editor.apply();
    }

    public String getCountry(){
        Locale locale = Locale.getDefault();
        String country = locale.getCountry();
        return country.toLowerCase();
    }


}
