package disono.webmons.com.clean_architecture.presentation.ui.activities.authenticate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.emmasuzuki.easyform.EasyTextInputLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import disono.webmons.com.clean_architecture.R;
import disono.webmons.com.clean_architecture.domain.executor.implementations.MainThreadImplement;
import disono.webmons.com.clean_architecture.domain.executor.implementations.ThreadExecutor;
import disono.webmons.com.clean_architecture.domain.models.MeModel;
import disono.webmons.com.clean_architecture.presentation.converters.Inputs;
import disono.webmons.com.clean_architecture.presentation.presenters.implementations.RegisterWatcher;
import disono.webmons.com.clean_architecture.presentation.presenters.interfaces.RegisterPresenter;
import disono.webmons.com.clean_architecture.presentation.ui.activities.MainActivity;
import disono.webmons.com.dependencies.ActivityBaseComponent;
import disono.webmons.com.utilities.animations.transitions.Sliders;
import disono.webmons.com.utilities.exception.WBConsole;
import disono.webmons.com.utilities.library.Dialogs.Sweet.WBAlerts;
import disono.webmons.com.utilities.ui.DialogFactory;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 2016-04-12 11:26 AM
 */
public class RegisterActivity extends AppCompatActivity implements RegisterPresenter.View {
    private final static String TAG = "RegisterActivity:Activity";
    Activity mActivity;
    private Inputs inputs = new Inputs();
    private SweetAlertDialog progressDialog;
    private RegisterPresenter registerPresenter;

    @BindView(R.id.edit_txt_register_first_name)
    EasyTextInputLayout edit_txt_register_first_name;

    @BindView(R.id.edit_txt_register_last_name)
    EasyTextInputLayout edit_txt_register_last_name;

    @BindView(R.id.edit_txt_register_email)
    EasyTextInputLayout edit_txt_register_email;

    @BindView(R.id.edit_txt_register_username)
    EasyTextInputLayout edit_txt_register_username;

    @BindView(R.id.edit_txt_register_password)
    EasyTextInputLayout edit_txt_register_password;

    @BindView(R.id.edit_txt_register_phone)
    EasyTextInputLayout edit_txt_register_phone;

    @BindView(R.id.edit_txt_register_address)
    EasyTextInputLayout edit_txt_register_address;

    @BindView(R.id.btn_register_submit)
    Button btn_register_submit;

    @Inject
    WBAlerts wbAlerts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // context
        mActivity = this;
        Sliders.enter(mActivity);

        ButterKnife.bind(this);

        ActivityBaseComponent.inject(this);
        ActivityBaseComponent.component().inject(this);

        registerPresenter = new RegisterWatcher(
                ThreadExecutor.getInstance(),
                MainThreadImplement.getInstance(),
                this,
                this.inputs
        );
    }

    @Override
    public void listeners() {
        btn_register_submit.setOnClickListener(view -> registerPresenter.submit());
    }

    @Override
    public void success(MeModel meModel) {
        if (meModel.email_confirmed == 1) {
            Intent intent = null;

            if (meModel.role.equals("client")) {
                intent = new Intent(mActivity, MainActivity.class);
            }

            if (intent != null) {
                startActivity(intent);
                finish();
            }
        } else {
            DialogFactory.confirm(mActivity, "Successfully Registered!",
                    "Please verify your email address to complete the registration.", (dialogInterface, i) -> {
                        new MeModel().single().clear();

                        // start the login
                        Intent intent = new Intent(mActivity, LoginActivity.class);
                        startActivity(intent);
                    });
        }
    }

    @Override
    public void submit() {
        this.inputs.setInput("first_name", edit_txt_register_first_name.getEditText().getText());
        this.inputs.setInput("last_name", edit_txt_register_last_name.getEditText().getText());
        this.inputs.setInput("phone", edit_txt_register_phone.getEditText().getText());
        this.inputs.setInput("address", edit_txt_register_address.getEditText().getText());
        this.inputs.setInput("username", edit_txt_register_username.getEditText().getText());
        this.inputs.setInput("email", edit_txt_register_email.getEditText().getText());
        this.inputs.setInput("password", edit_txt_register_password.getEditText().getText());
    }

    @Override
    public void showProgress() {
        progressDialog = wbAlerts.progress("Registering...", false);
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.cancel();
    }

    @Override
    public void showError(String message) {
        wbAlerts.error("Failed", message).show();
        WBConsole.e(TAG, message);
    }
}
