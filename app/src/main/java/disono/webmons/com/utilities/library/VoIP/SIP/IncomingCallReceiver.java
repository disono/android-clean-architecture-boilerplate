package disono.webmons.com.utilities.library.VoIP.SIP;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.sip.SipAudioCall;
import android.net.sip.SipProfile;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import disono.webmons.com.clean_architecture.presentation.ui.activities.communication.voice.SIPService;
import disono.webmons.com.clean_architecture.presentation.ui.activities.communication.voice.WalkieTalkieActivity;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 9/26/2016 10:34 PM
 */

public class IncomingCallReceiver extends BroadcastReceiver {
    private final static String TAG = "IncomingCallReceiver:BR";
    public static SipAudioCall incomingCall = null;

    /**
     * Processes the incoming call, answers it, and hands it over to the
     * WalkieTalkieActivity.
     *
     * @param context The context under which the receiver is running.
     * @param intent  The intent being received.
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "Incoming Call...");

        try {
            // accept incoming call
            incomingCall = SIPService.manager.takeAudioCall(intent, new SipAudioCall.Listener() {
                @Override
                public void onRinging(SipAudioCall call, SipProfile caller) {

                }

                @Override
                public void onCallEnded(SipAudioCall call) {
                    _updateStatus(context, SIPService.ENDCALL, null, "Ready");
                }
            });

            // caller username
            String callerName = incomingCall.getPeerProfile().getUserName();

            // pass caller to service
            SIPService.call = incomingCall;

            // start activity to start accepting call
            Intent i = new Intent(context, WalkieTalkieActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.putExtra("state", SIPService.ACCEPTCALL);
            i.putExtra("status", "Answer Call");
            i.putExtra("calling", callerName + " is calling...");
            context.startActivity(i);
        } catch (Exception e) {
            Log.e(TAG, "onReceive: " + e.getMessage());

            if (incomingCall != null) {
                incomingCall.close();
            }
        }
    }

    /**
     * Broadcast update on view state
     *
     * @param context
     * @param buttonStatus
     * @param calling
     * @param status
     */
    private void _updateStatus(Context context, int buttonStatus, String calling, String status) {
        Intent i = new Intent(SIPService.ACTION_SERVICE);
        i.putExtra("state", buttonStatus);
        i.putExtra("calling", calling);
        i.putExtra("status", status);
        LocalBroadcastManager.getInstance(context).sendBroadcast(i);
    }
}
