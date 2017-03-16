package Exceptions;


import Model.Application;

public class WallCollisionException extends Exception {
    public WallCollisionException(){
        super();
        System.out.println("You hit a wall !");
        Application.getApp().getGameOver().setGameOverMessage("You hit a wall !");
        Application.getApp().enterState(Application.getApp().getGameOver().getID());
    }
}

