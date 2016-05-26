package disono.webmons.com.clean_architecture.presentation.ui.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import disono.webmons.com.clean_architecture.R;
import disono.webmons.com.clean_architecture.data.services.git.adapter.GitAdapter;
import disono.webmons.com.clean_architecture.data.services.git.model.GitModel;
import disono.webmons.com.clean_architecture.presentation.presenters.listeners.DialogInterfaceFactory;
import disono.webmons.com.clean_architecture.presentation.presenters.MainPresenter.View;
import disono.webmons.com.clean_architecture.presentation.ui.activities.auth.LoginActivity;
import disono.webmons.com.clean_architecture.presentation.ui.activities.user.UserListActivity;
import disono.webmons.com.clean_architecture.threading.MainThreadImp;
import disono.webmons.com.clean_architecture.util.ui.DialogFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Author: Archie, Disono (disono.apd@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Created at: 2016-04-12 11:26 AM
 */
public class MainActivity extends AppCompatActivity implements View {
    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ctx = this.getApplication().getApplicationContext();

        DialogFactory.error(this, "Error", "No internet connection!", new DialogInterfaceFactory().OnClick(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ctx, "Working...", Toast.LENGTH_LONG).show();
            }
        })).show();

        MainThreadImp.getInstance().post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(ctx, "Working...", Toast.LENGTH_LONG).show();
            }
        });

        Intent intent = new Intent(getApplicationContext(), UserListActivity.class);
        startActivity(intent);

        // call API Demo
        GitAdapter gitAdapter = new GitAdapter();
        Call<GitModel> call = gitAdapter.getUser("disono");
        call.enqueue(new Callback<GitModel>() {
            @Override
            public void onResponse(Call<GitModel> call, Response<GitModel> response) {
                final Response<GitModel> res = response;

                MainThreadImp.getInstance().post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ctx, "API Response: " + res.toString(), Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onFailure(Call<GitModel> call, Throwable t) {
                MainThreadImp.getInstance().post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ctx, "Error API...", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String message) {

    }
}
