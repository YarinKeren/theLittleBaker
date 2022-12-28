package com.example.thelittlebaker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class cookiesListActivity extends AppCompatActivity {
    ListView cookiesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cookies_list);

        cookiesList=findViewById(R.id.cookiesList);
        CookiesAdapter adapter =new CookiesAdapter(this,R.layout.cookies_item,LoggedInUser.cookiesList);

        cookiesList.setAdapter(adapter);
    }
}