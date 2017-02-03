package com.inerun.dropinsta.adapter;

/**
 * Created by vinay on 18/01/17.
 */


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.inerun.dropinsta.R;
import com.inerun.dropinsta.base.BaseActivity;
import com.inerun.dropinsta.data.ParcelListingData;

import java.util.ArrayList;

/**
 * Created by vineet on 11/29/2016.
 */


public class DeliverySearchAdapter extends BaseRecyclerViewAdapter implements CompoundButton.OnCheckedChangeListener {
    private Context context;
    private ArrayList<ParcelListingData.ParcelData> parcelDataList;
    private static OnItemClickListener mItemClickListener;
    private boolean onBind;


    public ArrayList<ParcelListingData.ParcelData> getParcelDataList() {
        return parcelDataList;
    }

    public void setParcelDataList(ArrayList<ParcelListingData.ParcelData> parcelDataList) {
        this.parcelDataList = parcelDataList;
    }

    public DeliverySearchAdapter(Context context, ArrayList<ParcelListingData.ParcelData> parcelDataList) {
        this.parcelDataList = parcelDataList;
        this.context = context;
    }


//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View itemView;
////        if(viewType == ParcelListingData.ParcelData.PENDING) {
//        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.delivery_customer_search_item, parent, false);
//
////        }else {
////            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.parcel_list_item_delivered, parent, false);
////        }
//        ViewHolder viewHolder = new ViewHolder(itemView);
//        return viewHolder;
//    }

    @Override
    protected BaseRecyclerViewAdapter.ViewHolder getViewHolder(View itemView) {

        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    protected View oncreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;

        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.delivery_customer_search_item, parent, false);
        return itemView;
    }

    @Override
    public void onbindViewHolder(BaseRecyclerViewAdapter.ViewHolder viewholder, int position) {
        ViewHolder holder= (ViewHolder) viewholder.holder;
        ParcelListingData.ParcelData data = parcelDataList.get(position);

        holder.customerName.setText(context.getString(R.string.parcel_customer_name,data.getName()));
        holder.custId.setText(context.getString(R.string.parcel_customer_id,data.getCustid()));
        holder.barcode.setText(data.getBarcode());
        holder.date.setText(data.getDate());
        holder.address.setText(data.getDeliveryAddress());
        holder.checkBox.setTag(position);
        if (data.getPayment_type() == 0) {
            holder.payment.setText(data.getAmount() + " " + data.getCurrency());
        } else {
            holder.payment.setText(context.getString(R.string.payment_prepaid));
        }
//        holder.comment.setText(data.getSpecialinstructions());
        if(data.getSpecialinstructions() != null && data.getSpecialinstructions().trim().length() > 0) {
            holder.comment.setText(context.getString(R.string.special_instructions_title, data.getSpecialinstructions()));
        }else{
            holder.comment.setText(context.getString(R.string.special_instructions_title, context.getString(R.string.payment_prepaid)));
        }
        onBind = true;
        holder.checkBox.setChecked(data.isselected());
        onBind = false;

        holder.item_rootLayout.setTag(position);
//        holder.callbtn.setTag(position);
    }




//    @Override
//    public int getItemViewType(int position) {
//
//        if (parcelDataList.get(position).isDelivered()) {
//            return ParcelListingData.ParcelData.DELIVERED;
//        } else{
//            return ParcelListingData.ParcelData.PENDING;
//        }
//
//    }



    @Override
    public ArrayList initObjectList() {
        return  parcelDataList;
    }

    public interface OnItemClickListener {
        public void onItemClick(View v, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        DeliverySearchAdapter.mItemClickListener = mItemClickListener;
    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
        if (!onBind) {
            int pos = (int) compoundButton.getTag();
            if (validateCheck(parcelDataList.get(pos))) {
                parcelDataList.get(pos).setIsselected(checked);
            }


            // your process when checkBox changed
            // ...

            notifyDataSetChanged();
        }


    }

    private boolean validateCheck(ParcelListingData.ParcelData checkparcel) {

        boolean flag = true;

        for (int i = 0; i < parcelDataList.size(); i++) {
            ParcelListingData.ParcelData parcel = parcelDataList.get(i);

            if (parcel.isselected() && (!parcel.getCustid().equalsIgnoreCase(checkparcel.getCustid()))) {

                flag = false;
                ((BaseActivity)context).showSnackbar(R.string.different_parcels_error);
            }
        }

        return flag;
    }
    public ArrayList<ParcelListingData.ParcelData> getSelectedParcels() {

        ArrayList<ParcelListingData.ParcelData> selectedParcelList= new ArrayList<>();

        for (int i = 0; i < parcelDataList.size(); i++) {
            ParcelListingData.ParcelData parcel = parcelDataList.get(i);
            if (parcel.isselected() ) {
                selectedParcelList.add(parcel);


            }
        }

        return selectedParcelList;
    }
    public void selectAllParcels(boolean checked) {

        for (int i = 0; i < parcelDataList.size(); i++) {
            if (!parcelDataList.get(i).isDelivered()) {
                parcelDataList.get(i).setIsselected(checked);
            }
        }
        notifyDataSetChanged();


    }
    public class ViewHolder extends BaseRecyclerViewAdapter.ViewHolder implements View.OnClickListener {
        public TextView barcode, date, phone, address, payment, comment, customerName, custId;
        public ImageButton callbtn;
        public LinearLayout item_rootLayout;
        public CheckBox checkBox;

        public ViewHolder(View view) {
            super(view);


//            name = (TextView) view.findViewById(R.id.parcel_name);
            customerName = (TextView) view.findViewById(R.id.customer_name);
            custId = (TextView) view.findViewById(R.id.customer_id);
            date = (TextView) view.findViewById(R.id.date);

            address = (TextView) view.findViewById(R.id.address);
            barcode = (TextView) view.findViewById(R.id.barcode);
            payment = (TextView) view.findViewById(R.id.payment);
            phone = (TextView) view.findViewById(R.id.phone_txt);
            comment = (TextView) view.findViewById(R.id.comment);
            callbtn = (ImageButton) view.findViewById(R.id.call_action);
            item_rootLayout = (LinearLayout) view.findViewById(R.id.item_rootLayout);
            checkBox = (CheckBox) view.findViewById(R.id.checkbox);

            checkBox.setOnCheckedChangeListener(DeliverySearchAdapter.this);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            // TODO Auto-generated method stub
            int pos= (int) view.getTag();
            Log.i("pos",""+pos);
            if(pos>=0) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(view, pos);
                }
            }
        }
    }

}
