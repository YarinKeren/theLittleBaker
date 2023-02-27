package com.example.thelittlebaker;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirebaseHelper {
    public static FirebaseDatabase db;
    public static DatabaseReference myRef;
    public static List<Map<String,String>> fromDBList;
    public static List<Map<String,Long>> fromDBNums;

    public static List<String> fromDBRef;


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

    public static void deleteUser(User user){
        if (myRef!=null){
            try{
                myRef.child(user.getFbId()).removeValue();
                LoggedInUser.dbUserList.remove(user);
                Log.d("FB","user deleted");

            }catch (Exception e){
                Log.e("FB",String.valueOf(e));
            }
        }
    }
}

