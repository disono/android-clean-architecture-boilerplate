package disono.webmons.com.clean_architecture.presentation.ui.activities.settings;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import disono.webmons.com.clean_architecture.R;
import disono.webmons.com.clean_architecture.dependencies.ActivityBaseComponent;
import disono.webmons.com.clean_architecture.presentation.presenters.blueprint.SettingsPresenter;
import disono.webmons.com.clean_architecture.presentation.ui.adapters.settings.SettingsPagerAdapter;
import disono.webmons.com.clean_architecture.presentation.ui.transitions.Sliders;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 2016-04-12 11:26 AM
 */
public class SettingsActivity extends AppCompatActivity implements SettingsPresenter.View {
    private final String TAG = "SettingsAct:Activity";
    Activity mActivity;

    @BindView(R.id.tab_settings_layout)
    TabLayout tab_settings_layout;

    @BindView(R.id.pager_settings)
    ViewPager pager_settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // activity
        mActivity = this;
        Sliders.enter(mActivity);

        ButterKnife.bind(this);

        ActivityBaseComponent.inject(this);
        ActivityBaseComponent.component().inject(this);

        initTab();
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

    public void initTab() {
        tab_settings_layout.addTab(tab_settings_layout.newTab().setText("General"));
        tab_settings_layout.addTab(tab_settings_layout.newTab().setText("Security"));

        final SettingsPagerAdapter adapter = new SettingsPagerAdapter(getSupportFragmentManager(), tab_settings_layout.getTabCount());
        pager_settings.setAdapter(adapter);
        pager_settings.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab_settings_layout));
        tab_settings_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager_settings.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
