package com.example.thelittlebaker;



import android.graphics.Bitmap;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;


public class Patisserie implements DatabaseReference.CompletionListener {

    protected String name;
    protected double price;
    protected int orderAmount;
    protected Bitmap image;
    protected String id;
    protected String details;
    protected float ratingAmount;
    protected double numOfVotes;

    public Patisserie() {

    }
    public Patisserie(String name,double price, int amount){
        this.name=name;
        this.price=price;
        this.orderAmount=amount;

    }
    public Patisserie(String name, Long price, int orderAmount, String writeOnCake, String id,float ratingAmount,double numOfVotes) {
        this.name=name;
        this.price=price;
        this.orderAmount=orderAmount;
        this.details=writeOnCake;
        this.id=id;
        this.ratingAmount=ratingAmount;
        this.numOfVotes=numOfVotes;

    }

    public Patisserie(String name, Long price, int orderAmount, String writeOnCake, String id) {
        this.name=name;
        this.price=price;
        this.orderAmount=orderAmount;
        this.details=writeOnCake;
        this.id=id;
        this.ratingAmount=0;
        this.numOfVotes=0;

    }




    public Patisserie(String name, int orderAmount){
        this.name=name;
        this.orderAmount=orderAmount;
    }

    public Patisserie(String name,double price, int orderAmount,String details){
        this.name=name;
        this.price=price;
        this.orderAmount=orderAmount;
        this.details=details;
        this.image=null;
    }



    public Patisserie(String name, double price, Bitmap image,String id,float ratingAmount) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.orderAmount=0;
        this.id=id;
        this.ratingAmount=ratingAmount;
    }

    public Patisserie(String name, double price,String id,float ratingAmount) {
        this.name = name;
        this.price = price;
        this.image = null;
        this.orderAmount=0;
        this.id=id;
        this.ratingAmount=ratingAmount;
    }
    public Patisserie(String name,double price,String id ){
        this.name=name;
        this.price=price;
        this.image=null;
        this.orderAmount=0;
        this.id=id;

    }

    public Patisserie(String name,double price) {
        this.name = name;
        this.price = price;
        this.image = null;
        this.orderAmount = 0;
    }
    public  Patisserie(String name, double price, Bitmap image){
        this.name = name;
        this.price = price;
        this.image = image;
        this.orderAmount=0;
    }

    public Patisserie(String name, Long price, String id, Long ratingAmount, int numOfVotes) {
        this.name = name;
        this.price = price;
        this.image = null;
        this.id=id;
        this.ratingAmount=ratingAmount;
        this.numOfVotes=numOfVotes;
    }


    public int getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(int orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public float getRatingAmount() {
        return ratingAmount;
    }

    public void setRatingAmount(float ratingAmount) {
        this.ratingAmount = ratingAmount;
    }

    public double getNumOfVotes() {
        return numOfVotes;
    }

    public void setNumOfVotes(double numOfVotes) {
        this.numOfVotes = numOfVotes;
    }

    public boolean theyAreEquals(Patisserie p){
        if(this.name.equals(p.getName()) && this.price==p.getPrice())
            return true;
        return false;
    }


    @Override
    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {

    }
}
