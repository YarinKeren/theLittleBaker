package com.example.thelittlebaker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class stripeCakesListActivity extends AppCompatActivity {
    ListView stripeCakesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stripe_cakes_list);

        stripeCakesList=findViewById(R.id.stripeCakesList);
        StripeCakesAdapter adapter =new StripeCakesAdapter(this,R.layout.stripecakes_item,LoggedInUser.stripeCakesList);

        stripeCakesList.setAdapter(adapter);
    }
}