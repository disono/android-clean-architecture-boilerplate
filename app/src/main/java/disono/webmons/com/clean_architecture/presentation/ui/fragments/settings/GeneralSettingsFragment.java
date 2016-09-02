package disono.webmons.com.clean_architecture.presentation.ui.fragments.settings;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.emmasuzuki.easyform.EasyTextInputLayout;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.io.File;
import java.util.Calendar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;
import disono.webmons.com.clean_architecture.R;
import disono.webmons.com.clean_architecture.dependencies.ActivityBaseComponent;
import disono.webmons.com.clean_architecture.domain.executor.MainThreadImplement;
import disono.webmons.com.clean_architecture.domain.executor.implement.ThreadExecutor;
import disono.webmons.com.clean_architecture.domain.model.MeModel;
import disono.webmons.com.clean_architecture.presentation.converters.Inputs;
import disono.webmons.com.clean_architecture.presentation.presenters.blueprint.GeneralSettingsPresenter;
import disono.webmons.com.clean_architecture.presentation.presenters.implementation.GeneralSettingsImplement;
import disono.webmons.com.clean_architecture.presentation.ui.activities.authenticate.LoginActivity;
import disono.webmons.com.clean_architecture.utilities.helpers.WBFile;
import disono.webmons.com.clean_architecture.utilities.helpers.WBHttp;
import disono.webmons.com.clean_architecture.utilities.helpers.WBTime;
import disono.webmons.com.clean_architecture.utilities.library.Dialogs.Sweet.WBAlerts;
import disono.webmons.com.clean_architecture.utilities.sensor.Camera.Launcher;
import disono.webmons.com.clean_architecture.utilities.sensor.Connection.Network;
import disono.webmons.com.clean_architecture.utilities.ui.DialogFactory;
import disono.webmons.com.clean_architecture.utilities.ui.ToastFactory;
import timber.log.Timber;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 2016-04-12 11:26 AM
 */
public class GeneralSettingsFragment extends Fragment implements GeneralSettingsPresenter.View, DatePickerDialog.OnDateSetListener {
    private final String TAG = "GeneralSettingsFragment:Fragment";
    private Activity mActivity;

    @Inject
    Launcher launcher;
    private int REQUEST_IMAGE_CAPTURE;
    private GeneralSettingsPresenter generalSettingsPresenter;
    private Inputs inputs = new Inputs();
    private SweetAlertDialog progressDialog;
    private File uriImage;

    @Inject
    Network network;

    @BindView(R.id.img_avatar)
    CircleImageView img_avatar;

    @BindView(R.id.btn_general_update)
    Button btn_general_update;

    @BindView(R.id.edit_txt_first_name)
    EasyTextInputLayout edit_txt_first_name;

    @BindView(R.id.edit_txt_last_name)
    EasyTextInputLayout edit_txt_last_name;

    @BindView(R.id.edit_txt_address)
    EasyTextInputLayout edit_txt_address;

    @BindView(R.id.edit_txt_phone)
    EasyTextInputLayout edit_txt_phone;

    @BindView(R.id.spinner_gender)
    Spinner spinner_gender;

    @BindView(R.id.btn_general_birthday)
    Button btn_general_birthday;

    @BindView(R.id.btn_general_logout)
    Button btn_general_logout;

    @Inject
    WBAlerts wbAlerts;

    public GeneralSettingsFragment() {
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
        View view = inflater.inflate(R.layout.fragment_general_settings, container, false);
        ButterKnife.bind(this, view);

        ActivityBaseComponent.inject(mActivity);
        ActivityBaseComponent.component().inject(this);

        // request code for camera
        REQUEST_IMAGE_CAPTURE = launcher.REQUEST_IMAGE_CAPTURE;

        generalSettingsPresenter = new GeneralSettingsImplement(
                ThreadExecutor.getInstance(),
                MainThreadImplement.getInstance(),
                this,
                this.inputs
        );

        return view;
    }

    @Override
    public void showProgress() {
        progressDialog = wbAlerts.progress("Updating Profile...", false);
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.cancel();
    }

    @Override
    public void showError(String message) {
        wbAlerts.error("Update Failed", message).show();

        Timber.e("%s, Error: %s", TAG, message);
    }

    @Override
    public void listeners() {
        // camera for avatar
        img_avatar.setOnClickListener(view -> startActivityForResult(launcher.takeIntentPicture(), REQUEST_IMAGE_CAPTURE));

        // date picker for birthday
        btn_general_birthday.setOnClickListener(view -> {
            Calendar now = Calendar.getInstance();
            DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(
                    GeneralSettingsFragment.this,
                    now.get(Calendar.YEAR),
                    now.get(Calendar.MONTH),
                    now.get(Calendar.DAY_OF_MONTH)
            );
            datePickerDialog.show(mActivity.getFragmentManager(), "Datepickerdialog");
        });

        // create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(mActivity,
                R.array.spinner_gender, android.R.layout.simple_spinner_item);
        // specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // apply the adapter to the spinner
        spinner_gender.setAdapter(adapter);
        spinner_gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 1) {
                    inputs.setInput("gender", "Male");
                } else if (position == 2) {
                    inputs.setInput("gender", "Female");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // submit
        btn_general_update.setOnClickListener(view -> generalSettingsPresenter.submit());

        // logout
        btn_general_logout.setOnClickListener(view ->
                DialogFactory.confirm(mActivity,
                        "Logout", "Are you sure to logout?", (dialogInterface, i) -> {
                            new MeModel().single().clear();
                            mActivity.finishAffinity();

                            // start the login
                            Intent intent = new Intent(mActivity, LoginActivity.class);
                            startActivity(intent);
                        }));
    }

    @Override
    public void setValues() {
        final MeModel meModel = new MeModel().single();

        if (meModel.avatar != null && network.hasConnection()) {
            WBHttp.imgSetCIDownload(meModel.avatar, img_avatar);
        }

        edit_txt_first_name.getEditText().setText(meModel.first_name);
        edit_txt_last_name.getEditText().setText(meModel.last_name);
        edit_txt_address.getEditText().setText(meModel.address);
        edit_txt_phone.getEditText().setText(meModel.phone);

        // gender
        if (meModel.gender.equals("Male")) {
            spinner_gender.setSelection(1);
        } else if (meModel.gender.equals("Female")) {
            spinner_gender.setSelection(2);
        }

        // birthday
        String birthDay = "Birthday " + meModel.birthday;
        btn_general_birthday.setText(birthDay);
    }

    @Override
    public void update() {
        this.inputs.setInput("first_name", edit_txt_first_name.getEditText().getText());
        this.inputs.setInput("last_name", edit_txt_last_name.getEditText().getText());
        this.inputs.setInput("address", edit_txt_address.getEditText().getText());
        this.inputs.setInput("phone", edit_txt_phone.getEditText().getText());

        this.inputs.setInput("image", uriImage);
    }

    @Override
    public void success() {
        ToastFactory.show(mActivity.getApplicationContext(), "Profile updated successfully.");
    }

    // on activity result
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            if (imageBitmap != null) {
                // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
                Uri tempUri = WBFile.getImageUri(mActivity.getApplicationContext(), imageBitmap);

                // CALL THIS METHOD TO GET THE ACTUAL PATH
                uriImage = new File(WBFile.getRealPathFromURI(tempUri, mActivity));
            }
        }
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String fullDate = WBTime.getMonthForInt(monthOfYear) + " " + dayOfMonth + ", " + year;

        this.inputs.setInput("birthday", fullDate);

        String birthDay = "Birthday " + fullDate;
        btn_general_birthday.setText(birthDay);
    }
}
