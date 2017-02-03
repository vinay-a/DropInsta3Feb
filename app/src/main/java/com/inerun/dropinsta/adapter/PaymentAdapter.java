package com.inerun.dropinsta.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inerun.dropinsta.R;
import com.inerun.dropinsta.data.ParcelListingData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vineet on 11/29/2016.
 */


public class PaymentAdapter extends BaseRecyclerViewAdapter {
    private Context context;
    private ArrayList<ParcelListingData.ParcelData> parcelDataList;
    private static OnItemClickListener mItemClickListener;

    public class ViewHolder extends BaseRecyclerViewAdapter.ViewHolder implements View.OnClickListener {
        public TextView barcode, payment;


        public ViewHolder(View view) {
            super(view);
            barcode = (TextView) view.findViewById(R.id.barcode);
            payment = (TextView) view.findViewById(R.id.payment);
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


    public PaymentAdapter(Context context, ArrayList<ParcelListingData.ParcelData> parcelDataList) {
        this.parcelDataList = parcelDataList;
        this.context = context;
    }



    @Override
    protected BaseRecyclerViewAdapter.ViewHolder getViewHolder(View itemView) {
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    protected View oncreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;

        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.payment_list_item, parent, false);
        return itemView;
    }

    @Override
    public void onbindViewHolder(BaseRecyclerViewAdapter.ViewHolder viewholder, int position) {
        ViewHolder holder= (ViewHolder) viewholder.holder;
        ParcelListingData.ParcelData data = parcelDataList.get(position);

        holder.barcode.setText(data.getBarcode());
        if (data.getPayment_type() == 0) {
            holder.payment.setText(data.getAmount() + " " + data.getCurrency());
        } else {
            holder.payment.setText(context.getString(R.string.payment_prepaid));
        }
    }



//    @Override
//    public int getItemViewType(int position) {
//
//        if (parcelDataList.get(position).isDelivered()) {
//            return ParcelListingData.ParcelData.DELIVERED;
//        } else {
//            return ParcelListingData.ParcelData.PENDING;
//        }
//
//    }



    @Override
    public ArrayList initObjectList() {
        return parcelDataList;
    }

    public interface OnItemClickListener {
        public void onItemClick(View v, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        PaymentAdapter.mItemClickListener = mItemClickListener;
    }


    public List<ParcelListingData.ParcelData> getParcelDataList() {
        return parcelDataList;
    }

    public void setParcelDataList(ArrayList<ParcelListingData.ParcelData> parcelDataList) {
        this.parcelDataList = parcelDataList;
    }

}