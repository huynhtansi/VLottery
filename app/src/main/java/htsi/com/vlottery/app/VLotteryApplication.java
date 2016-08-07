package htsi.com.vlottery.app;

import android.app.Application;
import android.support.multidex.MultiDexApplication;

import com.google.android.gms.ads.MobileAds;

import htsi.com.vlottery.R;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by htsi.
 * Since: 8/4/16 on 12:58 PM
 * Project: VLottery
 */
public class VLotteryApplication extends Application {

    private static VLotteryApplication sInstance;


    public VLotteryApplication () { sInstance = this;}

    public static VLotteryApplication getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();


        //  Set default font to MyriadPro
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(Constants.APP_DEFAULT_FONT)
                .setFontAttrId(R.attr.fontPath)
                .build());

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-2798467803781502/1097151075");
    }
}
