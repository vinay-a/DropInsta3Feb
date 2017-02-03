package com.inerun.dropinsta.activity_warehouse;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.inerun.dropinsta.DropInsta;
import com.inerun.dropinsta.R;
import com.inerun.dropinsta.adapter.WhAddParcelAdapter;
import com.inerun.dropinsta.base.BaseFragment;
import com.inerun.dropinsta.constant.UrlConstants;
import com.inerun.dropinsta.data.ServiceResponse;
import com.inerun.dropinsta.helper.ExpandableHeightListView;
import com.inerun.dropinsta.scanner.CameraTestActivity;
import com.inerun.dropinsta.service.DIRequestCreator;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by vinay on 16/12/16.
 */

public class WhAddParcelFragment extends BaseFragment implements View.OnClickListener {

    private static final int RACK_SCAN = 101;
    private static final int PARCEL_SCAN = 102;
    private static final String ADDPARCEL = "ADD_PARCEL";
    private Context context;
    private Button saveallbutton;
    private LinearLayout addRack;
    private TextView rackNum, username, location;
    private ListView parcellistview;
    private LinearLayout addparcel;
    private WhAddParcelAdapter adapter;

    public static Fragment newInstance() {
        WhAddParcelFragment fragment = new WhAddParcelFragment();
        return fragment;
    }


    @Override
    public int inflateView() {
        return R.layout.wh_parcel_add;
    }

    @Override
    public void customOnCreateView(View root, LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setShowBackArrow(true);
        context = getActivity();
        initView();
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.mipmap.back_arrow3);

        setListeners();
        setData();


    }


    private void initView() {
        saveallbutton = (Button) getViewById(R.id.wh_save_all);
        addRack = (LinearLayout) getViewById(R.id.rack_linear_layout);
        rackNum = (TextView) getViewById(R.id.wh_home_rack_num);
        username = (TextView) getViewById(R.id.wh_home_name);
        location = (TextView) getViewById(R.id.wh_home_location);
        parcellistview = (ExpandableHeightListView) getViewById(R.id.parcel_listview);
        addparcel = (LinearLayout) getViewById(R.id.addnewparcel_layout);

        username.setText(DropInsta.getUser().getName());
        location.setText(DropInsta.getUser().getLocation());
    }

    private void setListeners() {
        saveallbutton.setOnClickListener(this);
        addRack.setOnClickListener(this);
        addparcel.setOnClickListener(this);

    }

    private void setData() {
        adapter = new WhAddParcelAdapter(getActivity());

        parcellistview.setAdapter(adapter);

    }

    @Override
    protected String getAnalyticsName() {
        return null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rack_linear_layout:
                startActivityForResult(new Intent(getActivity(), CameraTestActivity.class), RACK_SCAN);
                break;
            case R.id.addnewparcel_layout:

                startActivityForResult(new Intent(getActivity(), CameraTestActivity.class), PARCEL_SCAN);

                break;
            case R.id.wh_save_all:
                String rack = "" + rackNum.getText();
                saveRackAndParcelData(rack, adapter.getParcellist());
                break;
        }

    }

    private void saveRackAndParcelData(String rack, List<String> parcellist) {

        if (rack.length() == 0) {
            showSnackbar(R.string.add_rack_error);
        } else if (parcellist.size() == 0) {
            showSnackbar(R.string.add_parcel_error);
        } else {
//            showSnackbar(R.string.saved_success);

            try {


                Map<String, String> params = DIRequestCreator.getInstance(getActivity()).getAddParcelMapParams(rack, parcellist);

                DropInsta.serviceManager().postRequest(UrlConstants.URL_ADD, params, response_listener, response_errorlistener, ADDPARCEL);
            } catch (JSONException e) {
                e.printStackTrace();
                showSnackbar(R.string.exception_alert_message_parsing_exception);
            }
        }
    }

    Response.Listener<String> response_listener= new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            Gson gson= new Gson();
            ServiceResponse serviceResponse = gson.fromJson(response, ServiceResponse.class);
            if(serviceResponse.getStatus())
            {
               clearData();
            }else
            {
                List<String> failed_parcel= null;
                try {
                    failed_parcel = getFailedParcels(serviceResponse
                    .getParcels());
                    if(failed_parcel!=null&&failed_parcel.size()>0) {
                        adapter.setParcellist(failed_parcel);
                    }else{
                        clearData();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    showSnackbar(e.getMessage());
                }

            }
            showSnackbar(serviceResponse.getMessage());

        }


    };

    private List<String> getFailedParcels(ArrayList<ServiceResponse.AddData> response) throws JSONException {
        List<String> failed_parcel= new ArrayList<>();
if(response!=null) {
    for (int i = 0; i < response.size(); i++) {
        failed_parcel.add(response.get(i).getParcelno());
    }
}
        return failed_parcel;
    }

    private void clearData() {
        rackNum.setText("");
        adapter.getParcellist().clear();
        adapter.notifyDataSetChanged();
    }

    Response.ErrorListener response_errorlistener=new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            showSnackbar(error.getMessage());
        }
    };
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case RACK_SCAN:

                if (resultCode == Activity.RESULT_OK && data.hasExtra(CameraTestActivity.INTENT_BARCODE_VALUE)) {
                    rackNum.setText(data.getStringExtra(CameraTestActivity.INTENT_BARCODE_VALUE));
                }
                break;
            case PARCEL_SCAN:

                if (resultCode == Activity.RESULT_OK && data.hasExtra(CameraTestActivity.INTENT_BARCODE_VALUE)) {
                    adapter.addParcelToList(data.getStringExtra(CameraTestActivity.INTENT_BARCODE_VALUE));
                }
                break;
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        DropInsta.serviceManager().cancelAllRequest(ADDPARCEL);
    }

}
