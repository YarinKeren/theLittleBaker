package com.example.thelittlebaker;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SpecialAdapter extends ArrayAdapter {

    List<Special> buffer=new ArrayList<>();


    public SpecialAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.buffer.addAll(objects);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null)
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.special_item,parent,false);
        TextView detalis,inputText,showAmount;
        ImageView img;
        EditText writeOnCake,numberInput;
        Button addToCart,plus,minus,rank;
        RatingBar ratingBar;
        boolean isPie=buffer.get(position).getName().endsWith("pie") || buffer.get(position).getName().endsWith("Pie");




        detalis=convertView.findViewById(R.id.detalisSpecial);
        img=convertView.findViewById(R.id.imgSpecial);
        inputText=convertView.findViewById(R.id.inputText);
        writeOnCake=convertView.findViewById(R.id.writeOnCake);
        addToCart=convertView.findViewById(R.id.addToCartSpecialBtn);
        plus=convertView.findViewById(R.id.plusSpecialBtn);
        minus=convertView.findViewById(R.id.minusSpecialBtn);
        showAmount=convertView.findViewById(R.id.amountSpecialOrder);
        numberInput=convertView.findViewById(R.id.numberInput);
        ratingBar=convertView.findViewById(R.id.ratingSpecial);
        rank=convertView.findViewById(R.id.rankSpecialBtn);



        if(buffer.get(position).getName().endsWith("pie")|| buffer.get(position).getName().endsWith("Pie")){
           inputText.setVisibility(View.INVISIBLE);
           writeOnCake.setVisibility(View.INVISIBLE);
           numberInput.setVisibility(View.INVISIBLE);
        }
        else if (buffer.get(position).getName().startsWith("number")){
            inputText.setVisibility(View.VISIBLE);
            numberInput.setVisibility(View.VISIBLE);
            inputText.setText("what number would you like the cake number?");
        }
        else{
            inputText.setVisibility(View.VISIBLE);
            inputText.setText("What to write down on the cake?");
            writeOnCake.setVisibility(View.VISIBLE);

        }

        detalis.setText(buffer.get(position).getName());
        img.setImageBitmap(buffer.get(position).getImage());
        ratingBar.setRating(buffer.get(position).getRatingAmount());
        minus.setVisibility(View.INVISIBLE);

        rank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buffer.get(position).setNumOfVotes(buffer.get(position).getNumOfVotes()+1);
                buffer.get(position).setRatingAmount((float) ((ratingBar.getRating()+buffer.get(position).getRatingAmount())/buffer.get(position).getNumOfVotes()));
               SpecialFBHelper.updateRating(buffer.get(position));

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
                if(!isPie && buffer.get(position).getName().startsWith("number")) {
                    buffer.get(position).setWriteOnCake(numberInput.getText().toString());
                    buffer.get(position).setDetails(numberInput.getText().toString());
                }

                else if(!isPie && !buffer.get(position).getName().startsWith("number")) {
                    buffer.get(position).setWriteOnCake(writeOnCake.getText().toString());
                    buffer.get(position).setDetails(writeOnCake.getText().toString());
                }
                if(!isPie && buffer.get(position).getWriteOnCake().equals(""))
                    Toast.makeText(getContext().getApplicationContext(), "You have not write nothing", Toast.LENGTH_LONG).show();
                else if(buffer.get(position).getOrderAmount()==0)
                    Toast.makeText(getContext().getApplicationContext(), "You have not selected a quantity to order",Toast.LENGTH_LONG).show();


                if(!isPie && buffer.get(position).getName().startsWith("number")) {
                    LoggedInUser.orderList.add(new Special(buffer.get(position).getName(), (int) buffer.get(position).getPrice(), buffer.get(position).getOrderAmount(), numberInput.getText().toString()));
                    buffer.get(position).setOrderAmount(buffer.get(position).getOrderAmount()-1);
                    }
                else if (!isPie && !buffer.get(position).getName().startsWith("number")) {
                     LoggedInUser.orderList.add(new Special(buffer.get(position).getName(), (int) buffer.get(position).getPrice(), buffer.get(position).getOrderAmount(), writeOnCake.getText().toString()));
                     buffer.get(position).setOrderAmount(buffer.get(position).getOrderAmount()-1);
                    }
                else {
                     LoggedInUser.orderList.add(buffer.get(position));
                     buffer.get(position).setOrderAmount(buffer.get(position).getOrderAmount()-1);
                    }
                 Toast.makeText(getContext().getApplicationContext(), buffer.get(position).getName()+" Added to cart",Toast.LENGTH_LONG).show();


            }
        });



        return  convertView;

    }
}
