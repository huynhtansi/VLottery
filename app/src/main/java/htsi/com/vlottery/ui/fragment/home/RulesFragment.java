package htsi.com.vlottery.ui.fragment.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import htsi.com.vlottery.R;
import htsi.com.vlottery.ui.fragment.base.BaseFragment;

/**
 * Created by Huỳnh Phúc on 8/6/2016.
 */
public class RulesFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_rules, container, false);
    }
}
