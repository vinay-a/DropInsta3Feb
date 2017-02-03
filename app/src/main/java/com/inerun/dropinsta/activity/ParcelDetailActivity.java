package com.inerun.dropinsta.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;

import com.inerun.dropinsta.R;
import com.inerun.dropinsta.constant.UrlConstants;
import com.inerun.dropinsta.data.ParcelListingData;

/**
 * Created by vineet on 12/5/2016.
 */

public class ParcelDetailActivity extends FragmentBaseActivity{

    private ParcelListingData.ParcelData parcelData;

    @Override
    public Fragment getFragment() {
        Bundle bundle = getBundleFromIntent(ParcelDetailActivity.this);
        if(bundle != null){
            parcelData = (ParcelListingData.ParcelData) bundle.getSerializable(UrlConstants.KEY_DATA);
        }
        return ParcelDetailFragment.newInstance(parcelData);
    }


    @Override
    public int toolBarTitle() {
        return R.string.title_parcel_detail;
    }
}
