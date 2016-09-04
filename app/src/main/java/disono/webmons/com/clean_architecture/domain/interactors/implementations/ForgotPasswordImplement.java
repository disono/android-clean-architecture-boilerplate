package disono.webmons.com.clean_architecture.domain.interactors.implementations;

import disono.webmons.com.clean_architecture.domain.executor.interfaces.Executor;
import disono.webmons.com.clean_architecture.domain.executor.interfaces.MainThread;
import disono.webmons.com.clean_architecture.domain.interactors.base.AbstractInteractor;
import disono.webmons.com.clean_architecture.domain.interactors.interfaces.ForgotPasswordContract;
import disono.webmons.com.clean_architecture.presentation.converters.Inputs;
import disono.webmons.com.clean_architecture.storage.networks.ErrorUtils;
import disono.webmons.com.clean_architecture.storage.networks.schema.SuccessSchema;
import disono.webmons.com.clean_architecture.storage.networks.services.authentication.adapter.AuthAdapter;
import disono.webmons.com.utilities.exception.WBConsole;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 9/4/2016 10:56 AM
 */
public class ForgotPasswordImplement extends AbstractInteractor implements ForgotPasswordContract {
    private final static String TAG = "ForgotPasswordImplement:Class";

    private ForgotPasswordContract.Callback mCallback;
    private MainThread mMainThread;
    private Inputs mInputs;

    public ForgotPasswordImplement(Executor threadExecutor, MainThread mainThread,
                                   ForgotPasswordContract.Callback callback, Inputs inputs) {
        super(threadExecutor, mainThread);

        this.mMainThread = mainThread;
        this.mCallback = callback;
        this.mInputs = inputs;
    }

    @Override
    public void run() {
        mMainThread.post(() -> {
            String email = (mInputs.getInput("email") != null) ? mInputs.getInput("email").toString() : null;

            AuthAdapter authAdapter = new AuthAdapter();
            Call<SuccessSchema> call = authAdapter.forgot(email);
            call.enqueue(new retrofit2.Callback<SuccessSchema>() {
                @Override
                public void onResponse(Call<SuccessSchema> call, Response<SuccessSchema> response) {
                    response(call, response);
                }

                @Override
                public void onFailure(Call<SuccessSchema> call, Throwable t) {
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
    private void response(Call<SuccessSchema> call, Response<SuccessSchema> response) {
        if (response.isSuccessful() && response.body() != null) {
            // success
            mCallback.success();
        } else if (response.code() == 422) {
            mCallback.failed(ErrorUtils.converter(response.errorBody()));
        } else {
            String error = response.code() + " : " + response.message();
            mCallback.failed(error);
            WBConsole.e(TAG, error);
        }
    }
}
