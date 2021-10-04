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
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cmpt276assignment.model.Game_Manager;
import com.example.cmpt276assignment.model.player_score;

import java.util.ArrayList;

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

        //checks the inputs provided by the user for game creation
        checkInputs();
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

    private void checkInputs() {
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

        for(int x = 0; x < components.size(); x++){
            for(int i = 0; i < components.get(x).size(); i++){
                listenerSetups(components.get(x).get(i), x, components);
            }
        }
    }

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

    private boolean fieldsCheck(int player, ArrayList<ArrayList<EditText>> components){
        for(int x = 0; x < components.get(player).size(); x++){
            if(components.get(player).get(x).getText().toString().equals("")){
                return false;
            }
        }
        return true;
    }

    private void scoreUpdate(int player, ArrayList<ArrayList<EditText>> components){
        TextView scoreComponent;
        if(player == 0){
            scoreComponent = findViewById(R.id.calculationDisplay1);
        }else{
            scoreComponent = findViewById(R.id.calculationDisplay2);
        }
        int var1 = Integer.parseInt(components.get(player).get(0).getText().toString());
        int var2 = Integer.parseInt(components.get(player).get(1).getText().toString());
        int var3 = Integer.parseInt(components.get(player).get(2).getText().toString());
        scoreComponent.setText(player_score.getFinalScore(var1, var2, var3) + "");
    }
}