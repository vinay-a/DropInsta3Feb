package com.inerun.dropinsta.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.inerun.dropinsta.R;
import com.inerun.dropinsta.helper.RecyclerViewHolderOnClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vinay on 20/12/16.
 */

public class WhAddParcelAdapter extends BaseAdapter implements View.OnClickListener {
    List<String> parcellist;
    Context context;
    LayoutInflater inflater;

    public void setParcellist(List<String> parcellist) {
        this.parcellist = parcellist;
    }

    public WhAddParcelAdapter(Context context) {
        this.parcellist = new ArrayList<>();
        this.context=context;
         inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//
//
//        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.whaddparcelrow, parent, false);
//
//        final WhAddParcelAdapter.ViewHolder viewHolder = new WhAddParcelAdapter.ViewHolder(itemView);
//
//        viewHolder.edit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("WhAddParcelAdapter", "position = " + viewHolder.getAdapterPosition());
//
//
//            }
//        });
//        viewHolder.done.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//        return viewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//
////        holder.done.setTag(1, position);
////        holder.done.setTag(2, holder);
//
////        holder.edit.setOnClickListener(new RecyclerViewHolderOnClickListener( position) {
////
////            @Override
////            public void onRecyclerClick( int pos, View view) {
////
////
////            }
////        });
//        holder.delete.setOnClickListener(this);
////        holder.done.setOnClickListener(new RecyclerViewHolderOnClickListener() {
////            @Override
////            public void onRecyclerClick(int pos, View view) {
////                ViewHolder viewholder = (ViewHolder) view.getTag();
//////                int pos = (int) view.getTag(1);
//////                String parcel = "" + viewholder.parcelnumedt.getText();
//////                viewholder.parcelnumedt.setVisibility(View.GONE);
//////                viewholder.done.setVisibility(View.GONE);
//////                parcellist.set(pos, parcel);
//////
//////                viewholder.parcelnumtxt.setText(parcel);
//////
//////                viewholder.parcelnumtxt.setVisibility(View.VISIBLE);
//////                viewholder.edit.setVisibility(View.VISIBLE);
//////                notifyDataSetChanged();
////            }
////        });
//        holder.done.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Log.d("WhAddParcelAdapter", "position = " + viewHolder.getAdapterPosition());
////
////
////
////                String parcel = "" + viewHolder.parcelnumedt.getText();
////                viewHolder.parcelnumedt.setVisibility(View.GONE);
////                viewHolder.done.setVisibility(View.GONE);
////                parcellist.set(viewHolder.getAdapterPosition(), parcel);
////
////                viewHolder.parcelnumtxt.setText(parcel);
////
////                viewHolder.parcelnumtxt.setVisibility(View.VISIBLE);
////                viewHolder.edit.setVisibility(View.VISIBLE);
////                notifyDataSetChanged();
//
//            }
//        });
//
//
//
//
//
//    }

//    @Override
//    public int getItemCount() {
//        return parcellist.size();
//    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_parcel_delete:
                int pos = (int) view.getTag();
                parcellist.remove(pos);
                notifyDataSetChanged();


                break;

        }

    }

    public List<String> getParcellist() {

        return parcellist;
    }


    @Override
    public int getCount() {
        return parcellist.size();
    }

    @Override
    public Object getItem(int pos) {
        return pos;
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(final int position, View convertview, ViewGroup viewGroup) {
        ViewHolder holder=null;

        if(convertview==null)
        {

          convertview=  inflater.inflate(R.layout.whaddparcelrow,null);
            holder= new ViewHolder(convertview);
           convertview.setTag(holder);
        }else {
            holder= (ViewHolder) convertview.getTag();
        }
        String parcelnum = parcellist.get(position);
        holder.parcelnumtxt.setText(parcelnum);
        holder.edit.setTag(holder);
        holder.delete.setTag(position);
        holder.done.setTag(holder);
        holder.edit.setOnClickListener(new RecyclerViewHolderOnClickListener(position) {
            @Override
            public void onRecyclerClick(int pos, View view) {
                Log.d("WhAddParcelAdapter", "position = " + pos);


////
////
////
                ViewHolder viewHolder= (ViewHolder) view.getTag();
                String parcel = "" + viewHolder.parcelnumtxt.getText();
                viewHolder.parcelnumedt.setVisibility(View.VISIBLE);
                viewHolder.done.setVisibility(View.VISIBLE);
//                parcellist.set(viewHolder.getAdapterPosition(), parcel);

                viewHolder.parcelnumedt.setText(parcel);

                viewHolder.parcelnumtxt.setVisibility(View.GONE);
                viewHolder.edit.setVisibility(View.GONE);
                notifyDataSetChanged();

            }
        });
        holder.done.setOnClickListener(new RecyclerViewHolderOnClickListener(position) {
            @Override
            public void onRecyclerClick(int pos, View view) {
                Log.d("WhAddParcelAdapter", "position = " + pos);
////
////
////
                ViewHolder viewHolder= (ViewHolder) view.getTag();
                String parcel = "" + viewHolder.parcelnumedt.getText();
                viewHolder.parcelnumedt.setVisibility(View.GONE);
                viewHolder.done.setVisibility(View.GONE);
                parcellist.set(pos, parcel);

                viewHolder.parcelnumtxt.setText(parcel);

                viewHolder.parcelnumtxt.setVisibility(View.VISIBLE);
                viewHolder.edit.setVisibility(View.VISIBLE);
                notifyDataSetChanged();
            }
        });
        holder.delete.setOnClickListener(this);
        return convertview;
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
        public TextView parcelnumtxt;
        public EditText parcelnumedt;
        public ImageView edit, delete, done;


        public ViewHolder(View view) {
            super(view);
            edit = (ImageView) view.findViewById(R.id.add_parcel_edit);
            delete = (ImageView) view.findViewById(R.id.add_parcel_delete);
            done = (ImageView) view.findViewById(R.id.add_parcel_done);
            parcelnumtxt = (TextView) view.findViewById(R.id.add_parcel_num);
            parcelnumedt = (EditText) view.findViewById(R.id.add_parcel_num_edt);
//            done.setOnClickListener();

        }


    }

    public void addParcelToList(String parcel) {
        parcellist.add(parcel);
        notifyDataSetChanged();
    }


}
