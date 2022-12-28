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

public class CookiesFBHelper {
    public static FirebaseDatabase db;
    public static DatabaseReference myRef;
    public static List<Map<String,String>> fromDBList;
    public static List<Map<String,Long>> fromDBNums;
    public static List<String> fromDBRef;


    public CookiesFBHelper(final String path){
        db = FirebaseDatabase.getInstance();
        myRef = db.getReference(path);
        Log.d("FB", "cookies FirebaseHelper: Connected");
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
                Log.d("FB", "onDataChange:cookies Done downloading. ");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    @NonNull
    public static List<Patisserie>convertToCookiesList(){
        List<Patisserie> cookiesList = new ArrayList<>();
        if(fromDBNums!=null && fromDBList!=null && fromDBList.size()>0)
            for(int i=0 ; i<fromDBList.size() ; i++){
                Cookies temp = new Cookies(fromDBList.get(i).get("name"),Math.toIntExact(fromDBNums.get(i).get("price")),fromDBList.get(i).get("amountInPackage"),fromDBRef.get(i),fromDBNums.get(i).get("ratingAmount"),Math.toIntExact(fromDBNums.get(i).get("numOfVotes")));
                temp.setDetails(" ");
                downloadImage(fromDBList.get(i).get("name"),temp);
                cookiesList.add(temp);
            }
        return cookiesList;
    }

    public static void updateRating(Cookies cookies){
        if (myRef!=null){
            try{
                myRef.child(cookies.getId()).child("ratingAmount").setValue(cookies.getRatingAmount());
                myRef.child(cookies.getId()).child("numOfVotes").setValue(cookies.getNumOfVotes());
                Log.d("FB","The rating has been updated");
            }catch (Exception e){
                Log.e("FB", String.valueOf(e));
            }
        }
    }

    private static void downloadImage(String name,Cookies obj) {
        StorageReference storageReference= FirebaseStorage.getInstance().getReference("images/").child("Cookies"+name);
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

    public static boolean upload(Cookies cookies){
        if(myRef != null){
            try{
                myRef.push().setValue(cookies);
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
    public static void deleteItem(Cookies cookies){
        if (myRef!=null){
            try{
                myRef.child(cookies.getId()).removeValue();
                deleteImage(cookies.getName());
                LoggedInUser.cookiesNames.remove(cookies.getName());
                LoggedInUser.cookiesList.remove(cookies);
            }
            catch (Exception e){
                Log.d("FB",String.valueOf(e));
            }

        }
    }
    public static void deleteImage(String name){
        try{
            StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("images/").child("Cookies"+name);
            storageReference.delete();

        }catch (Exception e) {
            Log.e("FB", "delete image failed " + e);
        }
    }
}
