package com.bawp.recipebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.Menu;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button createRecipeBtn;
    private Button allRecipesBtn;
    private Button btnSaveAndExit;


    //Open New Recipe page / New Recipe button Event handler
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createRecipeBtn = (Button) findViewById(R.id.button);
        allRecipesBtn = (Button) findViewById(R.id.btnAllRecipes);
        btnSaveAndExit = (Button) findViewById(R.id.exitBtn);
        //Listener for new recipe button
        createRecipeBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                openNewRecipeActivity();
            }
        });
        //Listener for all recipes button
        allRecipesBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) { openAllRecipesActivity(); }
        });
        //Listener for Save and Exit button | Shut down app
        btnSaveAndExit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });

    }


    //Method that runs on New Recipe button click
    public void openNewRecipeActivity(){
        Intent intent = new Intent(this, NewRecipeActivity.class);
        startActivity(intent);
    }

    //Method that runs on All Recipes button click | Posts recipes on all recipes page
    public void openAllRecipesActivity(){
        Intent intent = new Intent(this, AllRecipesActivity.class);
        startActivity(intent);
    }
}