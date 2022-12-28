package com.example.thelittlebaker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button login,register,list,add,delete;
    Intent loginActivity,registerActivity,userListActivity,addItemActivity,deleteItemActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login=findViewById(R.id.loginBtn);
        register=findViewById(R.id.registerBtn);
        textView=findViewById(R.id.textView6);
        list=findViewById(R.id.userBtn);
        add=findViewById(R.id.addItem);
        delete=findViewById(R.id.deleteBtn);
        list.setVisibility(View.INVISIBLE);
        add.setVisibility(View.INVISIBLE);
        delete.setVisibility(View.INVISIBLE);

        if(LoggedInUser.loggedUser!=null) {
            Log.d("FB", "user access "+LoggedInUser.loggedUser.getAcecssLevel());
            if(LoggedInUser.loggedUser.getAcecssLevel()==1){
                list.setVisibility(View.VISIBLE);
                add.setVisibility(View.VISIBLE);
                delete.setVisibility(View.VISIBLE);
                register.setVisibility(View.INVISIBLE);
                login.setVisibility(View.INVISIBLE);
            }
            textView.setText("Hello " + LoggedInUser.loggedUser.getUserName());
            login.setText("logout");
            login.setBackgroundColor(-65536);
        }
        else {
            textView.setText("please log in");
            login.setText("login");

        }

        DialogInterface.OnClickListener dialogListener=new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        LoggedInUser.loggedUser=null;
                        login.setText("login");
                        startActivity(new Intent(getBaseContext(),MainActivity.class));
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        Toast.makeText(MainActivity.this, "nothing happened", Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        };
        addItemActivity=new Intent(this,addItemActivity.class);
        deleteItemActivity=new Intent(this,deleteItemActivity.class);
        loginActivity = new Intent(this,loginActivity.class);
        registerActivity = new Intent(this,registerActivity.class);
        userListActivity= new Intent(this,UserListActivity.class);

        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setMessage("are you sure you want to log out?")
                .setPositiveButton("yes",dialogListener)
                .setNegativeButton("no",dialogListener);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(addItemActivity);
            }
        });

        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(userListActivity);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(LoggedInUser.loggedUser==null)
                    startActivity(loginActivity);
                else{
                    builder.show();

                }

            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(registerActivity);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(deleteItemActivity);
            }
        });

    }
}