package com.example.thelittlebaker;


import android.graphics.Bitmap;
import android.net.Uri;

public class Salty extends  Patisserie {

    private String amountInPackage;

    public Salty(String name, int price, Bitmap image, String amountInPackage,String id, float ratingAmount) {
        super(name, price, image,id,ratingAmount);
        this.amountInPackage = amountInPackage;
    }

    public Salty(String name, int price, String amount) {
        super(name, price);
        this.amountInPackage = amount;
    }
    public Salty(String name, int price, String amount,String id) {
        super(name, price,id);
        this.amountInPackage = amount;
    }

    public Salty(String name, int price, String amountInPackage, String id, Long ratingAmount,double numOfVotes) {
        super(name, (long) price,id,ratingAmount, (int) numOfVotes);
        this.amountInPackage = amountInPackage;
    }

    public String getAmountInPackage() {
        return amountInPackage;
    }

    public void setAmountInPackage(String amountInPackage) {
        this.amountInPackage = amountInPackage;
    }

    @Override
    public String toString() {
        return "SaltyCookies{" +
                "amountInPackage='" + amountInPackage + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", image=" + image +
                '}';
    }
}
