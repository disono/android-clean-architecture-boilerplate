package disono.webmons.com.clean_architecture.util.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

import disono.webmons.com.clean_architecture.R;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Created at: 2016-04-13 11:26 AM
 */
public class DialogFactory {
    public static Dialog error(Context context,
                               String title, String message,
                               DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setNeutralButton(R.string.dialog_button_ok, onClickListener);

        return alertDialog.create();
    }

    public static Dialog error(Context context) {
        return error(context,
                context.getString(R.string.dialog_error_title),
                context.getString(R.string.dialog_error_message), null);
    }

    public static ProgressDialog progress(Context context, String message) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(message);

        return progressDialog;
    }

    public static ProgressDialog progress(Context context) {
        return progress(context,
                context.getString(R.string.dialog_progress_message));
    }
}
