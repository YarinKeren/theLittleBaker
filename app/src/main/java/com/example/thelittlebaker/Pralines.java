package com.example.thelittlebaker;



import android.graphics.Bitmap;
import android.net.Uri;

public class Pralines extends Patisserie{

    private double amountInPackage;

    public Pralines(String name, double price, Bitmap image, double amountInPackage,String id,float ratingAmount) {
        super(name, price, image, id,ratingAmount);
        this.amountInPackage = amountInPackage;
    }
    public  Pralines(String name, double price, double amountInPackage){
        super(name,price);
        this.amountInPackage=amountInPackage;
    }
    public  Pralines(String name, double price, double amountInPackage,String id){
        super(name,price,id);
        this.amountInPackage=amountInPackage;
    }

    public Pralines(String name, Long price, Long amountInPackage, String id, Long ratingAmount, int numOfVotes) {
        super(name, price, id,ratingAmount,numOfVotes);
        this.amountInPackage = amountInPackage;
    }

    public double getAmountInPackage() {
        return amountInPackage;
    }

    public void setAmountInPackage(double amountInPackage) {
        this.amountInPackage = amountInPackage;
    }

    @Override
    public String toString() {
        return "Pralines{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", image=" + image +
                ", amountInPackage=" + amountInPackage +
                '}';
    }


}
