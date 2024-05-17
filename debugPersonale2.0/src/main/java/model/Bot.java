package model;

import java.util.Random;

public class Bot {
    private  String botName;

    public Bot(){
        this.botName = generateName();

    }
    public String getName(){
        return botName;
    }

    private static String generateName(){
        final String[] animals = {"Cat", "Dog","Sheep", "Cow", "Horse", "Chicken", "Pig", "Bee","Rabbit", "Duck"};
        final String[] adjectives = {"Good", "Hot", "Cold", "Small", "Free", "Hungry", "Happy", "Old", "Cute","Young"};

        final Random random = new Random();
        int i = random.nextInt(adjectives.length);
        int j = random.nextInt(animals.length);

        String n = adjectives[i] + animals[j] ;
        return n;

    }

}
