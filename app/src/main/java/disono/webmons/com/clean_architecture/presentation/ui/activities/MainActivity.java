package disono.webmons.com.clean_architecture.presentation.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import disono.webmons.com.clean_architecture.R;
import disono.webmons.com.clean_architecture.presentation.presenters.MainPresenter;

public class MainActivity extends AppCompatActivity implements MainPresenter.View {
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
