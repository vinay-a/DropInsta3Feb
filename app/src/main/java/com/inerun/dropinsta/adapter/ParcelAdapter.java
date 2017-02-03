package com.inerun.dropinsta.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.inerun.dropinsta.R;
import com.inerun.dropinsta.data.ParcelListingData;

import java.util.List;

/**
 * Created by vineet on 11/29/2016.
 */


public class ParcelAdapter extends RecyclerView.Adapter<ParcelAdapter.ViewHolder> {
    private Context context;
    private List<ParcelListingData.ParcelData> parcelDataList;
    private static OnItemClickListener mItemClickListener;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name,id,barcode, date,phone, address, payment, comment;
        public ImageButton callbtn;
        public LinearLayout item_rootLayout;

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
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            // TODO Auto-generated method stub
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(view, getPosition());
            }
        }
    }


    public ParcelAdapter(Context context, List<ParcelListingData.ParcelData> parcelDataList) {
        this.parcelDataList = parcelDataList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        if(viewType == ParcelListingData.ParcelData.PENDING) {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.parcel_list_item_pending, parent, false);

        }else {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.parcel_list_item_delivered, parent, false);
        }
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ParcelListingData.ParcelData data = parcelDataList.get(position);

        holder.barcode.setText(data.getBarcode());
        holder.date.setText(data.getDate());
        holder.address.setText(data.getDeliveryAddress());
        if(data.getPayment_type() == 0) {
            holder.payment.setText(data.getAmount() + " " + data.getCurrency());
        }else{
            holder.payment.setText(context.getString(R.string.payment_prepaid));
        }
        holder.comment.setText(data.getSpecialinstructions());

    }

    @Override
    public int getItemViewType(int position) {

        if (parcelDataList.get(position).isDelivered()) {
            return ParcelListingData.ParcelData.DELIVERED;
        } else{
            return ParcelListingData.ParcelData.PENDING;
        }

    }

    @Override
    public int getItemCount() {
        return parcelDataList.size();
    }

    public interface OnItemClickListener {
        public void onItemClick(View v, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        ParcelAdapter.mItemClickListener = mItemClickListener;
    }
}