package disono.webmons.com.utilities.library.CloudMessage.FireBase;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import disono.webmons.com.clean_architecture.R;
import disono.webmons.com.clean_architecture.domain.models.MeModel;
import disono.webmons.com.clean_architecture.presentation.ui.activities.MainActivity;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 9/10/2016 4:25 PM
 */
public class FMessaging extends FirebaseMessagingService {
    private static final String TAG = "Messaging:Class";
    public String setTitle = "New Notification";

    public FMessaging() {
        super();
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // calling method to generate notification
        sendNotification(remoteMessage.getNotification().getBody());
    }

    // This method is only generating push notification
    // It is same as we did in earlier posts
    private void sendNotification(String messageBody) {
        MeModel meModel  = new MeModel();

        // received only if user is authenticated
        if (meModel.check()) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                    PendingIntent.FLAG_ONE_SHOT);

            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(this.setTitle)
                    .setContentText(messageBody)
                    .setAutoCancel(true)
                    .setSound(defaultSoundUri)
                    .setContentIntent(pendingIntent);

            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            notificationManager.notify(0, notificationBuilder.build());
        }

        Log.i(TAG, "New notification received.");
    }
}
