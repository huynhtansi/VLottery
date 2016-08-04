package htsi.com.vlottery.app;

import android.support.multidex.MultiDexApplication;

import htsi.com.vlottery.R;
import htsi.com.vlottery.di.component.AppComponent;
import htsi.com.vlottery.di.component.DaggerAppComponent;
import htsi.com.vlottery.di.module.AppModule;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by htsi.
 * Since: 8/4/16 on 12:58 PM
 * Project: VLottery
 */
public class VLotteryApplication extends MultiDexApplication {

    private static VLotteryApplication sInstance;

    private AppComponent mAppComponent;

    public VLotteryApplication () { sInstance = this;}

    public static VLotteryApplication getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        this.mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();

        //  Set default font to MyriadPro
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(Constants.APP_DEFAULT_FONT)
                .setFontAttrId(R.attr.fontPath)
                .build());
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
