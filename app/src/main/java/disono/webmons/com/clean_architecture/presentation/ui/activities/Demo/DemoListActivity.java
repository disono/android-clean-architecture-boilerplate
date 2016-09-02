package disono.webmons.com.clean_architecture.presentation.ui.activities.demo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListViewCompat;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import disono.webmons.com.clean_architecture.R;
import disono.webmons.com.clean_architecture.presentation.presenters.blueprint.MainPresenter;
import disono.webmons.com.clean_architecture.utilities.ui.SnackbarFactory;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 2016-04-12 11:26 AM
 */
public class DemoListActivity extends AppCompatActivity implements MainPresenter.View {
    private Context ctx;
    private ArrayList<String> userNames = new ArrayList<>();
    ListViewCompat listViewCompat;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_list);
        ctx = this;

        for (int i = 1; i < 11; i++) {
            userNames.add("User " + i);
        }

        adapter = new ArrayAdapter<>(this, R.layout.adapter_demo_list, userNames);

        listViewCompat = (ListViewCompat) findViewById(R.id.user_list_view);
        if (listViewCompat != null) {
            listViewCompat.setAdapter(adapter);
            listViewCompat.setOnItemClickListener((parent, view, position, id) -> {
                SnackbarFactory.message(ctx, view, "Username: " + userNames.get(position)).show();

                // add data username
                userNames.add("User " + (userNames.size() + 1));
                adapter = new ArrayAdapter<>(ctx, R.layout.adapter_demo_list, userNames);
                listViewCompat.setAdapter(adapter);
                adapter.notifyDataSetChanged();
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
