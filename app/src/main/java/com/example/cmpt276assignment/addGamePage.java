package com.example.cmpt276assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

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
        getSupportActionBar().setTitle(R.string.game_add);
    }
}