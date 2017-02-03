package com.inerun.dropinsta.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inerun.dropinsta.R;
import com.inerun.dropinsta.adapter.DeliveryAllParcelsAdapter;
import com.inerun.dropinsta.base.BaseFragment;
import com.inerun.dropinsta.base.DeviceInfoUtil;
import com.inerun.dropinsta.constant.AppConstant;
import com.inerun.dropinsta.constant.UrlConstants;
import com.inerun.dropinsta.data.ParcelListingData;
import com.inerun.dropinsta.helper.SimpleDividerItemDecoration;
import com.inerun.dropinsta.sql.DIDbHelper;

import java.util.ArrayList;

/**
 * Created by vinay on 16/01/17.
 */

public class DeliveryAllParcelsFragment extends BaseFragment {

    private RecyclerView recyclerView;
    protected ArrayList<ParcelListingData.ParcelData> parcellist;
    private DeliveryAllParcelsAdapter mAdapter;

    public static Fragment newInstance() {
        DeliveryAllParcelsFragment fragment = new DeliveryAllParcelsFragment();
        return fragment;
    }

    @Override
    public int inflateView() {
        return R.layout.fragment_delivery_all_parcels;
    }

    @Override
    public void customOnCreateView(View root, LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setShowBackArrow(true);
        initView();
        parcellist = getData();
        setdata();
    }


    private void initView() {
//        search = (EditText) getViewById(R.id.customer_search_edt);
        recyclerView = (RecyclerView) getViewById(R.id.recyclerView);
//        update = (Button) getViewById(R.id.parcel_update);
//        deliver = (Button) getViewById(R.id.parcel_delivered);
//
//        update.setOnClickListener(this);
//        deliver.setOnClickListener(this);
    }

    protected ArrayList<ParcelListingData.ParcelData> getData() {
//        parcellist = DIDbHelper.getParcelListData(getActivity()).getDeliveryData();
        return DIDbHelper.getDeliveryInfoForListing(getActivity());

    }

    private void setdata() {
        mAdapter = new DeliveryAllParcelsAdapter(getActivity(), parcellist);

        mAdapter.setOnItemClickListener(onClickAdapterListener);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
//            recyclerView.addItemDecoration(new DividerItemDecoration(getDrawable(this, R.drawable.parcel_item_thumbnail_divider)));
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));

        recyclerView.setAdapter(mAdapter);


    }

    DeliveryAllParcelsAdapter.OnItemClickListener onClickAdapterListener = new DeliveryAllParcelsAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View v, int position) {
            switch (v.getId()) {
                case R.id.call_action:


                    if (DeviceInfoUtil.hasPermissions(getActivity(), AppConstant.requiredPermissions())) {
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + mAdapter.getParcelDataList().get(position).getDelivery_phone()));
                        startActivity(intent);
                    } else {
                        showLongToast(R.string.permissions_missing);
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                Uri.fromParts("package", getActivity().getPackageName(), null));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }

                    break;
                default:
                    Log.i("onItemClick", "OnClick: "+position);
//                Intent intent = new Intent(getActivity(), ProductListingActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(UrlConstants.KEY_DATA, parcellist.get(position));

                    gotoActivity(ParcelDetailActivity.class, bundle);
                    //slide from right to left
                    getActivity().overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);

                    break;
            }

        }
    };

    @Override
    protected String getAnalyticsName() {
        return null;
    }
}
