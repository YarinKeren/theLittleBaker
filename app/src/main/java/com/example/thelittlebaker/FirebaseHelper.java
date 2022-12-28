package com.example.thelittlebaker;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FirebaseHelper {
    public static FirebaseDatabase db;
    public static DatabaseReference myRef;
    public static List<Map<String,String>> fromDBList;
    public static List<Map<String,Long>> fromDBNums;
    public static List<Map<String,String>>fromDBPati;
    public static List<Map<String,Long>>fromDBPatiNums;
    public static List<String> fromDBRef;
    public static List<String> fromDBPatiRef;

    //public static List<User> dbUserList;

    public FirebaseHelper(final String path){
        db = FirebaseDatabase.getInstance();
        myRef = db.getReference(path);

        FirebaseHelper.myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                fromDBList = new ArrayList<>();
                fromDBNums= new ArrayList<>();
                fromDBRef=new ArrayList<>();

                for(DataSnapshot data : snapshot.getChildren()){
                    fromDBList.add((Map) data.getValue());
                    fromDBRef.add(data.getKey());
                    fromDBNums.add((Map) data.getValue());

                }
                Log.d("FB", "onDataChange: Done downloading. ");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public static List<User> convertToUserList(){
        List<User> userList = new ArrayList<>();
        for(int i=0 ; i<fromDBList.size() ; i++){
            User temp = new User(fromDBList.get(i).get("userName"),fromDBList.get(i).get("password"), fromDBNums.get(i).get("age"), fromDBList.get(i).get("email"),Math.toIntExact(fromDBNums.get(i).get("acecssLevel")),fromDBRef.get(i));
            userList.add(temp);
        }
        return userList;
    }

    public static boolean isExists(String name){
        if(fromDBList != null)
            for (Map<String,String> map:fromDBList) {
                if (map.get("userName").equals(name)) {
                    Log.d("FB", "isExists: yassss ");
                    return true;
                }
            }
        return false;
    }

    public static String isExists(String name, double age, String mail){
        if(fromDBList != null)
            for (int i=0 ; i<fromDBList.size() ; i++) {
                if(fromDBList.get(i).get("userName").equals(name) &&
                        fromDBList.get(i).get("email").equals(mail) &&
                        fromDBNums.get(i).get("age")==age)
                    return fromDBList.get(i).get("password");
            }
        return null;
    }

    public static boolean login(String username, String password){
        if(myRef!=null && LoggedInUser.dbUserList != null) {
            for (User u : LoggedInUser.dbUserList) {
                if(u.getUserName().equals(username) && u.getPassword().equals(password)) {
                    Log.d("FB", "login:Login seccesful ");
                    LoggedInUser.loggedUser=u;
                    LoggedInUser.canHeOrder();
                    LoggedInUser.orderList=new ArrayList<>();
                    downloadOrderList();

                    return true;
                }
            }
            Log.d("FB", "login:login failed user or pass incorrect ");
            return false;
        }
        Log.e("FB", "login:error Fb is not ready ");
        return false;
    }

    public static boolean register(User user){
        if(myRef != null){
            try{
                myRef.push().setValue(user);
                Log.d("FB", "register success"+user);
                return true;
            }
            catch (Exception e){
                Log.e("FB", "register failed" );
                return false;
            }
        }
        return false;
    }

    public static void update(){
        if(myRef != null){
            try{
                myRef.child(LoggedInUser.loggedUser.getFbId()).child("lastOrder").setValue(LoggedInUser.loggedUser.getLastOrder());
                Log.d("FB","List uploaded");
            }catch (Exception e){
                Log.e("FB",String.valueOf(e));
            }
        }

    }


    public static void downloadOrderList(){
        myRef.child(LoggedInUser.loggedUser.getFbId()).child("lastOrder").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                fromDBPati=new ArrayList<>();
                fromDBPatiNums=new ArrayList<>();
                fromDBPatiRef=new ArrayList<>();

                for( DataSnapshot item: snapshot.getChildren()){
                    fromDBPati.add((Map<String, String>) item.getValue());
                    fromDBPatiNums.add((Map<String, Long>) item.getValue());
                    fromDBPatiRef.add(item.getKey());
                }
                Log.d("FB","end to download the order list");
                List<Patisserie>temp=new ArrayList<>();
                for (int i = 0; i < fromDBPati.size(); i++) {
                    Patisserie patisserie=new Patisserie(fromDBPati.get(i).get("name"),fromDBPatiNums.get(i).get("price"), Math.toIntExact(fromDBPatiNums.get(i).get("orderAmount")),fromDBPati.get(i).get("writeOnCake"),fromDBPatiRef.get(i));
                    temp.add(patisserie);
                }
                LoggedInUser.orderList=temp;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public static void deleteFromOrderList(Patisserie patisserie){
        myRef.child(LoggedInUser.loggedUser.getFbId()).child("lastOrder").child(patisserie.getId()).removeValue();
    }
}

