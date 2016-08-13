package htsi.com.vlottery.data.model;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Huỳnh Phúc on 8/6/2016.
 */
@IgnoreExtraProperties
public class Home {

    public String jackpot;
    public String lastResult;
    public String lastTimeResult;
    public String nextTimeResult;
    public String status;


    public Home() {}

    public Home(String jackpot, String lastResult, String lastTimeResult,
                String status, String nextTimeResult) {
        this.jackpot = jackpot;
        this.lastResult = lastResult;
        this.lastTimeResult = lastTimeResult;
        this.status = status;
        this.nextTimeResult = nextTimeResult;
    }
}
