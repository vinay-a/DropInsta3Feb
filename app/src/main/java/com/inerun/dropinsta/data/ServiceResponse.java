package com.inerun.dropinsta.data;

import java.util.ArrayList;

/**
 * Created by vinay on 20/12/16.
 */

public class ServiceResponse {
    boolean status;
    String message;
    ArrayList<AddData> parcels;

    public  ArrayList<AddData> getParcels() {
        return parcels;
    }

    public boolean getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public class AddData{
        String parcelno;

        public String getParcelno() {
            return parcelno;
        }
    }
}
