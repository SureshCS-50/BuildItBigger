package com.example;

import java.util.Random;

public class JokeStore {
    private Random random = new Random();

//    Jokes taken from "http://www.short-funny.com/"

    public String getJoke(){
        String joke;
        switch (random.nextInt(4)) {
            case 0:
                joke = "Can a kangaroo jump higher than a house? Of course, a house doesn’t jump at all.";
                break;
            case 1:
                joke = "Of course I have a talent. I'm really good in bed. Sometimes I sleep more than 9 hours in one go.";
                break;
            case 2:
                joke = "It is important to make breaks between individual exercises. I personally stick to breaks of about 3-4 years.";
                break;
            default:
                joke = "My neighbors are listening to great music. Whether they like it or not.";
                break;
        }
        return joke;
    }
}
