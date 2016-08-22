package disono.webmons.com.clean_architecture.data.services.auth.adapter;

import android.content.Context;

import disono.webmons.com.clean_architecture.R;
import disono.webmons.com.clean_architecture.data.services.auth.api.AuthAPI;
import disono.webmons.com.clean_architecture.data.services.auth.model.ForgotModel;
import disono.webmons.com.clean_architecture.data.services.auth.model.LoginModel;
import disono.webmons.com.clean_architecture.data.services.auth.model.RegisterModel;
import disono.webmons.com.clean_architecture.data.services.auth.model.UserModel;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 2016-05-05 07:23 PM
 */
public class AuthAdapter {
    private final String baseUrl;
    private final Context ctx;

    public AuthAdapter(Context ctx) {
        this.ctx = ctx;
        baseUrl = ctx.getResources().getString(R.string.http);
    }

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
    public Call<RegisterModel> register(String first_name, String last_name, String email,
                                        String password, String username) {
        Retrofit client = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AuthAPI authAPI = client.create(AuthAPI.class);

        return authAPI.register(first_name, last_name, email, password, username);
    }

    /**
     * Login
     *
     * @param email
     * @param password
     * @return
     */
    public Call<LoginModel> login(String email, String password) {
        Retrofit client = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AuthAPI authAPI = client.create(AuthAPI.class);

        return authAPI.login(email, password);
    }

    /**
     * Forgot password
     *
     * @param email
     * @return
     */
    public Call<ForgotModel> forgot(String email) {
        Retrofit client = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AuthAPI authAPI = client.create(AuthAPI.class);

        return authAPI.forgot(email);
    }

    /**
     * Get the authenticated user
     *
     * @param username
     * @param token
     * @return
     */
    public Call<UserModel> user(String username, String token) {
        Retrofit client = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AuthAPI authAPI = client.create(AuthAPI.class);

        return authAPI.user(username, token);
    }
}
