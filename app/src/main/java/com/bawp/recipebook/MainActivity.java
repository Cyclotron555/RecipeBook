package com.bawp.recipebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button createRecipeBtn;

    //Open New Recipe page / New Recipe button Event handler
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createRecipeBtn = (Button) findViewById(R.id.button);
        createRecipeBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                openNewRecipeActivity();
            }
        });
    }
    //Method that runs on New Recipe button click
    public void openNewRecipeActivity(){
        Intent intent = new Intent(this, NewRecipeActivity.class);
        startActivity(intent);
    }
}