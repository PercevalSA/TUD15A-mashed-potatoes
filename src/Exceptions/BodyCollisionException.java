package Exceptions;

import Model.Application;

public class BodyCollisionException extends Exception {
    public BodyCollisionException(){
        super();
        System.out.println("You ate yourself");
        Application.getApp().getGameOver().setGameOverMessage("You ate yourself !");
        Application.getApp().enterState(Application.getApp().getGameOver().getID());
    }
}

