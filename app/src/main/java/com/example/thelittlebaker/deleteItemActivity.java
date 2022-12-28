package com.example.thelittlebaker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class deleteItemActivity extends AppCompatActivity  {

    Spinner classSpinner,itemsSpinner;
    Button deleteBtn;
    String selected=null;
    Patisserie selectedItem=null;
    List<String> selectedListSt = LoggedInUser.pralinesNames;
    List<Patisserie> selectedList=LoggedInUser.pralinesList;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_item);

        ArrayList<String> classList = new ArrayList<>();
        classList.add("");
        classList.add("Pralines");
        classList.add("Cookies");
        classList.add("Salty");
        classList.add("Special");
        classList.add("StripeCakes");

        classSpinner = findViewById(R.id.classSpinner);
        itemsSpinner = findViewById(R.id.itemsNamesSpinner);
        deleteBtn=findViewById(R.id.deleteBtn);






        ArrayAdapter<String> classAdapter
                = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                classList);


        classAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        classSpinner.setAdapter(classAdapter);
        classSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selected = adapterView.getItemAtPosition(i).toString();
                itemsSpinner.setVisibility(View.VISIBLE);
                Log.d("FB", "the selected class is " + selected);

                switch (selected) {
                    case "Pralines":
                        selectedListSt = LoggedInUser.pralinesNames;
                        selectedList = LoggedInUser.pralinesList;
                        break;
                    case "Cookies":
                        selectedListSt = LoggedInUser.cookiesNames;
                        selectedList = LoggedInUser.cookiesList;
                        break;
                    case "Salty":
                        selectedListSt = LoggedInUser.saltyNames;
                        selectedList =LoggedInUser.saltyList;
                        break;
                    case "Special":
                        selectedListSt = LoggedInUser.specialNames;
                        selectedList = LoggedInUser.specialList;
                        break;
                    case "StripeCakes":
                        selectedListSt = LoggedInUser.stripeCakesNames;
                        selectedList =LoggedInUser.stripeCakesList;
                        break;
                    default:
                        break;
                }

                ArrayAdapter<String> namesAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, selectedListSt);
                namesAdapter.setDropDownViewResource(android.R.layout
                        .simple_spinner_dropdown_item);
                itemsSpinner.setAdapter(namesAdapter);
                itemsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        for (int j = 0; j < selectedList.size(); j++) {
                            if (selectedList.get(j).getName().equals(adapterView.getItemAtPosition(i).toString()))
                                selectedItem = selectedList.get(j);
                        }
                       Log.d("FB", "the selected item is "+adapterView.getItemAtPosition(i).toString());

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (selected) {
                    case "Pralines":
                        PralinesFBHelper.deleteItem((Pralines) selectedItem);
                        Toast.makeText(deleteItemActivity.this,"item deleted",Toast.LENGTH_LONG).show();
                        break;
                    case "Cookies":
                        CookiesFBHelper.deleteItem((Cookies) selectedItem);
                        Toast.makeText(deleteItemActivity.this,"item deleted",Toast.LENGTH_LONG).show();
                        break;
                    case "Salty":
                        SaltyFBHelper.deleteItem((Salty) selectedItem);
                        Toast.makeText(deleteItemActivity.this,"item deleted",Toast.LENGTH_LONG).show();
                        break;
                    case "Special":
                        SpecialFBHelper.deleteItem((Special) selectedItem);
                        Toast.makeText(deleteItemActivity.this,"item deleted",Toast.LENGTH_LONG).show();
                        break;
                    case "StripeCakes":
                        StripeCakesFBHelper.deleteItem((StripeCakes) selectedItem);
                        Toast.makeText(deleteItemActivity.this,"item deleted",Toast.LENGTH_LONG).show();
                        break;
                    default:
                        break;
                }

            }
        });




    }


}


