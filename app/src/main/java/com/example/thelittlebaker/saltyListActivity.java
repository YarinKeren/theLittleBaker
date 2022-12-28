package com.example.thelittlebaker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class saltyListActivity extends AppCompatActivity {
    ListView saltyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salty_list);

        saltyList=findViewById(R.id.saltyList);
        SaltyAdapter adapter =new SaltyAdapter(this,R.layout.salty_item,LoggedInUser.saltyList);

        saltyList.setAdapter(adapter);
    }
}