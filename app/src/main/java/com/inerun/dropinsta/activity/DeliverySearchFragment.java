package com.inerun.dropinsta.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.inerun.dropinsta.R;
import com.inerun.dropinsta.adapter.DeliverySearchAdapter;
import com.inerun.dropinsta.base.BaseFragment;
import com.inerun.dropinsta.constant.UrlConstants;
import com.inerun.dropinsta.data.ParcelListingData;
import com.inerun.dropinsta.helper.SimpleDividerItemDecoration;
import com.inerun.dropinsta.sql.DIDbHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vinay on 16/01/17.
 */

public class DeliverySearchFragment extends BaseFragment implements View.OnClickListener {


    private static final int UPDATE_REQUEST = 301;
    private static final int DELIVERY_REQUEST = 302;
    private ArrayList<ParcelListingData.ParcelData> parcellist;
    private EditText search;

    private Button update, deliver;
    private RecyclerView recyclerView;
    private DeliverySearchAdapter mAdapter;

    public static Fragment newInstance() {
        DeliverySearchFragment fragment = new DeliverySearchFragment();
        return fragment;
    }

    @Override
    public int inflateView() {
        return R.layout.activity_delivery_customer_search;
    }

    @Override
    public void customOnCreateView(View root, LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setShowBackArrow(true);
        initView();

    }


    private void initView() {
        search = (EditText) getViewById(R.id.customer_search_edt);
        recyclerView = (RecyclerView) getViewById(R.id.customer_search_listview);
        update = (Button) getViewById(R.id.parcel_update);
        deliver = (Button) getViewById(R.id.parcel_delivered);

        update.setOnClickListener(this);
        deliver.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
        setdata();
    }

    private void getData() {
//        parcellist = DIDbHelper.getParcelListData(getActivity()).getDeliveryData();
//        parcellist = DIDbHelper.getPendingParcelsList(getActivity());
        parcellist = DIDbHelper.getPendingParcelInfoForListing(getActivity());

    }

    private void setdata() {
        mAdapter = new DeliverySearchAdapter(getActivity(), parcellist);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
//            recyclerView.addItemDecoration(new DividerItemDecoration(getDrawable(this, R.drawable.parcel_item_thumbnail_divider)));
//        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));

        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new DeliverySearchAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Log.i("onItemClick", "OnClick");
                Bundle bundle = new Bundle();
                bundle.putSerializable(UrlConstants.KEY_DATA, parcellist.get(position));

                gotoActivity(ParcelDetailActivity.class, bundle);
                //slide from right to left
                getActivity().overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
            }
        });

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                compareText(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }

    private void compareText(CharSequence charSequence) {
        ArrayList<ParcelListingData.ParcelData> filteredParcellist = new ArrayList<>();
        if(parcellist != null){
            for (ParcelListingData.ParcelData parcelData: parcellist){
                if(parcelData.getCustid().toLowerCase().contains(charSequence.toString().toLowerCase().trim())){
                    filteredParcellist.add(parcelData);
                }
            }
        }
        mAdapter.setParcelDataList(filteredParcellist);
        mAdapter.notifyDataSetChanged();
    }


    @Override
    protected String getAnalyticsName() {
        return null;
    }


    @Override
    public void onClick(View view) {
        ArrayList<ParcelListingData.ParcelData> selectedparcellist;
        switch (view.getId()) {
            case R.id.parcel_update:
                selectedparcellist = mAdapter.getSelectedParcels();
                if (validateParcels(selectedparcellist)) {
                    updateParcelStatusToDb(selectedparcellist);

                }
                break;
            case R.id.parcel_delivered:
                selectedparcellist = mAdapter.getSelectedParcels();
                if (validateParcels(selectedparcellist)) {
                    deliverParcels(selectedparcellist);
                }
                break;
        }

    }

    private boolean validateParcels(ArrayList<ParcelListingData.ParcelData> selectedparcellist) {
        if (selectedparcellist != null && selectedparcellist.size() > 0) {
            return true;
        } else {
            showSnackbar(R.string.selected_parcel_error);
        }
        return false;
    }


    private void updateParcelStatusToDb(ArrayList<ParcelListingData.ParcelData> selectedParcels) {

        Bundle bundle = new Bundle();
        bundle.putSerializable(UrlConstants.KEY_UPDATE_DATA, selectedParcels);
        gotoActivityForResult(DeliveryUpdateActivity.class, bundle, UPDATE_REQUEST);


    }

    private void deliverParcels(ArrayList<ParcelListingData.ParcelData> selectedParcels) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(UrlConstants.KEY_UPDATE_DATA, selectedParcels);
        gotoActivityForResult(DeliveryParcelDeliveryActivity.class, bundle,DELIVERY_REQUEST);
    }

}
