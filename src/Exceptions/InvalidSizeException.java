package Exceptions;

import Model.Application;
import Viewer.GameOver;

public class InvalidSizeException extends Exception {
    public InvalidSizeException() {
        super();
        System.out.println("You ate a bad apple");
        GameOver gameOver = new GameOver(Application.GAMEOVER);
        Application.getApp().addState(gameOver);
        gameOver.setGameOverMessage("You ate a bad apple");
        Application.getApp().enterState(gameOver.getID());
    }
}
