package com.example.thelittlebaker;
        ;

import android.graphics.Bitmap;
import android.net.Uri;

public class StripeCakes extends Patisserie{


    public StripeCakes(String name, int price, Bitmap image,String id, float ratingAmount) {
        super(name, price, image,id,ratingAmount);
    }


    public StripeCakes(String name, int price){
        super(name,price);
    }

    public StripeCakes(String name, int price,String id){
        super(name,price,id);
    }

    public StripeCakes(String name, int price, String id, Long ratingAmount,double numOfVotes) {
        super(name, (long) price, id,ratingAmount, (int) numOfVotes);
    }

    @Override
    public String toString() {
        return "StripeCakes{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", image=" + image +
                '}';
    }

}
