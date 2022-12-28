package com.example.thelittlebaker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class RecoveryActivity extends AppCompatActivity {
    EditText userName, age, email, okPass;
    Button recover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recovery);

        userName=findViewById(R.id.recoveryUserName);
        age=findViewById(R.id.recoveryAge);
        email=findViewById(R.id.recoveryEmail);
        recover=findViewById(R.id.recoveryBtn);
        okPass=findViewById(R.id.okPass);

        recover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameEdit= userName.getText().toString();
                String mailEdit= email.getText().toString();
                double ageEdit= Double.valueOf(age.getText().toString());
                String status= FirebaseHelper.isExists(usernameEdit,ageEdit,mailEdit);
                if(status != null){
                    okPass.setText(status);
                    okPass.setVisibility(View.VISIBLE);
                    Snackbar.make(getCurrentFocus(),"found your password", BaseTransientBottomBar.LENGTH_LONG).show();
                }
                else{
                    Snackbar.make(getCurrentFocus()," cant found your password", BaseTransientBottomBar.LENGTH_LONG).show();
                }
            }
        });
    }
}