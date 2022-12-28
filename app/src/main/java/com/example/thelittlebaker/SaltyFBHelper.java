package com.example.thelittlebaker;


import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SaltyFBHelper {
    public static FirebaseDatabase db;
    public static DatabaseReference myRef;
    public static List<Map<String,String>> fromDBList;
    public static List<Map<String,Long>> fromDBNums;
    public static List<String> fromDBRef;

    public SaltyFBHelper(final String path){

        db = FirebaseDatabase.getInstance();
        myRef = db.getReference(path);
        Log.d("FB", "salty FirebaseHelper: Connected");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                fromDBList = new ArrayList<>();
                fromDBNums= new ArrayList<>();
                fromDBRef=new ArrayList<>();


                Log.d("FB","onDataChange: Started Downloading..");

                for(DataSnapshot data : snapshot.getChildren()){
                    fromDBList.add((Map) data.getValue());
                    fromDBRef.add(data.getKey());
                    fromDBNums.add((Map) data.getValue());



                }
                Log.d("FB", "onDataChange: salty Done downloading. ");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public static List<Patisserie> convertToSaltyCookiesList(){
        List<Patisserie> saltyCookiesList = new ArrayList<>();
        if(fromDBNums!=null && fromDBList!=null && fromDBList.size()>0)
            for(int i=0 ; i<fromDBList.size() ; i++){
                Salty temp = new Salty(fromDBList.get(i).get("name"),Math.toIntExact(fromDBNums.get(i).get("price")),fromDBList.get(i).get("amountInPackage"),fromDBRef.get(i),fromDBNums.get(i).get("ratingAmount"),Math.toIntExact(fromDBNums.get(i).get("numOfVotes")));
                temp.setDetails(" ");
                downloadImage(fromDBList.get(i).get("name"),temp);
                saltyCookiesList.add(temp);
            }
        return saltyCookiesList;
    }

    public static void updateRating(Salty salty){
        if (myRef!=null){
            try{
                myRef.child(salty.getId()).child("ratingAmount").setValue(salty.getRatingAmount());
                myRef.child(salty.getId()).child("numOfVotes").setValue(salty.getNumOfVotes());
                Log.d("FB","The rating has been updated");
            }catch (Exception e){
                Log.e("FB", String.valueOf(e));
            }
        }
    }

    private static void downloadImage(String name, Salty obj) {
        StorageReference storageReference= FirebaseStorage.getInstance().getReference("images/").child("Salty"+name);
        try{
            File localFile= File.createTempFile("tempfile","jpeg");
            storageReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    obj.setImage(BitmapFactory.decodeFile(localFile.getAbsolutePath()));


                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static boolean upload(Salty salty){
        if(myRef != null){
            try{
                myRef.push().setValue(salty);
                Log.d("FB", "upload success");
                return true;
            }
            catch (Exception e){
                Log.e("FB", "upload failed "+e );
                return false;
            }
        }
        return false;
    }

    public static void deleteItem(Salty salty){
        if (myRef!=null){
            try{
                myRef.child(salty.getId()).removeValue();
                deleteImage(salty.getName());
                LoggedInUser.saltyNames.remove(salty.getName());
                LoggedInUser.saltyList.remove(salty);
            }
            catch (Exception e){
                Log.d("FB",String.valueOf(e));
            }

        }
    }
    public static void deleteImage(String name){
        try{
            StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("images/").child("Salty"+name);
            storageReference.delete();

        }catch (Exception e) {
            Log.e("FB", "delete image failed " + e);
        }
    }

}
