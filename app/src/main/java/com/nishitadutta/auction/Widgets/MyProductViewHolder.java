package com.nishitadutta.auction.Widgets;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.nishitadutta.auction.Activities.CountOfRequestsActivity;
import com.nishitadutta.auction.Custom.Constants;
import com.nishitadutta.auction.Objects.Product;
import com.nishitadutta.auction.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by madhu_000 on 10/14/2016.
 */
public class MyProductViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_myproduct_name)
    TextView tvProductName;

    @BindView(R.id.tv_price_myproduct)
    TextView tvPrice;

    @BindView(R.id.tv_description_myproduct)
    TextView tvDescription;

    @BindView(R.id.tv_count_of_bidders)
    TextView tvBiddersCount;

    public MyProductViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setAttributes(final Product product){

        tvProductName.setText(product.getName());
        tvPrice.setText(String.valueOf(product.getPrice()));
        tvDescription.setText(product.getDescription());
        final String productName=product.getName();
        final float price=product.getPrice();


        tvBiddersCount.setOnClickListener(new View.OnClickListener() {
            public static final String TAG = "onMyProductViewHolderClick";

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), CountOfRequestsActivity.class);
                String id= product.getProductId();
                intent.putExtra(Constants.EXTRA_PRODUCTID, id);
                intent.putExtra(Constants.EXTRA_NAME, productName);
                intent.putExtra(Constants.EXTRA_PRICE, price);
                v.getContext().startActivity(intent);
            }
        });
    }
}
