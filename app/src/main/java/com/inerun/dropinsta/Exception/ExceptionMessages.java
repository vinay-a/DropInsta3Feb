package com.inerun.dropinsta.Exception;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteException;

import com.inerun.dropinsta.R;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * Created by vinay on 19/01/17.
 */

public class ExceptionMessages {

    public static String getMessageFromException(Context context, int responsecode, Exception exception) {
        String exception_message = "";
        if (responsecode == 500) {
            exception_message = context.getString(R.string.exception_alert_message_internal_server_error);

        } else if (responsecode == 404) {
            exception_message = context.getString(R.string.server_exception_error_message_resource_not_found);

        } else if (responsecode == 403) {
            exception_message = context.getString(R.string.server_exception_error_message_forbidden);

        } else {
            if (exception == null) {
                exception_message = context.getString(R.string.exception_alert_message_error);

            } else {
                exception_message =context.getString(getExceptionMessage(exception));
            }

        }


        return exception_message;
    }

    public static int getExceptionMessage(Exception exception) {
        int exception_message = R.string.exception_alert_message_error;
        if (exception.getClass().equals(UnknownHostException.class)) {
            exception_message = R.string.activity_base_alert_message_unknown_host_exception;
        } else if (exception.getClass().equals(IOException.class)) {
            exception_message = R.string.exception_alert_message_illegalstate_exception;
        }
//            else if (exception.getClass().equals(JSONException.class) || exception.getClass().equals(JsonSyntaxException.class)) {
//                exception_message = R.string.exception_alert_message_parsing_exception);
//            }
        else if (exception.getClass().equals(TimeOutException.class) || exception.getClass().equals(SocketTimeoutException.class)) {
            exception_message = R.string.exception_alert_message_timeout_exception;
        } else if (exception.getClass().equals(NetworkException.class)) {
            exception_message = R.string.exception_alert_message_network;
        } else if (exception.getClass().equals(IllegalStateException.class)) {
            exception_message = R.string.exception_alert_message_illegalstate_exception;
        }else if (exception.getClass().equals(SQLiteException.class)||exception.getClass().equals(SQLiteConstraintException.class)) {
            exception_message = R.string.exception_alert_message_sqllite_exception;
        } else {
            exception_message = R.string.exception_alert_message_error;
        }
        return exception_message;
    }
}
