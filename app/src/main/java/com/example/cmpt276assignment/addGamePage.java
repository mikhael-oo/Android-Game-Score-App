package com.example.cmpt276assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.cmpt276assignment.model.Game_Manager;

public class addGamePage extends AppCompatActivity {

    private Game_Manager gameSystem;

    public static Intent makeLaunchIntent(Context c){
        return new Intent(c, addGamePage.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_game_page);

        gameSystem = Game_Manager.getInstance();

        //presets the top toolbar
        Toolbar mainToolBar = (Toolbar) findViewById(R.id.mainToolbar);
        setSupportActionBar(mainToolBar);
        //feature adjustment
        getSupportActionBar().setTitle(R.string.game_add);

        //allows for a back button to be rendered on the toolbar
        ActionBar AB = getSupportActionBar();
        AB.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //inflates the menu
        getMenuInflater().inflate(R.menu.menu_edit_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case R.id.save_bar: //refers to the save button
                //placeholder for later actual code
                Toast.makeText(this, "Game added!", Toast.LENGTH_SHORT).show();
                return true;
            case android.R.id.home: //refers to the back button
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}