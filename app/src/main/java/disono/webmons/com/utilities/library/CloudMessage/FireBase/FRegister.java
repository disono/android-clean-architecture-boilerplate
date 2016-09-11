package disono.webmons.com.utilities.library.CloudMessage.FireBase;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import disono.webmons.com.clean_architecture.domain.models.MeModel;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 9/10/2016 4:19 PM
 */
public class FRegister extends FirebaseInstanceIdService {
    private static final String TAG = "Register:Class";

    public FRegister() {
        super();
    }

    @Override
    public void onTokenRefresh() {
        MeModel meModel  = new MeModel();

        // send the registration if authenticated
        if (meModel.check()) {
            // Getting registration token
            String currentToken = FirebaseInstanceId.getInstance().getToken();

            // send this token to server
            Log.i(TAG, "FCM Token: " + currentToken);
        }
    }
}
