package disono.webmons.com.clean_architecture.storage.networks;

import disono.webmons.com.clean_architecture.domain.models.MeModel;
import disono.webmons.com.Configurations;
import disono.webmons.com.utilities.exception.WBConsole;
import disono.webmons.com.utilities.helpers.Encryption.JWT;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 8/31/2016 12:52 PM
 */
public class ServiceGenerator {
    private static final String TAG = "ServiceGenerator:Class";
    public static final String API_BASE_URL = Configurations.envString("baseURL");

    private static OkHttpClient.Builder httpClient;

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create());

    /**
     * Create service
     *
     * @param serviceClass
     * @param authToken
     * @param <S>
     * @return
     */
    public static <S> S createService(Class<S> serviceClass, final String authToken) {
        MeModel meModel = new MeModel();
        httpClient = new OkHttpClient.Builder();

        // add http headers
        if (authToken != null && meModel.check() &&
                meModel.single().token_key != null && !meModel.single().token_key.equals("") &&
                meModel.single().primary_id > 0 && !Integer.toString(meModel.single().primary_id).equals("")) {
            _httpClient(authToken, meModel);
        }

        OkHttpClient client = httpClient.build();
        Retrofit retrofit = builder.client(client).build();
        return retrofit.create(serviceClass);
    }

    /**
     * Create service without token
     *
     * @param serviceClass
     * @param <S>
     * @return
     */
    public static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, null);
    }

    /**
     * Create service with token
     *
     * @param serviceClass
     * @param <S>
     * @return
     */
    public static <S> S createServiceToken(Class<S> serviceClass) {
        return createService(serviceClass, JWT.generateToken());
    }

    /**
     * Create http header
     *
     * @param authToken
     * @param meModel
     */
    private static void _httpClient(String authToken, MeModel meModel) {
        try {
            // JWT token
            httpClient.addInterceptor(chain -> {
                Request original = chain.request();

                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder()
                        .header("Authorization", "Bearer " + authToken)
                        .method(original.method(), original.body());

                Request request = requestBuilder.build();
                return chain.proceed(request);
            });

            // users id
            httpClient.addInterceptor(chain -> {
                Request original = chain.request();

                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder()
                        .header("authenticated_id", Integer.toString(meModel.single().primary_id))
                        .method(original.method(), original.body());

                Request request = requestBuilder.build();
                return chain.proceed(request);
            });

            // token key
            httpClient.addInterceptor(chain -> {
                Request original = chain.request();

                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder()
                        .header("token_key", meModel.single().token_key)
                        .method(original.method(), original.body());

                Request request = requestBuilder.build();
                return chain.proceed(request);
            });
        } catch (NullPointerException e) {
            WBConsole.e(TAG, e.getMessage());
        }
    }
}
