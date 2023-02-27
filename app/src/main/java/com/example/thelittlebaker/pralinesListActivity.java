package com.example.thelittlebaker;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class pralinesListActivity extends AppCompatActivity {

    ListView pralinesList;
    FloatingActionButton fab, waze, insta;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pralines_list);


        fab=findViewById(R.id.gotocartbtnPralines);
        pralinesList=findViewById(R.id.pralinesList);
        waze = findViewById(R.id.wazeBtnPralines);
        insta = findViewById(R.id.instaBtnPralines);
        PralinesAdapter adapter =new PralinesAdapter(this,R.layout.pralines_item,LoggedInUser.pralinesList);

        pralinesList.setAdapter(adapter);
        fab.setOnClickListener(view -> {
            finish();
            startActivity(new Intent(getBaseContext(), cartActivity.class));
        });

        waze.setOnClickListener(view -> {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://waze.com/ul/hsvc7f0gth")));
        });

        insta.setOnClickListener(view ->{
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://instagram.com/tthelittlebaker?igshid=Yzg5MTU1MDY=")));
        });
    }
}