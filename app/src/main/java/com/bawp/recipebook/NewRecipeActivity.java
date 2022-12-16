package com.bawp.recipebook;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewRecipeActivity extends AppCompatActivity {
    //buttons and text fields
    Button btnSubmit;
    EditText recipeName;
    EditText  recipeBody;

    //On Creation event
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_recipe);
        btnSubmit = findViewById(R.id.btnSubmit);
        recipeName = findViewById(R.id.txtTitle);
        recipeBody = findViewById(R.id.txtBody);

        //Create Submit Button Listener
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RecipeModel recipeModel;
                try{
                    recipeModel = new RecipeModel(-1, recipeName.getText().toString(), recipeBody.getText().toString());
                    Toast.makeText(NewRecipeActivity.this, recipeModel.toString(), Toast.LENGTH_LONG).show();
                }
                catch (Exception e){
                    Toast.makeText(NewRecipeActivity.this, "Error Creating Customer!", Toast.LENGTH_LONG).show();
                    recipeModel = new RecipeModel(-1, "error", recipeBody.getText().toString());
                }

                DatabaseHelper dbHelper = new DatabaseHelper(NewRecipeActivity.this);
                String success = dbHelper.addRecord(recipeModel);
                //Toast.makeText(NewRecipeActivity.this, "Success= " + success, Toast.LENGTH_SHORT).show();
                recipeName.setText("");
                recipeBody.setText("");
            }
        });
    }
}