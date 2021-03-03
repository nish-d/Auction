package com.nishitadutta.auction.Widgets;

import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.nishitadutta.auction.Activities.BidProductActivity_;
import com.nishitadutta.auction.Objects.Product;
import com.nishitadutta.auction.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by madhu_000 on 10/15/2016.
 */
public class MyRequestsViewHolder extends RecyclerView.ViewHolder {

    private static final String TAG ="Product Values" ;
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

        Log.e(TAG, "setAttributes: "+product.getProductId()+product.getName()+
                product.getPrice()+product.getDescription() );
        tvProductName.setText(product.getName());
        tvPrice.setText(String.valueOf(product.getPrice()));
        tvDescription.setText(product.getDescription());
        tvBidPrice.setText("2");
    }

}
