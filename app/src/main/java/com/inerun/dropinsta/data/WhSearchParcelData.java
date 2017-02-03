package com.inerun.dropinsta.data;

import java.util.ArrayList;

/**
 * Created by vinay on 19/12/16.
 */

public class WhSearchParcelData {

    private boolean status;
    private String message;
    private ArrayList<ParcelListingData.ParcelData> deliverydata;

    public boolean isStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }


    public ArrayList<ParcelListingData.ParcelData> getDeliverydata() {
        return deliverydata;
    }
}
