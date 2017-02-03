package com.inerun.dropinsta.base;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.ContextThemeWrapper;

public class AlertDialogUtil implements DialogInterface.OnClickListener {

    static ConnectionDialogClickListener dialoglistener;

    public static AlertDialog.Builder showAlertDialog(Context context,
                                                      int title, int errormessage, int postivebutton, int negative_button) {
        AlertDialog.Builder alert;

        if (android.os.Build.VERSION.SDK_INT >= 11) {
            alert = new AlertDialog.Builder(new ContextThemeWrapper(context,
                    android.R.style.Theme_Holo_Dialog));
        } else {
            alert = new AlertDialog.Builder(new ContextThemeWrapper(context,
                    android.R.style.Theme_Dialog));

        }
        alert.setTitle(title);
        alert.setMessage(errormessage);
        alert.setPositiveButton(postivebutton, new AlertDialogUtil());
        alert.setNegativeButton(negative_button, new AlertDialogUtil());

        return alert;
    }

    public static AlertDialog.Builder showAlertDialog(Context context,
                                                      int title, int errormessage, int postivebutton,
                                                      int negative_button, ConnectionDialogClickListener listener) {
        AlertDialog.Builder alert;
        AlertDialogUtil.dialoglistener = listener;

        if (android.os.Build.VERSION.SDK_INT >= 11) {
            alert = new AlertDialog.Builder(new ContextThemeWrapper(context,
                    android.R.style.Theme_Holo_Dialog));
        } else {
            alert = new AlertDialog.Builder(new ContextThemeWrapper(context,
                    android.R.style.Theme_Dialog));

        }
        alert.setTitle(title);
        alert.setMessage(errormessage);
        alert.setPositiveButton(postivebutton, new AlertDialogUtil());
        alert.setNegativeButton(negative_button, new AlertDialogUtil());

        return alert;
    }

    public static AlertDialog.Builder showDialogwithNeutralButton(
            Context context, String title, String errormessage, String button,
            ConnectionDialogClickListener dialogclicklistener) {
        AlertDialog.Builder alert;
        AlertDialogUtil.dialoglistener = dialogclicklistener;
        if (android.os.Build.VERSION.SDK_INT >= 11) {
            alert = new AlertDialog.Builder(new ContextThemeWrapper(context,
                    android.R.style.Theme_Holo_Dialog));
        } else {
            alert = new AlertDialog.Builder(new ContextThemeWrapper(context,
                    android.R.style.Theme_Dialog));

        }

        alert.setTitle(title);
        alert.setMessage(errormessage);
        alert.setNeutralButton(button, new AlertDialogUtil());

        return alert;

    }

    public static AlertDialog.Builder showDialogwithNeutralButton(
            Context context, int title, int errormessage, int button,
            ConnectionDialogClickListener dialogclicklistener) {
        AlertDialog.Builder alert;
        AlertDialogUtil.dialoglistener = dialogclicklistener;
        if (android.os.Build.VERSION.SDK_INT >= 11) {
            alert = new AlertDialog.Builder(new ContextThemeWrapper(context,
                    android.R.style.Theme_Holo_Dialog));
        } else {
            alert = new AlertDialog.Builder(new ContextThemeWrapper(context,
                    android.R.style.Theme_Dialog));

        }

        alert.setTitle(context.getString(title));
        alert.setMessage(context.getString(errormessage));
        alert.setNeutralButton(context.getString(button), new AlertDialogUtil());

        return alert;

    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        // TODO Auto-generated method stub
        switch (which) {
            case DialogInterface.BUTTON_POSITIVE:
                if (dialoglistener != null) {
                    AlertDialogUtil.dialoglistener
                            .dialogClicklistener(DialogInterface.BUTTON_POSITIVE);
                }
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                if (dialoglistener != null) {
                    AlertDialogUtil.dialoglistener
                            .dialogClicklistener(DialogInterface.BUTTON_NEGATIVE);
                }
                break;
            case DialogInterface.BUTTON_NEUTRAL:
                if (dialoglistener != null) {
                    AlertDialogUtil.dialoglistener
                            .dialogClicklistener(DialogInterface.BUTTON_NEUTRAL);
                }
                break;

            default:
                break;
        }

    }

//    public static AlertDialog showDialogwithListview(final Activity context, int dialogid, ArrayList<String> addresslist, AdapterView.OnItemClickListener listener)
//    {
////        String names[] ={"A","B","C","D"};
//        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
//        LayoutInflater inflater = context.getLayoutInflater();
//        View convertView = (View) inflater.inflate(R.layout.checkout_dialog_lv, null);
//        alertDialog.setView(convertView);
//        alertDialog.setCancelable(true);
//
//        ListView lv = (ListView) convertView.findViewById(R.id.dialog_lv);
//        lv.setTag(dialogid);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,addresslist);
//        lv.setAdapter(adapter);
//        AlertDialog alert = alertDialog.show();
//
//        lv.setOnItemClickListener(listener);
//        return alert;
//
//    }






    /**
     * @params this interface is for tracking of dialog button int button will
     * be result either DialogInterface.BUTTON_POSITIVE or
     * DialogInterface.BUTTON_NEGATIVE
     */

    public interface ConnectionDialogClickListener {
        public void dialogClicklistener(int button);
    }

}
