package com.example.thelittlebaker;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class registerActivity extends AppCompatActivity {

    FirebaseHelper fbh;
    EditText name,email,pass1,pass2, age;
    Button register;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        fbh=new FirebaseHelper("Users");

        age=findViewById(R.id.ageInput);
        name=findViewById(R.id.nameInput);
        email=findViewById(R.id.inputMail);
        pass1=findViewById(R.id.pass1);
        pass2=findViewById(R.id.pass2);
        register=findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(!(pass1.getText().toString().equals(pass2.getText().toString())))
                    Toast.makeText(getBaseContext(),"password don't match",Toast.LENGTH_LONG).show();
                else {
                    if(name.getText().length()<2)
                        Toast.makeText(getBaseContext(),"user name too short +3 chars",Toast.LENGTH_LONG).show();
                    else if(!(email.getText().toString().contains("@") && email.getText().toString().contains(".")))
                        Toast.makeText(getBaseContext(),"mail isn't in the correct format",Toast.LENGTH_LONG).show();
                    else{
                        if(FirebaseHelper.isExists(name.getText().toString())){
                            Toast.makeText(registerActivity.this, "user already exists", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        User temp =new User(name.getText().toString(),pass1.getText().toString(),Double.parseDouble(age.getText().toString()),email.getText().toString());
                        boolean status=FirebaseHelper.register(temp);
                        if(status)
                            Toast.makeText(registerActivity.this, "registertion succes", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(registerActivity.this, "registertion failed", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }
}