package disono.webmons.com.clean_architecture.domain.interactors.implementations;

import disono.webmons.com.clean_architecture.domain.executor.interfaces.Executor;
import disono.webmons.com.clean_architecture.domain.executor.interfaces.MainThread;
import disono.webmons.com.clean_architecture.domain.interactors.base.AbstractInteractor;
import disono.webmons.com.clean_architecture.domain.interactors.interfaces.SecuritySettingsContract;
import disono.webmons.com.clean_architecture.domain.models.MeModel;
import disono.webmons.com.clean_architecture.presentation.converters.Inputs;
import disono.webmons.com.clean_architecture.storage.networks.ErrorUtils;
import disono.webmons.com.clean_architecture.storage.networks.schema.Me.Data;
import disono.webmons.com.clean_architecture.storage.networks.schema.Me.MeSchema;
import disono.webmons.com.clean_architecture.storage.networks.services.settings.adapter.SettingsAdapter;
import disono.webmons.com.utilities.exception.WBConsole;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 9/3/2016 6:28 PM
 */
public class SecuritySettingsImplement extends AbstractInteractor implements SecuritySettingsContract {
    private final static String TAG = "SecuritySettingsImplement:Class";

    private SecuritySettingsContract.Callback mCallback;
    private MainThread mMainThread;
    private Inputs mInputs;

    public SecuritySettingsImplement(Executor threadExecutor, MainThread mainThread,
                                     SecuritySettingsContract.Callback callback, Inputs inputs) {
        super(threadExecutor, mainThread);

        this.mMainThread = mainThread;
        this.mCallback = callback;
        this.mInputs = inputs;
    }

    @Override
    public void run() {
        mMainThread.post(() -> {
            String email = (mInputs.getInput("email") != null) ? mInputs.getInput("email").toString() : null;
            String current_password = (mInputs.getInput("current_password") != null) ? mInputs.getInput("current_password").toString() : null;
            String password = (mInputs.getInput("password") != null) ? mInputs.getInput("password").toString() : null;

            SettingsAdapter settingsAdapter = new SettingsAdapter();
            Call<MeSchema> call = settingsAdapter.security(email, current_password, password);
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

            int primary_id = data.getId();
            MeModel meModel = new MeModel().update(primary_id);

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

            meModel.update();

            // success
            mCallback.success(meModel);
        } else if (response.code() == 422) {
            mCallback.failed(ErrorUtils.converter(response.errorBody()));
        } else {
            String error = response.code() + " : " + response.message();
            mCallback.failed(error);
            WBConsole.e(TAG, error);
        }
    }
}
