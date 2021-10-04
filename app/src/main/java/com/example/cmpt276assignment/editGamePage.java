package com.example.cmpt276assignment;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.GameManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cmpt276assignment.model.Game;
import com.example.cmpt276assignment.model.Game_Manager;
import com.example.cmpt276assignment.model.player_score;

import org.w3c.dom.Text;

import java.util.ArrayList;

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

        //checks the inputs provided by the user
        checkInputs();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu
        getMenuInflater().inflate(R.menu.menu_edit_page, menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case R.id.save_bar: //refers to the save button
                saveCheck();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setup() {
        int x = index;
        Game game = gameManager.getGame(x);
        EditText player1card = findViewById(R.id.editPlayer1Cards);
        player1card.setText(Integer.toString(game.getPlayer(0).getCardSum()));
        //editParam(player1card);
        EditText player1wager = findViewById(R.id.editPlayer1Wagers);
        player1wager.setText(Integer.toString(game.getPlayer(0).getWagerTotal()));
        EditText player1points = findViewById(R.id.editPlayer1Points);
        player1points.setText(Integer.toString(game.getPlayer(0).getPointSum()));
        TextView player1total = findViewById(R.id.calculationDisplay1);
        player1total.setText(Integer.toString(game.getPlayer(0).getScore()));

        if (game.size() == 2) {
            EditText player2card = findViewById(R.id.editPlayer2Cards);
            player2card.setText(Integer.toString(game.getPlayer(1).getCardSum()));
            EditText player2wager = findViewById(R.id.editPlayer2Wagers);
            player2wager.setText(Integer.toString(game.getPlayer(1).getWagerTotal()));
            EditText player2points = findViewById(R.id.editPlayer2Points);
            player2points.setText(Integer.toString(game.getPlayer(1).getPointSum()));
            TextView player2total = findViewById(R.id.calculationDisplay2);
            player2total.setText(Integer.toString(game.getPlayer(1).getScore()));
        }
    }

    public static Intent makeIntent(Context c, int pos) {
       index = pos;

        return new Intent(c, editGamePage.class);
    }

    public void editParam(EditText et) {
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                et.setText(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    //checks and sets up all the listeners
    private void checkInputs() {
        ArrayList<ArrayList<EditText>> components = getPageComponents();

        for(int x = 0; x < components.size(); x++){
            for(int i = 0; i < components.get(x).size(); i++){
                listenerSetups(components.get(x).get(i), x, components);
            }
        }
    }

    //sets up a listener, listeners update when a focus change is detected
    private void listenerSetups(EditText target, int playerAssociation, ArrayList<ArrayList<EditText>> components){
        target.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus){
                if(!hasFocus){
                    if(fieldsCheck(playerAssociation, components)) {
                        scoreUpdate(playerAssociation, components);
                    }else{
                        TextView scoreComponent;
                        if(playerAssociation == 0){
                            scoreComponent = findViewById(R.id.calculationDisplay1);
                        }else{
                            scoreComponent = findViewById(R.id.calculationDisplay2);
                        }
                        scoreComponent.setText(R.string.calcPlaceholder);
                    }
                }
            }
        });
    }

    //checks if all fields are filled
    private boolean fieldsCheck(int player, ArrayList<ArrayList<EditText>> components){
        for(int x = 0; x < components.get(player).size(); x++){
            if(components.get(player).get(x).getText().toString().equals("")){
                return false;
            }
        }
        return true;
    }

    //checks the column to see if the entire column is cleared
    private boolean columnCheck(int player, ArrayList<ArrayList<EditText>> components){
        for(int x = 0; x < components.get(player).size(); x++){
            if(!components.get(player).get(x).getText().toString().equals("")){
                return false;
            }
        }
        return true;
    }

    //updates the score for a certain player
    private void scoreUpdate(int player, ArrayList<ArrayList<EditText>> components){
        TextView scoreComponent;
        if(player == 0){
            scoreComponent = findViewById(R.id.calculationDisplay1);
        }else{
            scoreComponent = findViewById(R.id.calculationDisplay2);
        }
        int var1 = getValues(components.get(player).get(0));
        int var2 = getValues(components.get(player).get(1));
        int var3 = getValues(components.get(player).get(2));
        scoreComponent.setText(player_score.getFinalScore(var1, var2, var3) + "");
    }

    //checks if the requirements for creating a game are passed
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void saveCheck(){
        ArrayList<ArrayList<EditText>> components = getPageComponents();


        if(fieldsCheck(0, components)){

            int var1 = getValues(components.get(0).get(0));
            int var2 = getValues(components.get(0).get(1));
            int var3 = getValues(components.get(0).get(2));
            ArrayList<player_score> players = new ArrayList<>();
            players.add(new player_score(var1, var2, var3));

            if(columnCheck(1, components)){
                gameManager.addGame(players);
                finish();
                Toast.makeText(this, "Game added!", Toast.LENGTH_SHORT).show();
                //Toast.makeText(addGamePage.this, "ONLY PLAYER 1", Toast.LENGTH_SHORT).show();
            }else if(fieldsCheck(1, components)){
                var1 = getValues(components.get(1).get(0));
                var2 = getValues(components.get(1).get(1));
                var3 = getValues(components.get(1).get(2));
                players.add(new player_score(var1, var2, var3));
                gameManager.addGame(players);
                finish();
                Toast.makeText(this, "Game added!", Toast.LENGTH_SHORT).show();
                //Toast.makeText(addGamePage.this, "BOTH PLAYERS", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(editGamePage.this, "Error, please check player 2 inputs", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(editGamePage.this, "Error, please check player 1 inputs", Toast.LENGTH_SHORT).show();
        }
    }

    //gets the text and parses the value into an integer
    private int getValues(EditText component){
        return Integer.parseInt(component.getText().toString());
    }

    //gets all the components on the page and returns it as an array (2d)
    private ArrayList<ArrayList<EditText>> getPageComponents(){
        ArrayList<ArrayList<EditText>> components = new ArrayList<>();

        ArrayList<EditText> temp = new ArrayList<>();
        temp.add(findViewById(R.id.editPlayer1Cards));
        temp.add(findViewById(R.id.editPlayer1Points));
        temp.add(findViewById(R.id.editPlayer1Wagers));
        components.add(temp);

        ArrayList<EditText> temp2 = new ArrayList<>();
        temp2.add(findViewById(R.id.editPlayer2Cards));
        temp2.add(findViewById(R.id.editPlayer2Points));
        temp2.add(findViewById(R.id.editPlayer2Wagers));
        components.add(temp2);

        return components;
    }
}