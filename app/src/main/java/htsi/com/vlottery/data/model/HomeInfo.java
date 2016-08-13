package htsi.com.vlottery.data.model;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Huỳnh Phúc on 8/6/2016.
 */
@IgnoreExtraProperties
public class HomeInfo {

    private String jackpot;
    private String lastResult;
    private String lastTimeResult;
    private String status;
    private String nextTimeResult;
    private int rollingBall;

    public HomeInfo() {}

    public String getJackpot() {
        return jackpot;
    }

    public void setJackpot(String pJackpot) {
        jackpot = pJackpot;
    }

    public String getLastResult() {
        return lastResult;
    }

    public void setLastResult(String pLastResult) {
        lastResult = pLastResult;
    }

    public String getLastTimeResult() {
        return lastTimeResult;
    }

    public void setLastTimeResult(String pLastTimeResult) {
        lastTimeResult = pLastTimeResult;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String pStatus) {
        status = pStatus;
    }

    public String getNextTimeResult() {
        return nextTimeResult;
    }

    public void setNextTimeResult(String pNextTimeResult) {
        nextTimeResult = pNextTimeResult;
    }

    public int getRollingBall() {
        return rollingBall;
    }

    public void setRollingBall(int pRollingBall) {
        rollingBall = pRollingBall;
    }
}
