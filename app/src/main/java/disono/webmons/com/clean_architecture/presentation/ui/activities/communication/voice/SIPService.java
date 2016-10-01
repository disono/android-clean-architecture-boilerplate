package disono.webmons.com.clean_architecture.presentation.ui.activities.communication.voice;

import android.app.Activity;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.sip.SipAudioCall;
import android.net.sip.SipException;
import android.net.sip.SipManager;
import android.net.sip.SipProfile;
import android.net.sip.SipRegistrationListener;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.text.ParseException;

import disono.webmons.com.Configurations;
import disono.webmons.com.utilities.library.VoIP.SIP.IncomingCallReceiver;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 9/27/2016 9:37 AM
 */

public class SIPService extends Service {
    private final static String TAG = "SIPService:Service";

    public final static String ACTION_SERVICE = SIPService.class.getName();

    public static SipManager manager = null;
    public static SipProfile me = null;
    public static SipAudioCall call = null;
    public static IncomingCallReceiver callReceiver;

    public static int ENDCALL = 0;
    public static int ACCEPTCALL = 1;
    public static int CALLACCEPTED = 2;
    public static int MAKECALL = 3;

    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate");

        _setUpIncomingCall();
        _initializeManager();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand");
        return Service.START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind");
        return null;
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy");
        _destroySIP();
    }

    /**
     * Set up the intent filter.  This will be used to fire an
     * IncomingCallReceiver when someone calls the SIP address used by this application.
     */
    private void _setUpIncomingCall() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.SipDemo.INCOMING_CALL");
        callReceiver = new IncomingCallReceiver();
        registerReceiver(callReceiver, filter);
    }

    /**
     * When we get back from the preference setting Activity, assume
     * settings have changed, and re-login with new auth info.
     */
    private void _initializeManager() {
        if (manager == null) {
            manager = SipManager.newInstance(this);
        }

        _initializeLocalProfile();
    }

    /**
     * Logs you into your SIP provider, registering this device as the location to
     * send SIP calls to for your SIP address.
     */
    private void _initializeLocalProfile() {
        if (manager == null) {
            return;
        }

        // unregister profile to server
        if (me != null) {
            _closeLocalProfile();
        }

        // SIP authentication details
        String username = "";
        String password = "";
        String domain = Configurations.envString("sipDomain");

        // check if domain configuration is set
        if (domain != null) {
            return;
        }

        // always check the variables for values
        if (username.length() == 0 || domain.length() == 0 || password.length() == 0) {
            return;
        }

        try {
            SipProfile.Builder builder = new SipProfile.Builder(username, domain);
            builder.setPassword(password);
            me = builder.build();

            // intent for incoming calls
            Intent i = new Intent();
            i.setAction("android.SipDemo.INCOMING_CALL");
            PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, Intent.FILL_IN_DATA);
            manager.open(me, pi, null);

            // This listener must be added AFTER manager.open is called,
            // Otherwise the methods aren't guaranteed to fire.
            manager.setRegistrationListener(me.getUriString(), new SipRegistrationListener() {
                @Override
                public void onRegistering(String localProfileUri) {
                    Log.i(TAG, "_initializeLocalProfile:Registering with SIP Server...");
                }

                @Override
                public void onRegistrationDone(String localProfileUri, long expiryTime) {
                    Log.i(TAG, "_initializeLocalProfile:Ready");
                    _updateStatus(0, "Ready", null);
                }

                @Override
                public void onRegistrationFailed(String localProfileUri, int errorCode,
                                                 String errorMessage) {
                    Log.e(TAG, "_initializeLocalProfile:Registration failed: " + errorMessage);
                }
            });
        } catch (ParseException pe) {
            Log.e(TAG, "ParseException: " + pe.getMessage());
        } catch (SipException se) {
            Log.e(TAG, "SipException: " + se.getMessage());
        }
    }

    /**
     * Destroy SIP
     */
    private void _destroySIP() {
        // remove SIP profile
        _removeProfile();

        // end call
        endCall();

        // close profile
        _closeLocalProfile();

        // unregister to received calls
        if (callReceiver != null) {
            this.unregisterReceiver(callReceiver);
        }
    }

    /**
     * Remove profile
     */
    private void _removeProfile() {
        try {
            manager.unregister(me, new SipRegistrationListener() {
                @Override
                public void onRegistering(String s) {
                    Log.i(TAG, "_removeProfile:Uregistering with SIP Server...");
                }

                @Override
                public void onRegistrationDone(String s, long l) {
                    Log.i(TAG, "_removeProfile:Ready");
                }

                @Override
                public void onRegistrationFailed(String localProfileUri, int errorCode,
                                                 String errorMessage) {
                    Log.e(TAG, "_removeProfile:Registration failed: " + errorMessage);
                }
            });
        } catch (SipException e) {
            Log.e(TAG, "_removeProfile:Failed to close local profile: " + e.getMessage());
        }
    }

    /**
     * Closes out your local profile, freeing associated objects into memory
     * and unregistering your device from the server.
     */
    private void _closeLocalProfile() {
        if (manager == null) {
            return;
        }

        try {
            // close profile
            if (me != null) {
                manager.close(me.getUriString());
            }
        } catch (Exception e) {
            Log.e(TAG, "Failed to close local profile: " + e.getMessage());
        }
    }

    /**
     * Make an outgoing call.
     */
    public void initiateCall(Activity context, String to_call) {
        try {
            SipAudioCall.Listener listener = new SipAudioCall.Listener() {
                // Much of the client's interaction with the SIP Stack will
                // happen via listeners.  Even making an outgoing call, don't
                // forget to set up a listener to set things up once the call is established.
                @Override
                public void onCallEstablished(SipAudioCall call) {
                    call.startAudio();
                    call.setSpeakerMode(true);
                    call.toggleMute();
                }

                @Override
                public void onCallEnded(SipAudioCall call) {
                    Log.i(TAG, "Ending Call...");
                    _updateStatus(SIPService.ENDCALL, "Ready", null);
                }
            };

            call = manager.makeAudioCall(me.getUriString(), to_call, listener, 30);

            // start activity to start accepting call
            Intent i = new Intent(context, WalkieTalkieActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.putExtra("state", SIPService.MAKECALL);
            i.putExtra("status", "Calling...");
            i.putExtra("calling", "monster_man@sip.linphone.org");
            context.startActivity(i);
        } catch (Exception e) {
            Log.e(TAG, "Error when trying to make a call: " + e.getMessage());

            if (me != null) {
                try {
                    manager.close(me.getUriString());
                } catch (Exception ee) {
                    Log.e(TAG, "Error when trying to close manager: " + ee.getMessage());
                }
            }

            if (call != null) {
                call.close();
            }
        }
    }

    /**
     * End call
     */
    public static void endCall() {
        if (call != null) {
            try {
                Log.i(TAG, "Ending Call.");
                call.endCall();
            } catch (SipException se) {
                Log.e(TAG, "Error ending call.", se);
            }

            call.close();
        }
    }

    /**
     * Update status
     *
     * @param state
     * @param status
     * @param calling
     */
    private void _updateStatus(int state, String status, String calling) {
        Intent i = new Intent(ACTION_SERVICE);
        i.putExtra("state", state);
        i.putExtra("status", status);
        i.putExtra("calling", calling);
        LocalBroadcastManager.getInstance(this).sendBroadcast(i);
    }
}
