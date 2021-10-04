package com.example.cmpt276assignment;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cmpt276assignment.model.Game;
import com.example.cmpt276assignment.model.Game_Manager;

import org.w3c.dom.Text;

public class editGamePage extends AppCompatActivity {
    private Game_Manager gameManager;
    private static int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_game);

        gameManager = Game_Manager.getInstance();
        setup();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.edit_page);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu
        getMenuInflater().inflate(R.menu.menu_edit_page, menu);
        return true;
    }

    private void setup() {
        int x = index;
        Game game = gameManager.getGame(x);
        EditText player1card = findViewById(R.id.editPlayer1Cards);
        player1card.setText(Integer.toString(game.getPlayer(0).getCardSum()));
        EditText player1wager = findViewById(R.id.editPlayer1Wagers);
        player1wager.setText(Integer.toString(game.getPlayer(0).getWagerTotal()));
        EditText player1points = findViewById(R.id.editPlayer1Points);
        player1points.setText(Integer.toString(game.getPlayer(0).getPointSum()));
        TextView player1total = findViewById(R.id.calculationDisplay1);
        player1total.setText(Integer.toString(game.getPlayer(0).getScore()));

        EditText player2card = findViewById(R.id.editPlayer2Cards);
        player2card.setText(Integer.toString(game.getPlayer(1).getCardSum()));
        EditText player2wager = findViewById(R.id.editPlayer2Wagers);
        player2wager.setText(Integer.toString(game.getPlayer(1).getWagerTotal()));
        EditText player2points = findViewById(R.id.editPlayer2Points);
        player2points.setText(Integer.toString(game.getPlayer(1).getPointSum()));
        TextView player2total = findViewById(R.id.calculationDisplay2);
        player2total.setText(Integer.toString(game.getPlayer(1).getScore()));

    }

    public static Intent makeIntent(Context c, int pos) {
       index = pos;

        return new Intent(c, editGamePage.class);
    }
}