package com.example.thelittlebaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class loginActivity extends AppCompatActivity {

    Button singIn;
    EditText password, name;
    FirebaseHelper userDB;
    CookiesFBHelper cookiesDB;
    PralinesFBHelper pralinesDB;
    SaltyFBHelper saltyDB;
    SpecialFBHelper specialDB;
    StripeCakesFBHelper stripeCakesDB;

    Handler h,h1,h2,h3,h4,h5;
    TextView recover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userDB= new FirebaseHelper("Users");
        cookiesDB= new CookiesFBHelper("Cookies");
        pralinesDB= new PralinesFBHelper("pralines");
        saltyDB= new SaltyFBHelper("Salty");
        specialDB= new SpecialFBHelper("Special");
        stripeCakesDB=new StripeCakesFBHelper("StripeCakes");

        singIn = findViewById(R.id.signIn);
        name = findViewById(R.id.name);
        password = findViewById(R.id.pass);
        recover=findViewById(R.id.forgotPass);


        recover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(),RecoveryActivity.class));
            }
        });

        singIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = name.getText().toString();
                String pass = password.getText().toString();
                if (user.length() < 3) {
                    Toast.makeText(loginActivity.this, "username is too short", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (pass.length() < 1) {
                    Toast.makeText(loginActivity.this, "password is empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                Thread t1=new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        LoggedInUser.preparePralinesList();
                        Log.d("FB", "the PralinesList size is " + LoggedInUser.pralinesList.size());

                    }
                };
                Thread t2=new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        LoggedInUser.prepareSaltyList();
                        Log.d("FB","the SaltyList size is "+LoggedInUser.saltyList.size());

                    }
                };
                Thread t3=new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        LoggedInUser.prepareCookiesList();
                        Log.d("FB","the CookiesList size is "+LoggedInUser.cookiesList.size());

                    }
                };
                Thread t4=new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        LoggedInUser.prepareSpecialList();
                        Log.d("FB","the SpecialList size is "+LoggedInUser.specialList.size());

                    }
                };
                Thread t5=new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        LoggedInUser.prepareStripeCakesList();
                        Log.d("FB","the StripeCakesList size is "+LoggedInUser.stripeCakesList.size());
                    }
                };
                Thread t = new Thread() {
                    @Override
                    public void run() {
                        super.run();

                        LoggedInUser.prepare();
                        LoggedInUser.prepareSt();
                        boolean status = FirebaseHelper.login(user, pass);
                        if (status) {
                            Toast.makeText(loginActivity.this, "login successfully", Toast.LENGTH_SHORT).show();
                            Toast.makeText(loginActivity.this, "hello " + user, Toast.LENGTH_SHORT).show();
                            if(LoggedInUser.loggedUser.getAcecssLevel()==0){
                                startActivity(new Intent(getBaseContext(),orderActivity.class));
                            }
                            else {
                                startActivity(new Intent(getBaseContext(), MainActivity.class));
                            }
                        }
                        else
                            Toast.makeText(loginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                    }
                };
                h=new Handler();
                h.postDelayed(t,1000);
                h1=new Handler();
                h1.postDelayed(t1,1000);
                h2=new Handler();
                h2.postDelayed(t2,1000);
                h3=new Handler();
                h3.postDelayed(t3,1000);
                h4=new Handler();
                h4.postDelayed(t4,1000);
                h5=new Handler();
                h5.postDelayed(t5,1000);

            }
        });

    }
}