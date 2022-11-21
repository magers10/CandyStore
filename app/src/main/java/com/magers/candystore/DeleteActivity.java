package com.magers.candystore;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DeleteActivity extends AppCompatActivity {

    private DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbManager = new DatabaseManager(this);
        updateView();
    }

    //Build a view dynamically with all candies
    private void updateView() {
        ArrayList<Candy> candies = dbManager.selectAll();
        RelativeLayout layout = new RelativeLayout(this);
        ScrollView scrollView = new ScrollView(this);
        RadioGroup group = new RadioGroup(this);

        for(Candy candy : candies) {
            RadioButton rb = new RadioButton(this);
            rb.setId(candy.getId());
            rb.setText(candy.toString());
            group.addView(rb);
        }
        //set up event handling
        RadioButtonHandler rbh = new RadioButtonHandler();
        group.setOnCheckedChangeListener(rbh);

        //create back button
        Button backButton = new Button(this);
        backButton.setText("Back");

        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { //what happens when Back button is clicked
                DeleteActivity.this.finish();
            }
        });

        scrollView.addView(group);
        layout.addView(scrollView);

        //add a back button at the bottom of view
        RelativeLayout.LayoutParams params
                = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        layout.addView(backButton, params);

        setContentView(layout);


    }

    private class RadioButtonHandler implements RadioGroup.OnCheckedChangeListener { //listens for what is checked from list of candies
        @Override
        public void onCheckedChanged(RadioGroup group, int checkId) {
            //delete candy from db
            dbManager.deleteById(checkId);
            Toast.makeText(DeleteActivity.this, "Candy deleted:",
                    Toast.LENGTH_SHORT).show();

            //update screen
            updateView();

        }
    }
}