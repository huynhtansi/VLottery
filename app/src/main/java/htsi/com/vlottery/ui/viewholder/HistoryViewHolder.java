package htsi.com.vlottery.ui.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import htsi.com.vlottery.R;
import htsi.com.vlottery.data.model.History;

/**
 * Created by htsi.
 * Since: 8/13/16 on 1:43 PM
 * Project: VLottery
 */
public class HistoryViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.textPeriodNo)
    TextView mTextPeriodNo;

    @BindView(R.id.textDate)
    TextView mTextDate;

    @BindView(R.id.panelResult)
    ViewGroup mPanelResult;


    public HistoryViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Context pContext, History pHistory, int position) {
        mTextPeriodNo.setText(pContext.getString(R.string.format_period_history, position));
        mTextDate.setText(pContext.getString(R.string.format_date_history, pHistory.getDate()));

        if (pHistory.getResult() != null) {
            String balls[] = pHistory.getResult().split("-");
            for (int i=0; i<mPanelResult.getChildCount(); i++) {
                View view = mPanelResult.getChildAt(i);
                ((TextView)view).setText(balls[i]);
            }
        }
    }
}
