package disono.webmons.com.clean_architecture.presentation.ui.activities.authenticate;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;

import com.emmasuzuki.easyform.EasyTextInputLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import disono.webmons.com.clean_architecture.R;
import disono.webmons.com.clean_architecture.domain.executor.implementations.MainThreadImplement;
import disono.webmons.com.clean_architecture.domain.executor.implementations.ThreadExecutor;
import disono.webmons.com.clean_architecture.presentation.converters.Inputs;
import disono.webmons.com.clean_architecture.presentation.presenters.implementations.ForgotPasswordWatcher;
import disono.webmons.com.clean_architecture.presentation.presenters.interfaces.ForgotPasswordPresenter;
import disono.webmons.com.dependencies.ActivityBaseComponent;
import disono.webmons.com.utilities.animations.transitions.Sliders;
import disono.webmons.com.utilities.library.Dialogs.Sweet.WBAlerts;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 2016-04-12 11:26 AM
 */
public class ForgotPasswordActivity extends AppCompatActivity implements ForgotPasswordPresenter.View {
    private final static String TAG = "ForgotPasswordActivity:Activity";
    private Activity mActivity;
    private Inputs inputs = new Inputs();
    private SweetAlertDialog progressDialog;
    private ForgotPasswordPresenter forgotPasswordPresenter;

    @BindView(R.id.edit_txt_forgot_email)
    EasyTextInputLayout edit_txt_forgot_email;

    @BindView(R.id.btn_forgot_submit)
    AppCompatButton btn_forgot_submit;

    @Inject
    WBAlerts wbAlerts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        // context
        mActivity = this;
        Sliders.enter(mActivity);

        ButterKnife.bind(this);

        ActivityBaseComponent.inject(this);
        ActivityBaseComponent.component().inject(this);

        forgotPasswordPresenter = new ForgotPasswordWatcher(
                ThreadExecutor.getInstance(),
                MainThreadImplement.getInstance(),
                this,
                this.inputs
        );
    }

    @Override
    public void listeners() {
        btn_forgot_submit.setOnClickListener(view -> forgotPasswordPresenter.submit());
    }

    @Override
    public void success() {
        wbAlerts.success("Request Recieved", "Please check your email to verify your request.");
    }

    @Override
    public void submit() {
        this.inputs.setInput("email", edit_txt_forgot_email.getEditText().getText());
    }

    @Override
    public void showProgress() {
        progressDialog = wbAlerts.progress("Sending request...", false);
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.cancel();
    }

    @Override
    public void showError(String message) {
        wbAlerts.error("Failed", message).show();
    }
}
