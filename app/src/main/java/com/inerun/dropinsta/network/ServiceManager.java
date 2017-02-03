package com.inerun.dropinsta.network;

import android.app.Application;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.inerun.dropinsta.R;
import com.loopj.android.http.RequestParams;
import com.squareup.okhttp.OkHttpClient;

import java.util.Map;


/**
 * Created by vinay on 10/14/2016.
 */
public class ServiceManager {
    private final String TAG = "ServiceManager";
    static ServiceManager manager;
    static Application appInstance;
    ProgressBar progressBar;
    private RequestQueue mRequestQueue;
    private LruBitmapCache mLruBitmapCache;
    private ImageLoader mImageLoader;


    public static ServiceManager init() {
        if (manager == null)
            manager = new ServiceManager();

        return manager;
    }

    public static ServiceManager init(Application appInstance) {
        if (manager == null)
            manager = new ServiceManager();
        ServiceManager.appInstance = appInstance;
        return manager;
    }

    public static ServiceManager setAppInstance(Application appInstance) {
        ServiceManager.appInstance = appInstance;
        return manager;
    }

    public ServiceManager addRequest(String url, Response.Listener responseListener, Response.ErrorListener errorListener, final TypeToken typetoken, String tag, String params) {

        final GsonGetRequest gsonGetRequest =
                getServiceObject(url, responseListener, this.errorListener, typetoken, params);
        Log.i(TAG, "" + System.currentTimeMillis());
        showProgress();
        addRequest(gsonGetRequest, tag);
        return manager;
    }

    public ServiceManager addPostRequest(String url, Response.Listener responseListener, Response.ErrorListener errorListener, final TypeToken typetoken, String tag, String params) {

        final GsonPostRequest gsonPostRequest = getPostServiceObject(url, responseListener, this.errorListener, typetoken, params);
        Log.i(TAG, "" + System.currentTimeMillis());
        showProgress();
        addRequest(gsonPostRequest, tag);
        return manager;
    }

    public void showProgress() {
        if (progressBar != null)
            progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgress() {
        if (progressBar != null)
            progressBar.setVisibility(View.GONE);
    }

    public ServiceManager setProgress(ProgressBar progressBar) {
        this.progressBar = progressBar;
        return manager;
    }


    public void cancelAllRequest(String... params) {
        for (String param : params)
            cancelAllRequests(param);
    }

    public ServiceManager cancelExistingRequest(String tag) {
        cancelAllRequests(tag);
        return manager;
    }

    public void loadImage(NetworkImageView imageView, ImageLoader loader, String url) {
        imageView.setDefaultImageResId(R.mipmap.default_image);
        imageView.setErrorImageResId(R.mipmap.default_image);
        imageView.setImageUrl(url, loader);
    }

    Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            // Deal with the error here

            hideProgress();
            if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                Toast.makeText(appInstance,
                        "error network timeout",
                        Toast.LENGTH_LONG).show();
            } else if (error instanceof AuthFailureError) {
                //TODO
                Toast.makeText(appInstance,
                        "AuthFailure Error",
                        Toast.LENGTH_LONG).show();
            } else if (error instanceof ServerError) {
                //TODO
                Toast.makeText(appInstance,
                        "Server Error",
                        Toast.LENGTH_LONG).show();
            } else if (error instanceof NetworkError) {
                //TODO
                Toast.makeText(appInstance,
                        "Network Error",
                        Toast.LENGTH_LONG).show();
            } else if (error instanceof ParseError) {
                //TODO
                Toast.makeText(appInstance,
                        "Parse Error",
                        Toast.LENGTH_LONG).show();
            }

        }
    };
//    !-------------------------------------------------- Abstraction Start Here ----------------------------------------------------------!

    @NonNull
    public RequestQueue getVolleyRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(appInstance, new OkHttpStack(new OkHttpClient()));
        }

        return mRequestQueue;
    }

    private static void addRequest(@NonNull final Request<?> request) {
        init().getVolleyRequestQueue().add(request);
    }

    public static void addRequest(@NonNull final Request<?> request, @NonNull final String tag) {
        request.setTag(tag);
        addRequest(request);
    }

    private static void cancelAllRequests(@NonNull final String tag) {
        init().getVolleyRequestQueue().cancelAll(tag);
    }

    @NonNull
    public ImageLoader getVolleyImageLoader() {
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader
                    (
                            getVolleyRequestQueue(),
                            init().getVolleyImageCache()
                    );
        }

        return mImageLoader;
    }

    @NonNull
    private LruBitmapCache getVolleyImageCache() {
        if (appInstance != null) {
            if (mLruBitmapCache == null) {
                mLruBitmapCache = new LruBitmapCache(appInstance);
            }
        } else {
            Log.e(TAG, "Service Manager not Intialised in Application");
        }
        return mLruBitmapCache;
    }

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(DummyObject.class, new DummyObjectDeserializer())
            .create();


    public static GsonGetRequest getServiceObject
            (String Url,
             @NonNull final Response.Listener listener,
             @NonNull final Response.ErrorListener errorListener,

             final TypeToken typetoken, String params) {
        String url = Url;
        if (params != null) {

//            Log.i("Url", "" + Url);
//            Log.i("Params", "" + params);
            url = url + "?" + params;


        }
        Log.i("GetQuery", "" + url);
        return new GsonGetRequest<>
                (
                        url,
                        typetoken.getType(),
                        gson,
                        listener,
                        errorListener
                );
    }

    public static GsonPostRequest getPostServiceObject
            (String Url,
             @NonNull final Response.Listener listener,
             @NonNull final Response.ErrorListener errorListener,

             final TypeToken typetoken, String params) {
        String url = Url;
//        if (params != null) {
//
////            Log.i("Url", "" + Url);
////            Log.i("Params", "" + params);
//            url = url + "?" + params;
//
//
//        }

        Log.i("URL", "" + url);
        RequestParams requestparams= new RequestParams(params);
        Log.i("Params", "" + requestparams);
        Log.i("embeddedurl", "" + url+"?"+requestparams);

        return new GsonPostRequest<>
                (
                        url, params,
                        typetoken.getType(),
                        gson,
                        listener,
                        errorListener
                );
    }

    public void postRequest(String url, Map<String, String> params, Response.Listener<String> response_listener, Response.ErrorListener response_errorlistener, String tag) {

        Log.i("URL_POST", "" + url);
        RequestParams requestparams= new RequestParams(params);
        Log.i("Params", "" + requestparams);
        Log.i("embeddedurl", "" + url+"?"+requestparams);
        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, url, params, response_listener, response_errorlistener);
        addRequest(jsObjRequest, tag);

    }
    public void postRequest(String url, Map<String, String> params,ProgressBar progressBar, Response.Listener<String> response_listener, Response.ErrorListener response_errorlistener, String tag) {

        Log.i("URL_POST", "" + url);
        RequestParams requestparams= new RequestParams(params);
        Log.i("Params", "" + requestparams);
        Log.i("embeddedurl", "" + url+"?"+requestparams);
        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST,progressBar, url, params, response_listener, response_errorlistener);
        if(progressBar!=null)
        {
            progressBar.setVisibility(View.VISIBLE);
        }
        addRequest(jsObjRequest, tag);

    }


}
