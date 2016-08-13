package htsi.com.vlottery.ui.activity;

import android.content.Context;
import android.content.Intent;

import htsi.com.vlottery.ui.activity.base.BaseActivity;
import htsi.com.vlottery.ui.fragment.HistoryFragment;
import htsi.com.vlottery.ui.fragment.base.BaseFragment;

/**
 * Created by htsi.
 * Since: 8/13/16 on 1:11 PM
 * Project: VLottery
 */
public class HistoryActivity extends BaseActivity {

    public static void openHistoryActivity(Context pContext) {
        Intent intent = new Intent(pContext, HistoryActivity.class);
        pContext.startActivity(intent);
    }

    @Override
    protected BaseFragment hostFragment() {
        return new HistoryFragment();
    }
}
