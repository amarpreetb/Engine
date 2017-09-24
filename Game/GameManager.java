package com.AMax.game;

import com.AMax.Engine.AbstractGame;
import com.AMax.Engine.GameContainer;
import com.AMax.Engine.Renderer;

import java.awt.*;
import java.awt.event.KeyEvent;
import com.AMax.Engine.gfx.Image;


public class GameManager extends AbstractGame{

    private Image image;


    public GameManager(){
        image = new Image("/Untitled.png");
    }

    @Override
    public void update(GameContainer gc, float dt) {
        if (gc.getInput().isKey(KeyEvent.VK_A)){
            System.out.println("A");
        }
    }

    @Override
    public void render(GameContainer gc, Renderer r) {
    	
    	r.drawImage(image, gc.getInput().getMouseX() - 32, gc.getInput().getMouseY() - 32);

    }

    public static void main(String[] args){
        GameContainer gc = new GameContainer(new GameManager());
        gc.start();
    }
}
