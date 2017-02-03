package com.inerun.dropinsta.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.inerun.dropinsta.DropInsta;
import com.inerun.dropinsta.R;
import com.inerun.dropinsta.activity_warehouse.WhDashboardActivity;
import com.inerun.dropinsta.base.BaseActivity;
import com.inerun.dropinsta.constant.AppConstant;
import com.inerun.dropinsta.constant.Utils;
import com.inerun.dropinsta.data.POD;
import com.inerun.dropinsta.sql.DIDbHelper;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by vineet on 2/1/2017.
 */

public class PodDeleteActivity extends BaseActivity {
    @Override
    public int customSetContentView() {
        return R.layout.activity_blank;
    }

    @Override
    public void customOnCreate(Bundle savedInstanceState) {
//        if(hasPermissions(this, AppConstant.requiredPermissions())) {
//            ArrayList<POD> podArrayList = DIDbHelper.getOldPODsListByDate(this);
//
//            if (podArrayList != null && podArrayList.size() > 0) {
//                for (POD pod : podArrayList) {
//                    String podName = pod.getName();
//
//                    File file = new File(Environment.getExternalStorageDirectory() + File.separator + SignActivity.FOLDERNAME + "/" + podName);
//                    Log.i("file_path", file.toString());
////                File file= new File(android.os.Environment.getExternalStorageDirectory()+ "/myfolder/myimage.jpg");
//                    if (file.exists()) {
//                        file.delete();
//                    }
//                }
//                DIDbHelper.deleteOldPodsData(this, podArrayList);
//
//            } else {
//
//               goFurther();
//            }
//        }else{
//            goFurther();
//        }


        AsyncTaskRunner runner = new AsyncTaskRunner();
        runner.execute();
//        goFurther();
    }

    private void goFurther() {
        if (Utils.isUserLoggedIn(this)) {
            if (DropInsta.getUser().isDeliveryUser()) {
                gotoHomeActivity();
            } else {
                gotoWarehouseActivity();
            }
        } else {
            gotoLoginActivity();

        }
        finish();
    }

    private void gotoLoginActivity() {
        Intent intent = new Intent(this,
                LoginActivity.class);
        startActivity(intent);


    }

    private void gotoHomeActivity() {

        Intent intent = new Intent(this,
                DeliveryDashBoardActivity.class);
        startActivity(intent);

    }

    private void gotoWarehouseActivity() {

        Intent intent = new Intent(this,
                WhDashboardActivity.class);
        startActivity(intent);

    }

    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        private String resp;


        @Override
        protected String doInBackground(String... params) {


            try {
//                Thread.sleep(5000);
                if (hasPermissions(PodDeleteActivity.this, AppConstant.requiredPermissions())) {
                    ArrayList<POD> podArrayList = DIDbHelper.getOldPODsListByDate(PodDeleteActivity.this);

                    if (podArrayList != null && podArrayList.size() > 0) {
                        for (POD pod : podArrayList) {
                            String podName = pod.getName();

                            File file = new File(Environment.getExternalStorageDirectory() + File.separator + SignActivity.FOLDERNAME + "/" + podName);
                            Log.i("file_path", file.toString());
                            if (file.exists()) {
                                file.delete();
                            }
                        }
                        DIDbHelper.deleteOldPodsData(PodDeleteActivity.this, podArrayList);
//                        Toast.makeText(PodDeleteActivity.this, "POd table date update Successfully",Toast.LENGTH_LONG).show();
                    }
                }

            }  catch (Exception e) {
                e.printStackTrace();
                resp = e.getMessage();
            }
            return resp;
        }


        @Override
        protected void onPostExecute(String result) {
            // execution of result of Long time consuming operation
            hideProgress();
            goFurther();
//            Toast.makeText(PodDeleteActivity.this, "POd table date update Successfully",Toast.LENGTH_LONG).show();
        }


        @Override
        protected void onPreExecute() {
            showProgress();
        }


    }

}
