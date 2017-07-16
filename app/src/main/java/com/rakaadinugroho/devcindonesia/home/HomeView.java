package com.rakaadinugroho.devcindonesia.home;

import android.content.Intent;

import com.facebook.accountkit.Account;

/**
 * Created by Raka Adi Nugroho on 7/16/17.
 *
 * @Github github.com/rakaadinugroho
 * @Contact nugrohoraka@gmail.com
 */

public interface HomeView {
    void userDataLoaded(Account account);
    void userDataFailed(String message);
    void signout(String message, Intent intent);
}
