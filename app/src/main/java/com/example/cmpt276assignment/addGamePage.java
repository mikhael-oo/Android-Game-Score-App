package com.example.cmpt276assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class addGamePage extends AppCompatActivity {

    public static Intent makeLaunchIntent(Context c){
        return new Intent(c, addGamePage.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_game_page);
    }
}