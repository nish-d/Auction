package com.nishitadutta.auction.Widgets;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.nishitadutta.auction.Activities.BidProductActivity_;
import com.nishitadutta.auction.Custom.Constants;
import com.nishitadutta.auction.Objects.Product;
import com.nishitadutta.auction.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by madhu_000 on 10/15/2016.
 */
public class MyRequestsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_name_myRequest)
    TextView tvProductName;

    @BindView(R.id.tv_price_myRequest)
    TextView tvPrice;

    @BindView(R.id.tv_description_myRequest)
    TextView tvDescription;

    @BindView(R.id.tv_bid_price_myRequest)
    TextView tvBidPrice;

    public MyRequestsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setAttributes(final Product product){

        tvProductName.setText(product.getName());
        tvPrice.setText(String.valueOf(product.getPrice()));
        tvDescription.setText(product.getDescription());
        tvBidPrice.setText("2");
    }

}
