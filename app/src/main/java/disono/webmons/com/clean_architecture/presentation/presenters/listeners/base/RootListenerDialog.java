package disono.webmons.com.clean_architecture.presentation.presenters.listeners.base;

import android.content.DialogInterface;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Created at: 2016-04-13 03:24 PM
 */
public interface RootListenerDialog {
    DialogInterface.OnClickListener OnClick(DialogInterface.OnClickListener onClickListener);
}
