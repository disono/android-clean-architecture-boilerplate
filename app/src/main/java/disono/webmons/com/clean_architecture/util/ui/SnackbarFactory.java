package disono.webmons.com.clean_architecture.util.ui;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Author: Archie, Disono (disono.apd@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Created at: 2016-04-13 12:03 PM
 */
public class SnackbarFactory {
    public static Snackbar message(Context context, View view, String message) {
        return Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
    }
}
