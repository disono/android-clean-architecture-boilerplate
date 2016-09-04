package disono.webmons.com.clean_architecture.storage.networks;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

import disono.webmons.com.utilities.exception.WBConsole;
import okhttp3.ResponseBody;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 8/31/2016 12:49 PM
 */
public class ErrorUtils {
    private final static String TAG = "ErrorUtils:Class";

    public static String converter(ResponseBody errorBody) {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(errorBody.byteStream()));
        String line;

        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            WBConsole.e(TAG, e.getMessage());
        }

        try {
            JSONObject mapper = new JSONObject(sb.toString());

            if (mapper.optJSONObject("errors") != null) {
                JSONObject object = mapper.getJSONObject("errors");
                String errors = "";

                Iterator x = object.keys();
                JSONArray jsonArray = new JSONArray();

                while (x.hasNext()) {
                    String key = (String) x.next();
                    jsonArray.put(object.get(key));
                }

                for (int i = 0; i < jsonArray.length(); i++) {
                    errors += ((i > 0) ? "\n" : "") + "- " + jsonArray.getString(i);
                }

                return errors;
            }

            return mapper.getString("errors");
        } catch (JSONException e) {
            WBConsole.e(TAG, e.getMessage());
        }

        return null;
    }
}
