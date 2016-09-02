package disono.webmons.com.clean_architecture.presentation.ui.transitions;

import android.app.Activity;

import disono.webmons.com.clean_architecture.R;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 9/1/2016 7:07 PM
 */
public class Sliders {
    public static void enter(Activity activity) {
        activity.overridePendingTransition(R.anim.slide_leave, R.anim.slide_enter);
    }
}
