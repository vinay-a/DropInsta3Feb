package com.inerun.dropinsta.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.inerun.dropinsta.R;
import com.inerun.dropinsta.data.ParcelListingData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vineet on 11/29/2016.
 */


public class DeliveryAllParcelsAdapter extends BaseRecyclerViewAdapter {
    private Context context;
    private ArrayList<ParcelListingData.ParcelData> parcelDataList;
    private static OnItemClickListener mItemClickListener;

    public class ViewHolder extends BaseRecyclerViewAdapter.ViewHolder implements View.OnClickListener {
        public TextView name,id,barcode, date,phone, address, payment, comment;
        public ImageButton callbtn;
        public LinearLayout item_rootLayout;
        int pos;

        public ViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.parcel_name);
            id = (TextView) view.findViewById(R.id.customer_id);
            address = (TextView) view.findViewById(R.id.address);
            barcode = (TextView) view.findViewById(R.id.barcode);
            payment = (TextView) view.findViewById(R.id.payment);
            phone = (TextView) view.findViewById(R.id.phone_txt);
            callbtn = (ImageButton) view.findViewById(R.id.call_action);
            item_rootLayout = (LinearLayout) view.findViewById(R.id.item_rootLayout);
            item_rootLayout.setOnClickListener(this);
            callbtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            // TODO Auto-generated method stub
            if (mItemClickListener != null) {
                int pos= (int) view.getTag();
                Log.i("pos",""+pos);
                if(pos>=0) {
                    mItemClickListener.onItemClick(view, pos);
                }
            }
        }
    }


    public DeliveryAllParcelsAdapter(Context context, ArrayList<ParcelListingData.ParcelData> parcelDataList) {
        this.parcelDataList = parcelDataList;
        this.context = context;
    }

    public List<ParcelListingData.ParcelData> getParcelDataList() {
        return parcelDataList;
    }



    @Override
    protected BaseRecyclerViewAdapter.ViewHolder getViewHolder(View itemView) {
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    protected View oncreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        if(viewType == ParcelListingData.ParcelData.PENDING) {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.parcel_list_item_pending, parent, false);

        }else {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.parcel_list_item_delivered, parent, false);
        }
        return itemView;
    }

    @Override
    public void onbindViewHolder(BaseRecyclerViewAdapter.ViewHolder viewholder, int position) {

        ViewHolder holder= (ViewHolder) viewholder.holder;
        ParcelListingData.ParcelData data = parcelDataList.get(position);

        holder.barcode.setText(data.getBarcode());
        holder.name.setText(context.getString(R.string.parcel_customer_name,data.getName()));
        holder.id.setText(context.getString(R.string.parcel_customer_id,data.getCustid()));
        holder.phone.setText(data.getDelivery_phone());
//        holder.date.setText(data.getDate());
        holder.address.setText(data.getDeliveryAddress());
        if(data.getPayment_type() == 0) {
            holder.payment.setText(data.getAmount() + " " + data.getCurrency());
        }else{
            holder.payment.setText(context.getString(R.string.payment_prepaid));
        }
//        holder.comment.setText(data.getSpecialinstructions());
        holder.item_rootLayout.setTag(position);
        holder.callbtn.setTag(position);
    }


    @Override
    public int getItemviewType(int position) {

        if (parcelDataList.get(position).isDelivered()) {
            return ParcelListingData.ParcelData.DELIVERED;
        } else{
            return ParcelListingData.ParcelData.PENDING;
        }
    }

    @Override
    public ArrayList initObjectList() {
        return parcelDataList;
    }

    public interface OnItemClickListener {
        public void onItemClick(View v, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        DeliveryAllParcelsAdapter.mItemClickListener = mItemClickListener;
    }
}