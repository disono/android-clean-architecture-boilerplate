package disono.webmons.com.clean_architecture.presentation.listeners;

import android.content.DialogInterface;

import disono.webmons.com.clean_architecture.presentation.listeners.base.RootListenerDialog;

/**
 * Author: Archie, Disono
 * Package: disono.webmons.com.clean_architecture.presentation.listeners
 * Created at: 2016-04-13 03:20 PM
 */
public class DialogInterfaceFactory implements RootListenerDialog {
    @Override
    public DialogInterface.OnClickListener OnClick(DialogInterface.OnClickListener onClickListener) {
        return onClickListener;
    }
}
