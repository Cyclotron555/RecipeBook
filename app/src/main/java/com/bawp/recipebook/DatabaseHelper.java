package com.bawp.recipebook;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String RECIPES_TABLE = "RECIPES_TABLE";
    public static final String RECIPE_TITLE = "RECIPE_TITLE";
    public static final String RECIPE_BODY = "RECIPE_BODY";
    public static final String COLUMN_ACTIVE_RECIPE = "COLUMN_ACTIVE_RECIPE";
    public static final String RECIPES_DB = "recipes.db";

    //Constructor
    public DatabaseHelper(@Nullable Context context) {
        super(context, RECIPES_DB, null, 1);
    }

    //On creation create table RECIPES_TABLE
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + RECIPES_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + RECIPE_TITLE + " TEXT, " + RECIPE_BODY + " TEXT " + COLUMN_ACTIVE_RECIPE + " TEXT)";
        db.execSQL((createTable));
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
            return "Failed";
        else
            return "Successfully Inserted";
    }


}
