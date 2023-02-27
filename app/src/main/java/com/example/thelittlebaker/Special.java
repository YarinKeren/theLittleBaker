package com.example.thelittlebaker;


import android.graphics.Bitmap;
import android.net.Uri;

public class Special extends Patisserie{



    private String writeOnCake;

    public Special(String name, int price,String id,float ratingAmount,double numOfVotes) {
        super(name, (long) price,id, (long) ratingAmount, (int) numOfVotes);
    }

    public Special(String name, int price) {
        super(name, price);
    }
    public Special(String name, int price,String id) {
        super(name, price,id);
    }
    public Special(String name,int price, int amount,String writeOnCake){
        super(name,price,amount,writeOnCake);
        this.writeOnCake=writeOnCake;
    }

    public String getWriteOnCake() {
        return writeOnCake;
    }

    public void setWriteOnCake(String writeOnCake) {
        this.writeOnCake = writeOnCake;
    }


    @Override
    public String toString() {
        return "Special{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", image=" + image +
                '}';
    }
}

