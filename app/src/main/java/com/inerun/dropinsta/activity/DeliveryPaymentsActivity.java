package com.inerun.dropinsta.activity;

import android.support.v4.app.Fragment;

import com.inerun.dropinsta.R;

/**
 * Created by vinay on 16/01/17.
 */

public class DeliveryPaymentsActivity extends FragmentBaseActivity {
    @Override
    public Fragment getFragment() {
        return DeliveryPaymentsFragment.newInstance();
    }

    @Override
    public int toolBarTitle() {
        return R.string.payment_collection;
    }
}
