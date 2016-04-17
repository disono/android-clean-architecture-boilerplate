package disono.webmons.com.clean_architecture.presentation.ui.listeners.base;

import android.content.DialogInterface;

/**
 * Author: Archie, Disono
 * Website: www.webmons.com
 * License: Apache 2.0
 * Created at: 2016-04-13 03:24 PM
 */
public interface RootListenerDialog {
    DialogInterface.OnClickListener OnClick(DialogInterface.OnClickListener onClickListener);
}
