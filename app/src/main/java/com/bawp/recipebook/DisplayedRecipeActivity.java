package com.bawp.recipebook;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayedRecipeActivity extends AppCompatActivity {

    EditText txtTitle;
    EditText txtBody;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displayed_recipe);
        txtTitle = findViewById(R.id.dispRecipeTitle);
        txtBody = findViewById(R.id.dispRecipeBody);
        AllRecipesActivity allRecipesActivity = new AllRecipesActivity();
        txtTitle.setText(allRecipesActivity.clickedRecipe);
        txtBody.setText(allRecipesActivity.clickedRecipe);
        //Toast.makeText(DisplayedRecipeActivity.this, allRecipesActivity.clickedRecipe, Toast.LENGTH_LONG).show();
    }
}
