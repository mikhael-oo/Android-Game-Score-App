package com.example.cmpt276assignment;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.cmpt276assignment.model.Game;
import com.example.cmpt276assignment.model.Game_Manager;
import com.example.cmpt276assignment.model.player_score;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Game_Manager gameSystem;

    private List<Game> allGames = new ArrayList<>();

    ListView targetList;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameSystem = Game_Manager.getInstance();

        //FAB interaction, opens up the addGamePage activity upon click
        FloatingActionButton addGameButton = findViewById(R.id.createGameButton);
        addGameButton.setOnClickListener(view -> {
            Intent i = addGamePage.makeLaunchIntent(MainActivity.this);
            startActivity(i);
        });

        createTestInput();
        //renders the contents of the gameManager to the list
        renderList();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void renderList() {
        ArrayList<String> outputList = new ArrayList<>();
        for(int x = 0; x < gameSystem.currentGameNum(); x++){
            outputList.add(gameSystem.getInfo(x));
        }

        //adapts the information from the list to prepare for display on the device
        ArrayAdapter strListAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, outputList);

        //renders the information
        targetList = (ListView) findViewById(R.id.gameDisplay);
        targetList.setAdapter(strListAdapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createTestInput(){
        List<player_score> testInputs = new ArrayList<>();
        player_score input = new player_score(25, 40, 3);
        testInputs.add(input);
        for(int x = 1; x < 2; x++){
            input = new player_score(x * 25, x * 40, x + 1);
            testInputs.add(input);
        }
        gameSystem.addGame(testInputs);
        testInputs = new ArrayList<>();
        for(int x = 0; x < 2; x++){
            input = new player_score(x * 10, x * 20, x + 1);
            testInputs.add(input);
        }
        gameSystem.addGame(testInputs);
        testInputs = new ArrayList<>();
        for(int x = 0; x < 2; x++){
            input = new player_score(10, 25, 1);
            testInputs.add(input);
        }
        gameSystem.addGame(testInputs);
    }
}