package disono.webmons.com.clean_architecture.presentation.ui.activities.authenticate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

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
import disono.webmons.com.clean_architecture.presentation.presenters.implementations.LoginWatcher;
import disono.webmons.com.clean_architecture.presentation.presenters.interfaces.LoginPresenter;
import disono.webmons.com.clean_architecture.presentation.ui.activities.MainActivity;
import disono.webmons.com.dependencies.ActivityBaseComponent;
import disono.webmons.com.utilities.animations.transitions.Sliders;
import disono.webmons.com.utilities.library.Dialogs.Sweet.WBAlerts;
import timber.log.Timber;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 2016-04-12 11:26 AM
 */
public class LoginActivity extends AppCompatActivity implements LoginPresenter.View {
    private final String TAG = "LoginActivity:Activity";
    private Activity mActivity;

    private LoginPresenter loginPresenter;
    private Inputs inputs = new Inputs();
    private SweetAlertDialog progressDialog;

    @BindView(R.id.btn_login_submit)
    Button btn_login_submit;

    @BindView(R.id.text_register)
    TextView text_register;

    @BindView(R.id.text_forgot)
    TextView text_forgot;

    @BindView(R.id.edit_txt_email)
    EasyTextInputLayout edit_txt_email;

    @BindView(R.id.edit_txt_password)
    EasyTextInputLayout edit_txt_password;

    @Inject
    WBAlerts wbAlerts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // context
        mActivity = this;
        Sliders.enter(mActivity);

        ButterKnife.bind(this);

        ActivityBaseComponent.inject(this);
        ActivityBaseComponent.component().inject(this);

        loginPresenter = new LoginWatcher(
                ThreadExecutor.getInstance(),
                MainThreadImplement.getInstance(),
                this,
                this.inputs
        );
    }

    @Override
    public void showProgress() {
        progressDialog = wbAlerts.progress("Authenticating...", false);
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.cancel();
    }

    @Override
    public void showError(String message) {
        wbAlerts.error("Login Failed", message).show();
        Timber.e("%s, Error: %s", TAG, message);
    }

    @Override
    public void dashboard(MeModel meModel) {
        Intent intent = null;

        if (meModel.role.equals("client")) {
            intent = new Intent(mActivity, MainActivity.class);
        }

        Timber.i("%s, Role: %s", TAG, meModel.role);
        if (intent != null) {
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void submit() {
        Intent intent = new Intent(mActivity, RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public void forgot() {
        Intent intent = new Intent(mActivity, ForgotPasswordActivity.class);
        startActivity(intent);
    }

    @Override
    public void listeners() {
        btn_login_submit.setOnClickListener(view -> loginPresenter.submit());
        text_register.setOnClickListener(view -> loginPresenter.register());
        text_forgot.setOnClickListener(view -> loginPresenter.forgot());
    }

    @Override
    public void forms() {
        inputs.setInput("email", edit_txt_email.getEditText().getText());
        inputs.setInput("password", edit_txt_password.getEditText().getText());
    }
}
