package com.AMax.game;

import com.AMax.Engine.AbstractGame;
import com.AMax.Engine.GameContainer;
import com.AMax.Engine.Renderer;

import java.awt.*;
import java.awt.event.KeyEvent;
import com.AMax.Engine.gfx.Image;
import com.AMax.Engine.gfx.ImageTile;


public class GameManager extends AbstractGame{

    private ImageTile image;


    public GameManager(){

        image = new ImageTile("/Untitled.png", 16, 16);
    }

    @Override
    public void update(GameContainer gc, float dt) {
        if (gc.getInput().isKey(KeyEvent.VK_A)){
            System.out.println("A");
        }

        temp += dt * 20;
        if(temp > 3){
            temp = 0;
        }
    }
    float temp = 0;

    @Override
    public void render(GameContainer gc, Renderer r) {
    	
    	r.drawImageTile(image, gc.getInput().getMouseX() - 16, gc.getInput().getMouseY() - 16, (int)temp, 0);

    }

    public static void main(String[] args){
        GameContainer gc = new GameContainer(new GameManager());
        gc.start();
    }
}
