package com.nishitadutta.auction.Widgets;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//import com.nishitadutta.auction.Activities.BidProductActivity_;
import com.nishitadutta.auction.Activities.BidProductActivity;
import com.nishitadutta.auction.Custom.Constants;
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
            public static final String TAG = "onProductViewHolderClick";

            @Override
            public void onClick(View v) {

                Intent intent=new Intent(v.getContext(), BidProductActivity.class);
                intent.putExtra(Constants.EXTRA_NAME, tvProductName.getText());
                intent.putExtra(Constants.EXTRA_PRICE, tvPrice.getText());
                intent.putExtra(Constants.EXTRA_DESCRIPTION, tvDescription.getText());
                String productId= product.getProductId();

                try {
                        System.out.print(product.getName() + product.getPrice() + product.getDescription() + productId);
                    intent.putExtra(Constants.EXTRA_PRODUCTID, productId);

                }
                catch (Exception e)
                {
                    System.out.print(e);
                }
                v.getContext().startActivity(intent);
            }
        });
    }
}
