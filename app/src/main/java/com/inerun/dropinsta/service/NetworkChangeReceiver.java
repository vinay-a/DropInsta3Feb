package com.inerun.dropinsta.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.inerun.dropinsta.constant.AppConstant;
import com.inerun.dropinsta.constant.UrlConstants;

/**
 * Created by vineet on 12/12/2016.
 */

public class NetworkChangeReceiver extends BroadcastReceiver {
    public static final int TYPE_POD_UPDATED = 111;
    private static final String TAG = "internet";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "Network connectivity change");

        if (intent.getExtras() != null) {
            final ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            final NetworkInfo ni = connectivityManager.getActiveNetworkInfo();

            if (ni != null && ni.isConnected()) {
                Log.i(TAG, "Network " + ni.getTypeName() + " connected");
//                onNetworkChange();
                sendNetworkChangeBroadcast(context);
            } else if (intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, Boolean.FALSE)) {
                Log.d(TAG, "There's no network connectivity");
            }
        }
    }

    private void sendNetworkChangeBroadcast(Context context) {
        Intent intent = new Intent();
        intent.setAction(AppConstant.ACTION_POD);
        intent.putExtra(UrlConstants.KEY_TYPE, DIReceiver.TYPE_NETWORK_CHANGE);
        context.sendBroadcast(intent);
    }

//    abstract public void onNetworkChange();
}
