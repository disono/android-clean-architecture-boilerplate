package disono.webmons.com.clean_architecture.storage.networks.services.settings.adapter;

import disono.webmons.com.clean_architecture.storage.networks.ServiceGenerator;
import disono.webmons.com.clean_architecture.storage.networks.schema.Me.MeSchema;
import disono.webmons.com.clean_architecture.storage.networks.services.settings.api.SettingsAPI;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 2016-05-05 07:23 PM
 */
public class SettingsAdapter {

    /**
     * General
     *
     * @param first_name
     * @param last_name
     * @param address
     * @param about
     * @param phone
     * @param gender
     * @param birthday
     * @return
     */
    public Call<MeSchema> general(RequestBody first_name, RequestBody last_name,
                                  RequestBody address, RequestBody about, RequestBody phone,
                                  RequestBody gender, RequestBody birthday, MultipartBody.Part image) {
        SettingsAPI settingsAPI = ServiceGenerator.createServiceToken(SettingsAPI.class);

        return settingsAPI.generalSettings(first_name, last_name, address, about, phone, gender,
                birthday, image);
    }

    /**
     * Security
     *
     * @param email
     * @param current_password
     * @param password
     * @return
     */
    public Call<MeSchema> security(String email, String current_password, String password) {
        SettingsAPI settingsAPI = ServiceGenerator.createServiceToken(SettingsAPI.class);

        return settingsAPI.securitySettings(email, current_password, password);
    }
}
