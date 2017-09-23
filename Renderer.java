package com.AMax.Engine;

import java.awt.image.DataBufferInt;

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
            pixel[i] += i;
        }


    }
}
