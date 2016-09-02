package disono.webmons.com.clean_architecture.storage;

import disono.webmons.com.clean_architecture.utilities.helpers.WBSecurity;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 8/24/2016 6:51 PM
 */
public class Configurations {
    private final static String _baseURL = "http://your-domain/api/v1/";
    private final static String _JWT_SECRET = "";

    // token expiry date (minutes)
    private final static int _JWT_Exp = 10;
    //  earliest point in time that the token can be used (minutes)
    private final static int _JWT_Nbf = 10;

    /**
     * String values
     *
     * @param key
     * @return
     */
    public static String envString(String key) {
        if (key.equals("baseURL")) {
            return _baseURL;
        } else if (key.equals("JWT_SECRET")) {
            return WBSecurity.encodeBase64(_JWT_SECRET);
        }

        return null;
    }

    /**
     * Integer values
     *
     * @param key
     * @return
     */
    public static int envInt(String key) {
        if (key.equals("JWT_Exp")) {
            return _JWT_Exp;
        } else if (key.equals("JWT_Nbf")) {
            return _JWT_Nbf;
        }

        return 0;
    }
}
