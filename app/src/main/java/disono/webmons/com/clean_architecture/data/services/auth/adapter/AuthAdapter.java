package disono.webmons.com.clean_architecture.data.services.auth.adapter;


import android.content.Context;

import disono.webmons.com.clean_architecture.R;
import disono.webmons.com.clean_architecture.data.services.auth.api.AuthAPI;
import disono.webmons.com.clean_architecture.data.services.git.model.GitModel;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
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
    public Call<GitModel> register(String first_name, String last_name, String email,
                                   String password, String username) {
        Retrofit client = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AuthAPI gitAPI = client.create(AuthAPI.class);

        return gitAPI.register(first_name, last_name, email, password, username);
    }

    /**
     * Login
     *
     * @param email
     * @param password
     * @return
     */
    public Call<GitModel> login(String email, String password) {
        Retrofit client = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AuthAPI gitAPI = client.create(AuthAPI.class);

        return gitAPI.login(email, password);
    }

    public Call<GitModel> forgot(String email) {
        Retrofit client = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AuthAPI gitAPI = client.create(AuthAPI.class);

        return gitAPI.forgot(email);
    }

    /**
     * Get the authenticated user
     *
     * @param username
     * @param token
     * @return
     */
    public Call<GitModel> user(String username, String token) {
        Retrofit client = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AuthAPI gitAPI = client.create(AuthAPI.class);

        return gitAPI.user(username, token);
    }
}
