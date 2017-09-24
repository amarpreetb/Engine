package com.AMax.Engine;

import java.awt.*;
import java.awt.image.DataBufferInt;
import com.AMax.Engine.gfx.Image;

public class Renderer {

    private  int pixelWidth, pixelHeight;
    private  int[] pixel;

    public Renderer(GameContainer gc){

        pixelWidth = gc.getWidth();
        pixelHeight = gc.getHeight();

        pixel = ((DataBufferInt)gc.getWindow().getImage().getRaster().getDataBuffer()).getData();


    }

    public void clear(){

        for (int i = 0;i < pixel.length; i++){
            pixel[i] = 0;
        }
    }

    public void setPixel(int x, int y, int value){

        if((x < 0 || x >= pixelWidth || y < 0 || y >= pixelHeight || value == 0xffff00ff)){
            return;
        }

        pixel[x + y * pixelWidth] = value;
    }

    public void drawImage(Image image, int offX, int offY){
    	
    	int xPos = 0;
    	int yPos = 0;
    	int widthPos = image.getWidth();
    	int heightPos = image.getHeight();

        if(offX < -image.getWidth()){
            return;
        }

        if(offY < -image.getHeight()){
            return;
        }

        if(offX >= pixelWidth){
            return;
        }

        if(offY >= pixelHeight){
            return;
        }

    	if(offX < 0){
    	    xPos -= offX;
        }

        if(offY < 0){
            yPos -= offY;
        }


        if(widthPos + offX >= pixelWidth){
    	    widthPos -= widthPos + offX - pixelWidth;
        }
    	
    	if(heightPos + offY >= pixelHeight){
    	    heightPos -= heightPos + offY - pixelHeight;
        }
    	
        for(int y = yPos; y < heightPos; y++){
            for(int x = xPos; x < widthPos; x++){
                setPixel(x + offX, y  +offY, image.getPixel()[x + y * image.getWidth()]);
            }

        }
    }
}
