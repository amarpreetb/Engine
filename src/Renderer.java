package com.AMax.Engine;

import java.awt.*;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.AMax.Engine.gfx.Image;
import com.AMax.Engine.gfx.ImageRequest;
import com.AMax.Engine.gfx.ImageTile;
import com.AMax.Engine.gfx.Font;


public class Renderer {
	
	private Font font  = Font.STANDARD;
	private ArrayList<ImageRequest> imageRequest = new ArrayList<ImageRequest>();
	
    private  int pixelWidth, pixelHeight;
    private  int[] pixel;
    private  int[] zbuffer;
    private int zDepth = 0;
    private boolean processing = false;

    public Renderer(GameContainer gc){

        pixelWidth = gc.getWidth();
        pixelHeight = gc.getHeight();

        pixel = ((DataBufferInt)gc.getWindow().getImage().getRaster().getDataBuffer()).getData();
        zbuffer = new int[pixel.length];
        
    }

    public void clear(){

        for (int i = 0;i < pixel.length; i++){
            pixel[i] = 0;
            zbuffer[i] = 0;        }
    }
    
    public void process() {
    	
    	processing = true;
    	
    	Collections.sort(imageRequest, new Comparator<ImageRequest>() {

			@Override
			public int compare(ImageRequest i0, ImageRequest i1) {
				
				if(i0.zDepth < i1.zDepth) {
					return 1;
				}
				if () {
					
				}
				
				return 0;
			}
    		
    	});
    	for(int i = 0; i < imageRequest.size(); i++) {
    		setzDepth(ir.zDepth);
    		drawImage(ir.image, ir.offX, ir.offY);
    		
    	}
    	imageRequest.clear();
    }
	public void setPixel(int x, int y, int value){
    	
		int alpha  = ((value >> 24) & 0xff);
    	int index = x + y * pixelWidth;
    	
        if((x < 0 || x >= pixelWidth || y < 0 || y >= pixelHeight) || alpha == 0){
            return;
        }
        
        if (zbuffer[index] > zDepth) {
        	return;
        }
        
        zbuffer[index] = zDepth;
        
        if(alpha == 255) {
        	pixel[index] = value;
        }
        else {
        	//int color = 0;
        	int pixelColor = pixel[index];
       
        	int red = ((pixelColor >> 16) & 0xff) + (int)((((pixelColor >> 16) & 0xff) - ((value >> 16) & 0xff)) * (alpha / 256f));
        	int green = ((pixelColor >> 8) & 0xff) + (int)((((pixelColor >> 8) & 0xff) - ((value >> 8) & 0xff)) * (alpha / 256f));        	
        	int blue = (pixelColor & 0xff) + (int)(((pixelColor & 0xff - value & 0xff)) * (alpha / 256f));        	

        	
        	pixel[index] = (255 << 24 | red << 16 | green << 8 | blue);
        }
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
    	
    	if(image.isAlpha) {
    		
    	}
    	
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

    public void drawRect(int offX, int offY, int width, int height, int color){

        for(int y = 0; y <= height; y++){
            setPixel ( offX, y + offY, color);
            setPixel(offX + width, y + offY, color);
        }

        for(int x = 0; x <= width; x++){
            setPixel ( x + offX, offY, color);
            setPixel(x + offX, offY + height, color);
        }

    }
    
    public void drawFillRect(int offX, int offY, int width, int height, int color){
    	int xPos = 0;
        int yPos = 0;
        int widthPos = width;
        int heightPos = height;

        if(offX < -width){
            return;
        }

        if(offY < -height){
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

        //clipping
        if(widthPos + xPos >= pixelWidth){
            widthPos -= widthPos + offX - pixelWidth;
        }

        if(heightPos + yPos >= pixelHeight){
            heightPos -= heightPos + offY - pixelHeight;
        }
    	
    	for (int y = yPos; y <= heightPos; y++){
            for (int x = xPos; x <= widthPos; x++){
                setPixel(x + offX, y + offY, color);
            }
        }
    }

}
