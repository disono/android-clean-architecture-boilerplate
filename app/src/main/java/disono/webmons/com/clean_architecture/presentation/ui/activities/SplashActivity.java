package disono.webmons.com.clean_architecture.presentation.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import disono.webmons.com.clean_architecture.R;
import disono.webmons.com.clean_architecture.domain.models.MeModel;
import disono.webmons.com.clean_architecture.presentation.ui.activities.authenticate.LoginActivity;
import me.wangyuwei.particleview.ParticleView;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 2016-04-25 11:26 AM
 */
public class SplashActivity extends AppCompatActivity {
    private Activity mActivity;

    // Splash screen timer
    private final static int SPLASH_TIME_OUT = 3000;
    private ParticleView particleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mActivity = this;

        // animtion text
        particleView = (ParticleView) findViewById(R.id.content_name);
        particleView.postDelayed(() -> particleView.startAnim(), 200);

        // start the activity
        new Handler().postDelayed(() -> {
            // This method will be executed once the timer is over
            // Start your app main activity
            // you can do a cache for your app or background server call while splash is loading
            MeModel meModel = new MeModel();
            Intent intent;

            if (meModel.check()) {
                intent = new Intent(mActivity, MainActivity.class);
            } else {
                intent = new Intent(mActivity, LoginActivity.class);
            }

            startActivity(intent);
            finish();
        }, SPLASH_TIME_OUT);
    }
}
