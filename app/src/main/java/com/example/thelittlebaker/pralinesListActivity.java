package com.example.thelittlebaker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

public class pralinesListActivity extends AppCompatActivity {

    ListView pralinesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pralines_list);



        pralinesList=findViewById(R.id.pralinesList);
        PralinesAdapter adapter =new PralinesAdapter(this,R.layout.pralines_item,LoggedInUser.pralinesList);

        pralinesList.setAdapter(adapter);
    }
}