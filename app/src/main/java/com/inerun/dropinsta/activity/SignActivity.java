package com.inerun.dropinsta.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.gcacace.signaturepad.views.SignaturePad;
import com.inerun.dropinsta.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by vinay on 08/11/16.
 */

public class SignActivity extends Activity implements SignaturePad.OnSignedListener, View.OnClickListener {
    public static final String FOLDERNAME = ".SignaturePad";
    public static final String INTENT_FILENAME = "filename";
    private static final String DATEIME_FORMAT = "yyyy.MM.dd_HH.mm.ss ";
    private SignaturePad mSignaturePad;
    private Button mClearButton;
    private Button mSaveButton;
    private String TAG = "SignActivity";
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private String filepath;
    private Button cancelSign;
    private EditText receiver_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        verifyStoragePermissions(this);
        setContentView(R.layout.activity_sign);
        mSignaturePad = (SignaturePad) findViewById(R.id.signature_pad);

        mSignaturePad.setOnSignedListener(this);
        mClearButton = (Button) findViewById(R.id.clear);
        mSaveButton = (Button) findViewById(R.id.save);
        cancelSign = (Button) findViewById(R.id.cancelSign);
        cancelSign.setOnClickListener(this);
        mClearButton.setOnClickListener(this);
        mSaveButton.setOnClickListener(this);
        mSignaturePad.setOnSignedListener(this);

        receiver_name = (EditText) findViewById(R.id.receiver_name);

    }

    @Override
    public void onStartSigning() {

    }

    @Override
    public void onSigned() {
        // Check if no view has focus:
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        mSaveButton.setEnabled(true);
        mClearButton.setEnabled(true);
    }

    @Override
    public void onClear() {
        mSaveButton.setEnabled(false);
        mClearButton.setEnabled(false);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.save:


                    Bitmap signatureBitmap = mSignaturePad.getSignatureBitmap();
                    if (addJpgSignatureToGallery(signatureBitmap)) {
                        Log.i(TAG, "Signature saved into the Gallery");
                        if (filepath != null && filepath.length() > 0) {

                            Intent intent = new Intent();
                            intent.putExtra(INTENT_FILENAME, filepath);

                            setResult(RESULT_OK, intent);
                            finish();
                        }
                    } else {
                        Toast.makeText(SignActivity.this, "Unable to store the signature", Toast.LENGTH_SHORT).show();
                    }


                break;
            case R.id.clear:
                mSignaturePad.clear();
                break;
            case R.id.cancelSign:
                setResult(RESULT_CANCELED);
                finish();
                break;
        }

    }

    public boolean addJpgSignatureToGallery(Bitmap signature) {
        boolean result = false;
        try {
            String name = getDateTime(DATEIME_FORMAT);
            File photo = new File(getAlbumStorageDir(FOLDERNAME), String.format("Signature_%s.jpg", name));
            String path = saveBitmapToJPG(signature, photo);
            Log.i(TAG, "FilePath: " + path);
            filepath = path;
//            scanMediaFile(photo);
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    private String getDateTime(String dateFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

        // Create a calendar object that will convert the date and time value in milliseconds to date. 
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        return formatter.format(calendar.getTime());

    }

    /**
     * Checks if the app has permission to write to device storage
     * <p/>
     * If the app does not has permission then the user will be prompted to grant permissions
     *
     * @param activity the activity from which permissions are checked
     */
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    public File getAlbumStorageDir(String albumName) {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStorageDirectory() + File.separator +
                albumName);
        if (!file.mkdirs()) {
            Log.e("SignaturePad", "Directory not created");
        }
        return file;
    }

    public String saveBitmapToJPG(Bitmap bitmap, File photo) throws IOException {
        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(bitmap, 0, 0, null);
        OutputStream stream = new FileOutputStream(photo);
        newBitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream);
        stream.close();
        return photo.getPath();

    }
}
