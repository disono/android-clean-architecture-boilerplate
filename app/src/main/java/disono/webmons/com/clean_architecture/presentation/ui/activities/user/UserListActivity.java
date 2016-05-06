package disono.webmons.com.clean_architecture.presentation.ui.activities.user;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

import disono.webmons.com.clean_architecture.R;
import disono.webmons.com.clean_architecture.presentation.presenters.MainPresenter;
import disono.webmons.com.clean_architecture.util.ui.DialogFactory;
import disono.webmons.com.clean_architecture.util.ui.SnackbarFactory;

public class UserListActivity extends AppCompatActivity implements MainPresenter.View {
    private Context ctx;
    private String[] userNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        ctx = this;

        userNames = new String[]{
                "User 1",
                "User 2",
                "User 3"
        };
        ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.adapter_user_list, userNames);

        ListViewCompat listViewCompat = (ListViewCompat) findViewById(R.id.user_list_view);
        if (listViewCompat != null) {
            listViewCompat.setAdapter(adapter);
            listViewCompat.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    SnackbarFactory.message(ctx, view, "Username: " + userNames[position]).show();
                }
            });
        }
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
