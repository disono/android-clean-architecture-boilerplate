package disono.webmons.com.utilities.library.Dialogs.Sweet;

import android.app.Activity;
import android.graphics.Color;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 8/31/2016 6:38 PM
 */
public class WBAlerts {
    private Activity activity;

    public WBAlerts(Activity activity) {
        this.activity = activity;
    }

    /**
     * Initiliaze
     *
     * @param type
     * @return
     */
    public SweetAlertDialog init(int type) {
        return new SweetAlertDialog(this.activity, type);
    }

    /**
     * Create
     *
     * @param name
     * @param content
     * @param type
     * @param color
     * @param cancelable
     * @return
     */
    public SweetAlertDialog creator(String name, String content, int type, String color, boolean cancelable) {
        SweetAlertDialog pDialog = this.init(type);

        if (color != null) {
            pDialog.getProgressHelper().setBarColor(Color.parseColor(color));
        }

        if (name != null) {
            pDialog.setTitleText(name);
        }

        if (content != null) {
            pDialog.setContentText(content);
        }

        pDialog.setCancelable(cancelable);

        return pDialog;
    }

    /**
     * Progress
     *
     * @param name
     * @param content
     * @param color
     * @param cancelable
     * @return
     */
    public SweetAlertDialog progress(String name, String content, String color, boolean cancelable) {
        return this.creator(name, content, SweetAlertDialog.PROGRESS_TYPE, color, cancelable);
    }

    /**
     * Progress minimal
     *
     * @param name
     * @param content
     * @param cancelable
     * @return
     */
    public SweetAlertDialog progress(String name, String content, boolean cancelable) {
        return this.creator(name, content, SweetAlertDialog.PROGRESS_TYPE, null, cancelable);
    }

    /**
     * Progress name only
     *
     * @param name
     * @param cancelable
     * @return
     */
    public SweetAlertDialog progress(String name, boolean cancelable) {
        return this.creator(name, null, SweetAlertDialog.PROGRESS_TYPE, null, cancelable);
    }

    /**
     * Alert
     *
     * @param name
     * @param content
     * @return
     */
    public SweetAlertDialog alert(String name, String content) {
        return this.creator(name, content, SweetAlertDialog.PROGRESS_TYPE, null, true);
    }

    /**
     * Error
     *
     * @param name
     * @param content
     * @return
     */
    public SweetAlertDialog error(String name, String content) {
        return this.init(SweetAlertDialog.ERROR_TYPE)
                .setTitleText(name)
                .setContentText(content);
    }

    /**
     * Warning
     *
     * @param name
     * @param content
     * @param button
     * @return
     */
    public SweetAlertDialog warning(String name, String content, String button) {
        return this.init(SweetAlertDialog.WARNING_TYPE)
                .setTitleText(name)
                .setContentText(content)
                .setConfirmText(button);
    }

    /**
     * Success
     *
     * @param name
     * @param content
     * @return
     */
    public SweetAlertDialog success(String name, String content) {
        return this.init(SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText(name)
                .setContentText(content);
    }

    /**
     * Custom icon
     *
     * @param name
     * @param content
     * @param icon
     * @return
     */
    public SweetAlertDialog icon(String name, String content, int icon) {
        return this.init(SweetAlertDialog.CUSTOM_IMAGE_TYPE)
                .setTitleText(name)
                .setContentText(content)
                .setCustomImage(icon);
    }
}
