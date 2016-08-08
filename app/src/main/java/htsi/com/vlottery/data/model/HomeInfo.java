package htsi.com.vlottery.data.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Map;

/**
 * Created by Huỳnh Phúc on 8/6/2016.
 */
@IgnoreExtraProperties
public class HomeInfo {

    String jackpot;
    String lastResult;
    String lastTimeResult;
    String status;
    String nextTimeResult;
    int rollingBall;
    Map<String, Integer> ballValues;

    public HomeInfo() {}

    public String getJackpot() {
        return jackpot;
    }

    public String getLastResult() {
        return lastResult;
    }

    public String getStatus() {
        return status;
    }

    public String getNextTimeResult() {
        return nextTimeResult;
    }

    public int getRollingBall() {
        return rollingBall;
    }

    public String getLastTimeResult() {
        return lastTimeResult;
    }

    public Map<String, Integer> getBallValues() {
        return ballValues;
    }

    public void setJackpot(String jackpot) {
        this.jackpot = jackpot;
    }

    public void setLastResult(String lastResult) {
        this.lastResult = lastResult;
    }

    public void setLastTimeResult(String lastTimeResult) {
        this.lastTimeResult = lastTimeResult;
    }

    public void setNextTimeResult(String nextTimeResult) {
        this.nextTimeResult = nextTimeResult;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
