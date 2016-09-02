package disono.webmons.com.clean_architecture.utilities.sensor.Connection;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

import timber.log.Timber;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 8/22/2016 5:46 PM
 */
public class Network {
    private final String TAG = "Network:Class";
    private Activity activity;
    private Context ctx;

    public static final String WIFI = "wifi";
    public static final String WIMAX = "wimax";

    // mobile
    public static final String MOBILE = "mobile";

    // cellular
    public static final String CELLULAR = "cellular";

    // 2G network types
    public static final String TWO_G = "2g";
    public static final String GSM = "gsm";
    public static final String GPRS = "gprs";
    public static final String EDGE = "edge";

    // 3G network types
    public static final String THREE_G = "3g";
    public static final String CDMA = "cdma";
    public static final String UMTS = "umts";
    public static final String HSPA = "hspa";
    public static final String HSUPA = "hsupa";
    public static final String HSDPA = "hsdpa";
    public static final String ONEXRTT = "1xrtt";
    public static final String EHRPD = "ehrpd";

    // 4G network types
    public static final String FOUR_G = "4g";
    public static final String LTE = "lte";
    public static final String UMB = "umb";
    public static final String HSPA_PLUS = "hspa+";

    // return type
    public static final String TYPE_UNKNOWN = "unknown";
    public static final String TYPE_ETHERNET = "ethernet";
    public static final String TYPE_ETHERNET_SHORT = "eth";
    public static final String TYPE_WIFI = "wifi";
    public static final String TYPE_2G = "2g";
    public static final String TYPE_3G = "3g";
    public static final String TYPE_4G = "4g";
    public static final String TYPE_NONE = "none";

    ConnectivityManager connectivityManager;
    NetworkInfo info;

    public Network(Activity activity) {
        this.activity = activity;
        this.ctx = this.activity.getApplication();

        this.connectivityManager = (ConnectivityManager) this.activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        this.info = this.connectivityManager.getActiveNetworkInfo();
    }

    /**
     * Latest connection information
     *
     * @return
     */
    public JSONObject connectionInfo() {
        String type = TYPE_NONE;
        String extraInfo = "";

        if (info != null) {
            // if we are not connected to any network set type to none
            if (!this.info.isConnected()) {
                type = TYPE_NONE;
            } else {
                type = this.type(this.info);
            }

            extraInfo = this.info.getExtraInfo();
        }

        JSONObject connectionInfo = new JSONObject();

        try {
            connectionInfo.put("type", type);
            connectionInfo.put("info", extraInfo);
        } catch (JSONException e) {
            Timber.e("%s, Error: %s", TAG, e.getMessage());
        }

        return connectionInfo;
    }

    /**
     * Check for network connection
     *
     * @return
     */
    public boolean hasConnection() {
        if (this.info == null) {
            return false;
        }

        return this.info.isConnected();
    }

    /**
     * Type of connection
     *
     * @param info
     * @return
     */
    private String type(NetworkInfo info) {
        if (info != null) {
            String type = info.getTypeName().toLowerCase(Locale.US);

            if (type.equals(WIFI)) {
                return TYPE_WIFI;
            } else if (type.toLowerCase().equals(TYPE_ETHERNET) || type.toLowerCase().startsWith(TYPE_ETHERNET_SHORT)) {
                return TYPE_ETHERNET;
            } else if (type.equals(MOBILE) || type.equals(CELLULAR)) {
                type = info.getSubtypeName().toLowerCase(Locale.US);

                if (type.equals(GSM) ||
                        type.equals(GPRS) ||
                        type.equals(EDGE) ||
                        type.equals(TWO_G)) {

                    return TYPE_2G;
                } else if (type.startsWith(CDMA) ||
                        type.equals(UMTS) ||
                        type.equals(ONEXRTT) ||
                        type.equals(EHRPD) ||
                        type.equals(HSUPA) ||
                        type.equals(HSDPA) ||
                        type.equals(HSPA) ||
                        type.equals(THREE_G)) {

                    return TYPE_3G;
                } else if (type.equals(LTE) ||
                        type.equals(UMB) ||
                        type.equals(HSPA_PLUS) ||
                        type.equals(FOUR_G)) {

                    return TYPE_4G;
                }
            }
        } else {
            return TYPE_NONE;
        }

        return TYPE_UNKNOWN;
    }
}
