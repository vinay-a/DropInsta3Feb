package com.inerun.dropinsta.Exception;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.inerun.dropinsta.activity.ExceptionHandlerActivity;
import com.inerun.dropinsta.constant.UrlConstants;

import java.io.PrintWriter;
import java.io.StringWriter;

//import com.tigmoo.app.ExceptionHandlerActivity;
//import com.tigmoo.constant.UrlConstants;

public class MyExceptionHandler implements
        Thread.UncaughtExceptionHandler {
    private final Context myContext;


    public MyExceptionHandler(Context context) {

        myContext = context;
    }


    @Override
    public void uncaughtException(Thread thread, Throwable e) {
        handleUncaughtException(thread, e);
    }

    public void handleUncaughtException(Thread thread, Throwable exception) {
        StringWriter stackTrace = new StringWriter();
        exception.printStackTrace(new PrintWriter(stackTrace));
//        System.err.println(stackTrace);

        String s = stackTrace.toString();
        Log.i("MyExHandler", "" + s);
        Log.i("MyExHandler", "Excep" + exception.getMessage());


        Intent intent = new Intent(myContext, ExceptionHandlerActivity.class);
        // see step 5.
        intent.putExtra(UrlConstants.KEY_EXCEPTION, s); // required when starting from Application
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK); // required when starting from Application
        myContext.startActivity(intent);
        try {


            ((Activity) myContext).finish();
        } catch (Exception e) {
            e.printStackTrace();
        }

//        System.exit(1); // kill off the crashed app
    }


}
