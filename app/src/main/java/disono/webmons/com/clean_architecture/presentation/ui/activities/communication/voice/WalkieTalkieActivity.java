package disono.webmons.com.clean_architecture.presentation.ui.activities.communication.voice;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.sip.SipAudioCall;
import android.net.sip.SipException;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import disono.webmons.com.clean_architecture.R;
import disono.webmons.com.utilities.library.VoIP.SIP.IncomingCallReceiver;

public class WalkieTalkieActivity extends AppCompatActivity {
    private final static String TAG = "WalkieTalkieActivity:AC";
    Activity mActivity;

    private static String calling = "Retreiving...";
    private static int state = SIPService.ENDCALL;
    private static String status = "Ready";

    @BindView(R.id.btn_call)
    Button btn_call;

    @BindView(R.id.btn_end_call)
    Button btn_end_call;

    @BindView(R.id.txt_view_calling_identity)
    TextView txt_view_calling_identity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_walkie_talkie);

        // turn on the screen
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        // activity
        mActivity = this;

        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            state = bundle.getInt("state");
            status = bundle.getString("status");
            calling = (bundle.getString("calling") != null) ? bundle.getString("calling") : "Retreiving...";

            // end calling if button state is zero
            if (state == SIPService.ENDCALL) {
                _endCall();
            }

            // button listeners
            _listeners();

            // state receiver
            _receiver();

            // view states
            _viewState();
        }
    }

    /**
     * Incoming calls handler
     */
    private void _receiver() {
        // received updates on service
        LocalBroadcastManager.getInstance(mActivity).registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent != null) {
                    state = intent.getIntExtra("state", SIPService.ENDCALL);
                    status = intent.getStringExtra("status");
                    calling = (intent.getStringExtra("status") != null) ? intent.getStringExtra("status") : "Retreiving...";

                    // end calling if button state is zero
                    if (state == 0) {
                        _endCall();
                    }

                    // update view state
                    _viewState();
                }
            }
        }, new IntentFilter(SIPService.ACTION_SERVICE));
    }

    /**
     * Button listeners
     */
    private void _listeners() {
        btn_end_call.setVisibility(View.GONE);

        // accept call
        btn_call.setOnClickListener(view -> {
            if (state == SIPService.ACCEPTCALL) {
                // answer incoming call
                _answerCall();
            } else if (state == SIPService.CALLACCEPTED) {
                // end current call
                state = SIPService.ENDCALL;
                _endCall();
            }

            // update view state
            _viewState();
        });

        // end call
        btn_end_call.setOnClickListener(view -> {
            // end current call
            state = SIPService.ENDCALL;
            _endCall();

            // update view state
            _viewState();
        });
    }

    /**
     * Answer the incoming call
     */
    private void _answerCall() {
        SipAudioCall incomingCall = IncomingCallReceiver.incomingCall;

        if (incomingCall != null) {
            try {
                incomingCall.answerCall(30);
                incomingCall.startAudio();
                incomingCall.setSpeakerMode(true);
                if (incomingCall.isMuted()) {
                    incomingCall.toggleMute();
                }

                SIPService.call = incomingCall;

                state = SIPService.CALLACCEPTED;
                status = "End Call";

                // update view state
                _viewState();
            } catch (SipException e) {
                Log.e(TAG, e.getMessage());
                _endCall();
            }
        }
    }

    /**
     * End current call
     */
    private void _endCall() {
        state = SIPService.ENDCALL;
        status = "Ready";

        SIPService.endCall();
        finish();
    }

    /**
     * Change button status
     */
    private void _viewState() {

        if (state == SIPService.ENDCALL) {
            // end curent call
            btn_call.setText(status);
            btn_call.setVisibility(View.VISIBLE);
            btn_end_call.setVisibility(View.GONE);
        } else if (state == SIPService.MAKECALL) {
            // make call
            btn_call.setText(status);
            btn_call.setVisibility(View.GONE);
            btn_end_call.setVisibility(View.VISIBLE);
        } else {
            // accept call
            btn_call.setText(status);
            btn_call.setVisibility(View.GONE);
            btn_end_call.setVisibility(View.VISIBLE);
        }

        // update person calling details
        txt_view_calling_identity.setText(calling);
    }
}
