package com.example.thelittlebaker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class addSaltyActivity extends AppCompatActivity {

    ImageView imgGallery;
    Button upload,add;
    EditText name,price,amount;
    Uri imageUri;
    SaltyFBHelper SaltyDB;
    StorageReference storageReference;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_salty);

        SaltyDB=new SaltyFBHelper("Salty");
        name=findViewById(R.id.saltyName);
        amount=findViewById(R.id.saltyAmount);
        price=findViewById(R.id.saltyPrice);
        upload=findViewById(R.id.btnGallerySalty);
        imgGallery=findViewById(R.id.imgGallerySalty);
        add=findViewById(R.id.addSaltyBtn);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectImage();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uploadImage();
                String productName=name.getText().toString();
                String priceStr=price.getText().toString();
                String productAmount=amount.getText().toString();
                int productPrice=priceStr.equals("")?0:Integer.valueOf(priceStr);
                Uri productImage=imageUri;

                if(productName.equals(""))
                    Toast.makeText(addSaltyActivity.this,"you didn't input the product name",Toast.LENGTH_SHORT).show();
                else if(productPrice<=0 )
                    Toast.makeText(addSaltyActivity.this,"the price must be more then 0",Toast.LENGTH_SHORT).show();
                else if(productAmount.equals(""))
                    Toast.makeText(addSaltyActivity.this,"the amount must be more then 0",Toast.LENGTH_SHORT).show();
                else if(productImage==null)
                    Toast.makeText(addSaltyActivity.this,"you need to upload an image",Toast.LENGTH_SHORT).show();
                else {
                    Salty temp = new Salty(productName, productPrice, productAmount);
                    temp.setDetails(" ");
                    Toast.makeText(addSaltyActivity.this,"succes",Toast.LENGTH_SHORT).show();
                    if(temp!=null) {
                        Log.d("FB","object is ready");
                        boolean status = SaltyFBHelper.upload(temp);
                        if (status) {
                            name.setText("");
                            price.setText("");
                            amount.setText("");
                            Log.d("FB", "upload yes");
                        }
                    }
                }
            }
        });
    }

    private void uploadImage() {

        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("uploading file...");
        progressDialog.show();

        String fileName=name.getText().toString();
        storageReference= FirebaseStorage.getInstance().getReference("images/Salty"+fileName);
        storageReference.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        imgGallery.setImageURI(null);
                        Toast.makeText(addSaltyActivity.this,"successfully uploaded",Toast.LENGTH_SHORT).show();
                        if(progressDialog.isShowing())
                            progressDialog.dismiss();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if(progressDialog.isShowing())
                            progressDialog.dismiss();
                        Toast.makeText(addSaltyActivity.this,"failed to upload the image",Toast.LENGTH_SHORT).show();

                    }
                });
    }

    private void selectImage() {

        Intent intent =new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,100);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==100 && data!=null && data.getData()!=null){
            imageUri=data.getData();
            imgGallery.setImageURI(imageUri);
        }

    }

}