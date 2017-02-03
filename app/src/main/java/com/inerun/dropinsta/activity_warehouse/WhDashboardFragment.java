package com.inerun.dropinsta.activity_warehouse;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inerun.dropinsta.R;
import com.inerun.dropinsta.base.BaseFragment;

/**
 * Created by vinay on 15/12/16.
 */

public class WhDashboardFragment extends BaseFragment implements View.OnClickListener {

    Context context;
    public static Fragment newInstance() {
        WhDashboardFragment fragment = new WhDashboardFragment();
        return fragment;
    }


    @Override
    public int inflateView() {
        return R.layout.wh_parcel_home;
    }

    @Override
    public void customOnCreateView(View root, LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context=getActivity();

//        getViewById(R.id.addparcel_layout).setOnClickListener(this);
        getViewById(R.id.addparcel_image_opacity).setOnClickListener(this);
//        getViewById(R.id.addparcel_image).setOnClickListener(this);
//        getViewById(R.id.searchparcel_layout).setOnClickListener(this);
        getViewById(R.id.searchparcel_image_opacity).setOnClickListener(this);
//        getViewById(R.id.searchparcel_image).setOnClickListener(this);



    }

    @Override
    protected String getAnalyticsName() {
        return null;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
//            case R.id.addparcel_layout:
//            navigateToFragment(context,WhAddParcelFragment.newInstance());
//
//                break;
//            case R.id.searchparcel_layout:
//                navigateToFragment(context,WhSearchParcelFragment.newInstance());
//
//                break;
            case R.id.addparcel_image_opacity:
                navigateToFragment(context,WhAddParcelFragment.newInstance());

                break;
            case R.id.searchparcel_image_opacity:
                navigateToFragment(context,WhSearchParcelFragment.newInstance());

                break;
//            case R.id.addparcel_image:
//                navigateToFragment(context,WhAddParcelFragment.newInstance());
//
//                break;
//            case R.id.searchparcel_image:
//                navigateToFragment(context,WhSearchParcelFragment.newInstance());
//
//                break;
        }

    }
}
