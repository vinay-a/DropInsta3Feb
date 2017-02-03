package com.inerun.dropinsta.helper;

import android.view.View;

/**
 * Created by vinay on 20/12/16.
 */

abstract public class RecyclerViewHolderOnClickListener implements View.OnClickListener {

    int pos;

    public RecyclerViewHolderOnClickListener(int pos) {

        this.pos = pos;
    }

    @Override
    public void onClick(View view) {
        onRecyclerClick(  pos, view);
    }
    public abstract void onRecyclerClick(int pos, View view);
}
