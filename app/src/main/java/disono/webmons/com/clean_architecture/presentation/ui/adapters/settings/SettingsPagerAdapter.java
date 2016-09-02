package disono.webmons.com.clean_architecture.presentation.ui.adapters.settings;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import disono.webmons.com.clean_architecture.presentation.ui.fragments.settings.GeneralSettingsFragment;
import disono.webmons.com.clean_architecture.presentation.ui.fragments.settings.SecuritySettingsFragment;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 9/2/2016 11:27 AM
 */
public class SettingsPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public SettingsPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new GeneralSettingsFragment();
            case 1:
                return new SecuritySettingsFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
