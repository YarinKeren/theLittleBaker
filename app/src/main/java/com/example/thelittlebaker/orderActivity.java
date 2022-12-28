package com.example.thelittlebaker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



public class orderActivity extends AppCompatActivity {

    Button pralinesBtn,cookiesBtn,saltyBtn,stripeCakesBtn,specialBtn,logOutBtn,cartBtn,paymentBtn,saveBtn;
    Intent Ipralines,IsaltyCookies,Ispecial,IstripeCakes,Icookies,Ilogin,Icart,Ipayment;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);


        pralinesBtn=findViewById(R.id.orderPralinesBtn);
        cookiesBtn=findViewById(R.id.orderCookiesBtn);
        saltyBtn=findViewById(R.id.orderSaltyBtn);
        stripeCakesBtn=findViewById(R.id.orderStripeCakesBtn);
        specialBtn=findViewById(R.id.orderSpecialBtn);
        logOutBtn=findViewById(R.id.logOutBtn);
        cartBtn=findViewById(R.id.cartBtn);
        paymentBtn=findViewById(R.id.paymentBtn);
        saveBtn=findViewById(R.id.saveBtn);

        Ipralines= new Intent(this,pralinesListActivity.class);
        IsaltyCookies= new Intent(this, saltyListActivity.class);
        Ispecial= new Intent(this, specialListActivity.class);
        IstripeCakes= new Intent(this,stripeCakesListActivity.class);
        Icookies=new Intent(this,cookiesListActivity.class);
        Ilogin=new Intent(this,loginActivity.class);
        Icart=new Intent(this,cartActivity.class);
        Ipayment=new Intent(this,paymentActivity.class);

//        Log.d("FB",String.valueOf(LoggedInUser.orderList.size()));

        if (LoggedInUser.canOrder){
            saveBtn.setVisibility(View.INVISIBLE);
        }
        else{
            saveBtn.setVisibility(View.VISIBLE);
            paymentBtn.setVisibility(View.INVISIBLE);
        }

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (LoggedInUser.orderList.size()==0)
                    Toast.makeText(orderActivity.this,"you didn't order Nothing",Toast.LENGTH_LONG).show();
                else{
                    LoggedInUser.loggedUser.setLastOrder(LoggedInUser.orderList);
                    FirebaseHelper.update();
                }


            }
        });

        DialogInterface.OnClickListener dialogListener=new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        LoggedInUser.loggedUser=null;
                        startActivity(new Intent(getBaseContext(),MainActivity.class));
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        Toast.makeText(orderActivity.this, "nothing happened", Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        };
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setMessage("are you sure you want to log out?")
                .setPositiveButton("yes",dialogListener)
                .setNegativeButton("no",dialogListener);

        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(LoggedInUser.loggedUser==null)
                    startActivity(Ilogin);
                else{
                    builder.show();

                }
            }
        });

        pralinesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(Ipralines);
            }
        });

        cookiesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(Icookies);
            }
        });

        saltyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(IsaltyCookies);
            }
        });

        stripeCakesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(IstripeCakes);
            }
        });

        specialBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(Ispecial);
            }
        });

        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(Icart);
            }
        });

        paymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(Ipayment);
            }
        });


    }
}