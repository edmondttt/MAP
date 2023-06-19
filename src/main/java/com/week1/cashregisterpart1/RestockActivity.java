package com.week1.cashregisterpart1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class RestockActivity extends AppCompatActivity {
    private Button backBtn, OKBtn;
    private EditText editTextProductNumber;
    String userInput;
    private AlertDialog.Builder builder;
    private ListView listView;
    private ProductAdapter adapter;
    Product selectedProduct;
    int productQuantity;
    String productName;
    double productPrice;
    ArrayList<Product> Products;
    private TextView txtProductName,txtProductQuantity, txtTotalPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_restock);

        txtProductName = findViewById(R.id.textviewProductName);
        txtProductQuantity = findViewById(R.id.textviewQuantity);
        txtTotalPrice = findViewById(R.id.textviewTotalAmount);
        builder = new AlertDialog.Builder(this);

        listView = findViewById(R.id.product_listview);
        Products = (ArrayList<Product>) getIntent().getSerializableExtra("key2");
        adapter = new ProductAdapter(this, Products);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected product from the adapter
                selectedProduct = adapter.getItem(position);
                Products = new ArrayList<>();
                // Display the product details
                productName = selectedProduct.getName();
                productQuantity = selectedProduct.getQuantity();
                productPrice = selectedProduct.getPrice();
                txtProductName.setText(productName);
                txtProductQuantity.setText(String.valueOf(productQuantity));
                txtTotalPrice.setText(String.valueOf(productPrice));

            }
        });

        editTextProductNumber = findViewById(R.id.number_picker);
        editTextProductNumber.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    // Handle the action here
                    userInput = editTextProductNumber.getText().toString();
                    // Do something with the entered text


                    return true;
                }
                return false;
            }
        });


        OKBtn = findViewById(R.id.buttonOk);
        OKBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Setting message manually and performing action on button click
                builder.setMessage("Do you want to confirm restock?th\n").setTitle("Confirm Restock")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                selectedProduct.setQuantity(productQuantity + Integer.parseInt(userInput));
                                adapter.notifyDataSetChanged();
                                Toast.makeText(RestockActivity.this, "Restocked!", Toast.LENGTH_SHORT).show();

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                editTextProductNumber.setText("");
                            }
                        });
                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.show();
            }

    });




        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent Intent = new Intent(RestockActivity.this, MainActivity.class);
                Intent.putExtra("key2", Products);
                startActivity(Intent);
            }
        });

    }

}