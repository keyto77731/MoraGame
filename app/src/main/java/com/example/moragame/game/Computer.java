package com.example.moragame.game;

import com.example.moragame.GameState;
import com.example.moragame.OnActionListener;

import java.util.Random;

public class Computer extends Player{
    private OnActionListener actionListener;
    public void AI(){
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        setMora(getRandomMora());

        actionListener.onAction(GameState.PLATER_ROUND);

    }
    public Computer(OnActionListener listener){
        actionListener = listener;
    }


    public static Mora getRandomMora(){
        //int index = new Random().nextInt(Mora.PAPER.ordinal()+1);
        int index = new Random().nextInt(Mora.NONE.ordinal());
        if(index==0){
            return Mora.SCISSOR;
        }else if(index==1){
            return Mora.ROCK;
        }else {
            return Mora.PAPER;
        }

    }
}
