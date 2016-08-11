package htsi.com.vlottery.data.model;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Huỳnh Phúc on 8/6/2016.
 */
@IgnoreExtraProperties
public class HomeInfo {

    public String jackpot;
    public String lastResult;
    public String lastTimeResult;
    public String status;
    public String nextTimeResult;
    public int rollingBall;

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
