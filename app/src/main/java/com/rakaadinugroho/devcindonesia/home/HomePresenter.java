package com.rakaadinugroho.devcindonesia.home;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;
import com.rakaadinugroho.devcindonesia.AuthActivity;

import static com.facebook.login.widget.ProfilePictureView.TAG;

/**
 * Created by Raka Adi Nugroho on 7/16/17.
 *
 * @Github github.com/rakaadinugroho
 * @Contact nugrohoraka@gmail.com
 */

public class HomePresenter {
    private HomeView view;
    public HomePresenter(HomeView view){
        this.view   = view;
    }
    public void loadUserData(){
        AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
            @Override
            public void onSuccess(Account account) {
                view.userDataLoaded(account);
            }

            @Override
            public void onError(AccountKitError accountKitError) {
                view.userDataFailed(accountKitError.getErrorType().getMessage().toString().trim());
            }
        });
    }

    public void logoutUser(Activity activity){
        AccountKit.logOut();
        Intent intent   = new Intent(activity, AuthActivity.class);
        view.signout("Signout Success", intent);
    }
}
