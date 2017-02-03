package com.inerun.dropinsta.activity;

import android.support.v4.app.Fragment;

import com.inerun.dropinsta.R;

/**
 * Created by vinay on 16/01/17.
 */

public class DeliverySearchActivity extends FragmentBaseActivity {
    @Override
    public Fragment getFragment() {
        return DeliverySearchFragment.newInstance();
    }


    @Override
    public int toolBarTitle() {
        return R.string.title_customer_search;
    }



}
