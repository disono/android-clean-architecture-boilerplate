package disono.webmons.com.utilities.helpers;

import android.content.Context;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 9/3/2016 4:15 PM
 */
public class WBForm {

    /**
     * Default spinner
     *
     * @param context
     * @param spinnerArray
     * @param spinner
     * @param onItemSelectedListener
     * @return
     */
    public static Spinner defaultSpinner(Context context, int spinnerArray, Spinner spinner,
                                         AdapterView.OnItemSelectedListener onItemSelectedListener) {
        // create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context,
                spinnerArray, android.R.layout.simple_spinner_item);

        // specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(onItemSelectedListener);

        return spinner;
    }
}
