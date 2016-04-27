package disono.webmons.com.clean_architecture.presentation.presenters.listeners;

import android.content.DialogInterface;

import disono.webmons.com.clean_architecture.presentation.presenters.listeners.base.RootListenerDialog;

/**
 * Author: Archie, Disono (disono.apd@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Created at: 2016-04-13 03:20 PM
 */
public class DialogInterfaceFactory implements RootListenerDialog {
    @Override
    public DialogInterface.OnClickListener OnClick(DialogInterface.OnClickListener onClickListener) {
        return onClickListener;
    }
}
