import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class LoggedInUser {
    public static User loggedUser;
    public static List<User> dbUserList;
    public static List<String> dbUserSt;
    public static List<Pralines>pralinesList;
    public static List<Salty>saltyList;
    public static List<Special>specialList;
    public static List<StripeCakes>stripeCakesList;
    public static List<Cookies>cookiesList;


    public static void prepare(){
        dbUserList = FirebaseHelper.convertToUserList();
        Log.d("FB", "prepare:  User List is ready");
    }

    public static void prepareSaltyList(){
        saltyList= SaltyFBHelper.convertToSaltyCookiesList();
        Log.d("FB","prepare: salty cookies list is ready");
    }
    public static void prepareSpecialList(){
        specialList=SpecialFBHelper.convertToSpecialList();
        Log.d("FB","prepare: special list is ready");
    }
    public static void preparePralinesList(){
        pralinesList=PralinesFBHelper.convertToPralinesList();
        Log.d("FB","prepare: pralines list is ready");
    }
    public static void prepareStripeCakesList(){
        stripeCakesList=StripeCakesFBHelper.convertToStripeCakesList();
        Log.d("FB","prepare:stripe cakes list is ready");
    }
    public static void prepareCookiesList(){
        cookiesList=CookiesFBHelper.convertToCookiesList();
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
}
