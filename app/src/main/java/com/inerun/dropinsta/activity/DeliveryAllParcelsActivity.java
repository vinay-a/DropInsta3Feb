package com.inerun.dropinsta.activity;

import android.support.v4.app.Fragment;

import com.inerun.dropinsta.R;

/**
 * Created by vinay on 16/01/17.
 */

public class DeliveryAllParcelsActivity extends FragmentBaseActivity {
    @Override
    public Fragment getFragment() {
        return DeliveryAllParcelsFragment.newInstance();
    }


    @Override
    public int toolBarTitle() {
        return R.string.title_allparcels;
    }
}
