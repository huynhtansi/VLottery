package htsi.com.vlottery.di.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by htsi.
 * Since: 8/4/16 on 1:00 PM
 * Project: VLottery
 */
@Module
public class AppModule {

    private final Application mApplication;

    public AppModule(Application pApplication) {
        mApplication = pApplication;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return mApplication;
    }
}
