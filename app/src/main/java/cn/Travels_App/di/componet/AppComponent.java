package cn.Travels_App.di.componet;

import android.content.Context;

import cn.Travels_App.App;
import cn.Travels_App.di.module.AppModule;
import cn.Travels_App.network.APIService;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    void inject(App app);

    Context getContext();

    APIService getAPIService();

}
