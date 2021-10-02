package com.example.cmpt276assignment.model;

public class player_score {

    private int score = 0;

    private int cardSum;
    private int pointSum;
    private int wagerTotal;

    public player_score(int cardNum, int pointSum, int wagerCount){

        this.cardSum = cardNum;
        this.pointSum = pointSum;
        this.wagerTotal = wagerCount;

        calculateFinalScore();
    }

    private void calculateFinalScore(){
        if(cardSum < 0 || pointSum < 0 || wagerTotal < 0){
            throw new IllegalArgumentException();
        }

        //card presence check
        if(cardSum == 0) {
            return;
        }

        score = pointSum;

        //point deduction
        score -= 20;

        //wager check
        if(wagerTotal > 0){
            score *= wagerTotal + 1;
        }

        //cards check and bonus
        if(cardSum > 7){
            score += 20;
        }
    }

    public int getCardSum() {
        return cardSum;
    }

    public void setCardSum(int cardSum) {
        this.cardSum = cardSum;
        calculateFinalScore();
    }

    public int getPointSum() {
        return pointSum;
    }

    public void setPointSum(int pointSum) {
        this.pointSum = pointSum;
        calculateFinalScore();
    }

    public int getWagerTotal() {
        return wagerTotal;
    }

    public void setWagerTotal(int wagerTotal) {
        this.wagerTotal = wagerTotal;
        calculateFinalScore();
    }

    public int getScore() {
        return score;
    }
}
