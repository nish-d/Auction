package com.nishitadutta.auction.Widgets;
import android.support.v7.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nishitadutta.auction.Activities.BidProductActivity;
import com.nishitadutta.auction.Activities.MainActivity;
import com.nishitadutta.auction.Custom.Constants;
import com.nishitadutta.auction.Fragments.AllProductsFragment;
import com.nishitadutta.auction.Objects.Product;
import com.nishitadutta.auction.R;


import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Nishita on 06-10-2016.
 */

public class ProductViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_description)
    TextView tvDescription;

    @BindView(R.id.tv_product_name)
    TextView tvProductName;

    @BindView(R.id.tv_price)
    TextView tvPrice;

    @BindView(R.id.btn_request)
    Button btnRequest;

    public ProductViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        
    }

    public void setAttributes(final Product product){

        tvProductName.setText(product.getName());
        tvPrice.setText(String.valueOf(product.getPrice()));
        tvDescription.setText(product.getDescription());
        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(v.getContext(), BidProductActivity.class);
                intent.putExtra(Constants.EXTRA_NAME,product.getName());
                intent.putExtra(Constants.EXTRA_PRICE,product.getPrice() );
                intent.putExtra(Constants.EXTRA_DESCRIPTION,product.getDescription() );
                v.getContext().startActivity(intent);
            }
        });
    }
}
