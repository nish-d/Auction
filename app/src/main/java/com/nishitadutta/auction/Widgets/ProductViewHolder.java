package com.nishitadutta.auction.Widgets;
import android.support.v7.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.nishitadutta.auction.Activities.BidProductActivity;
import com.nishitadutta.auction.Activities.BidProductActivity_;
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
    String childProductId;
    DatabaseReference mDatabaseReference;
    FirebaseRecyclerAdapter firebaseRecyclerAdapter;

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

    public void setAttributes(final Product product, final int position){

        tvProductName.setText(product.getName());
        tvPrice.setText(String.valueOf(product.getPrice()));
        tvDescription.setText(product.getDescription());

        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(v.getContext(), BidProductActivity_.class);
                intent.putExtra(Constants.EXTRA_NAME, tvProductName.getText());
                intent.putExtra(Constants.EXTRA_PRICE, tvPrice.getText());
                intent.putExtra(Constants.EXTRA_DESCRIPTION, tvDescription.getText());


                try {
                    final String childProductId = mDatabaseReference.getRef().child("Product").getKey();
                    //childProductId=firebaseRecyclerAdapter.getRef(position).child("Product").getKey();
                    System.out.print(product.getName() + product.getPrice() + product.getDescription() + childProductId);
                    intent.putExtra(Constants.EXTRA_PRODUCTID, childProductId);

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
