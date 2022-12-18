package com.bawp.recipebook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class AllRecipesActivity  extends AppCompatActivity {
    private ListView lvAllRecipes;
    private Button btnDelete;
    private Button btnOpen;
    public String clickedRecipe;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_recipes);
        //Assign the buttons by ID
        lvAllRecipes = (ListView) findViewById(R.id.allRecipesListView);
        btnDelete = (Button)findViewById(R.id.deleteRecipeBtn);
        btnOpen = (Button)findViewById(R.id.openRecipeBtn);
        //Display all recipes automatically on page load
        DatabaseHelper databaseHelper = new DatabaseHelper(AllRecipesActivity.this);
        List<String> allRecipes = databaseHelper.getRecipes();
        //Get all recipe names from the array adapter

        ArrayAdapter recipeArrayAdapter = new ArrayAdapter<String>(AllRecipesActivity.this,
                android.R.layout.simple_list_item_1, allRecipes);
        lvAllRecipes.setAdapter(recipeArrayAdapter);

        //Create listener for Open Selected button
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDisplayedRecipe();
            }
        });
        //Create listener for Delete Selected button
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDisplayedRecipe();
            }
        });
        //Listener for each item in list
        lvAllRecipes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                clickedRecipe = parent.getItemAtPosition(position).toString();
            }
        });
    }


    //Method runs on Open Selected button event
    public void openDisplayedRecipe() {
        Intent intent = new Intent(this, DisplayedRecipeActivity.class);
        startActivity(intent);
        DatabaseHelper dh = new DatabaseHelper(AllRecipesActivity.this);
    }

    //Method runs on Delete Selected item button event
    public void deleteDisplayedRecipe() {
        DatabaseHelper dh = new DatabaseHelper(AllRecipesActivity.this);
        dh.deleteOne(clickedRecipe);
        //Refresh the page
        DatabaseHelper databaseHelper = new DatabaseHelper(AllRecipesActivity.this);
        List<String> allRecipes = databaseHelper.getRecipes();
        ArrayAdapter recipeArrayAdapter = new ArrayAdapter<String>(AllRecipesActivity.this, android.R.layout.simple_list_item_1, allRecipes);
        lvAllRecipes.setAdapter(recipeArrayAdapter);
        //Inform user about deletion
        Toast.makeText(AllRecipesActivity.this, "Recipe was Deleted" + clickedRecipe, Toast.LENGTH_LONG).show();
        }

}
