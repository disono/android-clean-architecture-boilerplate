package disono.webmons.com.clean_architecture.data.services.auth.api;

import disono.webmons.com.clean_architecture.data.services.git.model.GitModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Author: Archie, Disono (disono.apd@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Created at: 2016-05-05 12:43 PM
 */
public interface AuthAPI {
    @POST("/auth/register")
    Call<GitModel> register(@Field("first_name") String first_name, @Field("last_name") String last_name,
                            @Field("email") String email, @Field("password") String password,
                            @Field("username") String username);

    @POST("/auth/login")
    Call<GitModel> login(@Field("email") String email, @Field("password") String password);

    @POST("/auth/forgot")
    Call<GitModel> forgot(@Field("email") String email);

    @POST("/auth/user/{username}")
    Call<GitModel> user(@Path("username") String user, @Field("token") String token);
}
