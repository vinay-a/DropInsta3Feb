package com.inerun.dropinsta.helper;

import android.content.Context;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.util.Patterns;
import android.widget.Toast;

import com.inerun.dropinsta.R;
import com.inerun.dropinsta.base.BaseActivity;
import com.inerun.dropinsta.constant.UrlConstants;
import com.inerun.dropinsta.data.StatusData;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by vinay on 23/11/16.
 */

public class DIHelper {
    public static boolean validateLoginData(Context context, String email, String pass) {


        if (email.length() < 1) {
//            Toast.makeText(context, R.string.error_email_field, Toast.LENGTH_LONG).show();
            ((BaseActivity) context).showSnackbar(R.string.error_email_field);
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//            Toast.makeText(context, R.string.error_invalid_email_field, Toast.LENGTH_LONG).show();
            ((BaseActivity) context).showSnackbar(R.string.error_invalid_email_field);
            return false;
        }
        if (pass.length() < 1) {
//            Toast.makeText(context, R.string.error_password_field, Toast.LENGTH_LONG).show();
            ((BaseActivity) context).showSnackbar(R.string.error_password_field);
            return false;
        }

        return true;
    }

    public static boolean validateReceiverName(Context context, String receiverName) {
        if (receiverName.length() < 1) {
            Toast.makeText(context, R.string.error_receiver_name, Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    public static boolean validateComment(Context context, String comment) {
        if (comment == null || comment.trim().length() < 1) {
//            Toast.makeText(context, R.string.error_comment, Toast.LENGTH_LONG).show();
            ((BaseActivity) context).showSnackbar(R.string.error_comment);
            return false;
        }

        return true;
    }

    public static boolean getStatus(JSONObject json) throws JSONException {
        if (json.has(UrlConstants.KEY_STATUS)) {
            return json.getBoolean(UrlConstants.KEY_STATUS);
        }
        return false;

    }

    public static String getMessage(JSONObject json) throws JSONException {
        if (json.has(UrlConstants.KEY_MESSAGE)) {
            return json.getString(UrlConstants.KEY_MESSAGE);
        }
        return "";
    }

    public static ArrayList<String> getStringArray(){
        ArrayList<String> status = new ArrayList<>();
        status.add("Delivered");
        status.add("Pending");
        status.add("Door Closed");

        return status;
    }

    public static ArrayList<StatusData> getStatusArrayList(){
        ArrayList<StatusData> status = new ArrayList<>();
        StatusData  statusData = new StatusData("0","Receiver not present.");
        status.add(statusData);
        StatusData  statusData1 = new StatusData("1","Door Closed");
        status.add(statusData1);
        StatusData statusData3 = new StatusData("","Select Option");
        status.add(statusData3);

        return status;
    }

    public static String getDateTime(String dateFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        return formatter.format(calendar.getTime());

    }



    public static void playBeepSound() {
        ToneGenerator toneG = new ToneGenerator(AudioManager.STREAM_ALARM, 100);
        toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 200);

    }
}
