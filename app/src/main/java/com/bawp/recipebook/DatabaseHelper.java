package com.bawp.recipebook;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String RECIPES_TABLE = "RECIPES_TABLE";
    public static final String RECIPE_TITLE = "RECIPE_TITLE";
    public static final String RECIPE_BODY = "RECIPE_BODY";
    public static final String COLUMN_ACTIVE_RECIPE = "COLUMN_ACTIVE_RECIPE";
    public static final String RECIPES_DB = "recipes.db";
    public static final String ID = "ID";

    //Constructor
    public DatabaseHelper(@Nullable Context context) {
        super(context, RECIPES_DB, null, 1);
    }

    //On creation create table RECIPES_TABLE
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + RECIPES_TABLE + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + RECIPE_TITLE + " TEXT, " + RECIPE_BODY + " TEXT, " + COLUMN_ACTIVE_RECIPE + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS RECIPES_TABLE");
        onCreate(db);
    }

    //Add record into db recipe (title and body)
    public String addRecord(RecipeModel recipeModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(RECIPE_TITLE, recipeModel.getName());
        cv.put(RECIPE_BODY, recipeModel.getDescription());
        long result = db.insert("RECIPES_TABLE", null, cv);
        if(result == 1)
            return "Successfully Inserted";
        else
            return "Failure!";
    }

    //List of recipes | get recipes from DB
    public List<String> getRecipes(){
        List<String> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + RECIPES_TABLE;
        SQLiteDatabase db = this. getWritableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()){
            //Loop through recipes
            do{
                int recipeID = cursor.getInt(0);
                String recipeTitle = cursor.getString(1);
                //String recipeBody = cursor.getString(2);
                //I leave the description null | Just display the titles
                RecipeModel newRecipe = new RecipeModel(recipeID, recipeTitle, null);
                String recipeDisplay = newRecipe.getName();
                returnList.add(recipeDisplay);
            }
            while (cursor.moveToNext());

        }
        else{
            //Failure don't add anything to the list
        }
        cursor.close();
        db.close();
        return returnList;
    }
    //Deletes a recipe by name column
    public boolean deleteOne(String recipeModel){
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = ("DELETE FROM " + RECIPES_TABLE + " WHERE " + RECIPE_TITLE + "=\"" + recipeModel + "\";");
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()){
            return  true;
        }
        else {
            return false;
        }
    }

}
