package com.inerun.dropinsta.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.inerun.dropinsta.DropInsta;
import com.inerun.dropinsta.R;
import com.inerun.dropinsta.base.BaseFragment;
import com.inerun.dropinsta.base.DeviceInfoUtil;
import com.inerun.dropinsta.constant.AppConstant;
import com.inerun.dropinsta.constant.UrlConstants;
import com.inerun.dropinsta.data.POD;
import com.inerun.dropinsta.data.ParcelListingData;
import com.inerun.dropinsta.sql.DIDbHelper;

import static android.app.Activity.RESULT_OK;

/**
 * Created by vineet on 12/10/2016.
 */

public class ParcelDetailFragment extends BaseFragment implements View.OnClickListener {

    private static final int UPDATE_REQUEST = 101;
    private final int SIGN_REQUEST = 102;


    private Button update_btn, delivered_btn;
    private TextView barcode,  address, payment, parcel_type,  delivery_status, delivery_comment, special_instruction;
    private LinearLayout btn_layout, delivery_comment_lay ;
    private ParcelListingData.ParcelData parcelData;
    private TextView name,custid,phone,sourcecity;
    private ImageButton callphone;

    public static Fragment newInstance(ParcelListingData.ParcelData parcelData) {
        ParcelDetailFragment fragment = new ParcelDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(UrlConstants.KEY_DATA, parcelData);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parcelData = (ParcelListingData.ParcelData) getArguments().getSerializable(UrlConstants.KEY_DATA);
    }

    @Override
    public int inflateView() {
        return R.layout.parcel_detail_fragment;
    }

    @Override
    public void customOnCreateView(View root, LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) throws Exception {
        setShowBackArrow(true);
        initView(root);
        setData();
    }

    @Override
    protected String getAnalyticsName() {
        return null;
    }



    private void initView(View root) {
        barcode = (TextView) root.findViewById(R.id.barcode);
//        date = (TextView) root.findViewById(R.id.date);
        address = (TextView) root.findViewById(R.id.address);
        payment = (TextView) root.findViewById(R.id.payment);
        sourcecity = (TextView) root.findViewById(R.id.source_add);
        name = (TextView) root.findViewById(R.id.parcel_name);
        custid = (TextView) root.findViewById(R.id.customer_id);
        callphone = (ImageButton) root.findViewById(R.id.call_action);
        phone = (TextView) root.findViewById(R.id.phone_txt);
        parcel_type = (TextView) root.findViewById(R.id.parcel_type);
        delivery_status = (TextView) root.findViewById(R.id.delivery_status);
        delivery_comment = (TextView) root.findViewById(R.id.delivery_comment);
        special_instruction = (TextView) root.findViewById(R.id.special_instruction);
        delivery_comment_lay = (LinearLayout) root.findViewById(R.id.delivery_comment_lay);

        btn_layout = (LinearLayout) root.findViewById(R.id.btn_layout);
        update_btn = (Button) root.findViewById(R.id.parcel_update);
        delivered_btn = (Button) root.findViewById(R.id.parcel_delivered);

        update_btn.setOnClickListener(this);
        delivered_btn.setOnClickListener(this);
        callphone.setOnClickListener(this);
    }

    private void setData() {
        barcode.setText(parcelData.getBarcode());
        name.setText(getString(R.string.parcel_customer_name,parcelData.getName()));
        custid.setText(getString(R.string.parcel_customer_id,parcelData.getCustid()));
        phone.setText(parcelData.getDelivery_phone());
        sourcecity.setText(parcelData.getSource_city());
        if(DropInsta.getUser().isWarehouseUser())
        {
//            date.setText("");
        }else {
//            date.setText(parcelData.getDate());
        }
        address.setText(getString(R.string.parcel_customer_address,parcelData.getDeliveryAddress()));
        if(parcelData.getPayment_type() == 0) {
            payment.setText(parcelData.getAmount() + " "+ parcelData.getCurrency());
        }else{
            payment.setText(getString(R.string.payment_prepaid));
        }

        parcel_type.setText(parcelData.getParcel_type());
        Log.i("delivery_status","" + parcelData.getDeliverystatus());
//        delivery_status.setText("" + parcelData.getDeliverystatus());
        if(parcelData.getDeliverycomments() != null && parcelData.getDeliverycomments().trim().length() > 0) {
            delivery_comment.setText(parcelData.getDeliverycomments());
            delivery_comment_lay.setVisibility(View.VISIBLE);
        }else{
            delivery_comment_lay.setVisibility(View.GONE);
        }
        if(parcelData.getSpecialinstructions() != null && parcelData.getSpecialinstructions().trim().length() > 0) {
            special_instruction.setText(parcelData.getSpecialinstructions());
        }else{
            special_instruction.setText(getString(R.string.payment_prepaid));
        }

        if(parcelData.isDelivered()){
            btn_layout.setVisibility(View.GONE);
            delivery_status.setText("DELIVERED");
        }else{
            btn_layout.setVisibility(View.GONE);
            delivery_status.setText("PENDING");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.parcel_delivered:
                startActivityForResult(new Intent(getActivity(), SignActivity.class), SIGN_REQUEST);
                //slide from right to left
                getActivity().overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
                break;
            case R.id.parcel_update:
                startActivityForResult(new Intent(getActivity(), ParcelUpdateActivity.class), UPDATE_REQUEST);
                //slide from right to left
                getActivity().overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
                break;


            case R.id.call_action:


                if (DeviceInfoUtil.hasPermissions(getActivity(), AppConstant.requiredPermissions())) {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + parcelData.getDelivery_phone()));
                    startActivity(intent);
                } else {
                    showLongToast(R.string.permissions_missing);
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                            Uri.fromParts("package", getActivity().getPackageName(), null));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }

                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {

            case SIGN_REQUEST:
                if (resultCode == RESULT_OK && data.hasExtra(SignActivity.INTENT_FILENAME)) {
                    String path = data.getStringExtra(SignActivity.INTENT_FILENAME);
                    String receiver_name = data.getStringExtra(UrlConstants.KEY_RECEIVER_NAME);
//                    setImage(path);
                    Log.i("POD_path", path);
                    Log.i("Receiver_Name", receiver_name);
                    String pod_name = path.substring(path.lastIndexOf("/") + 1);
                    Log.i("POD_Name", pod_name);
                    POD pod = new POD(pod_name, receiver_name);

                    DIDbHelper.insertPODInfo(pod, parcelData.getColumn_id(), getActivity());
//                    Toast.makeText(this, "Saved at"+ path, Toast.LENGTH_SHORT).show();
                    syncData();
                }
                break;

            case UPDATE_REQUEST:
                if (resultCode == RESULT_OK) {
                    String comment = data.getStringExtra(UrlConstants.KEY_COMMENT);
                    parcelData.setDeliverycomments(comment);
                    Log.i("Comment", comment);
                    DIDbHelper.updateParcelComment(getActivity(), parcelData.getColumn_id(), comment);
//                    Toast.makeText(this, "Saved Successfully", Toast.LENGTH_SHORT).show();
                    showSnackbar(R.string.saved_success);
                    setData();
                    syncData();
                }
                break;
        }


    }

}
