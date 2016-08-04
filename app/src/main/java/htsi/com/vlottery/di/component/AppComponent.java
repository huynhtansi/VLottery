package htsi.com.vlottery.di.component;

import javax.inject.Singleton;

import dagger.Component;
import htsi.com.vlottery.di.module.AppModule;

/**
 * Created by htsi.
 * Since: 8/4/16 on 1:02 PM
 * Project: VLottery
 */
@Singleton
@Component( modules = {
        AppModule.class
})
public interface AppComponent {

}
