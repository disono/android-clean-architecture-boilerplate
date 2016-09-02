package disono.webmons.com.clean_architecture.storage.services.authentication.adapter;

import disono.webmons.com.clean_architecture.storage.services.ServiceGenerator;
import disono.webmons.com.clean_architecture.storage.services.authentication.api.AuthAPI;
import disono.webmons.com.clean_architecture.storage.services.authentication.model.Me.MeModel;
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
    public Call<MeModel> register(String first_name, String last_name, String email,
                                  String password, String username) {
        AuthAPI authAPI = ServiceGenerator.createService(AuthAPI.class);

        return authAPI.register(first_name, last_name, email, password, username);
    }

    /**
     * Login
     *
     * @param email
     * @param password
     * @return
     */
    public Call<MeModel> login(String email, String password) {
        AuthAPI authAPI = ServiceGenerator.createService(AuthAPI.class);

        return authAPI.login(email, password);
    }

    /**
     * Forgot password
     *
     * @param email
     * @return
     */
    public Call forgot(String email) {
        AuthAPI authAPI = ServiceGenerator.createService(AuthAPI.class);

        return authAPI.forgot(email);
    }

    /**
     * Get the authenticated user
     *
     * @param id
     * @return
     */
    public Call<MeModel> me(int id) {
        AuthAPI authAPI = ServiceGenerator.createServiceToken(AuthAPI.class);

        return authAPI.me(id);
    }

    /**
     * Get the user token
     *
     * @param authenticated_id
     * @return
     */
    public Call<MeModel> tokenCreate(int authenticated_id) {
        AuthAPI authAPI = ServiceGenerator.createServiceToken(AuthAPI.class);

        return authAPI.tokenCreate(authenticated_id);
    }

    /**
     * Check the user token if valid
     *
     * @return
     */
    public Call<MeModel> tokenCheck() {
        AuthAPI authAPI = ServiceGenerator.createServiceToken(AuthAPI.class);

        return authAPI.tokenCheck();
    }
}
