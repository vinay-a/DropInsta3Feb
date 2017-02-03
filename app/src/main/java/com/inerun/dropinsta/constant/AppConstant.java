package com.inerun.dropinsta.constant;

import android.Manifest;
import android.os.Environment;

import com.google.gson.Gson;
import com.inerun.dropinsta.activity.SignActivity;
import com.inerun.dropinsta.data.WhSearchParcelData;

import java.io.File;

/**
 * Created by vineet on 5/26/2016.
 */
public class AppConstant {

    private static final boolean LIVE = false;

    public static final int LISTING_PAGING_LIMIT = 30;
    public static final int SLIDER_DURATION = 3000;
    public static final int DB_VERSION = 1;
    //    public static final String DB_NAME = "database.db";
    public static final String DB_NAME = "/mnt/sdcard/example.db";
    public static final String ACTION_POD = "action_pod";
    public static final int DEFAULT_TIMEOUT = 20 * 1000;
    public static final String USER_PREFRENCES_NAME = "user_preferences";
    public static final String APP_PREFRENCES_NAME = "lang_preferences";
    public static final String APP_PREFRENCES_STORE = "store_preference";
    public static final String APP_PREFRENCES_SORTBY = "sortby";
    public static final String POD_FOLDER_PATH = Environment.getExternalStorageDirectory() + File.separator + SignActivity.FOLDERNAME;
    public static final int LIST_FLAG = 0;
    public static final int GRID_FLAG = 1;

    public static final int Price_HighToLow = 0;
    public static final int Price_LowToHigh = 1;
    public static final int Name_AToZ = 2;
    public static final int Name_ZToA = 3;
    public static final int FILTER_NUM = 5;

    public static final int ADDRESS_ADD_NEW_FIRST_TIME = 1;
    public static final int ADDRESS_ADD_NEW = 2;
    public static final int ADDRESS_EDIT = 3;
    public static final int ADDRESS_CHANGE_BILLING = 4;
    public static final int ADDRESS_CHANGE_SHIPPING = 5;
//    public static final String DATEIME_FORMAT = "yyyy.MM.dd_HH.mm.ss "; //2017.01.24_06.55.14
    public static final String DATEIME_FORMAT = "yyyy-MM-dd HH:mm:ss ";  //2017-01-24 09:09:30
    public static final int MULTIPLE_PERMISSION_REQUESTCODE = 111;
    public static final int POD_Delete_NoOfDays = 0;



    public static void getSampleSearchParcelJson() {

    }

    public static WhSearchParcelData getSampleSearchParcelData() {
        String string = "{ \"status\":true,\n" +
                "   \"searchresult\":[{\"barcode\":\"123\",\n" +
                "    \"name\":\"Prabhat Sir\",\n" +
                "    \"rackno\":\"AB-123\"},{\"barcode\":\"124\",\n" +
                "    \"name\":\"Vineet Sir\",\n" +
                "    \"rackno\":\"AB-123\"},{\"barcode\":\"125\",\n" +
                "    \"name\":\"Sumit Sir\",\n" +
                "    \"rackno\":\"AB-123\"},{\"barcode\":\"123\",\n" +
                "    \"name\":\"Prabhat Sir\",\n" +
                "    \"rackno\":\"AB-123\"},{\"barcode\":\"124\",\n" +
                "    \"name\":\"Vineet Sir\",\n" +
                "    \"rackno\":\"AB-123\"},{\"barcode\":\"125\",\n" +
                "    \"name\":\"Sumit Sir\",\n" +
                "    \"rackno\":\"AB-123\"}]\n" +
                "    }";

        Gson gson = new Gson();

        WhSearchParcelData data = gson.fromJson(string, WhSearchParcelData.class);
        return data;
    }

    public static String[] requiredPermissions() {
        String[] permissions = new String[]{Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.CALL_PHONE};
        return permissions;
    }


    //Class for Files
    public static class Urls {


        public static final String URL_BASE = getBaseurl();

        private static String getBaseurl() {
            if (LIVE) {
                return "http://tigmoo-live/api/index/";
            } else {
                return "http://148.251.29.69/tigmoo-live/api/index/";

            }
        }


        /* BaseURL + service_name*/
        public static final String URL_SIGN_UP = URL_BASE + "registration";
        public static final String URL_LOGIN = URL_BASE + "login";
        public static final String URL_FBLOGIN = URL_BASE + "facebooklogin";
        public static final String URL_GPLUS_LOGIN = URL_BASE + "googlelogin";
        public static final String URL_GCM_SERVER = URL_BASE + "android";
        public static final String URL_STORE_DATA = URL_BASE + "getstore";
        public static final String URL_CATEGORY_DATA = URL_BASE + "getCategorytree";
        public static final String HOME_PAGE_URL = URL_BASE + "home";
        public static final String URL_PRODUCT_LISTING_PAGE = URL_BASE + "productlisting";
        public static final String URL_PRODUCT_IMAGES = URL_BASE + "productImage";
        public static final String URL_PRODUCT_DETAIL = URL_BASE + "ProductDetails";
        public static final String URL_DAILY_DEALS = URL_BASE + "Dailydeals";
        public static final String URL_SEARCH = URL_BASE + "getallProduct";
        public static final String URL_CONTACT_US = URL_BASE + "ContactUs";
        public static final String URL_HOT_SELLING = URL_BASE + "HotSelling";
        public static final String URL_ADD_REVIEW = URL_BASE + "AddProductRating";
        public static final String URL_UPDATE_PROFILE = URL_BASE + "AccountUpdate";
        public static final String URL_DASHBOARD = URL_BASE + "dashboard";
        public static final String URL_CHANGE_PASSWORD = URL_BASE + "changePassword";
        public static final String URL_COUNTRY = URL_BASE + "countries";
        public static final String URL_STATE = URL_BASE + "getRegionCollection";
        public static final String URL_ADD_ADDRESS = URL_BASE + "addAddress";
        public static final String URL_ADDRESS_BOOK = URL_BASE + "address";
        public static final String URL_DELETE_ADDRESS = URL_BASE + "deleteAddress";
        public static final String URL_EDIT_ADDRESS = URL_BASE + "editaddress";
        public static final String URL_ADD_TO_WISHLIST = URL_BASE + "addWishlist";
        public static final String URL_REMOVE_FROM_WISHLIST = URL_BASE + "RemoveWishlistItem";
        public static final String URL_WISHLIST = URL_BASE + "wishlist";
        public static final String URL_MY_ORDER = URL_BASE + "orderHistory";
        public static final String URL_UPDATE_WISHLIST = URL_BASE + "updateWishlist";
        public static final String URL_ORDER_DETAIL = URL_BASE + "viewOrder";
        public static final String URL_PRODUCT_REVIEWS = URL_BASE + "customerReview";
        public static final String URL_POINTS_REWARDS = URL_BASE + "rewardsPoints";
        public static final String URL_NEWSLETTER_SUBSCRIPTION = URL_BASE + "getsubscription";
        public static final String URL_SAVE_SUBSCRIPTION = URL_BASE + "subscription";
        public static final String URL_TRACK_ORDER = URL_BASE + "trackOrder";
        public static final String URL_FORGOT_PASSWORD = URL_BASE + "forgotPassword";
        public static final String URL_SALE = URL_BASE + "sales";
        public static final String URL_LOGOUT = URL_BASE + "logout";


        public static final String URL_ABOUTUS = URL_BASE + "aboutus";
        public static final String URL_PRIVACY_POLICY = URL_BASE + "privacypolicy";
        public static final String URL_PAYMENT_METHODS = URL_BASE + "payment-methods";
        public static final String URL_REWARDS_LOYALTY_PROGRAM = URL_BASE + "RewardsLoyaltyProgram";
        public static final String URL_FAQ = URL_BASE + "faq";

    }

    public static class Keys {


        public static final String DATA = "data";
        public static final String USER_ID = "userid";


        public static final String LOCATION = "location";
    }

    public static class GAnalyticsKeys {
        public static final String TRACKING_ID = "UA-87317319-1";


        public static String HOME = "Home";
        public static String PRODUCT_LISTING = "Product Listing";
        public static String PRODUCT_DETAIL = "Product Detail";
        public static String DAILY_DEALS = "Daily Deals";
        public static String HOT_SELLING = "Hot Selling";
        public static String SALE = "Sale";
        public static String ORDER_DETAIL = "Order Detail";
        public static String CART = "Cart";
        public static String WISHLIST = "Wishlist";
        public static String CONTACT_US = "Contact Us";
        public static String CHECKOUT = "Checkout";
        public static String CONFIRM = "Order Confirmation";


    }


    public static class StatusKeys {


        public static final int DELIVERED_STATUS = 9;
        public static final String USER_ID = "userid";

    }

}
