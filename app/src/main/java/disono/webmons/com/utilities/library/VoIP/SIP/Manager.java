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

import java.text.ParseException;

import timber.log.Timber;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 8/22/2016 5:23 PM
 */
public class Manager {
    private final String TAG = "Manager:Class";
    private Activity activity;
    private Context ctx;

    public SipManager mSipManager = null;
    public SipProfile mSipProfile = null;
    SipAudioCall sipAudioCall = null;

    public Manager(Activity activity) {
        this.activity = activity;
        ctx = this.activity.getApplication();

        if (mSipManager == null) {
            mSipManager = SipManager.newInstance(activity);
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
    public SipProfile register(String host, String username, String password, SipRegistrationListener sipRegistrationListener) {
        SipProfile.Builder builder;

        try {
            // create a SipProfile object
            builder = new SipProfile.Builder(username, host);
            builder.setPassword(password);
            mSipProfile = builder.build();

            // making calls and/or receiving generic SIP calls
            Intent intent = new Intent();
            intent.setAction("android.SipDemo.INCOMING_CALL");
            PendingIntent pendingIntent = PendingIntent.getBroadcast(ctx, 0, intent, Intent.FILL_IN_DATA);
            mSipManager.open(mSipProfile, pendingIntent, null);

            // tracks whether the SipProfile was successfully registered with your SIP service provider
            // https://developer.android.com/guide/topics/connectivity/sip.html
            mSipManager.setRegistrationListener(mSipProfile.getUriString(), sipRegistrationListener);
        } catch (ParseException | SipException e) {
            Timber.e("%s, Error: %s", TAG, e.getMessage());
        }

        return mSipProfile;
    }

    /**
     * Make an audio call
     */
    public void startCall(String sipAddress) {
        // set up a SipAudioCall.Listener. Much of the client's interaction with the SIP stack happens through listeners.
        SipAudioCall.Listener listener = new SipAudioCall.Listener() {
            @Override
            public void onCallEstablished(SipAudioCall call) {
                call.startAudio();
                call.setSpeakerMode(false);
                call.toggleMute();
            }

            @Override
            public void onCallEnded(SipAudioCall call) {

            }
        };

        try {
            this.sipAudioCall = mSipManager.makeAudioCall(mSipProfile.getUriString(), sipAddress, listener, 30);
        } catch (SipException e) {
            e.printStackTrace();
        }
    }

    /**
     * End an audio call
     */
    public void endCall() {
        if (this.sipAudioCall != null) {
            this.sipAudioCall.close();
            this.sipAudioCall = null;
        }
    }

    /**
     * If the application is done using a profile, it should close it to free associated objects
     * into memory and unregister the device from the server
     */
    public void close() {
        if (mSipManager == null) {
            return;
        }

        try {
            if (mSipProfile != null) {
                mSipManager.close(mSipProfile.getUriString());
            }
        } catch (Exception e) {
            Timber.e("%s, Error: Failed to close local profile, %s", TAG, e.getMessage());
        }
    }
}
