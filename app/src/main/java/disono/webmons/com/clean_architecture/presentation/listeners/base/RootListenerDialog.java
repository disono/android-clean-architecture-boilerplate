package disono.webmons.com.clean_architecture.presentation.listeners.base;

import android.content.DialogInterface;

/**
 * Author: Archie, Disono
 * Package: disono.webmons.com.clean_architecture.presentation.listeners.base
 * Created at: 2016-04-13 03:24 PM
 */
public interface RootListenerDialog {
    DialogInterface.OnClickListener OnClick(DialogInterface.OnClickListener onClickListener);
}
