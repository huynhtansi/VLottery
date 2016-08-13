package htsi.com.vlottery.data.model;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by htsi.
 * Since: 8/13/16 on 1:26 PM
 * Project: VLottery
 */
@IgnoreExtraProperties
public class History {

    private String mPeriod;
    private String date;
    private String result;

    public History() {

    }

    public String getPeriod() {
        return mPeriod;
    }

    public void setPeriod(String pPeriod) {
        mPeriod = pPeriod;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String pResult) {
        result = pResult;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String pDate) {
        date = pDate;
    }
}
