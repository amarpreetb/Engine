package com.AMax.Engine;

import java.awt.*;
import java.awt.image.DataBufferInt;
import com.AMax.Engine.gfx.Image;
import com.AMax.Engine.gfx.ImageTile;
import com.AMax.Engine.gfx.Font;

public class Renderer {

    private  int pixelWidth, pixelHeight;
    private  int[] pixel;
    private Font font =  Font.STANDARD;

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

    public void drawText(String text, int offX, int offY, int color){
        text = text.toUpperCase();
        int offset = 0;

        Image fontImage = font.getFontImage();

        for (int i = 0; i < text.length(); i++ ){
            int unicode = text.codePointAt(i) - 32;

            for(int y = 0; y < fontImage.getHeight(); y++){
                for(int x = 0; x < font.getWidths()[unicode]; x++){
                    if(font.getFontImage().getPixel()[(x + font.getOffset()[unicode]) + y * font.getFontImage().getWidth()] == 0xffffffff){
                        setPixel(x + offX + offset, y + offY, color);
                    }
                }
            }
            offset += font.getWidths()[unicode];
        }
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

    public void drawImageTile(ImageTile image, int offX, int offY, int tileX, int tileY){
        int xPos = 0;
        int yPos = 0;
        int widthPos = image.getTileWidth();
        int heightPos = image.getTileHeight();

        if(offX < -image.getTileWidth()){
            return;
        }

        if(offY < -image.getTileHeight()){
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
                setPixel(x + offX, y  +offY, image.getPixel()[(x + tileX * image.getTileWidth()) + (y + tileY * image.getTileWidth()) * image.getTileWidth()]);
            }

        }
    }
}
