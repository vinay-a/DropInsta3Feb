package com.inerun.dropinsta.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.inerun.dropinsta.R;
import com.inerun.dropinsta.base.BaseActivity;

/**
 * Created by vinay on 13/01/17.
 */

abstract public class FragmentBaseActivity extends BaseActivity {
    private Toolbar toolbar;

    @Override
    public int customSetContentView() {
        return R.layout.activity_cordinator_container;
    }

    @Override
    public void customOnCreate(Bundle savedInstanceState) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container,
                getFragment()).commit();
        setToolbarTitle();
//        setToolbarBackArrow();



    }


//    protected void setToolbarBackArrow() {
//        if (getSupportActionBar() != null){
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//            getSupportActionBar().setDisplayShowHomeEnabled(true);
//        }
//        toolbar.setNavigationIcon(getDrawable(this,R.mipmap.back_arrow_white));
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               onBackArrowPressed();
//            }
//        });
//
//
//
//    }

    private void onBackArrowPressed() {
        handleFragmentBackPressed();
    }

    private void setToolbarTitle() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (toolbar != null) {
            toolbar.setTitle(toolBarTitle());
        }
    }

    public int toolBarTitle() {
        return R.string.app_name;
    }


//    @Override
//    public void onBackPressed() {
////        handleFragmentBackPressed();
//
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    abstract public Fragment getFragment();



}
