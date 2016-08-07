package htsi.com.vlottery.ui.fragment.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabClickListener;

import butterknife.BindView;
import htsi.com.vlottery.R;
import htsi.com.vlottery.ui.fragment.base.BaseFragment;

/**
 * Created by htsi.
 * Since: 8/4/16 on 1:12 PM
 * Project: VLottery
 */
public class HomeContainerFragment extends BaseFragment {

    @BindView(R.id.viewPageHome)
    ViewPager mViewPageHome;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_container, container, false);
    }

    @Override
    protected void onScreenVisible() {
        super.onScreenVisible();
        setupUI();
    }

    private void setupUI() {
        final BottomBar bottomBar = BottomBar.attach(getActivity(), null);
        bottomBar.setItems(R.menu.bottom_bar_menu);
        bottomBar.setOnTabClickListener(new OnTabClickListener() {
            @Override
            public void onTabSelected(int position) {
                mViewPageHome.setCurrentItem(position, true);
            }

            @Override
            public void onTabReSelected(int position) {

            }
        });

        mViewPageHome.setOffscreenPageLimit(4);
        mViewPageHome.setAdapter(new PageHomeAdapter(getChildFragmentManager()));
        mViewPageHome.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomBar.selectTabAtPosition(position, true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private class PageHomeAdapter extends FragmentPagerAdapter {

        public PageHomeAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return HomeFragment.newInstance("Home Fragment");
                case 1:
                    return new RulesFragment();
                case 2:
                    return new RulesFragment();
                case 3:
                    return new RulesFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}
