package com.example.thelittlebaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class addItemActivity extends AppCompatActivity {

    Button addPralines,addSalty,addSpecial,addStripeCake,addCookies;
    Intent Ipralines,IsaltyCookies,Ispecial,IstripeCakes,Icookies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        addPralines=findViewById(R.id.addPralines);
        addSalty=findViewById(R.id.addSalty);
        addSpecial=findViewById(R.id.addSpecial);
        addStripeCake=findViewById(R.id.addStripeCakes);
        addCookies=findViewById(R.id.addCookies);

        Ipralines= new Intent(this,addPralinesActivity.class);
        IsaltyCookies= new Intent(this, addSaltyActivity.class);
        Ispecial= new Intent(this, addSpecialActivity.class);
        IstripeCakes= new Intent(this,addStripeCakesActivity.class);
        Icookies=new Intent(this,addCookiesActivity.class);

        addPralines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(Ipralines);
            }
        });

        addSalty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(IsaltyCookies);
            }
        });

        addSpecial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(Ispecial);
            }
        });

        addStripeCake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(IstripeCakes);
            }
        });

        addCookies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(Icookies);
            }
        });

    }
}