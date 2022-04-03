package cn.Travels_App.di.componet;

import javax.inject.Singleton;


import cn.Travels_App.di.module.RegModule;


import cn.Travels_App.ui.activity.RegActivity;
import dagger.Component;

@Singleton
@Component(modules={RegModule.class})
public interface RegComponent{
    void injectReg(RegActivity regActivity);
}
