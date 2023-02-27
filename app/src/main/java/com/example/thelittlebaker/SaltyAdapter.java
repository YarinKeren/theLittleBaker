package com.example.thelittlebaker;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SaltyAdapter extends ArrayAdapter {

    List<Salty> buffer=new ArrayList<>();

    public SaltyAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.buffer.addAll(objects);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null)
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.salty_item,parent,false);
        TextView detalis,showAmount;
        ImageView img;
        Button addToCart,plus,minus,rank;
        RatingBar ratingBar;


        detalis=convertView.findViewById(R.id.detalisSalty);
        img=convertView.findViewById(R.id.imgSalty);
        addToCart=convertView.findViewById(R.id.addToCartSaltyBtn);
        plus=convertView.findViewById(R.id.plusSaltyBtn);
        minus=convertView.findViewById(R.id.minusSaltyBtn);
        showAmount=convertView.findViewById(R.id.amountSaltyOrder);
        ratingBar=convertView.findViewById(R.id.ratingSalty);
        rank=convertView.findViewById(R.id.rankSaltyBtn);



        detalis.setText(buffer.get(position).getName());
        img.setImageBitmap(buffer.get(position).getImage());
        ratingBar.setRating(buffer.get(position).getRatingAmount());
        minus.setVisibility(View.INVISIBLE);

        if(!LoggedInUser.canOrder) {
            plus.setVisibility(View.INVISIBLE);
            addToCart.setVisibility(View.INVISIBLE);
        }
        rank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buffer.get(position).setNumOfVotes(buffer.get(position).getNumOfVotes()+1);
                buffer.get(position).setRatingAmount((float) ((ratingBar.getRating()+buffer.get(position).getRatingAmount())/buffer.get(position).getNumOfVotes()));
                SaltyFBHelper.updateRating(buffer.get(position));
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(buffer.get(position).getOrderAmount()==0)
                    minus.setVisibility(View.VISIBLE);

                buffer.get(position).setOrderAmount(buffer.get(position).getOrderAmount()+1);
                showAmount.setText(String.valueOf(buffer.get(position).getOrderAmount()));
                //notifyDataSetChanged();
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (buffer.get(position).getOrderAmount()==1)
                    minus.setVisibility(View.INVISIBLE);

                buffer.get(position).setOrderAmount(buffer.get(position).getOrderAmount()-1);
                showAmount.setText(String.valueOf(buffer.get(position).getOrderAmount()));
                //notifyDataSetChanged();
            }
        });
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buffer.get(position).setDetails("");
                if(buffer.get(position).getOrderAmount()==0)
                    Toast.makeText(getContext().getApplicationContext(), "You have not selected a quantity to order",Toast.LENGTH_LONG).show();
                else if (LoggedInUser.isInOrderList(buffer.get(position)))
                        buffer.get(position).setOrderAmount(buffer.get(position).getOrderAmount());
                else
                    LoggedInUser.orderList.add(buffer.get(position));
                Toast.makeText(getContext().getApplicationContext(), buffer.get(position).getName()+" Added to cart",Toast.LENGTH_LONG).show();


            }
        });

        return  convertView;

    }
}
