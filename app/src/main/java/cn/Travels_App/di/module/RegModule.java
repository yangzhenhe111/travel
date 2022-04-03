package cn.Travels_App.di.module;

import javax.inject.Singleton;

import cn.Travels_App.App;
import cn.Travels_App.persenter.RegPersenter;
import cn.Travels_App.view.RegView;
import dagger.Module;
import dagger.Provides;



@Module
public class RegModule {

    App app;
    RegView mRegView;

    public RegModule(App app,RegView mRegView){
        this.app = app;
        this.mRegView = mRegView;
    }

    @Provides
    App provideApp(){
        return this.app;
    }

    @Provides
    RegView providerRegView(){
        return this.mRegView;
    }


    @Provides
    @Singleton
    RegPersenter providePresenter(App app, RegView view){
        return new RegPersenter(app,view);
    }
}
