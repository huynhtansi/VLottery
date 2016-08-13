package htsi.com.vlottery.ui.activity;

import htsi.com.vlottery.ui.activity.base.BaseActivity;
import htsi.com.vlottery.ui.fragment.base.BaseFragment;
import htsi.com.vlottery.ui.fragment.home.HomeFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected BaseFragment hostFragment() {
        return new HomeFragment();
    }
}
