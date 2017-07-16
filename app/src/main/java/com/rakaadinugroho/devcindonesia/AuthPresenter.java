package com.rakaadinugroho.devcindonesia;

import android.app.Activity;
import android.content.Intent;

import com.facebook.appevents.AppEventsLogger;
import com.rakaadinugroho.devcindonesia.home.HomeActivity;

/**
 * Created by Raka Adi Nugroho on 7/16/17.
 *
 * @Github github.com/rakaadinugroho
 * @Contact nugrohoraka@gmail.com
 */

public class AuthPresenter {
    private AuthView view;
    public AuthPresenter(AuthView view){
        this.view   = view;
    }
    public void goToHome(Activity activity){
        /* Success login */
        Intent intent   = new Intent(activity, HomeActivity.class);
        view.moveTo(intent);
    }
    public void destroyData(){
    }
}
