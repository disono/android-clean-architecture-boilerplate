package disono.webmons.com.clean_architecture.presentation.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import disono.webmons.com.clean_architecture.R;
import disono.webmons.com.clean_architecture.presentation.presenters.MainPresenter.View;

/**
 * Author: Archie, Disono
 * Package: disono.webmons.com.clean_architecture.ui.activities
 * Created at: 2016-04-12 11:26 AM
 */
public class MainActivity extends AppCompatActivity implements View {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
