package disono.webmons.com.clean_architecture.domain.interactors.implementation;

import java.io.File;

import disono.webmons.com.clean_architecture.domain.executor.Executor;
import disono.webmons.com.clean_architecture.domain.executor.MainThread;
import disono.webmons.com.clean_architecture.domain.interactors.base.AbstractInteractor;
import disono.webmons.com.clean_architecture.domain.interactors.blueprint.GeneralSettingsInteractor;
import disono.webmons.com.clean_architecture.presentation.converters.Inputs;
import disono.webmons.com.clean_architecture.storage.services.ErrorUtils;
import disono.webmons.com.clean_architecture.storage.services.authentication.model.Me.Data;
import disono.webmons.com.clean_architecture.storage.services.authentication.model.Me.MeModel;
import disono.webmons.com.clean_architecture.storage.services.settings.adapter.SettingsAdapter;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 9/2/2016 5:36 PM
 */
public class GeneralSettingsImplement extends AbstractInteractor implements GeneralSettingsInteractor {
    private final String TAG = "GeneralSettImp:Class";
    private MainThread mMainThread;
    private GeneralSettingsInteractor.Callback mCallback;
    private Inputs mInputs;

    public GeneralSettingsImplement(Executor threadExecutor, MainThread mainThread,
                                    GeneralSettingsInteractor.Callback callback, Inputs inputs) {
        super(threadExecutor, mainThread);

        this.mMainThread = mainThread;
        this.mCallback = callback;
        this.mInputs = inputs;
    }

    @Override
    public void run() {
        mMainThread.post(() -> {
            // first_name, last_name, address, about, phone, gender, birthday, image
            RequestBody first_name = RequestBody.create(
                    MediaType.parse("multipart/form-data"), this.mInputs.getInput("first_name").toString());
            RequestBody last_name = RequestBody.create(
                    MediaType.parse("multipart/form-data"), this.mInputs.getInput("last_name").toString());

            RequestBody address = (this.mInputs.getInput("address") != null) ? RequestBody.create(
                    MediaType.parse("multipart/form-data"), this.mInputs.getInput("address").toString()) : null;
            RequestBody about = (this.mInputs.getInput("about") != null) ? RequestBody.create(
                    MediaType.parse("multipart/form-data"), this.mInputs.getInput("about").toString()) : null;
            RequestBody phone = (this.mInputs.getInput("phone") != null) ? RequestBody.create(
                    MediaType.parse("multipart/form-data"), this.mInputs.getInput("phone").toString()) : null;
            RequestBody gender = (this.mInputs.getInput("gender") != null) ? RequestBody.create(
                    MediaType.parse("multipart/form-data"), this.mInputs.getInput("gender").toString()) : null;
            RequestBody birthday = (this.mInputs.getInput("birthday") != null) ? RequestBody.create(
                    MediaType.parse("multipart/form-data"), this.mInputs.getInput("birthday").toString()) : null;

            // image
            MultipartBody.Part image = null;
            if (this.mInputs.getInput("image") != null) {
                // use the FileUtils to get the actual file by uri
                File file = (File) this.mInputs.getInput("image");

                // create RequestBody instance from file
                RequestBody requestFile =
                        RequestBody.create(MediaType.parse("multipart/form-data"), file);

                // MultipartBody.Part is used to send also the actual file name
                image = MultipartBody.Part.createFormData("image", file.getName(), requestFile);
            }

            SettingsAdapter settingsAdapter = new SettingsAdapter();
            Call<MeModel> call = settingsAdapter.general(
                    first_name,
                    last_name,
                    address,
                    about,
                    phone,
                    gender,
                    birthday,
                    image
            );
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

            int primary_id = data.getId();
            disono.webmons.com.clean_architecture.domain.model.MeModel meModel =
                    new disono.webmons.com.clean_architecture.domain.model.MeModel().update(primary_id);

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
            meModel.update();

            // success
            mCallback.success(meModel);
        } else if (response.code() == 422) {
            mCallback.failed(ErrorUtils.converter(response.errorBody()));
        } else {
            mCallback.failed(response.code() + " : " + response.message());
        }
    }
}
