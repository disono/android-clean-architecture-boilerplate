package disono.webmons.com.clean_architecture.domain.interactors.implementations;

import disono.webmons.com.clean_architecture.domain.executor.interfaces.Executor;
import disono.webmons.com.clean_architecture.domain.executor.interfaces.MainThread;
import disono.webmons.com.clean_architecture.domain.interactors.base.AbstractInteractor;
import disono.webmons.com.clean_architecture.domain.interactors.interfaces.RegisterContract;
import disono.webmons.com.clean_architecture.domain.models.MeModel;
import disono.webmons.com.clean_architecture.presentation.converters.Inputs;
import disono.webmons.com.clean_architecture.storage.networks.ErrorUtils;
import disono.webmons.com.clean_architecture.storage.networks.schema.Me.Data;
import disono.webmons.com.clean_architecture.storage.networks.schema.Me.MeSchema;
import disono.webmons.com.clean_architecture.storage.networks.services.authentication.adapter.AuthAdapter;
import disono.webmons.com.utilities.exception.WBConsole;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 9/3/2016 10:55 PM
 */
public class RegisterImplement extends AbstractInteractor implements RegisterContract {
    private final static String TAG = "RegisterImplement:Class";

    private RegisterContract.Callback mCallback;
    private MainThread mMainThread;
    private Inputs mInputs;

    public RegisterImplement(Executor threadExecutor, MainThread mainThread,
                             RegisterContract.Callback callback, Inputs inputs) {
        super(threadExecutor, mainThread);

        this.mMainThread = mainThread;
        this.mCallback = callback;
        this.mInputs = inputs;
    }

    @Override
    public void run() {
        mMainThread.post(() -> {
            String first_name = (mInputs.getInput("first_name") != null) ? mInputs.getInput("first_name").toString() : null;
            String last_name = (mInputs.getInput("last_name") != null) ? mInputs.getInput("last_name").toString() : null;
            String email = (mInputs.getInput("email") != null) ? mInputs.getInput("email").toString() : null;
            String password = (mInputs.getInput("password") != null) ? mInputs.getInput("password").toString() : null;
            String username = (mInputs.getInput("username") != null) ? mInputs.getInput("username").toString() : null;
            String address = (mInputs.getInput("address") != null) ? mInputs.getInput("address").toString() : null;
            String phone = (mInputs.getInput("phone") != null) ? mInputs.getInput("phone").toString() : null;
            int social_id = (mInputs.getInput("social_id") != null) ? (int) mInputs.getInput("social_id") : 0;

            AuthAdapter authAdapter = new AuthAdapter();
            Call<MeSchema> call = authAdapter.register(first_name, last_name, email,
                    password, username, address, phone, social_id);
            call.enqueue(new retrofit2.Callback<MeSchema>() {
                @Override
                public void onResponse(Call<MeSchema> call, Response<MeSchema> response) {
                    response(call, response);
                }

                @Override
                public void onFailure(Call<MeSchema> call, Throwable t) {
                    mCallback.failed(t.getMessage());
                    WBConsole.e(TAG, t.getMessage());
                }
            });
        });
    }

    /**
     * Respond
     *
     * @param response
     */
    private void response(Call<MeSchema> call, Response<MeSchema> response) {
        if (response.isSuccessful() && response.body() != null) {
            Data data = response.body().getData();

            if (data.getEnabled() == 1) {
                MeModel meModel = new MeModel();

                meModel.primary_id = data.getId();
                meModel.first_name = data.getFirstName();
                meModel.middle_name = data.getMiddleName();
                meModel.last_name = data.getLastName();
                meModel.full_name = data.getFullName();
                meModel.avatar = data.getAvatar();

                meModel.address = data.getAddress();
                meModel.birthday = data.getBirthday();
                meModel.phone = data.getPhone();
                meModel.gender = data.getGender();

                meModel.email = data.getEmail();
                meModel.username = data.getUsername();
                meModel.role = data.getRole();

                meModel.age = data.getAge();

                meModel.email_confirmed = data.getEmailConfirmed();
                meModel.enabled = data.getEnabled();

                meModel.secret_key = data.getSecretKey();
                meModel.token_key = data.getTokenKey();

                if (data.getEmailConfirmed() == 1) {
                    meModel.save();
                }

                // success
                mCallback.success(meModel);
            } else {
                mCallback.failed("Your account is disabled, please contact us.");
            }
        } else if (response.code() == 422) {
            mCallback.failed(ErrorUtils.converter(response.errorBody()));
        } else {
            String error = response.code() + " : " + response.message();
            mCallback.failed(error);
            WBConsole.e(TAG, error);
        }
    }
}
