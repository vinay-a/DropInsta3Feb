package com.inerun.dropinsta.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inerun.dropinsta.R;

import java.util.ArrayList;

/**
 * Created by vineet on 11/29/2016.
 */


abstract public class BaseRecyclerViewAdapter extends RecyclerView.Adapter<BaseRecyclerViewAdapter.ViewHolder> {
    private static final int LIST_NOT_EMPTY = 901;
    private static final int LIST_EMPTY = 902;
    private Context context;


    ArrayList objectlist;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView errortxt;

        public boolean isEmptyVH;
        public RecyclerView.ViewHolder holder;

        public ViewHolder(View view) {
            super(view);
            errortxt = (TextView) view.findViewById(R.id.error_textview);


        }


    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        ViewHolder viewHolder;
        if (viewType == LIST_EMPTY) {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.error_layout, parent, false);
            viewHolder = new ViewHolder(itemView);
            viewHolder.isEmptyVH = true;

        } else {
            itemView = oncreateViewHolder(parent, viewType);
            viewHolder = new ViewHolder(itemView);
            viewHolder.holder = getViewHolder(itemView);

            viewHolder.isEmptyVH = false;
        }


        return viewHolder;

    }

    protected abstract ViewHolder getViewHolder(View itemView);

    protected abstract View oncreateViewHolder(ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(ViewHolder holder1, int position) {
//        ViewHolder holder1=  holder;
        if (holder1.isEmptyVH) {
            holder1.errortxt.setText(getEmptyErrorTitle());
            holder1.errortxt.setVisibility(View.VISIBLE);
        } else {
            onbindViewHolder(holder1, position);
        }


    }

    private String getEmptyErrorTitle() {
        return "No data Found";
    }

    abstract public void onbindViewHolder(ViewHolder holder, int position);


    @Override
    public int getItemViewType(int position) {

        if (isListEmpty()) {
            return LIST_EMPTY;

        } else {
            return getItemviewType(position);
        }


    }

    public boolean isListEmpty() {
        return ((objectlist != null && objectlist.size() > 0) ? false : true);
    }

    public int getItemviewType(int position) {
//        return LIST_EMPTY;
        return LIST_NOT_EMPTY;
    }

    @Override
    public int getItemCount() {

        objectlist = initObjectList();
        if (isListEmpty()) {
            return 1;
        } else {
            return objectlist.size();
        }

    }

    abstract public ArrayList initObjectList();


}