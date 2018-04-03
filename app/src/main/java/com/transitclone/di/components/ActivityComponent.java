package com.transitclone.di.components;



import com.transitclone.di.modules.ActivityModule;
import com.transitclone.di.scopes.PerActivity;

import dagger.Component;



@PerActivity
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {


}
