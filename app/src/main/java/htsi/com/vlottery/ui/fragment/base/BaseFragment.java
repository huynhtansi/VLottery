package htsi.com.vlottery.ui.fragment.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by htsi.
 * Since: 8/4/16 on 1:06 PM
 * Project: VLottery
 */
public class BaseFragment extends Fragment {
    protected void onScreenVisible(){}

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("BaseFragment", "onViewCreated");
        ButterKnife.bind(this, view);
        setupComponent();
        onScreenVisible();
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    public String getTitle() {
        return null;
    }

    protected void setupComponent() {

    }
}
