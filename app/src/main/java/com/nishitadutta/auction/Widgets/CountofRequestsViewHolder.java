package com.nishitadutta.auction.Widgets;

import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.nishitadutta.auction.Objects.Request;
import com.nishitadutta.auction.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by madhu_000 on 10/14/2016.
 */

public class CountofRequestsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_bid_price)
    TextView tvPrice;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    String TAG="CntOfReqViewHolder";

    @BindView(R.id.view_divider)
    View vDivider;
    public CountofRequestsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setAttributes(final Request request) {
        if (request == null) {
            tvUsername.setVisibility(View.GONE);
            tvPrice.setVisibility(View.GONE);
            vDivider.setVisibility(View.GONE);
        } else {
            tvPrice.setText(String.valueOf(request.getBidPrice()));
            tvUsername.setText(request.getUserName());
            Log.e(TAG, "setAttributes: " + request.getBidPrice() + request.getUserName() );
            //tvPrice.setText(request.getBidPrice());
        }
    }
}
