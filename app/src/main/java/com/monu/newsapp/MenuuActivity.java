package com.monu.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MenuuActivity extends AppCompatActivity {
    private ImageButton news, books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuu);



        news = findViewById(R.id.newsBtn);
        books = findViewById(R.id.booksBtn);

        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuuActivity.this, NewsAppActivity.class);
                startActivity(intent);
            }
        });

        books.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuuActivity.this, BooksHomeActivity.class);
                startActivity(intent);
            }
        });
    }

    private static long back_pressed;
    private Toast backToast;

    @Override
    public void onBackPressed() {
        if (back_pressed + 2000 > System.currentTimeMillis()){
            backToast.cancel();
            super.onBackPressed();
            moveTaskToBack(true);
            finish();
            System.exit(0);
            Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(startMain);
            return;
        }
        else {
            backToast  = Toast.makeText(getBaseContext(), "Press once again to exit", Toast.LENGTH_SHORT);
            backToast.show();

        }
        back_pressed = System.currentTimeMillis();
    }
}