package com.nishitadutta.auction.Widgets;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

    public void setAttributes(Product product){
        tvProductName.setText(product.getName());
        tvPrice.setText(String.valueOf(product.getPrice()));
        tvDescription.setText(product.getDescription());
        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: go to another activity pass Product in intent
            }
        });
    }
}
