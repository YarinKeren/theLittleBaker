package com.example.thelittlebaker;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class cookiesListActivity extends AppCompatActivity {
    ListView cookiesList;
    FloatingActionButton fab, waze, insta;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cookies_list);

        fab = findViewById(R.id.gotocartbtnCookies);
        waze = findViewById(R.id.wazeBtnCookies);
        insta = findViewById(R.id.instaBtnCookies);

        cookiesList=findViewById(R.id.cookiesList);
        CookiesAdapter adapter =new CookiesAdapter(this,R.layout.cookies_item,LoggedInUser.cookiesList);

        cookiesList.setAdapter(adapter);

        fab.setOnClickListener(view -> {
            finish();
            startActivity(new Intent(getBaseContext(), cartActivity.class));
        });

        waze.setOnClickListener(view -> {
            startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://waze.com/ul/hsvc7f0gth")));
        });

        insta.setOnClickListener(view ->{
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://instagram.com/tthelittlebaker?igshid=Yzg5MTU1MDY=")));
        });
    }
}