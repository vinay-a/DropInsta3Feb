package com.inerun.dropinsta.base;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.provider.Settings.Secure;
import android.support.v4.app.ActivityCompat;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class DeviceInfoUtil {

	public static String getAndroidID(Context context) {
		String deviceId = Secure.getString(context.getContentResolver(),
				Secure.ANDROID_ID);
		return deviceId;
	}

	public static String getDeviceIMEI(Context context) {
		TelephonyManager TelephonyMgr = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		String szImei = TelephonyMgr.getDeviceId();
		return szImei;

	}

	public static String getModelName(Context context) {
		String deviceName = Build.MODEL;
		return deviceName;
	}

	public static String getBrandName(Context context) {

		String devicebrand = Build.MANUFACTURER;
		devicebrand = devicebrand.toUpperCase().charAt(0)
				+ devicebrand.substring(1);
		return devicebrand;
	}

	public static int getDeviceApiVersion(Context context) {

		return Build.VERSION.SDK_INT;

	}

//	public static List<String> getAllEmailAccounts(Context context) {
//		Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
//		Account[] accounts = AccountManager.get(context).getAccounts();
//		List<String> possibleEmail = new ArrayList<String>();
//		for (Account account : accounts) {
//			if (emailPattern.matcher(account.name).matches()) {
//				possibleEmail.add(account.name);
//
//			}
//		}
//		return possibleEmail;
//
//	}

//	public static String getPrimaryEmailAccount(Context context) {
//		Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
//		Account[] accounts = AccountManager.get(context).getAccounts();
//		String email = new String();
//		for (Account account : accounts) {
//			if (emailPattern.matcher(account.name).matches()) {
//				email = account.name;
//				break;
//			}
//		}
//		return email;
//
//	}

	public static String getDeviceAndroidVersionName(Context context) {
		String device_version;
		int version = getDeviceApiVersion(context);
		switch (version) {
		case Build.VERSION_CODES.CUPCAKE:

			device_version = "CUPCAKE";
			break;

		case Build.VERSION_CODES.DONUT:

			device_version = "DONUT";
			break;
		case Build.VERSION_CODES.ECLAIR:

			device_version = "ECLAIR";
			break;
		case Build.VERSION_CODES.ECLAIR_0_1:

			device_version = "ECLAIR";
			break;
		case Build.VERSION_CODES.ECLAIR_MR1:

			device_version = "ECLAIR";
			break;

		case Build.VERSION_CODES.FROYO:

			device_version = "FROYO";
			break;
		case Build.VERSION_CODES.GINGERBREAD:

			device_version = "GINGERBREAD";
			break;
		case Build.VERSION_CODES.GINGERBREAD_MR1:

			device_version = "GINGERBREAD";
			break;
		case Build.VERSION_CODES.HONEYCOMB:

			device_version = "HONEYCOMB";
			break;
		case Build.VERSION_CODES.HONEYCOMB_MR1:

			device_version = "HONEYCOMB";
			break;
		case Build.VERSION_CODES.HONEYCOMB_MR2:

			device_version = "HONEYCOMB";
			break;
		case Build.VERSION_CODES.ICE_CREAM_SANDWICH:

			device_version = "ICE CREAM SANDWICH";
			break;
		case Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1:

			device_version = "ICE CREAM SANDWICH";
			break;
		case Build.VERSION_CODES.JELLY_BEAN:

			device_version = "JELLY BEAN";
			break;
		case Build.VERSION_CODES.JELLY_BEAN_MR1:

			device_version = "JELLY BEAN 4.2";
			break;
		case Build.VERSION_CODES.JELLY_BEAN_MR2:

			device_version = "JELLY BEAN 4.3";
			break;
		case Build.VERSION_CODES.KITKAT:

			device_version = "KITKAT";
			break;

		default:
			device_version = "NOT FOUND";
			break;
		}

		return device_version;

	}

	public static int getSelfVersionCode(Context context) {
		PackageInfo pInfo;
		try {

			pInfo = context.getPackageManager().getPackageInfo(
					context.getPackageName(), PackageManager.GET_META_DATA);
			return pInfo.versionCode;

		} catch (NameNotFoundException e) {
			e.printStackTrace();
			Toast.makeText(context, "Package Name Not Foun", Toast.LENGTH_SHORT)
					.show();
			return 0;
		}

	}

	public static void redirectToPlayStore(Context context,
			String packagename) {

		Intent intent = new Intent(Intent.ACTION_VIEW);
		try {
			intent.setData(Uri.parse("market://details?id=" + packagename));
			context.startActivity(intent);
		} catch (Exception e) {
			context.startActivity(new Intent(Intent.ACTION_VIEW, Uri
					.parse("http://play.google.com/store/apps/details?id="
							+ packagename)));
		}

		// context.startActivity( new Intent(Intent.ACTION_VIEW,
		// Uri.parse(" market://details?id="+AppConstants.APP_PACKAGE)));
	}

//	public static boolean checkPlayServices(Context context, Handler mHandler) {
//		final Activity context2 = (Activity) context;
//		final int resultCode = GooglePlayServicesUtil
//				.isGooglePlayServicesAvailable(context2);
//		if (resultCode != ConnectionResult.SUCCESS) {
//			if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
//
//				mHandler.post(new Runnable() {
//					@Override
//					public void run() {
//						GooglePlayServicesUtil.getErrorDialog(resultCode,
//								context2,
//								9000)
//								.show();
//					}
//				});
//
//			} else {
//				Log.i("Unsupported", "This device is not supported.");
//				context2.finish();
//			}
//			return false;
//		}
//		return true;
//	}

	/**
	 * 
	 * @param context
	 * @return method to find whether sim is available or not
	 */
	public static boolean isSimCardAvailable(Context context) {
		TelephonyManager tm = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);

		return (tm.getSimState() != TelephonyManager.SIM_STATE_ABSENT);

	}

	@SuppressWarnings("deprecation")
	public static boolean isAirplaneModeOn(Context context) {
		boolean flag = false;

		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
			int i = Settings.System.getInt(context.getContentResolver(),
					Settings.System.AIRPLANE_MODE_ON, 0);
			if (i == 0)
				flag = true;
			// flag= Settings.System.getInt(context.getContentResolver(),
			// Settings.System.AIRPLANE_MODE_ON, 0) != 0;
		} else {

			int i = Settings.Global.getInt(context.getContentResolver(),
					Settings.Global.AIRPLANE_MODE_ON, 0);
			if (i == 0)
				flag = true;

		}

		return flag;
	}

	public static boolean isGsm(Context context) {
		DeviceInfoUtil dev = new DeviceInfoUtil();
		MySignalListener mysignal = dev.new MySignalListener(context);

		return mysignal.isGsm();

	}

	public static boolean isInternetConnectionAvailable(Context context) {

		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager
				.getActiveNetworkInfo();
		Log.e("state",
				""
						+ (activeNetworkInfo != null && activeNetworkInfo
						.isConnected()));
		return activeNetworkInfo != null && activeNetworkInfo.isConnected();

	}

	public static int getSignalStrength(Context context) {
		int strength = -1;
		SignalStrength signal;
		if (!isGsm(context)) {
			DeviceInfoUtil dev = new DeviceInfoUtil();
			TelephonyManager mTelManager = (TelephonyManager) context
					.getSystemService(Context.TELEPHONY_SERVICE);

			MySignalListener mysignal = dev.new MySignalListener(context);
			// mysignal.
			PhoneStateListener mSignalListener = new PhoneStateListener() {
				private int mStrength;

				@Override
				public void onSignalStrengthChanged(int asu) {
					Log.e("LOGTAG", "#1. " + String.valueOf(asu));
					if (mStrength != asu) {
						mStrength = asu;

					}
					super.onSignalStrengthChanged(asu);
				}
			};
			// mTelManager.listen(mSignalListener,
			// PhoneStateListener.LISTEN_SIGNAL_STRENGTH);
			mTelManager.listen(mysignal,
					MySignalListener.LISTEN_SIGNAL_STRENGTHS);

			strength = mysignal.getSignalStrength();
		}

		return strength;
	}

	public class MySignalListener extends PhoneStateListener {
		SignalStrength signalStrength;
		Context context;

		public MySignalListener(Context context) {

			this.context = context;

		}

		public int getSignalStrength() {
			int strength = -1;
			if (!isGsm()) {
				strength = signalStrength.getGsmSignalStrength();
			}
			return strength;
		}

		@Override
		public void onSignalStrengthsChanged(SignalStrength signalStrength) {
			super.onSignalStrengthsChanged(signalStrength);

			this.signalStrength = signalStrength;

		}

		public boolean isGsm() {

			return signalStrength.isGsm();

		}
	}


	public static boolean hasPermissions(Context context, String... permissions) {
		if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
			for (String permission : permissions) {
				if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
					return false;
				}
			}
		}
		return true;
	}
}
