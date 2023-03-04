package com.example.thelittlebaker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class orderActivity extends AppCompatActivity {

    Button pralinesBtn,cookiesBtn,saltyBtn,stripeCakesBtn,specialBtn,logOutBtn,cartBtn,paymentBtn,deleteUserBtn;
    Intent Ipralines,IsaltyCookies,Ispecial,IstripeCakes,Icookies,Ilogin,Icart,Ipayment,Imain;
    FloatingActionButton fab, waze, insta;
    TextView sorry;




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        sorry=findViewById(R.id.sorryTextOrder);
        pralinesBtn=findViewById(R.id.orderPralinesBtn);
        cookiesBtn=findViewById(R.id.orderCookiesBtn);
        saltyBtn=findViewById(R.id.orderSaltyBtn);
        stripeCakesBtn=findViewById(R.id.orderStripeCakesBtn);
        specialBtn=findViewById(R.id.orderSpecialBtn);
        logOutBtn=findViewById(R.id.logOutBtn);
        cartBtn=findViewById(R.id.cartBtn);
        paymentBtn=findViewById(R.id.paymentBtn);
        deleteUserBtn=findViewById(R.id.deleteUserBtn);
        fab=findViewById(R.id.gotocartbtnOrder);
        waze = findViewById(R.id.wazeBtnOrder);
        insta = findViewById(R.id.instaBtnOrder);

        Ipralines= new Intent(this,pralinesListActivity.class);
        IsaltyCookies= new Intent(this, saltyListActivity.class);
        Ispecial= new Intent(this, specialListActivity.class);
        IstripeCakes= new Intent(this,stripeCakesListActivity.class);
        Icookies=new Intent(this,cookiesListActivity.class);
        Ilogin=new Intent(this,loginActivity.class);
        Icart=new Intent(this,cartActivity.class);
        Ipayment=new Intent(this,paymentActivity.class);
        Imain=new Intent(this,MainActivity.class);

        AlertDialog.Builder readyOrderBuilder = new AlertDialog.Builder(this);
        readyOrderBuilder.setMessage("your order is ready");


        readyOrderBuilder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                OrderFBHelper.deleteOrder(LoggedInUser.loggedUser.getOrderId());
            }
        });


        boolean myOrderIsReady=LoggedInUser.cheakIfMyOrderReady();

        if (myOrderIsReady)
            readyOrderBuilder.show();

        if (LoggedInUser.canOrder){
            sorry.setVisibility(View.INVISIBLE);
        }
        else{
            sorry.setVisibility(View.VISIBLE);
            paymentBtn.setVisibility(View.INVISIBLE);
        }


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(getBaseContext(), cartActivity.class));
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

        deleteUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(orderActivity.this)
                        .setTitle("Delete User")
                        .setMessage("Are you sure you want to delete this user ?")

                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Continue with delete operation
                                FirebaseHelper.deleteUser(LoggedInUser.loggedUser);
                                LoggedInUser.loggedUser=null;
                                Toast.makeText(orderActivity.this, "user deleted", Toast.LENGTH_SHORT).show();
                                startActivity(Imain);
                            }
                        })
                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
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