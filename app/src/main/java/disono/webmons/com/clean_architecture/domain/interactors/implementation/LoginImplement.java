package disono.webmons.com.clean_architecture.domain.interactors.implementation;

import disono.webmons.com.clean_architecture.domain.executor.Executor;
import disono.webmons.com.clean_architecture.domain.executor.MainThread;
import disono.webmons.com.clean_architecture.domain.interactors.base.AbstractInteractor;
import disono.webmons.com.clean_architecture.domain.interactors.blueprint.LoginInteractor;
import disono.webmons.com.clean_architecture.presentation.converters.Inputs;
import disono.webmons.com.clean_architecture.storage.services.ErrorUtils;
import disono.webmons.com.clean_architecture.storage.services.authentication.adapter.AuthAdapter;
import disono.webmons.com.clean_architecture.storage.services.authentication.model.Me.Data;
import disono.webmons.com.clean_architecture.storage.services.authentication.model.Me.MeModel;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 2016-05-04 09:48 PM
 */
public class LoginImplement extends AbstractInteractor implements LoginInteractor {
    private final String TAG = "LoginImplement:Class";

    private MainThread mMainThread;
    private LoginInteractor.Callback mCallback;
    private Inputs mInputs;

    public LoginImplement(Executor executor,
                          MainThread mainThread,
                          Callback callback,
                          Inputs inputs) {
        super(executor, mainThread);

        this.mMainThread = mainThread;
        this.mCallback = callback;
        this.mInputs = inputs;
    }

    @Override
    public void run() {
        mMainThread.post(() -> {
            // implement this with your business logic
            // Call API
            AuthAdapter authAdapter = new AuthAdapter();
            Call<MeModel> call = authAdapter.login(mInputs.getInput("email").toString(), mInputs.getInput("password").toString());
            call.enqueue(new retrofit2.Callback<MeModel>() {
                @Override
                public void onResponse(Call<MeModel> call, Response<MeModel> response) {
                    response(call, response);
                }

                @Override
                public void onFailure(Call<MeModel> call, Throwable t) {
                    mCallback.failed(t.getMessage());
                }
            });
        });
    }

    /**
     * Respond
     *
     * @param response
     */
    private void response(Call<MeModel> call, Response<MeModel> response) {
        if (response.isSuccessful() && response.body() != null) {
            Data data = response.body().getData();
            disono.webmons.com.clean_architecture.domain.model.MeModel meModel =
                    new disono.webmons.com.clean_architecture.domain.model.MeModel();

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
            meModel.save();

            // success
            mCallback.success(meModel);
        } else if (response.code() == 422) {
            mCallback.failed(ErrorUtils.converter(response.errorBody()));
        } else {
            mCallback.failed(response.code() + " : " + response.message());
        }
    }
}
