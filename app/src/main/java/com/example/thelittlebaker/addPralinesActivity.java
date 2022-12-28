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

public class addPralinesActivity extends AppCompatActivity {
    ImageView imgGallery;
    Button upload,add;
    EditText name,price,amount;
    Uri imageUri;
    PralinesFBHelper pralinesDB;
    StorageReference storageReference;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pralines);
        pralinesDB=new PralinesFBHelper("pralines");

        name=findViewById(R.id.pralinesName);
        price=findViewById(R.id.pralinesPrice);
        amount=findViewById(R.id.amountPralines);
        upload=findViewById(R.id.selectImageBtn);
        imgGallery=findViewById(R.id.imgGalleryPralines);
        add=findViewById(R.id.uploadPralinesBtn);

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
                String amountStr=amount.getText().toString();
                double productPrice=priceStr.equals("")?0:Double.valueOf(priceStr);
                double amountProduct= amountStr.equals("")?0:Double.valueOf(amountStr);
                Uri productImage=imageUri;

                if(productName.equals(""))
                    Toast.makeText(addPralinesActivity.this,"you didn't input the product name",Toast.LENGTH_SHORT).show();
                else if(productPrice<=0 )
                    Toast.makeText(addPralinesActivity.this,"the price must be more then 0",Toast.LENGTH_SHORT).show();
                else if(amountProduct<=0)
                    Toast.makeText(addPralinesActivity.this,"the amount must be more then 0",Toast.LENGTH_SHORT).show();
                else if(productImage==null)
                    Toast.makeText(addPralinesActivity.this,"you need to upload an image",Toast.LENGTH_SHORT).show();
                else {
                    Pralines temp = new Pralines(productName,productPrice, amountProduct);
                    temp.setDetails(" ");
                    if(temp!=null) {
                        Log.d("FB","object is ready");
                        boolean status = PralinesFBHelper.upload(temp);
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
        storageReference= FirebaseStorage.getInstance().getReference("images/Pralines"+fileName);
        storageReference.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        imgGallery.setImageURI(null);
                        Toast.makeText(addPralinesActivity.this,"successfully uploaded",Toast.LENGTH_SHORT).show();
                        if(progressDialog.isShowing())
                            progressDialog.dismiss();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if(progressDialog.isShowing())
                            progressDialog.dismiss();
                        Toast.makeText(addPralinesActivity.this,"failed to upload the image",Toast.LENGTH_SHORT).show();

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