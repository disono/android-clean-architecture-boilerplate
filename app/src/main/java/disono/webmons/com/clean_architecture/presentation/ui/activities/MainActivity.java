package disono.webmons.com.clean_architecture.presentation.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import butterknife.ButterKnife;
import disono.webmons.com.clean_architecture.R;
import disono.webmons.com.clean_architecture.presentation.presenters.interfaces.MainPresenter;
import disono.webmons.com.clean_architecture.presentation.ui.activities.settings.SettingsActivity;
import disono.webmons.com.dependencies.ActivityBaseComponent;
import disono.webmons.com.utilities.animations.transitions.Sliders;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 2016-04-12 11:26 AM
 */
public class MainActivity extends AppCompatActivity implements MainPresenter.View {
    Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // activity
        mActivity = this;
        Sliders.enter(mActivity);

        ButterKnife.bind(this);

        ActivityBaseComponent.inject(this);
        ActivityBaseComponent.component().inject(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.default_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings_general:
                Intent intent = new Intent(mActivity, SettingsActivity.class);
                startActivity(intent);

                break;
            default:
                break;
        }

        return true;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
}
