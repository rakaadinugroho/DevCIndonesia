package com.rakaadinugroho.devcindonesia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.widget.Toast;

import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;
import com.rakaadinugroho.devcindonesia.home.HomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AuthActivity extends AppCompatActivity implements AuthView {
    private static final int REQUEST_CODE = 100;
    private AccessToken accessToken;

    private AuthPresenter authPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        ButterKnife.bind(this);

        authPresenter   = new AuthPresenter(this);

        accessToken = AccountKit.getCurrentAccessToken();
        if (accessToken != null)
            authPresenter.goToHome(AuthActivity.this);
    }

    @OnClick(R.id.auth_sms)
    public void auth_sms(){
        initAuthSMSHandler();
    }
    @OnClick(R.id.auth_mail)
    public void auth_mail(){
        initAuthMailHandler();
    }

    private void initAuthMailHandler() {
        final Intent intent = new Intent(this, AccountKitActivity.class);
        AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder =
                new AccountKitConfiguration.AccountKitConfigurationBuilder(
                        LoginType.EMAIL,
                        AccountKitActivity.ResponseType.CODE);
        intent.putExtra(
                AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION,
                configurationBuilder.build());
        startActivityForResult(intent, REQUEST_CODE);
    }

    private void initAuthSMSHandler() {
        final Intent intent = new Intent(this, AccountKitActivity.class);
        AccountKitConfiguration.AccountKitConfigurationBuilder builder  = new AccountKitConfiguration.AccountKitConfigurationBuilder(LoginType.PHONE, AccountKitActivity.ResponseType.TOKEN);
        intent.putExtra(AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION, builder.build());
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(
            final int requestCode,
            final int resultCode,
            final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            AccountKitLoginResult loginResult = data.getParcelableExtra(AccountKitLoginResult.RESULT_KEY);
            String toastMessage;
            if (loginResult.getError() != null) {
                toastMessage = loginResult.getError().getErrorType().getMessage();
                showErrorActivity(loginResult.getError());
            } else if (loginResult.wasCancelled()) {
                toastMessage = "Login Cancelled";
            } else {
                if (loginResult.getAccessToken() != null) {
                    toastMessage = "Success:" + loginResult.getAccessToken().getAccountId();
                } else {
                    toastMessage = String.format(
                            "Success:%s...",
                            loginResult.getAuthorizationCode().substring(0,10));
                }
                // loginResult.getAuthorizationCode()
                authPresenter.goToHome(AuthActivity.this);
            }
            Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show();
        }
    }

    private void showErrorActivity(AccountKitError error) {
        Toast.makeText(this, error.getErrorType().toString().trim(), Toast.LENGTH_SHORT).show();
    }
    @Override
    public void moveTo(Intent intent) {
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
