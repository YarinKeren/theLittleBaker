package com.example.thelittlebaker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class specialListActivity extends AppCompatActivity {
    ListView specialList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_list);

        specialList=findViewById(R.id.specialList);
        SpecialAdapter adapter =new SpecialAdapter(this,R.layout.special_item,LoggedInUser.specialList);

        specialList.setAdapter(adapter);
    }
}