package com.inerun.dropinsta.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.inerun.dropinsta.R;
import com.inerun.dropinsta.adapter.ParcelAdapter;
import com.inerun.dropinsta.base.BaseActivity;
import com.inerun.dropinsta.constant.UrlConstants;
import com.inerun.dropinsta.constant.Utils;
import com.inerun.dropinsta.data.ParcelListingData;
import com.inerun.dropinsta.helper.SimpleDividerItemDecoration;
import com.inerun.dropinsta.sql.DIDbHelper;

import java.util.ArrayList;
import java.util.List;

public class HomeActivityBackup extends BaseActivity {
    AppBarLayout appbar;
    Toolbar toolbar;
    private TextView deliver_num, pending_num, error_text;
    private RecyclerView recyclerView;
    private ParcelAdapter mAdapter;
    private List<ParcelListingData.ParcelData> parcelDataList = new ArrayList<>();
    private ParcelListingData parcelListingData;
    private LinearLayout top_layout;
    //    private CoordinatorLayout cordinatorLayout;
    private boolean doubleBackToExitPressedOnce = false;

    @Override
    public int customSetContentView() {
        return R.layout.app_bar_main;
    }

    @Override
    public void customOnCreate(Bundle savedInstanceState) {
//        cordinatorLayout = (CoordinatorLayout) findViewById(R.id.root_appbar);
//        getSupportActionBar().setIcon(R.mipmap.toolbaricon);
        parcelListingData = DIDbHelper.getParcelListData(this);
        parcelDataList = parcelListingData.getDeliveryData();
        appbar = (AppBarLayout) findViewById(R.id.appbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();

//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
//        toolbar.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                if (Build.VERSION.SDK_INT >= 16) {
//                    toolbar.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                } else {
//                    toolbar.getViewTreeObserver().removeGlobalOnLayoutListener(this);
//                }
//                toolbar.animate().translationY(-toolbar.getBottom()).setInterpolator(new AccelerateInterpolator()).start();
//            }
//        });

        error_text = (TextView) findViewById(R.id.error_textview);
        top_layout = (LinearLayout) findViewById(R.id.top_layout);
        deliver_num = (TextView) findViewById(R.id.dash_delivered_num);
        pending_num = (TextView) findViewById(R.id.dash_pending_num);
//        deliver_num.setText(""+ parcelListingData.getDelivered_num());
//        pending_num.setText(""+ parcelListingData.getPending_num());

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        recyclerView.addItemDecoration(new DividerItemDecoration(this.getResources().getDrawable(R.drawable.parcel_item_thumbnail_divider)));


        setDataAdapter(parcelDataList);

//        mAdapter = new ParcelAdapter(this, parcelDataList);
//        recyclerView.setHasFixedSize(true);
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setAdapter(mAdapter);
//
//        recyclerView.setOnScrollListener(new HidingScrollListener() {
//            @Override
//            public void onHide() {
//                hideView();
//            }
//
//            @Override
//            public void onShow() {
//                showView();
//            }
//        });

    }

    private void setDataAdapter(final List<ParcelListingData.ParcelData> parcelDataList) {
        if (parcelDataList != null && parcelDataList.size() > 0) {
            error_text.setVisibility(View.GONE);
            top_layout.setVisibility(View.VISIBLE);
            deliver_num.setText("" + parcelListingData.getDelivered_num());
            pending_num.setText("" + parcelListingData.getPending_num());

            mAdapter = new ParcelAdapter(this, this.parcelDataList);
            recyclerView.setHasFixedSize(true);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
//            recyclerView.addItemDecoration(new DividerItemDecoration(getDrawable(this, R.drawable.parcel_item_thumbnail_divider)));
            recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getApplicationContext()));

            recyclerView.setAdapter(mAdapter);

//            recyclerView.setOnScrollListener(new HidingScrollListener() {
//                @Override
//                public void onHide() {
//                    hideView();
//                }
//
//                @Override
//                public void onShow() {
//                    showView();
//                }
//            });

            mAdapter.setOnItemClickListener(new ParcelAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    Log.i("onItemClick", "OnClick");
//                Intent intent = new Intent(getActivity(), ProductListingActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(UrlConstants.KEY_DATA, parcelDataList.get(position));

//                startActivity(intent);
                    goToActivity(ParcelDetailActivity.class, bundle);
                    //slide from right to left
                    overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);


                }
            });
        } else {
            top_layout.setVisibility(View.GONE);
            error_text.setVisibility(View.VISIBLE);
        }


    }


    private void hideView() {

//        appbar.animate().translationY(-appbar.getHeight()).setInterpolator(new AccelerateInterpolator(2));
//        toolbar.animate().translationY(-toolbar.getHeight()).setInterpolator(new AccelerateInterpolator(2));
        top_layout.animate().translationY(-top_layout.getHeight()).setInterpolator(new AccelerateInterpolator(2));

        top_layout.setVisibility(View.GONE);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) recyclerView.getLayoutParams();
        params.weight = 10f;
        recyclerView.setLayoutParams(params);

//        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mFabButton.getLayoutParams();
//        int fabBottomMargin = lp.bottomMargin;
//        mFabButton.animate().translationY(mFabButton.getHeight()+fabBottomMargin).setInterpolator(new AccelerateInterpolator(2)).start();
    }

    private void showView() {

//        appbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
//        toolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
        top_layout.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
        top_layout.setVisibility(View.VISIBLE);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) recyclerView.getLayoutParams();
        params.weight = 7f;
        recyclerView.setLayoutParams(params);

//        mFabButton.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null && drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
//            Snackbar.make(cordinatorLayout, R.string.exit_msg, Snackbar.LENGTH_LONG).show();
            showSnackbar(R.string.exit_msg);

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {

//            performRequestSyncData();
            syncData();

//            parcelListingData = DIDbHelper.getParcelListData(this);
//            parcelDataList = parcelListingData.getDeliveryData();
//            Log.i("onOptionsItemSelected","" + parcelDataList.size());
//            setDataAdapter(parcelDataList);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


//    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.nav_camera) {
//            // Handle the camera action
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }else if (id == R.id.nav_logout) {
//            logout();
//        }
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }

    private void logout() {
        Utils.deletePrefs(this);
        showSnackbar(R.string.msg_logout);
        goToActivity(LoginActivity.class);
        finish();
    }


    @Override
    public void response() {
        super.response();
        parcelListingData = DIDbHelper.getParcelListData(HomeActivityBackup.this);
        parcelDataList = parcelListingData.getDeliveryData();
        Log.i("onOptionsItemSelected", "" + parcelDataList.size());
        setDataAdapter(parcelDataList);
    }

    @Override
    protected void onResume() {
        super.onResume();
        parcelListingData = DIDbHelper.getParcelListData(HomeActivityBackup.this);
        Log.i("parcelListingData", "" + parcelListingData);

        if (parcelListingData.getDeliveryData() != null && parcelListingData.getDeliveryData().size() > 0) {
            parcelDataList = parcelListingData.getDeliveryData();
            Log.i("onOptionsItemSelected", "" + parcelDataList.size());
            setDataAdapter(parcelDataList);
        } else {
            syncData();
        }
    }
}
