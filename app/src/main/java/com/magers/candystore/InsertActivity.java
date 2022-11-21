package com.magers.candystore;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class InsertActivity extends AppCompatActivity {  //will hold methods when hit button

    private DatabaseManager dbManager; //reference to db manager so that methods within can be referenced

    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager = new DatabaseManager(this);
        setContentView(R.layout.activity_insert);
    }

    public void insert(View v) {

        //Retrieve name and price
        Log.w("InsertActivity", "Insert Button Pushed");
        EditText nameET = findViewById(R.id.input_name);
        EditText priceET = findViewById(R.id.input_price);
        String name = nameET.getText().toString();
        String priceString = priceET.getText().toString();

        //insert into database
        try {

            double price = Double.parseDouble(priceString);
            Candy candy = new Candy(0, name, price);
            dbManager.insert(candy); //call insert method
            Toast.makeText(this, "Candy Added", Toast.LENGTH_SHORT).show(); //pass instance of candy object
        }
        catch (NumberFormatException nfe) {
            Toast.makeText(this, "Price Error", Toast.LENGTH_LONG).show(); //pass instance of candy object
        }

        //clear data
        nameET.setText(" ");
        priceET.setText(" ");
    }

    public void goBack(View view) {

        this.finish();

    }


}
