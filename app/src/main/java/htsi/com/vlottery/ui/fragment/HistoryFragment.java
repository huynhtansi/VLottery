package htsi.com.vlottery.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import butterknife.BindView;
import htsi.com.vlottery.R;
import htsi.com.vlottery.data.model.History;
import htsi.com.vlottery.ui.fragment.base.BaseFragment;
import htsi.com.vlottery.ui.viewholder.HistoryViewHolder;
import htsi.com.vlottery.utils.DividerItemDecoration;

/**
 * Created by htsi.
 * Since: 8/13/16 on 1:12 PM
 * Project: VLottery
 */
public class HistoryFragment extends BaseFragment {

    @BindView(R.id.listHistory)
    RecyclerView mListHistory;

    private LinearLayoutManager mManager;

    private FirebaseRecyclerAdapter<History,HistoryViewHolder> mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    protected void onScreenVisible() {
        super.onScreenVisible();

        /*if (!Utils.isConnectedToInternet(getContext())) {
            restoreFromSharedPreferences();
            if (mHomeInfo != null)
                setupUI();
            else
                showRetry();
        } else {
        }*/
        setupUI();

        loadInternetData();
    }

    private void setupUI() {

        mManager = new LinearLayoutManager(getActivity());
        mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);
        mListHistory.setLayoutManager(mManager);
        mListHistory.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
        mListHistory.setItemAnimator(new DefaultItemAnimator());
    }

    private void loadInternetData() {

        Log.d("HTSI", "loadInternetData");

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        Query historyQuery = database.child("history");

        mAdapter = new FirebaseRecyclerAdapter<History, HistoryViewHolder>(History.class, R.layout.layout_history_item, HistoryViewHolder.class, historyQuery) {

            @Override
            protected void populateViewHolder(HistoryViewHolder viewHolder, History model, int position) {
                viewHolder.bind(getContext(), model, position);
            }
        };
        mListHistory.setAdapter(mAdapter);
    }
}
