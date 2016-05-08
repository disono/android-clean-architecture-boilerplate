package disono.webmons.com.clean_architecture.presentation.ui.activities.user;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import disono.webmons.com.clean_architecture.R;
import disono.webmons.com.clean_architecture.presentation.presenters.MainPresenter;
import disono.webmons.com.clean_architecture.util.ui.DialogFactory;
import disono.webmons.com.clean_architecture.util.ui.SnackbarFactory;

public class UserListActivity extends AppCompatActivity implements MainPresenter.View {
    private Context ctx;
    private ArrayList<String> userNames = new ArrayList<String>();;
    ListViewCompat listViewCompat;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        ctx = this;

        for (int i = 1; i < 11; i++) {
            userNames.add("User " + i);
        }

        adapter = new ArrayAdapter<>(this, R.layout.adapter_user_list, userNames);

        listViewCompat = (ListViewCompat) findViewById(R.id.user_list_view);
        if (listViewCompat != null) {
            listViewCompat.setAdapter(adapter);
            listViewCompat.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    SnackbarFactory.message(ctx, view, "Username: " + userNames.get(position)).show();

                    // add data username
                    userNames.add("User " + (userNames.size() + 1));
                    adapter = new ArrayAdapter<>(ctx, R.layout.adapter_user_list, userNames);
                    listViewCompat.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
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
