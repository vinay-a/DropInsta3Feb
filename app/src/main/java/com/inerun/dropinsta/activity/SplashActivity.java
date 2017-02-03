package com.inerun.dropinsta.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.inerun.dropinsta.DropInsta;
import com.inerun.dropinsta.R;
import com.inerun.dropinsta.activity_warehouse.WhDashboardActivity;
import com.inerun.dropinsta.base.AlertUtil;
import com.inerun.dropinsta.base.BaseActivity;
import com.inerun.dropinsta.base.CheckConnectionUtil;
import com.inerun.dropinsta.constant.AppConstant;
import com.inerun.dropinsta.constant.Utils;
import com.inerun.dropinsta.sql.DIDbHelper;

import java.util.Timer;
import java.util.TimerTask;


public class SplashActivity extends BaseActivity {
    Context context;

    private static final int FORCE_UPDATE_DAILOG = 101;
    //    ServiceClient.ServiceCallBack login_callback = new ServiceClient.ServiceCallBack() {
//        @Override
//        public void performThisWhenServiceEnds(String message, Object response) {
//            if ((Boolean) response) {
//                gotoHomeActivity();
//
//            } else {
//                gotoLandingActivity();
//            }
//        }
//    };

    private Timer timer;


    @Override
    public int customSetContentView() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        return R.layout.activity_splash;
    }

//

    @Override
    public void customOnCreate(Bundle savedInstanceState) {
        context=SplashActivity.this;

        handleExitintent();

        setSplash(true);

        setDataToApplication();
        timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {



                        if (CheckConnectionUtil.checkMyConnectivity(SplashActivity.this)) {

                            if (hasPermissions(context, AppConstant.requiredPermissions())) {
                                DIDbHelper.ensureDatabaseIsCorrect(context);
                            }
//
                            gotoPodDeleteActivity();

//                            if (Utils.isUserLoggedIn(SplashActivity.this)) {
//                                if (DropInsta.getUser().isDeliveryUser()) {
//                                    gotoHomeActivity();
//                                } else {
//                                    gotoWarehouseActivity();
//                                }
//                            } else {
//                                gotoLoginActivity();
//
//                            }

                        } else {
                            AlertUtil.showAlertDialogFinishActivity(SplashActivity.this, getString(R.string.activity_base_alert_message_unknown_host_exception));
                        }

                    }
                });


            }
        }, 2500);

    }

    private void gotoPodDeleteActivity() {
        goToActivity(PodDeleteActivity.class);

    }

    private void setDataToApplication() {
        if (Utils.isUserLoggedIn(context)) {
            DropInsta.setUser(Utils.getLoginData(context));
        }

    }


    private boolean validateVersion() {
//        String savedversion = Utils.getVersionCode(SplashActivity.this);
//        String appversion = "" + DeviceInfoUtil.getSelfVersionCode(SplashActivity.this);
//        if (savedversion.equalsIgnoreCase(appversion)) {
//            return true;
//        } else {
//
//            AlertUtil.showDialogwithNeutralButton(SplashActivity.this, R.string.force_update_dialog_title, R.string.force_update_dialog_msg, R.string.ok, dialog_listener, FORCE_UPDATE_DAILOG).show();
//            return false;
//        }
        return true;
    }

    private void handleExitintent() {
        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
            android.os.Process.killProcess(android.os.Process.myPid());
        }

    }

    private void doLogin() {
//        if (Utils.getSocialLogin(SplashActivity.this)) {
//            gotoHomeActivity();
//        Intent intent = new Intent(SplashActivity.this, StoreActivity.class);
//        Intent intent = new Intent(SplashActivity.this, DBHomeActivity.class);
//        startActivity(intent);
        goToActivity(DeliveryDashBoardActivity.class);
//        Intent intent = new Intent(SplashActivity.this,
//                DeliveryDashBoardActivity.class);
//        startActivity(intent);
//        } else {
//
//            login_client = BTService.loginService(SplashActivity.this, null, login_callback, Utils.getUserEmail(SplashActivity.this), Utils.getPassword(SplashActivity.this));
//        }

    }


//    private void dosomething(ArrayList<CategoryData> categoryDatas){
//        Bundle bundle = new Bundle();
//        bundle.putSerializable(UrlConstants.KEY_CATEGORY, categoryDatas);
//        goToActivity(StoreActivity.class, bundle);
////        goToActivity(DeliveryDashBoardActivity.class, bundle);
//
//        /*Intent intent = new Intent(SplashActivity.this, DBHomeActivity.class);
//        intent.putExtra(UrlConstants.KEY_CATEGORY, categoryDatas);
//        startActivity(intent);*/
//
//
//    }

    private void gotoLoginActivity() {
        Intent intent = new Intent(SplashActivity.this,
                LoginActivity.class);
        startActivity(intent);


    }

    private void gotoHomeActivity() {

        Intent intent = new Intent(SplashActivity.this,
                DeliveryDashBoardActivity.class);
        startActivity(intent);

    }
private void gotoWarehouseActivity() {

        Intent intent = new Intent(SplashActivity.this,
                WhDashboardActivity.class);
        startActivity(intent);

    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        if (timer != null) {
            timer.cancel();
            finish();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (lang_client != null) {
//            lang_client.cancelService();
//        }
//        if (login_client != null) {
//            login_client.cancelService();
//        }
    }


}
