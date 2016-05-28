package disono.webmons.com.clean_architecture.data.services.git.adapter;

import disono.webmons.com.clean_architecture.data.services.git.api.GitAPI;
import disono.webmons.com.clean_architecture.data.services.git.model.GitModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Created at: 2016-05-06 01:09 PM
 */
public class GitAdapter {
    private final String baseUrl = "https://api.github.com";

    public Call<GitModel> getUser(String username) {
        Retrofit client = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GitAPI gitAPI = client.create(GitAPI.class);

        return gitAPI.getUser(username);
    }
}
