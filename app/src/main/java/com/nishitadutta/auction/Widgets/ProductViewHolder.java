package com.nishitadutta.auction.Widgets;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.nishitadutta.auction.Objects.Product;
import com.nishitadutta.auction.R;

import org.androidannotations.annotations.Bean;

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

    public ProductViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setAttributes(Product product){
        tvProductName.setText(product.getName());
        tvPrice.setText(String.valueOf(product.getPrice()));
        tvDescription.setText(product.getDescription());
    }
}
