package com.rakaadinugroho.devcindonesia;

import android.app.Application;
import android.content.Context;

import com.facebook.accountkit.AccountKit;

/**
 * Created by Raka Adi Nugroho on 7/15/17.
 *
 * @Github github.com/rakaadinugroho
 * @Contact nugrohoraka@gmail.com
 */

public class BaseApps extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        AccountKit.initialize(getApplicationContext());
        BaseApps.mContext   = getApplicationContext();
    }
    public static Context getmContext(){
        return BaseApps.getmContext();
    }
}
