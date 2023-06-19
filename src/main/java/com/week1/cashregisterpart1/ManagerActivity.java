package com.week1.cashregisterpart1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;
import java.util.ArrayList;

public class ManagerActivity extends AppCompatActivity {

    private Button historyBtn, restockBtn, backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        historyBtn = findViewById(R.id.historyBtn);
        restockBtn = findViewById(R.id.restockBtn);
        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent Intent = new Intent(ManagerActivity.this, MainActivity.class);
                startActivity(Intent);
            }
        });
        historyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent Intent = new Intent(ManagerActivity.this, HistoryActivity.class);
                ArrayList<PurchasedProduct> purchasedProducts = (ArrayList<PurchasedProduct>) getIntent().getSerializableExtra("key");
                Intent.putExtra("key", purchasedProducts);
                startActivity(Intent);

            }
        });
        restockBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent Intent = new Intent(ManagerActivity.this, RestockActivity.class);
                ArrayList<Product> Products = (ArrayList<Product>) getIntent().getSerializableExtra("key2");
                Intent.putExtra("key2", Products);
                startActivity(Intent);

            }
        });

    }
}