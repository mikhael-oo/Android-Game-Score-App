package com.example.cmpt276assignment.model;

public class player_score {

    private int score = 0;

    private int cardSum;
    private int pointSum;
    private int wagerTotal;

    public player_score(){

    }

    public player_score(int cardNum, int pointSum, int wagerCount){

        reSetScore(cardNum, pointSum, wagerCount);

        score = getFinalScore(this.cardSum, this.pointSum, this.wagerTotal);
    }

    public void reSetScore(int newCards, int newPoints, int newWagers){
        this.cardSum = newCards;
        this.pointSum = newPoints;
        this.wagerTotal = newWagers;

        score = getFinalScore(cardSum, pointSum, wagerTotal);
    }

    public static int getFinalScore(int cards, int points, int wagers){
        if(cards < 0 || points < 0 || wagers < 0){
            throw new IllegalArgumentException();
        }

        //card presence check
        if(cards == 0) {
            return 0;
        }

        int output = points;

        //point deduction
        output -= 20;

        //wager check
        if(wagers > 0){
            output *= wagers + 1;
        }

        //cards check and bonus
        if(cards > 7){
            output += 20;
        }

        return output;
    }

    public int getCardSum() {
        return cardSum;
    }

    public int getPointSum() {
        return pointSum;
    }

    public int getWagerTotal() {
        return wagerTotal;
    }

    public int getScore() {
        return score;
    }
}
