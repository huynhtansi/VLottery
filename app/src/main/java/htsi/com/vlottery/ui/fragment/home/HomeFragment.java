package htsi.com.vlottery.ui.fragment.home;

import android.animation.TimeInterpolator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.OnClick;
import cn.iwgang.countdownview.CountdownView;
import htsi.com.vlottery.R;
import htsi.com.vlottery.app.VLotteryApplication;
import htsi.com.vlottery.data.model.Home;
import htsi.com.vlottery.ui.activity.HistoryActivity;
import htsi.com.vlottery.ui.fragment.base.BaseFragment;
import htsi.com.vlottery.utils.Utils;

/**
 * Created by htsi.
 * Since: 8/4/16 on 1:13 PM
 * Project: VLottery
 */
public class HomeFragment extends BaseFragment {

    private static final String SHARED_PREFERENCES_FILE_NAME = "HOME_SHARED_PREFERECES";
    public static final String KEY_CURRENT_JACKPOT = "CurrentJackPot";
    public static final String KEY_LAST_RESULT = "LastResult";
    public static final String KEY_LAST_TIME_RESULT = "LastTimeResult";
    public static final String KEY_NEXT_TIME_RESULT = "NextTimeResult";
    public static final String KEY_STATUS = "Status";


    private static final TimeInterpolator sDecelerator = new DecelerateInterpolator();
    private static final TimeInterpolator sAccelerator = new AccelerateInterpolator();
    private static final int ANIM_DURATION = 500;

    @BindView(R.id.cdNextDrawing)
    CountdownView mCountDown;

    @BindView(R.id.textCurrentJackPot)
    TextView mTextCurrentJackPot;

    @BindView(R.id.textCurrentTimeResult)
    TextView mTextCurrentTimeResult;

    @BindView(R.id.textNextTimeDrawing)
    TextView mTextNextTimeDrawing;

    @BindView(R.id.textStatus)
    TextView mTextStatus;

    @BindView(R.id.pbLoading)
    ProgressBar mPbLoading;
    @BindView(R.id.mainContent) LinearLayout mMainCotent;

    @BindView(R.id.panelResult)
    LinearLayout mPanelResult;

    @BindView(R.id.panelStatus)
    ViewGroup mPanelStatus;

    @BindView(R.id.layoutRetry)
    ViewGroup mLayoutRetry;

    @BindView(R.id.btnRetry)
    Button mBtnRetry;

    @BindView(R.id.adView)
    AdView adView;

    private Home mHome;
    private NetworkChangeReceiver mReceiver;

    public static HomeFragment newInstance(String title) {
        HomeFragment fragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("TITLE", title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        mReceiver = new NetworkChangeReceiver();
        getContext().registerReceiver(mReceiver,
                new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onScreenVisible() {
        super.onScreenVisible();

        if (!Utils.isConnectedToInternet(getContext())) {
            restoreFromSharedPreferences();
            if (mHome != null)
                setupUI();
            else
                showRetry();
        } else {
            loadInternetData();
        }
    }

    @OnClick(R.id.btnViewHistory)
    public void onViewHistory(View pView) {
        HistoryActivity.openHistoryActivity(getContext());
    }

    private void setupUI() {
        mTextCurrentJackPot.setText(mHome.jackpot);

        if (mHome.lastResult != null) {
            String balls[] = mHome.lastResult.split("-");
            for (int i=0; i<mPanelResult.getChildCount(); i++) {
                View view = mPanelResult.getChildAt(i);
                ((TextView)view).setText(balls[i]);
            }
        }


        mTextCurrentTimeResult.setText(String.format(getResourceString(R.string.format_last_drawing_date), mHome.lastTimeResult));
        mTextNextTimeDrawing.setText(String.format(getResourceString(R.string.format_next_drawing_date), mHome.nextTimeResult));

        long nextTime = Utils.convertDateString(mHome.nextTimeResult) + 18*60*60*1000 - System.currentTimeMillis();
        mCountDown.start(nextTime);

        String status = mHome.status;
        if (!status.equals("idle")) {
            mPanelStatus.setVisibility(View.GONE);
            mTextStatus.setVisibility(View.VISIBLE);
        } else {
            mPanelStatus.setVisibility(View.VISIBLE);
            mTextStatus.setVisibility(View.GONE);
        }

        if (mPbLoading.getVisibility() == View.VISIBLE) {
            mPbLoading.setVisibility(View.GONE);

            mMainCotent.setScaleX(0.2f);
            mMainCotent.setScaleY(0.2f);
            mMainCotent.animate().
                    setDuration(ANIM_DURATION)
                    .setInterpolator(sDecelerator)
                    .alpha(1)
                    .scaleX(1.0f)
                    .scaleY(1.0f);
            mMainCotent.setVisibility(View.VISIBLE);
        }
    }


    private void showRetry() {
        mPbLoading.setVisibility(View.GONE);
        mLayoutRetry.setVisibility(View.VISIBLE);
        mBtnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Utils.isConnectedToInternet(getContext()))
                    loadInternetData();
                else
                    Toast.makeText(getContext(), getResourceString(R.string.no_internet_connection), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadInternetData() {

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        mLayoutRetry.setVisibility(View.GONE);
        if (mMainCotent.getVisibility() == View.VISIBLE)
            mMainCotent.setVisibility(View.INVISIBLE);
        mPbLoading.setVisibility(View.VISIBLE);

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        Query homeQuery = database.child("home");

        homeQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mHome = dataSnapshot.getValue(Home.class);
                setupUI();
           }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mHome != null)
            saveToSharedPreference();
        getContext().unregisterReceiver(mReceiver);
    }

    private void saveToSharedPreference() {
        SharedPreferences preferences = getContext().getSharedPreferences(SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        if (Utils.isValidString(mHome.jackpot))
            editor.putString(KEY_CURRENT_JACKPOT, mHome.jackpot);

        if (Utils.isValidString(mHome.lastResult))
            editor.putString(KEY_LAST_RESULT, mHome.lastResult);

        if (Utils.isValidString(mHome.lastTimeResult))
            editor.putString(KEY_LAST_TIME_RESULT, mHome.lastTimeResult);

        if (Utils.isValidString(mHome.nextTimeResult))
            editor.putString(KEY_NEXT_TIME_RESULT, mHome.nextTimeResult);

        if (Utils.isValidString(mHome.status))
            editor.putString(KEY_STATUS, mHome.status);

        editor.apply();
    }

    private void restoreFromSharedPreferences() {
        SharedPreferences preferences = getContext().getSharedPreferences(SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
        String currentJackPot = preferences.getString(KEY_CURRENT_JACKPOT, "");

        if (Utils.isValidString(currentJackPot)) {
            String lastResult = preferences.getString(KEY_LAST_RESULT, "");
            String lastTimeResult = preferences.getString(KEY_LAST_TIME_RESULT, "");
            String nextTimeResult = preferences.getString(KEY_NEXT_TIME_RESULT, "");
            String status = preferences.getString(KEY_STATUS, "idle");

            mHome = new Home(currentJackPot, lastResult, lastTimeResult, status, nextTimeResult);
        }
    }

    private String getResourceString(int id) {
        return VLotteryApplication.getInstance().getString(id);
    }

    private class NetworkChangeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (Utils.isConnectedToInternet(getContext())) {
                loadInternetData();
            }
        }
    }
}
