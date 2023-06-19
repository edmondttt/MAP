package com.week1.cashregisterpart1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {
    private PurchasedProductAdapter adapter;
    private ListView listView;
    private Button backBtn;
    private AlertDialog.Builder builder;
    PurchasedProduct selectedProduct;
    int productQuantity;
    String productName;
    double productPrice, totalPrice;
    ArrayList<Product> Products;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        builder = new AlertDialog.Builder(this);
        listView = findViewById(R.id.historyListView);
        ArrayList<PurchasedProduct> purchasedProducts = (ArrayList<PurchasedProduct>) getIntent().getSerializableExtra("key");
        adapter = new PurchasedProductAdapter(this, purchasedProducts);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedProduct = adapter.getItem(position);
                productName = selectedProduct.getName();
                productQuantity = selectedProduct.getQuantity();
                productPrice = selectedProduct.getPrice();
                totalPrice = productQuantity*productPrice;

                builder.setMessage("Product :" + productName + "Quantity :" + productQuantity + "Price :" + totalPrice);
                AlertDialog alert = builder.create();
                alert.setTitle("Purchase Details");
                alert.show();
            }
        });


        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent Intent = new Intent(HistoryActivity.this, MainActivity.class);
                startActivity(Intent);
            }
        });

    }
}