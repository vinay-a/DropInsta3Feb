package com.inerun.dropinsta;

import android.app.Application;
import android.util.Log;

import com.inerun.dropinsta.data.LoginData;
import com.inerun.dropinsta.fontlib.TypefaceUtil;
import com.inerun.dropinsta.network.ServiceManager;

/**
 * Created by vinay on 23/11/16.
 */

public class DropInsta extends Application {


    public  static LoginData user;
    static ServiceManager serviceManager;



    @Override
    public void onCreate() {
        super.onCreate();
        // Create default options which will be used for every
        //  displayImage(...) call if no options will be passed to this method
        Log.i("TMApplication","onCreate");
//        Thread.setDefaultUncaughtExceptionHandler(new MyExceptionHandler(this));
        serviceManager = ServiceManager.init(DropInsta.this);

        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/robotolight.ttf");
    }


    public static ServiceManager serviceManager() {
        return serviceManager;
    }


    public static LoginData getUser() {
        return user;
    }

    public  static void setUser(LoginData user) {
        DropInsta.user = user;
    }

}
