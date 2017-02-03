package com.inerun.dropinsta.activity_warehouse;

import android.os.Bundle;

import com.inerun.dropinsta.R;
import com.inerun.dropinsta.base.BaseActivity;

/**
 * Created by vinay on 15/12/16.
 */

public class WhDashboardActivity extends BaseActivity {
    private boolean doubleBackToExitPressedOnce=false;

    @Override
    public int customSetContentView() {

//        setWarehouse(true);
        return R.layout.activity_cordinator_container;
    }

    @Override
    public void customOnCreate(Bundle savedInstanceState) {







//        getSupportActionBar().setIcon(R.mipmap.toolbaricon);


        getSupportFragmentManager().beginTransaction().replace(R.id.container,
                WhDashboardFragment.newInstance()).commit();

    }


    @Override
    public void onBackPressed() {
            handleFragmentBackPressed();

    }




}
