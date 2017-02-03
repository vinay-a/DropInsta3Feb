package com.inerun.dropinsta.constant;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.inerun.dropinsta.data.LoginData;

/**
 * Created by vineet on 5/24/2016.
 */
public class Utils {

    /**
     * Checking for all possible internet providers
     */
    public static boolean isConnectingToInternet(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }

        }
        return false;
    }

// Storage Starts From Here

    /**
     * method to save Registration Data into Shared Preferences
     * @param context activity context
     * @param userid user id of user
     * @param email email of user


     */
    public static void saveLoginData(Context context, String userid, String email, String name, int usertype, String phone,String location)
    {
        saveUserData(context, userid, email, name, usertype, phone,location);
    }

    public static LoginData getLoginData(Context context)
    {
        LoginData loginData= new LoginData(getUserId(context),""+getUserType(context),getName(context),getUserEmail(context),getPhone(context),getLocation(context));
        return loginData;
    }



    private static void saveUserData(Context context, String userid, String email, String name, int usertype, String phone,String location)
    {
        saveUserId(context,userid);
        saveUserEmail(context, email);
        saveName(context, name);
        saveUserType(context, usertype);
        savePhone(context, phone);
        saveLocation(context, location);

    }
    public static String getLocation(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(
                AppConstant.USER_PREFRENCES_NAME,
                Context.MODE_PRIVATE);
        String value = sharedPref.getString(AppConstant.Keys.LOCATION, "");
        return value;
    }

    private static void saveLocation(Context context, String location) {
        SharedPreferences sharedPref = context.getSharedPreferences(
                AppConstant.USER_PREFRENCES_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(AppConstant.Keys.LOCATION, "" + location);
        editor.commit();
    }


    public static String getUserId(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(
                AppConstant.USER_PREFRENCES_NAME,
                Context.MODE_PRIVATE);
        String value = sharedPref.getString(AppConstant.Keys.USER_ID, "");
        return value;
    }

    public static void saveUserId(Context context, String userId) {
        SharedPreferences sharedPref = context.getSharedPreferences(
                AppConstant.USER_PREFRENCES_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(AppConstant.Keys.USER_ID, "" + userId);
        editor.commit();
    }

    public static String getUserEmail(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(
                AppConstant.USER_PREFRENCES_NAME,
                Context.MODE_PRIVATE);
        String value = sharedPref.getString(UrlConstants.KEY_EMAIL, "");
        return value;
    }

    public static void saveUserEmail(Context context, String email) {
        SharedPreferences sharedPref = context.getSharedPreferences(
                AppConstant.USER_PREFRENCES_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(UrlConstants.KEY_EMAIL, "" + email);
        editor.commit();
    }

    public static String getName(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(
                AppConstant.USER_PREFRENCES_NAME,
                Context.MODE_PRIVATE);
        String value = sharedPref.getString(UrlConstants.KEY_NAME, "");
        return value;
    }

    public static void saveName(Context context, String fname) {
        SharedPreferences sharedPref = context.getSharedPreferences(
                AppConstant.USER_PREFRENCES_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(UrlConstants.KEY_NAME, "" + fname);
        editor.commit();
    }

    public static void saveUserType(Context context, int userType) {
        SharedPreferences sharedPref = context.getSharedPreferences(
                AppConstant.USER_PREFRENCES_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(UrlConstants.KEY_USERTYPE, userType);
        editor.commit();
    }

    public static int getUserType(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(
                AppConstant.USER_PREFRENCES_NAME,
                Context.MODE_PRIVATE);
        int value = sharedPref.getInt(UrlConstants.KEY_USERTYPE, 0);
        return value;
    }

    public static void savePhone(Context context, String phone) {
        SharedPreferences sharedPref = context.getSharedPreferences(
                AppConstant.USER_PREFRENCES_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(UrlConstants.KEY_PHONE, phone);
        editor.commit();
    }

    public static String getPhone(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(
                AppConstant.USER_PREFRENCES_NAME,
                Context.MODE_PRIVATE);
        String value = sharedPref.getString(UrlConstants.KEY_PHONE, "");
        return value;
    }

    public static void savePassword(Context context, String password) {
        SharedPreferences sharedPref = context.getSharedPreferences(
                AppConstant.USER_PREFRENCES_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(UrlConstants.KEY_Password, password);
        editor.commit();
    }

    public static String getPassword(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(
                AppConstant.USER_PREFRENCES_NAME,
                Context.MODE_PRIVATE);
        String value = sharedPref.getString(UrlConstants.KEY_Password, "");
        return value;
    }

    public static boolean isUserLoggedIn(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(
                AppConstant.USER_PREFRENCES_NAME,
                Context.MODE_PRIVATE);
        if (sharedPref.contains(AppConstant.Keys.USER_ID)) {
            return true;
        }
        return false;
    }


    public static void deletePrefs(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(
                AppConstant.USER_PREFRENCES_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.clear().commit();
    }



//    public static String getGcmId(Context context) {
//        SharedPreferences sharedPref = context.getSharedPreferences(
//                AppConstant.APP_PREFRENCES_NAME,
//                Context.MODE_PRIVATE);
//        String value = sharedPref.getString(UrlConstants.GCM_REGID, null);
//        return value;
//    }
//
//    public static void saveGcmId(Context context, String gcm_regid) {
//        SharedPreferences sharedPref = context.getSharedPreferences(
//                AppConstant.APP_PREFRENCES_NAME,
//                Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPref.edit();
//        editor.putString(UrlConstants.GCM_REGID, "" + gcm_regid);
//        editor.commit();
//    }
//


//    public static int getStoreId(Context context) {
//        SharedPreferences sharedPref = context.getSharedPreferences(
//                AppConstant.APP_PREFRENCES_STORE,
//                Context.MODE_PRIVATE);
//        int value = sharedPref.getInt(AppConstant.Keys.STOREID, -1);
//
//        return value;
//    }
//
//    public static void saveStoreId(Context context, int storeId) {
//        SharedPreferences sharedPref = context.getSharedPreferences(
//                AppConstant.APP_PREFRENCES_STORE,
//                Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPref.edit();
//        editor.putInt(UrlConstants.STOREID, storeId);
//
//        editor.commit();
//    }
//    public static String getStoreName(Context context) {
//        SharedPreferences sharedPref = context.getSharedPreferences(
//                AppConstant.APP_PREFRENCES_STORE,
//                Context.MODE_PRIVATE);
//        String value = sharedPref.getString(UrlConstants.STORE_NAME, null);
//
//        return value;
//    }
//
//    public static void saveStoreName(Context context, String storeName) {
//        SharedPreferences sharedPref = context.getSharedPreferences(
//                AppConstant.APP_PREFRENCES_STORE,
//                Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPref.edit();
//        editor.putString(UrlConstants.STORE_NAME, storeName);
//
//        editor.commit();
//    }
//
//    public static int getSortByValue(Context context) {
//        SharedPreferences sharedPref = context.getSharedPreferences(
//                AppConstant.APP_PREFRENCES_SORTBY,
//                Context.MODE_PRIVATE);
//        int value = sharedPref.getInt(UrlConstants.SORTBY, 0);
//
//        return value;
//    }
//
//    public static void saveSortByValue(Context context, int value) {
//        SharedPreferences sharedPref = context.getSharedPreferences(
//                AppConstant.APP_PREFRENCES_SORTBY,
//                Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPref.edit();
//        editor.putInt(UrlConstants.SORTBY, value);
//
//        editor.commit();
//    }
//

//
//    public static void deletePrefsStore(Context context) {
//        SharedPreferences sharedPref = context.getSharedPreferences(
//                AppConstant.APP_PREFRENCES_STORE,
//                Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPref.edit();
//
//        editor.clear().commit();
//    }
//
//    public static void deletePrefsSortby(Context context) {
//        SharedPreferences sharedPref = context.getSharedPreferences(
//                AppConstant.APP_PREFRENCES_SORTBY,
//                Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPref.edit();
//
//        editor.clear().commit();
//    }
//
//
//    public static void setFirstTimeFalse(Context context) {
//        SharedPreferences sharedPref = context.getSharedPreferences(
//                AppConstant.USER_PREFRENCES_NAME,
//                Context.MODE_PRIVATE);
//
//        SharedPreferences.Editor editor = sharedPref.edit();
//        editor.putBoolean(UrlConstants.IS_FIRST_TIME_FOR_CART,
//                false);
//        editor.commit();
//
//    }
//
//    public static boolean checkIfFirstTime(Context context) {
//        SharedPreferences sharedPref = context.getSharedPreferences(
//                AppConstant.USER_PREFRENCES_NAME,
//                Context.MODE_PRIVATE);
//        boolean value = true;
//        SharedPreferences.Editor editor = sharedPref.edit();
//        value = sharedPref.getBoolean(
//                UrlConstants.IS_FIRST_TIME_FOR_CART, true);
//
//        return value;
//
//    }
//    public static void setFirstTimeTrue(Context context) {
//        SharedPreferences sharedPref = context.getSharedPreferences(
//                AppConstant.USER_PREFRENCES_NAME,
//                Context.MODE_PRIVATE);
//
//        SharedPreferences.Editor editor = sharedPref.edit();
//        editor.putBoolean(UrlConstants.IS_FIRST_TIME_FOR_CART,
//                true);
//        editor.commit();
//
//    }
//
//    public static int getSpendPoints(Context context) {
//        SharedPreferences sharedPref = context.getSharedPreferences(
//                AppConstant.USER_PREFRENCES_NAME,
//                Context.MODE_PRIVATE);
//        int value = sharedPref.getInt(UrlConstants.SPEND_POINTS, 0);
//        return value;
//    }
//
//    public static void saveSpendPoints(Context context, int spendpoints) {
//        SharedPreferences sharedPref = context.getSharedPreferences(
//                AppConstant.USER_PREFRENCES_NAME,
//                Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPref.edit();
//        editor.putInt(UrlConstants.SPEND_POINTS,  spendpoints);
//        editor.commit();
//    }
//
////    public static String getCartItems(Context context) {
////        SharedPreferences sharedPref = context.getSharedPreferences(
////                AppConstant.USER_PREFRENCES_NAME,
////                Context.MODE_PRIVATE);
////        String value = sharedPref.getString(UrlConstants.Cart_Item, "");
////        return value;
////    }
////
////    public static void saveCartItems(Context context, String cartitems) {
////        SharedPreferences sharedPref = context.getSharedPreferences(
////                AppConstant.USER_PREFRENCES_NAME,
////                Context.MODE_PRIVATE);
////        SharedPreferences.Editor editor = sharedPref.edit();
////        editor.putString(UrlConstants.Cart_Item, cartitems);
////        editor.commit();
////    }
//
//    public static String getSpenddiscount(Context context) {
//        SharedPreferences sharedPref = context.getSharedPreferences(
//                AppConstant.USER_PREFRENCES_NAME,
//                Context.MODE_PRIVATE);
//        String value = sharedPref.getString(UrlConstants.SPEND_DISCOUNT, null);
//        return value;
//    }
//
//    public static void saveSpenddiscount(Context context, String spendpoints) {
//        SharedPreferences sharedPref = context.getSharedPreferences(
//                AppConstant.USER_PREFRENCES_NAME,
//                Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPref.edit();
//        editor.putString(UrlConstants.SPEND_DISCOUNT,  spendpoints);
//        editor.commit();
//    }
//

}
