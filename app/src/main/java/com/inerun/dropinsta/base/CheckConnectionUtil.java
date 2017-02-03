package com.inerun.dropinsta.base;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @author Vinay Arora
 * @version version 1.0
 * @description class to Check Whether 1. Sim is Available or Not 2. Airplane
 * Mode is Off or On 3. Internet Connection is Available or not
 * <p/>
 * we can call this CheckConnectionUtil.checkthings and we have to
 * implement CheckConnectionCallback interface to get result code
 * Also it shows dialogs with Go Back and Retry options
 */

public class CheckConnectionUtil {

    static CheckConnectionCallback mycallback;


    /**
     * @param context
     * @return true if internet is working otherwise false
     */
    public static boolean checkMyConnectivity(Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();

    }


    /**
     * @params result code 1 success all connections are available 2 sim not
     * available 3 Airplane mode is on 4 Internet Connection is not
     * available
     */

    public interface CheckConnectionCallback {
        public void connectionStatus(int resultcode);

    }

}
