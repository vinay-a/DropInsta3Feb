package com.inerun.dropinsta.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.inerun.dropinsta.DropInsta;
import com.inerun.dropinsta.R;
import com.inerun.dropinsta.base.BaseActivity;
import com.inerun.dropinsta.constant.UrlConstants;
import com.inerun.dropinsta.data.POD;
import com.inerun.dropinsta.data.ParcelListingData;
import com.inerun.dropinsta.sql.DIDbHelper;

/**
 * Created by vineet on 12/5/2016.
 */

public class ParcelDetailActivity_copy extends BaseActivity implements View.OnClickListener {

    private static final int UPDATE_REQUEST = 101;
    private final int SIGN_REQUEST = 102;


    private Button update_btn, delivered_btn;
    private TextView barcode, date, address, payment, parcel_type, parcel_payment, delivery_status, delivery_comment, special_instruction;
    private LinearLayout btn_layout;
    private ParcelListingData.ParcelData parcelData;

    @Override
    public int customSetContentView() {
        return R.layout.activity_parcel_detail;
    }

    @Override
    public void customOnCreate(Bundle savedInstanceState) {

        Bundle bundle = getBundleFromIntent(this);
        if(bundle != null){
            parcelData = (ParcelListingData.ParcelData) bundle.getSerializable(UrlConstants.KEY_DATA);
        }
        initView();
        setData();

    }

    private void initView() {
        barcode = (TextView) findViewById(R.id.barcode);
        date = (TextView) findViewById(R.id.date);
        address = (TextView) findViewById(R.id.address);
        payment = (TextView) findViewById(R.id.payment);
        parcel_type = (TextView) findViewById(R.id.parcel_type);
        parcel_payment = (TextView) findViewById(R.id.parcel_payment);
        delivery_status = (TextView) findViewById(R.id.delivery_status);
        delivery_comment = (TextView) findViewById(R.id.delivery_comment);
        special_instruction = (TextView) findViewById(R.id.special_instruction);

        btn_layout = (LinearLayout) findViewById(R.id.btn_layout);
        update_btn = (Button) findViewById(R.id.parcel_update);
        delivered_btn = (Button) findViewById(R.id.parcel_delivered);

        update_btn.setOnClickListener(this);
        delivered_btn.setOnClickListener(this);
    }

    private void setData() {
        barcode.setText(parcelData.getBarcode());
        if(DropInsta.getUser().isWarehouseUser())
        {
            date.setText("");
        }else {
            date.setText(parcelData.getDate());
        }
        address.setText(parcelData.getDeliveryAddress());
        if(parcelData.getPayment_type() == 0) {
            payment.setText(parcelData.getAmount() + " "+ parcelData.getCurrency());
        }else{
            payment.setText(getString(R.string.payment_prepaid));
        }

        parcel_type.setText(parcelData.getParcel_type());
        parcel_payment.setText(parcelData.getAmount() + " " + parcelData.getCurrency());
        Log.i("delivery_status","" + parcelData.getDeliverystatus());
//        delivery_status.setText("" + parcelData.getDeliverystatus());
        delivery_comment.setText(parcelData.getDeliverycomments());
        special_instruction.setText(parcelData.getSpecialinstructions());

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
                startActivityForResult(new Intent(this, SignActivity.class), SIGN_REQUEST);
                //slide from right to left
                overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
                break;
            case R.id.parcel_update:
                startActivityForResult(new Intent(this, ParcelUpdateActivity.class), UPDATE_REQUEST);
                //slide from right to left
                overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
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

                    DIDbHelper.insertPODInfo(pod, parcelData.getColumn_id(), this);
//                    Toast.makeText(this, "Saved at"+ path, Toast.LENGTH_SHORT).show();
                    syncData();
                }
                break;

            case UPDATE_REQUEST:
                if (resultCode == RESULT_OK) {
                    String comment = data.getStringExtra(UrlConstants.KEY_COMMENT);
                    parcelData.setDeliverycomments(comment);
                    Log.i("Comment", comment);
                    DIDbHelper.updateParcelComment(this, parcelData.getColumn_id(), comment);
//                    Toast.makeText(this, "Saved Successfully", Toast.LENGTH_SHORT).show();
                    showSnackbar(R.string.saved_success);
                    setData();
                    syncData();
                }
                break;
        }


    }

}
