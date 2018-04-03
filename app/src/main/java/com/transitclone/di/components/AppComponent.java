package com.transitclone.di.components;




import com.transitclone.BaseActivity;
import com.transitclone.MapsActivity;
import com.transitclone.di.modules.HttpModule;
import com.transitclone.di.modules.SharedPrefsHelper;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {HttpModule.class, SharedPrefsHelper.class})
public interface AppComponent {
    //void inject(SignInActivity activity);

    void inject(BaseActivity activity);

}
