package com.inerun.dropinsta.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.inerun.dropinsta.R;
import com.inerun.dropinsta.base.BaseActivity;
import com.inerun.dropinsta.base.BaseFragment;
import com.inerun.dropinsta.constant.UrlConstants;
import com.inerun.dropinsta.data.POD;
import com.inerun.dropinsta.data.ParcelListingData;
import com.inerun.dropinsta.data.ParcelStatus;
import com.inerun.dropinsta.data.TransactionData;
import com.inerun.dropinsta.sql.DIDbHelper;

import java.util.ArrayList;

/**
 * Created by vinay on 16/01/17.
 */

public class DeliveryParcelDeliveryFragment extends BaseFragment implements View.OnClickListener {

    private ArrayList<ParcelListingData.ParcelData> arrayList;
    private TextView payment;
    private Button cardpayment, cashpayment;
    private float amount;
    private EditText transcid;
    private EditText collectedby;
    boolean iscard;
    private TransactionData transcdata;
    private final int SIGN_REQUEST = 102;
    private ProgressBar progress;


    public static Fragment newInstance(ArrayList<ParcelListingData.ParcelData> arrayList) {
        DeliveryParcelDeliveryFragment fragment = new DeliveryParcelDeliveryFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(UrlConstants.KEY_UPDATE_DATA, arrayList);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrayList = (ArrayList<ParcelListingData.ParcelData>) getArguments().getSerializable(UrlConstants.KEY_UPDATE_DATA);
    }

    @Override
    public int inflateView() {
        return R.layout.activity_delivery_payment_layout;
    }

    @Override
    public void customOnCreateView(View root, LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initview(root);
        setData();

    }

    private void setData() {
        amount = getTotalFromParcels();
        payment.setText("" + amount);
    }

    private float getTotalFromParcels() {
        for (ParcelListingData.ParcelData parcelData : arrayList) {
            amount += Float.parseFloat(parcelData.getAmount());
        }
        return amount;
    }

    private void initview(View root) {
        payment = (TextView) root.findViewById(R.id.payment_receieved_amount);
        cashpayment = (Button) root.findViewById(R.id.payment_cash);
        cardpayment = (Button) root.findViewById(R.id.payment_card);
        progress=getProgress();
        transcid = (EditText) root.findViewById(R.id.payment_transcid);
        collectedby = (EditText) root.findViewById(R.id.payment_collectedby);
        cashpayment.setOnClickListener(this);
        cardpayment.setOnClickListener(this);
        root.findViewById(R.id.parcel_cancel).setOnClickListener(this);
        root.findViewById(R.id.parcel_deliver).setOnClickListener(this);

    }

    @Override
    protected String getAnalyticsName() {
        return null;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.payment_card:
                iscard=true;
                setCardPayment(iscard);

                break;
            case R.id.payment_cash:
                iscard=false;
                setCardPayment(iscard);


                break;
            case R.id.parcel_cancel:

//                getActivity().setResult(Activity.RESULT_CANCELED);
                getActivity().finish();
                break;
            case R.id.parcel_deliver:
                String transid=""+transcid.getText();
                String collectdby=""+collectedby.getText();
                if(validatePayment(iscard,transid,collectdby)) {
                     transcdata = new TransactionData(iscard, "" + amount, transid, collectdby);
//                    got
                    startActivityForResult(new Intent(getActivity(), SignActivity.class), SIGN_REQUEST);

                }

                break;
        }
    }

    private boolean validatePayment(boolean iscard, String transid, String collectdby) {
        if(!isStringValid(collectdby))
        {
            showSnackbar(R.string.error_receiver_name);
            return false;
        }
        if(iscard&&!isStringValid(transid))
        {
            showSnackbar(R.string.error_transid);
            return false;
        }
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


            switch (requestCode) {

                case SIGN_REQUEST:
                    if (resultCode == Activity.RESULT_OK && data.hasExtra(SignActivity.INTENT_FILENAME)) {
                        String path = data.getStringExtra(SignActivity.INTENT_FILENAME);

//                    setImage(path);
                        Log.i("POD_path", path);
//                        Log.i("Receiver_Name", receiver_name);
                        String pod_name = path.substring(path.lastIndexOf("/") + 1);
                        Log.i("POD_Name", pod_name);

                        POD pod = new POD(pod_name, transcdata.getCollectedby());
                        deliverParcel(transcdata.iscard(),arrayList,transcdata.getTotalamount(),transcdata.getTranscid(),transcdata.getCollectedby(),pod);
                        //getActivity().finish();
//                    Toast.makeText(this, "Saved at"+ path, Toast.LENGTH_SHORT).show();
//                        syncData();
                    }
                    break;



            }



    }

    private void deliverParcel(boolean iscard, ArrayList<ParcelListingData.ParcelData> parcelDatas, String totalamount, String transcid, String collectedby, POD pod) {

        DIDbHelper.deliverParcelandUpdateTransaction(getActivity(),parcelDatas,new ParcelStatus(""+ParcelListingData.ParcelData.DELIVERED,"DELIVERED"),iscard,transcid,collectedby,totalamount,parcelDatas.get(0).getCurrency(),pod);
        ((BaseActivity)getActivity()).syncData();


    }

    private void setCardPayment(boolean iscard) {
        if (iscard) {
            cardpayment.setBackgroundColor(getColor(R.color.buttonColor));
            cardpayment.setTextColor(getColor(R.color.white));
            cashpayment.setBackgroundColor(getColor(android.R.color.transparent));
            cashpayment.setTextColor(getColor(android.R.color.black));
            transcid.setVisibility(View.VISIBLE);

        } else {
            cashpayment.setBackgroundColor(getColor(R.color.buttonColor));
            cardpayment.setTextColor(getColor(android.R.color.black));
            cardpayment.setBackgroundColor(getColor(android.R.color.transparent));
            cashpayment.setTextColor(getColor(android.R.color.white));
            transcid.setVisibility(View.GONE);

        }



    }
}
