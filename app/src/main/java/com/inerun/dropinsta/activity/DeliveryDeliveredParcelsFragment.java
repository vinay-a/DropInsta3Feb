package com.inerun.dropinsta.activity;

import android.os.Bundle;

import com.inerun.dropinsta.data.ParcelListingData;
import com.inerun.dropinsta.sql.DIDbHelper;

import java.util.ArrayList;

/**
 * Created by vinay on 16/01/17.
 */

public class DeliveryDeliveredParcelsFragment extends DeliveryAllParcelsFragment {


    public static DeliveryDeliveredParcelsFragment newInstance() {
        
        Bundle args = new Bundle();
        
        DeliveryDeliveredParcelsFragment fragment = new DeliveryDeliveredParcelsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected ArrayList<ParcelListingData.ParcelData> getData() {
//        super.getData();
        return  DIDbHelper.getDeliveredParcelInfoForListing(getActivity());

    }
}
