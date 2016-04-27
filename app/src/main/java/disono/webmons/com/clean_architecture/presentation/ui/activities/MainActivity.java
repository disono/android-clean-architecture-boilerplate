package disono.webmons.com.clean_architecture.presentation.ui.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import disono.webmons.com.clean_architecture.R;
import disono.webmons.com.clean_architecture.presentation.presenters.listeners.DialogInterfaceFactory;
import disono.webmons.com.clean_architecture.presentation.presenters.MainPresenter.View;
import disono.webmons.com.clean_architecture.threading.MainThreadImp;
import disono.webmons.com.clean_architecture.util.ui.DialogFactory;

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
