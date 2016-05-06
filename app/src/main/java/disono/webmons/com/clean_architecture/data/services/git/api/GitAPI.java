package disono.webmons.com.clean_architecture.data.services.git.api;

import disono.webmons.com.clean_architecture.data.services.git.model.GitModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Author: Archie, Disono (disono.apd@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Created at: 2016-05-06 01:09 PM
 */
public interface GitAPI {
    @GET("/users/{username}")
    Call<GitModel> getUser(@Path("username") String user);
}
