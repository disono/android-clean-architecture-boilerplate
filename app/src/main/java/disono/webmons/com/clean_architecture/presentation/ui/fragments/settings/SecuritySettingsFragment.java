package disono.webmons.com.clean_architecture.presentation.ui.fragments.settings;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import disono.webmons.com.clean_architecture.presentation.presenters.implementations.SecuritySettingsWatcher;
import disono.webmons.com.clean_architecture.presentation.presenters.interfaces.SecuritySettingsPresenter;
import disono.webmons.com.dependencies.ActivityBaseComponent;
import disono.webmons.com.utilities.exception.WBConsole;
import disono.webmons.com.utilities.library.Dialogs.Sweet.WBAlerts;
import disono.webmons.com.utilities.ui.ToastFactory;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 2016-04-12 11:26 AM
 */
public class SecuritySettingsFragment extends Fragment implements SecuritySettingsPresenter.View {
    private final static String TAG = "SecuritySettingsFragment:Fragment";
    private Activity mActivity;
    private SecuritySettingsPresenter securitySettingsPresenter;
    private Inputs inputs = new Inputs();
    private SweetAlertDialog progressDialog;

    @Inject
    WBAlerts wbAlerts;

    @BindView(R.id.edit_txt_security_email)
    EasyTextInputLayout edit_txt_security_email;

    @BindView(R.id.edit_txt_security_current_pass)
    EasyTextInputLayout edit_txt_security_current_pass;

    @BindView(R.id.edit_txt_security_new_pass)
    EasyTextInputLayout edit_txt_security_new_pass;

    @BindView(R.id.btn_security_save)
    Button btn_security_save;

    public SecuritySettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_security_settings, container, false);
        ButterKnife.bind(this, view);

        ActivityBaseComponent.inject(mActivity);
        ActivityBaseComponent.component().inject(this);

        securitySettingsPresenter = new SecuritySettingsWatcher(
                ThreadExecutor.getInstance(),
                MainThreadImplement.getInstance(),
                this,
                this.inputs
        );

        return view;
    }

    @Override
    public void listeners() {
        btn_security_save.setOnClickListener(view -> securitySettingsPresenter.submit());
    }

    @Override
    public void setValues() {
        final MeModel meModel = new MeModel().single();

        edit_txt_security_email.getEditText().setText(meModel.email);
    }

    @Override
    public void success() {
        ToastFactory.show(mActivity.getApplicationContext(), "Profile updated successfully.");
    }

    @Override
    public void submit() {
        this.inputs.setInput("email", edit_txt_security_email.getEditText().getText());
        this.inputs.setInput("current_password", edit_txt_security_current_pass.getEditText().getText());
        this.inputs.setInput("password", edit_txt_security_new_pass.getEditText().getText());
    }

    @Override
    public void showProgress() {
        progressDialog = wbAlerts.progress("Saving Changes...", false);
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.cancel();
    }

    @Override
    public void showError(String message) {
        wbAlerts.error("Update Failed", message).show();
        WBConsole.e(TAG, message);
    }
}
