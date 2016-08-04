package htsi.com.vlottery.ui.activity.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import htsi.com.vlottery.R;
import htsi.com.vlottery.ui.fragment.base.BaseFragment;

/**
 * Created by htsi.
 * Since: 8/4/16 on 1:13 PM
 * Project: VLottery
 */
public class HomeFragment extends BaseFragment {



    public static HomeFragment newInstance(String title) {
        HomeFragment fragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("TITLE", title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @BindView(R.id.textTitle)
    TextView mTextTitle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    protected void onScreenVisible() {
        super.onScreenVisible();

        String title = getArguments().getString("TITLE");
        mTextTitle.setText(title);
    }
}
