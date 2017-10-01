package com.AMax.game;

import com.AMax.Engine.AbstractGame;
import com.AMax.Engine.GameContainer;
import com.AMax.Engine.Renderer;

import java.awt.*;
import java.awt.event.KeyEvent;

import com.AMax.Engine.audio.SoundClip;
import com.AMax.Engine.gfx.Image;
import com.AMax.Engine.gfx.ImageTile;
import com.AMax.game.GameManager;


public class GameManager extends AbstractGame{

    private Image image;
    private Image image2;
    private SoundClip clip;


    public GameManager(){

        image = new ImageTile("/index.png", 16, 16);
        image2 = new Image("/Untitled.png");
        clip = new SoundClip("/cena.wav");
        image2.setAlpha(true);
        //clip.setVolume(-20); //Volume Adjuster
    }

    @Override
    public void update(GameContainer gc, float dt) {
    	if (gc.getInput().isKey(KeyEvent.VK_A)){
            //System.out.println("A");
            clip.play();
        }

        temp += dt;
        if(temp > 3){
            temp = 0;
        }
    }
    float temp = 0;

    @Override
    public void render(GameContainer gc, Renderer r) {
    	
    	r.drawImage(image2, 10, 10);
    	r.drawImage(image, gc.getInput().getMouseX(), gc.getInput().getMouseY());
        //r.drawFillRect(10, 10, 32, 32, 0xffffccff);
        //r.drawFillRect(gc.getInput().getMouseX() - 16, gc.getInput().getMouseY() - 16, 32, 32, 0xffffccff);
    }

    public static void main(String[] args){
        GameContainer gc = new GameContainer(new GameManager());
        gc.setWidth(320);
        gc.setWidth(240);
        gc.setScale(3f);
        gc.start();
    }
}
