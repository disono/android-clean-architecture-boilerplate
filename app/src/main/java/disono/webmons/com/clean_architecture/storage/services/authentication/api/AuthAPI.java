package disono.webmons.com.clean_architecture.storage.services.authentication.api;

import disono.webmons.com.clean_architecture.storage.services.authentication.model.Me.MeModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 2016-05-05 12:43 PM
 */
public interface AuthAPI {
    @FormUrlEncoded
    @POST("auth/register")
    Call<MeModel> register(@Field("first_name") String first_name, @Field("last_name") String last_name,
                           @Field("email") String email, @Field("password") String password,
                           @Field("username") String username);

    @FormUrlEncoded
    @POST("auth/login")
    Call<MeModel> login(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("auth/forgot")
    Call forgot(@Field("email") String email);

    @GET("auth/user/{id}")
    Call<MeModel> me(@Path("id") int id);

    @GET("auth/user/token/create")
    Call<MeModel> tokenCreate(@Path("authenticated_id") int authenticated_id);

    @GET("auth/user/token/check")
    Call<MeModel> tokenCheck();
}
