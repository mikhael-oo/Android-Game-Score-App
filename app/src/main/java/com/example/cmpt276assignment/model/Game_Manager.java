package com.example.cmpt276assignment.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class Game_Manager {
    private static List<Game> games = new ArrayList<>();

    private Game_Manager(){

    }

    //singleton code
    private static Game_Manager instance;
    public static Game_Manager getInstance(){
        if(instance == null){
            instance = new Game_Manager();
        }
        return instance;
    }

    public Game getGame(int index){
        return games.get(index);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addGame(List<player_score> players){
        Game newGame = new Game(players);
        games.add(newGame);
    }

    public int currentGameNum(){
        return games.size();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getInfo(int gameNum){
        if(gameNum >= games.size()){
            return "empty";
        }else{
            return games.get(gameNum).toString();
        }
    }

    //potentially return a value in case of removal fail?
    public void removeGame(int gameNum){
        games.remove(gameNum);
        //the index should be checked previously to ensure safe values
    }

    //overwrite a game at an index
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setGames(int index, List<player_score> scores) {
        Game newGame = new Game(scores);
        games.set(index, newGame);
    }
}
