package com.example.cmpt276assignment.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Game {
    private LocalDateTime creationTime;
    private List<player_score> players;
    private int highScore;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Game(List<player_score> players){
        creationTime = LocalDateTime.now();
        this.players = players;

        //finds the highest score among all the players
        highScore = players.get(0).getScore();
        for(int x = 1; x < players.size(); x++){
            if(players.get(x).getScore() > highScore){
                highScore = players.get(x).getScore();
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public String toString() {

        StringBuilder output = new StringBuilder();

        output.append(getCreationTime());

        output.append(" - ");

        //checks for a single or multiple winners, prints a message accordingly
        int winnerIndex = 1;
        boolean fCheck = true;
        for(int x = 0; x < players.size(); x++){
            if(players.get(x).getScore() == highScore){
                if(fCheck){
                    fCheck = false;
                    winnerIndex = x + 1;
                }else{
                    fCheck = true;
                    break;
                }
            }
        }

        if(fCheck){
            output.append("Tie game");
        }else{
            output.append("Player " + winnerIndex + " won");
        }

        output.append(": ");

        //prints out the score summarization
        for(int x = 0; x < players.size(); x++){
            output.append(players.get(x).getScore());
            if(x + 1 < players.size()){
                output.append(" vs ");
            }
        }



        return output.toString();
    }

    //outputs a custom formatted creation time string
    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getCreationTime(){
        DateTimeFormatter specialFormat = DateTimeFormatter.ofPattern("LLL dd @ hh:mma");
        return creationTime.format(specialFormat);
    }


    public player_score getPlayer(int index) {
        return players.get(index);
    }
}
