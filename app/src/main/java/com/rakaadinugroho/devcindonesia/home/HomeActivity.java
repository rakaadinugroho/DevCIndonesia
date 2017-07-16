package com.rakaadinugroho.devcindonesia.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.rakaadinugroho.devcindonesia.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements HomeView {
    private HomePresenter presenter;
    @BindView(R.id.home_email)
    TextView home_email;
    @BindView(R.id.home_hello)
    TextView home_hello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        presenter   = new HomePresenter(this);

        /*
        Load Data
         */
        presenter.loadUserData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater   = getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_logout:
                presenter.logoutUser(HomeActivity.this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void userDataLoaded(Account account) {
        if (account != null){
            home_email.setVisibility(View.VISIBLE);
            home_hello.setVisibility(View.VISIBLE);
            home_email.setText(account.getId().toString().trim());
        }
    }

    @Override
    public void userDataFailed(String message) {
        Toast.makeText(this, "Kesalahan : " + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void signout(String message, Intent intent) {
        Toast.makeText(this, message.toString().trim(), Toast.LENGTH_SHORT).show();
        startActivity(intent);
        finish();
    }
}
