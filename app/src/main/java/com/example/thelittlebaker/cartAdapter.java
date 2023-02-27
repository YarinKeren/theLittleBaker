package com.example.thelittlebaker;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class cartAdapter extends ArrayAdapter {

    List<Patisserie> buffer=new ArrayList<>();

    public cartAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.buffer.addAll(objects);
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cart_list, parent, false);
        TextView nameText,priceText,amountText,detalisText,detalisView;
        Button deleteBtn;

        detalisText=convertView.findViewById(R.id.detalisText);
        detalisView=convertView.findViewById(R.id.detalisView);
        nameText=convertView.findViewById(R.id.nameText);
        priceText=convertView.findViewById(R.id.priceText);
        amountText=convertView.findViewById(R.id.amountText);
        deleteBtn=convertView.findViewById(R.id.deleteItemBtn);




        if (buffer.get(position).getDetails().equals("")){
            detalisText.setVisibility(View.INVISIBLE);
            detalisView.setVisibility(View.INVISIBLE);
        }
        else {
            detalisText.setText(buffer.get(position).getDetails());
        }
        nameText.setText(buffer.get(position).getName());
        
        priceText.setText(String.valueOf(buffer.get(position).getPrice()));
        amountText.setText(String.valueOf(buffer.get(position).getOrderAmount()));

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoggedInUser.orderList.remove(buffer.get(position));
                Toast.makeText(getContext(),"item deleted", Toast.LENGTH_LONG).show();
                notifyDataSetChanged();


            }
        });

        return  convertView;
    }
}
