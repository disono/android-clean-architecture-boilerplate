package disono.webmons.com.utilities.library.VoIP.SIP;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.sip.SipAudioCall;
import android.net.sip.SipException;
import android.net.sip.SipManager;
import android.net.sip.SipProfile;
import android.net.sip.SipRegistrationListener;
import android.util.Log;

import java.text.ParseException;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 8/22/2016 5:23 PM
 */
public class SIPManager {
    private final static String TAG = "SIPManager:Class";
    private Activity mActivity;
    private Context ctx;
    
    public SipManager mSipManager = null;
    public SipProfile mSipProfile = null;
    public SipAudioCall mSipAudioCall = null;

    public SIPManager(Activity activity) {
        this.mActivity = activity;
        ctx = this.mActivity.getApplication();

        if (mSipManager == null) {
            mSipManager = SipManager.newInstance(this.mActivity);
        }
    }

    /**
     * Register SIP account
     *
     * @param host
     * @param username
     * @param password
     * @return
     */
    public SipProfile registerSIP(String host, String username, String password, SipRegistrationListener sipRegistrationListener) {
        SipProfile.Builder builder;

        try {
            // create a SipProfile object
            builder = new SipProfile.Builder(username, host);
            builder.setPassword(password);
            this.mSipProfile = builder.build();

            // making calls and/or receiving generic SIP calls
            Intent intent = new Intent();
            intent.setAction("android.SipDemo.INCOMING_CALL");
            PendingIntent pendingIntent = PendingIntent.getBroadcast(ctx, 0, intent, Intent.FILL_IN_DATA);
            mSipManager.open(this.mSipProfile, pendingIntent, null);

            // tracks whether the SipProfile was successfully registered with your SIP service provider
            // https://developer.android.com/guide/topics/connectivity/sip.html
            mSipManager.setRegistrationListener(this.mSipProfile.getUriString(), sipRegistrationListener);
        } catch (ParseException | SipException e) {
            Log.e(TAG, e.getMessage());
        }

        return this.mSipProfile;
    }

    /**
     * Make an audio call
     * Set up a SipAudioCall.Listener. Much of the client's interaction with the SIP stack happens through listeners.
     */
    public void startCallSIP(String sipAddress) {
        if (this.mSipProfile == null) {
            Log.e(TAG, "Unable to start a call error, you need to initialize SIP profile builder.");
            return;
        }

        SipAudioCall.Listener listener = new SipAudioCall.Listener() {
            @Override
            public void onCallEstablished(SipAudioCall call) {
                Log.i(TAG, "onCallEstablished");

                call.startAudio();
                call.setSpeakerMode(false);
                call.toggleMute();
            }

            @Override
            public void onCallEnded(SipAudioCall call) {
                // do something if call ended
                Log.i(TAG, "onCallEnded");
            }
        };

        try {
            this.mSipAudioCall = mSipManager.makeAudioCall(this.mSipProfile.getUriString(), sipAddress, listener, 300);
        } catch (SipException e) {
            Log.e(TAG, "Unable to start a call, " + e.getMessage());
        }
    }

    /**
     * End an audio call
     */
    public void endCallSIP() {
        if (this.mSipAudioCall != null) {
            this.mSipAudioCall.close();
            this.mSipAudioCall = null;
        }
    }

    /**
     * If the application is done using a profile, it should close it to free associated objects
     * into memory and unregister the device from the server
     */
    public void closeProfile() {
        if (mSipManager == null) {
            return;
        }

        try {
            if (mSipProfile != null) {
                mSipManager.close(mSipProfile.getUriString());
            }
        } catch (Exception e) {
            Log.e(TAG, "Failed to close local profile, " + e.getMessage());
        }
    }
}
