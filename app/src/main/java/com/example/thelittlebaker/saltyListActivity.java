package com.example.thelittlebaker;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class saltyListActivity extends AppCompatActivity {
    ListView saltyList;
    FloatingActionButton fab, waze, insta;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salty_list);

        fab = findViewById(R.id.gotocartbtnSalty);
        saltyList=findViewById(R.id.saltyList);
        waze = findViewById(R.id.wazeBtnSalty);
        insta = findViewById(R.id.instaBtnSalty);

        SaltyAdapter adapter =new SaltyAdapter(this,R.layout.salty_item,LoggedInUser.saltyList);

        saltyList.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(getBaseContext(), cartActivity.class));
            }
        });

        waze.setOnClickListener(view -> {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://waze.com/ul/hsvc7f0gth")));
        });

        insta.setOnClickListener(view ->{
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://instagram.com/tthelittlebaker?igshid=Yzg5MTU1MDY=")));
        });
    }
}