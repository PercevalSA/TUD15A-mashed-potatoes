package Exceptions;

import Model.Application;
import Viewer.GameOver;

public class BodyCollisionException extends Exception {
    public BodyCollisionException(){
        super();
        System.out.println("You ate yourself");
        GameOver gameOver = new GameOver(Application.GAMEOVER);
        Application.getApp().addState(gameOver);
        gameOver.setGameOverMessage("You ate yourself !");
        Application.getApp().enterState(gameOver.getID());
    }
}

