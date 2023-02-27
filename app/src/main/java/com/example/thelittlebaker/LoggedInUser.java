package com.example.thelittlebaker;

import android.util.Log;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class LoggedInUser {
    public static User loggedUser;
    public static boolean canOrder;
    public static List<User> dbUserList;
    public static List<String> dbUserSt;
    public static List<Patisserie>pralinesList;
    public static List<Patisserie>saltyList;
    public static List<Patisserie>specialList;
    public static List<Patisserie>stripeCakesList;
    public static List<Patisserie>cookiesList;
    public static List<Patisserie>orderList;
    public static List<String>pralinesNames;
    public static List<String>saltyNames;
    public static List<String>specialNames;
    public static List<String>cookiesNames;
    public static List<String>stripeCakesNames;
    public static List<Order> ordersList;

    public static void canHeOrder(){
        LocalDateTime today=LocalDateTime.now();
        final DateTimeFormatter formatter= DateTimeFormatter.ofPattern("E,HH:mm:ss");
        final String[] NoReservationsEng={"Thu","Fri","Sat"};
        final String[] NoReservationsHeb={"יום ה","יום ו","שבת,"};
        boolean possibleToOrder=true;
        String hour,minutes,seconds;


        String day= today.format(formatter);
        Log.d("time",day);
        if (day.startsWith("s") || day.startsWith("m") || day.startsWith("t") || day.startsWith("w") || day.startsWith("f")) {
            day=today.format(formatter).substring(0,3);
            hour = today.format(formatter).substring(4, 6);
            minutes = today.format(formatter).substring(7, 9);
            seconds = today.format(formatter).substring(10);
            for (int i = 0; i < NoReservationsEng.length; i++) {
                if (NoReservationsEng[i].equals(day))
                    possibleToOrder=false;
            }
            if (day.equals(NoReservationsEng[0]))
                if (Math.toIntExact(Long.parseLong(hour))>=12)
                    possibleToOrder=false;
                else
                    possibleToOrder=true;
        }
        else{
            if(day.startsWith("שבת")) {
                day = today.format(formatter).substring(0, 4);
                hour = today.format(formatter).substring(6, 8);
                minutes = today.format(formatter).substring(9, 11);
            }
            else{
                    day = today.format(formatter).substring(0, 5);
                    hour = today.format(formatter).substring(7, 9);
                    minutes = today.format(formatter).substring(10, 12);
                    //seconds = today.format(formatter).substring(13);
                }

            for (int i = 0; i < NoReservationsHeb.length; i++) {
                if (NoReservationsHeb[i].equals(day))
                    possibleToOrder=false;
            }
            if (day.equals(NoReservationsHeb[0]))
                if (Math.toIntExact(Long.parseLong(hour))>=12)
                    possibleToOrder=false;
                else
                    possibleToOrder=true;

        }
        Log.d("time",day);
        Log.d("time",hour);
        Log.d("time",minutes);
       // Log.d("time",seconds);


        LoggedInUser.canOrder=possibleToOrder;

    }

    public static void prepare(){
        dbUserList = FirebaseHelper.convertToUserList();
        Log.d("FB", "prepare:  User List is ready");
    }

    public static void prepareSaltyList(){
        saltyList=SaltyFBHelper.convertToSaltyCookiesList();
        saltyNames=new ArrayList<>();
        saltyNames.add(" ");
        for (int i = 0; i < saltyList.size(); i++) {
            saltyNames.add(((Salty)saltyList.get(i)).getName());
        }
        Log.d("FB","prepare: salty cookies list is ready");
    }

    public static void prepareOrderList(){
        ordersList=OrderFBHelper.convertToOrderList();
    }
    public static void prepareSpecialList(){
        specialList=SpecialFBHelper.convertToSpecialList();
        specialNames=new ArrayList<>();
        specialNames.add(" ");
        for (int i = 0; i < specialList.size(); i++) {
            specialNames.add(((Special)specialList.get(i)).getName());
        }
        Log.d("FB","prepare: special list is ready");
    }
    public static void preparePralinesList(){
        pralinesList=PralinesFBHelper.convertToPralinesList();
        pralinesNames=new ArrayList<>();
        pralinesNames.add(" ");
        for (int i = 0; i < pralinesList.size(); i++) {
            pralinesNames.add(((Pralines)pralinesList.get(i)).getName());
        }
        Log.d("FB","prepare: pralines list is ready");
    }
    public static void prepareStripeCakesList(){
        stripeCakesList=StripeCakesFBHelper.convertToStripeCakesList();
        stripeCakesNames=new ArrayList<>();
        stripeCakesNames.add(" ");
        for (int i = 0; i < stripeCakesList.size(); i++) {
            stripeCakesNames.add(((StripeCakes)stripeCakesList.get(i)).getName());
        }
        Log.d("FB","prepare:stripe cakes list is ready");
    }
    public static void prepareCookiesList(){
        cookiesList=CookiesFBHelper.convertToCookiesList();
        cookiesNames=new ArrayList<>();
        cookiesNames.add(" ");
        for (int i = 0; i < cookiesList.size(); i++) {
            cookiesNames.add(((Cookies)cookiesList.get(i)).getName());
        }
        Log.d("FB","prepare: cookies list is ready");
    }



    public static void prepareSt(){
        dbUserSt = new ArrayList<>();
        if(dbUserList != null){
            for (User u: dbUserList) {
                String temp= u.getUserName()+":"+u.getPassword();
                temp+=u.getAcecssLevel()==1?" (Admin)":"";
                dbUserSt.add(temp);
            }
        }

        Log.d("FB", "prepare:  User List is ready");
    }

    public static boolean isInOrderList(Patisserie p){
        if(LoggedInUser.orderList.size()==0)
            return false;
        for(int i=0 ; i< LoggedInUser.orderList.size() ; i++)
            if (LoggedInUser.orderList.get(i).theyAreEquals(p))
                return true;
        return false;
    }

    public static String createOrderSt(){
        String order="";
        for(int i=0 ; i<LoggedInUser.orderList.size() ; i++){

            String productName=LoggedInUser.orderList.get(i).getName();
            String orderDetails=LoggedInUser.orderList.get(i).getDetails();
            int orderAmount=LoggedInUser.orderList.get(i).getOrderAmount();

            order+="product name: "+productName+",";
            order+=" amount to order: "+orderAmount+",";
            if(!orderDetails.equals(""))
                order+="details: "+orderDetails+".";
            order+="\n";
        }
        return order;
    }

    public static boolean cheakIfMyOrderReady(){
        for (int i = 0; i < LoggedInUser.ordersList.size(); i++) {
            String name=LoggedInUser.ordersList.get(i).getUser();
            if (LoggedInUser.loggedUser.getUserName().equals(name))
                if (LoggedInUser.ordersList.get(i).isReady()) {
                    LoggedInUser.loggedUser.setOrderId(LoggedInUser.ordersList.get(i).getId());
                    return true;
                }
        }
        return false;
    }


}

