package htsi.com.vlottery.data.model;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by htsi.
 * Since: 8/13/16 on 1:26 PM
 * Project: VLottery
 */
@IgnoreExtraProperties
public class History {

    public String date;
    public String result;


    public History() {}

    public History(String date, String result) {
        this.date = date;
        this.result = result;
    }
}
