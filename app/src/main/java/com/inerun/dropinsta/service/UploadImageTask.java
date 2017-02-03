package com.inerun.dropinsta.service;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.inerun.dropinsta.R;
import com.inerun.dropinsta.data.ResponseData;
//import com.inerun.dropinsta.data.Response;

import org.json.JSONException;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import static android.R.attr.bitmap;

/**
 * Created by vinay on 8/12/2015.
 */
public class UploadImageTask extends AsyncTask<String, ResponseData, ResponseData> {
    Context context;
    ProgressBar progressBar;
//    ServiceClient.ServiceCallBack callBack;
    //    GridView photo_gridview;
    private String path;

    private Bitmap bitmap;

    private String UPLOAD_URL ="http://simplifiedcoding.16mb.com/VolleyUpload/upload.php";

    private String KEY_IMAGE = "image";
    private String KEY_NAME = "name";

    public UploadImageTask(Context context, ProgressBar progressBar) {
        this.context = context;
        this.progressBar = progressBar;
//        this.callBack = callBack;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected ResponseData doInBackground(String... params) {
        ResponseData response = new ResponseData();
        if (params != null && params.length > 0 && params[0].length() > 0) {
            path = params[0];
            Log.i("img uri", "" + path);
//            String imagename=BitmapUtil.getImageNameFromURI(img_uri);
//            try {
//                response = BTService.uploadImageService(context, path);
//                //BTService.uploadUserImageService(context, path);
//            } catch (IOException e) {
//                e.printStackTrace();
//                response.setException(e);
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//                response.setException(e);
//            } catch (Exception e) {
//                e.printStackTrace();
//                response.setException(e);
//            }

        } else {
            Log.i("Cancelling Upload", "Image is either null or not specified in parameter");
            cancel(true);
        }
        return response;
    }

    @Override
    protected void onPostExecute(ResponseData result) {
        super.onPostExecute(result);
        progressBar.setVisibility(View.INVISIBLE);
        if (result.getException() == null) {

//            callBack.performThisWhenServiceEnds(null, result);

        } else {
//            Toast.makeText(context, R.string.image_uploadexception, Toast.LENGTH_LONG).show();
        }

    }

    private void uploadImage(){
        //Showing the progress dialog
//        final ProgressDialog loading = ProgressDialog.show(this,"Uploading...","Please wait...",false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Disimissing the progress dialog
//                        loading.dismiss();
                        //Showing toast message of the response
                        Toast.makeText(context, s , Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Dismissing the progress dialog
//                        loading.dismiss();

                        //Showing toast
                        Toast.makeText(context, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Converting Bitmap to String
//                String image = getStringImage(bitmap);

                //Getting Image Name
//                String name = editTextName.getText().toString().trim();

                //Creating parameters
                Map<String,String> params = new Hashtable<String, String>();

                //Adding parameters
//                params.put(KEY_IMAGE, image);
//                params.put(KEY_NAME, name);

                //returning parameters
                return params;
            }
        };

        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }
}
