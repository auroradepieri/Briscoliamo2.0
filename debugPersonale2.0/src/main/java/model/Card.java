package model;

public class Card {
    private int value; //numero della carta
    private String suit; //seme della carta

    public Card(int value, String suit){
        this.value = value;
        this.suit = suit;
    }
    int getValue(){return value;}
    String getSuit(){return suit;}

    //per effettuare la stampa del numero + il seme della carta:
    @Override
    public String toString(){
        return value + "_" +suit;
    }
}
