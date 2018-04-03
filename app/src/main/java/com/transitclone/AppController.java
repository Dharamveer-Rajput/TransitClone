package com.transitclone;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.transitclone.di.components.AppComponent;
import com.transitclone.di.components.DaggerAppComponent;
import com.transitclone.di.modules.HttpModule;
import com.transitclone.di.modules.SharedPrefsHelper;


import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by rajsm on 01/03/2018.
 */


public class AppController extends Application {

    private RxBus bus;
    private AppComponent component;


    @Override
    public void onCreate() {
        super.onCreate();

        Stetho.initializeWithDefaults(this);

        bus = new RxBus();
        // FlowManager.init(this);


        component = DaggerAppComponent.builder().sharedPrefsHelper(new SharedPrefsHelper(this))
                .httpModule(new HttpModule(this))
                .build();


        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/OpenSans-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());

    }

    public RxBus bus() {
        return bus;
    }
    public AppComponent getComponent() {
        return component;
    }
}
