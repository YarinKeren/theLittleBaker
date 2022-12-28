package com.example.thelittlebaker;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thelittlebaker.R.id;

public class cartActivity extends AppCompatActivity {

    ListView cartListView;
    TextView totalPayment;
    Button paymentBtn,saveOrderBtn;
    Intent Ipayment;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Ipayment=new Intent(this,paymentActivity.class);

        double payment=0;
        cartListView=findViewById(R.id.cartListView);
        totalPayment=findViewById(R.id.totalPayment);
        paymentBtn=findViewById(id.confirmBtn);
        saveOrderBtn=findViewById(id.saveOrderBtn);
        cartAdapter adapter =new cartAdapter(this,R.layout.cart_list,LoggedInUser.orderList);

        cartListView.setAdapter(adapter);

        if (LoggedInUser.canOrder){
            saveOrderBtn.setVisibility(View.INVISIBLE);
        }
        else{
            saveOrderBtn.setVisibility(View.VISIBLE);
            paymentBtn.setVisibility(View.INVISIBLE);
        }

        saveOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (LoggedInUser.orderList.size()==0)
                    Toast.makeText(cartActivity.this,"you didn't order Nothing",Toast.LENGTH_LONG).show();
                else{
                    LoggedInUser.loggedUser.setLastOrder(LoggedInUser.orderList);
                    FirebaseHelper.update();

                }
            }
        });


        for (int i=0;i<LoggedInUser.orderList.size();i++)
            payment+=LoggedInUser.orderList.get(i).getPrice();
        if (payment>0)
            totalPayment.setVisibility(View.VISIBLE);
        totalPayment.setText(String.valueOf(payment)+"NIS");

        paymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(Ipayment);
            }
        });




    }
}