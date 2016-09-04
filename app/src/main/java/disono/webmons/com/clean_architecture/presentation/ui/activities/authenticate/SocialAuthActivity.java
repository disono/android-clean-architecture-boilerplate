package disono.webmons.com.clean_architecture.presentation.ui.activities.authenticate;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import disono.webmons.com.clean_architecture.R;
import disono.webmons.com.utilities.animations.transitions.Sliders;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 2016-04-12 11:26 AM
 */
public class SocialAuthActivity extends AppCompatActivity {
    private final String TAG = "SocialAuthActivity:Activity";
    Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_auth);

        // context
        mActivity = this;
        Sliders.enter(mActivity);
    }
}
