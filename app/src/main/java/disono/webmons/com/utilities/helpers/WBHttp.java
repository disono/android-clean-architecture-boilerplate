package disono.webmons.com.utilities.helpers;

import android.content.Context;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 9/2/2016 2:05 PM
 */
public class WBHttp {

    /**
     * Download image then set on src
     *
     * @param context
     * @param source
     * @param imageView
     */
    public static void imgURLLoad(Context context, String source, CircleImageView imageView) {
        Picasso.with(context).load(source).into(imageView);
    }
}
