package disono.webmons.com.clean_architecture.storage.services.settings.api;

import disono.webmons.com.clean_architecture.storage.services.authentication.model.Me.MeModel;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 2016-05-05 12:43 PM
 */
public interface SettingsAPI {

    // first_name, last_name, address, about, phone, gender, birthday, image
    @Multipart
    @POST("user/update/setting")
    Call<MeModel> generalSettings(@Part("first_name") RequestBody first_name, @Part("last_name") RequestBody last_name,
                                  @Part("address") RequestBody address, @Part("about") RequestBody about,
                                  @Part("phone") RequestBody phone, @Part("gender") RequestBody gender,
                                  @Part("birthday") RequestBody birthday, @Part MultipartBody.Part image);

    @FormUrlEncoded
    @POST("user/update/security")
    Call<MeModel> securitySettings(@Field("email") String email,
                                   @Field("current_password") String current_password,
                                   @Field("password") String password);
}
