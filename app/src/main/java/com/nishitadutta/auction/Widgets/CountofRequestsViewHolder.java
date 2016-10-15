package com.nishitadutta.auction.Widgets;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.nishitadutta.auction.Activities.CountOfRequestsActivity;
import com.nishitadutta.auction.Objects.Product;
import com.nishitadutta.auction.Objects.Request;
import com.nishitadutta.auction.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by madhu_000 on 10/14/2016.
 */

public class CountofRequestsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_price_product_request)
    TextView tvPrice;


    public CountofRequestsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setAttributes(final Request request){

        //tvPrice.setText(String.valueOf(request.getPrice()));
        tvPrice.setText("1");

    }
}
