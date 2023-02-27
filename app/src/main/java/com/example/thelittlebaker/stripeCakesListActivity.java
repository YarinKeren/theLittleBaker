package com.example.thelittlebaker;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class stripeCakesListActivity extends AppCompatActivity {
    ListView stripeCakesList;
    FloatingActionButton fab, waze, insta;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stripe_cakes_list);

        fab=findViewById(R.id.gotocartbtnStripeCakes);
        stripeCakesList=findViewById(R.id.stripeCakesList);
        waze = findViewById(R.id.wazeBtnStripe);
        insta = findViewById(R.id.instaBtntripe);

        StripeCakesAdapter adapter =new StripeCakesAdapter(this,R.layout.stripecakes_item,LoggedInUser.stripeCakesList);

        stripeCakesList.setAdapter(adapter);

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