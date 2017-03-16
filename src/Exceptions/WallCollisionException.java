package Exceptions;


import Model.Application;
import Viewer.GameOver;

public class WallCollisionException extends Exception {
    public WallCollisionException(){
        super();
        System.out.println("You hit a wall !");
        GameOver gameOver = new GameOver(Application.GAMEOVER);
        Application.getApp().addState(gameOver);
        gameOver.setGameOverMessage("You hit a wall !");
        Application.getApp().enterState(gameOver.getID());
    }
}

