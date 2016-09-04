package disono.webmons.com.utilities.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

import disono.webmons.com.clean_architecture.R;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 2016-04-13 11:26 AM
 */
public class DialogFactory {
    /**
     * Error Dialog box
     *
     * @param context
     * @param title
     * @param message
     * @param onClickListener
     * @return
     */
    public static Dialog error(Context context,
                               String title, String message,
                               DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setNeutralButton(R.string.dialog_button_ok, onClickListener);

        return alertDialog.create();
    }

    /**
     * Error Dialog box with default message
     *
     * @param context
     * @return
     */
    public static Dialog error(Context context) {
        return error(context,
                context.getString(R.string.dialog_error_title),
                context.getString(R.string.dialog_error_message), null);
    }

    /**
     * Progress dialog
     *
     * @param context
     * @param message
     * @param cancellable
     * @return
     */
    public static ProgressDialog progress(Context context, String message, boolean cancellable) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(cancellable);

        return progressDialog;
    }

    /**
     * Progress dialog with default message and cancellable
     *
     * @param context
     * @param cancellable
     * @return
     */
    public static ProgressDialog progress(Context context, boolean cancellable) {
        return progress(context,
                context.getString(R.string.dialog_progress_message), cancellable);
    }

    /**
     * Progress dialog with default message
     *
     * @param context
     * @return
     */
    public static ProgressDialog progress(Context context) {
        return progress(context,
                context.getString(R.string.dialog_progress_message), true);
    }

    /**
     * Confirm
     *
     * @param activity
     * @param title
     * @param message
     * @param dialogInterface
     * @return
     */
    public static AlertDialog confirm(Activity activity, String title, String message, DialogInterface.OnClickListener dialogInterface) {
        return new AlertDialog.Builder(activity)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.yes, dialogInterface)
                .setNegativeButton(android.R.string.no, null).show();
    }

    /**
     * Calendar
     *
     * @param activity
     * @param onDateSetListener
     */
    public static void calendar(Activity activity, DatePickerDialog.OnDateSetListener onDateSetListener) {
        Calendar now = Calendar.getInstance();
        DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(
                onDateSetListener,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show(activity.getFragmentManager(), "Datepickerdialog");
    }
}