package disono.webmons.com.clean_architecture.storage.networks.services.authentication.adapter;

import disono.webmons.com.clean_architecture.storage.networks.ServiceGenerator;
import disono.webmons.com.clean_architecture.storage.networks.schema.Me.MeSchema;
import disono.webmons.com.clean_architecture.storage.networks.schema.SuccessSchema;
import disono.webmons.com.clean_architecture.storage.networks.services.authentication.api.AuthAPI;
import retrofit2.Call;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 2016-05-05 07:23 PM
 */
public class AuthAdapter {

    /**
     * Register
     *
     * @param first_name
     * @param last_name
     * @param email
     * @param password
     * @param username
     * @return
     */
    public Call<MeSchema> register(String first_name, String last_name, String email,
                                   String password, String username, String address, String phone, int social_id) {
        AuthAPI authAPI = ServiceGenerator.createService(AuthAPI.class);

        return authAPI.register(first_name, last_name, email, password, username, address, phone, social_id);
    }

    /**
     * Login
     *
     * @param email
     * @param password
     * @return
     */
    public Call<MeSchema> login(String email, String password) {
        AuthAPI authAPI = ServiceGenerator.createService(AuthAPI.class);

        return authAPI.login(email, password);
    }

    /**
     * Forgot password
     *
     * @param email
     * @return
     */
    public Call<SuccessSchema> forgot(String email) {
        AuthAPI authAPI = ServiceGenerator.createService(AuthAPI.class);

        return authAPI.forgot(email);
    }

    /**
     * Get the authenticated user
     *
     * @param id
     * @return
     */
    public Call<MeSchema> me(int id) {
        AuthAPI authAPI = ServiceGenerator.createServiceToken(AuthAPI.class);

        return authAPI.me(id);
    }

    /**
     * Get the user token
     *
     * @param authenticated_id
     * @return
     */
    public Call<MeSchema> tokenCreate(int authenticated_id) {
        AuthAPI authAPI = ServiceGenerator.createServiceToken(AuthAPI.class);

        return authAPI.tokenCreate(authenticated_id);
    }

    /**
     * Check the user token if valid
     *
     * @return
     */
    public Call<MeSchema> tokenCheck() {
        AuthAPI authAPI = ServiceGenerator.createServiceToken(AuthAPI.class);

        return authAPI.tokenCheck();
    }
}
