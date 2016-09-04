package disono.webmons.com.utilities.library;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;

import timber.log.Timber;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 8/12/2016 2:04 PM
 */
public class SMS {
    private final String TAG = "SMS:Class";
    private Activity activity;
    private Context ctx;

    SmsManager smsManager;

    public SMS(Activity activity) {
        this.activity = activity;
        ctx = this.activity.getApplication();

        smsManager = SmsManager.getDefault();
    }

    /**
     * Send SMS message
     *
     * @param phoneNumber
     * @param message
     */
    public void send(String phoneNumber, String message) {
        try {
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
        } catch (Exception e) {
            Timber.e("%s, Error: %s", TAG, e.getMessage());
        }
    }

    /**
     * Send SMS using built in SMS application
     *
     * @param phoneNumber
     * @param message
     */
    public void builtInSMS(String phoneNumber, String message) {
        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
        sendIntent.putExtra("address", phoneNumber);
        sendIntent.putExtra("sms_body", message);

        try {
            this.activity.startActivity(sendIntent);
            this.activity.finish();
        } catch (android.content.ActivityNotFoundException e) {
            Timber.e("%s, Error: %s", TAG, e.getMessage());
        }
    }
}
