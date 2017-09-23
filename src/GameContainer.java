package com.AMax.Engine;

public class GameContainer implements Runnable{

    private Thread thread;
    private Window window;
    private Renderer renderer;

    private boolean running = false;
    private final double Update_Cap = 1.0/60.0;
    private int width = 1200, height = 800;
    private float scale = 1f;
    private String title = "Amax Engine";

    public  GameContainer(){

    }

    public void start(){

        window = new Window(this);
        thread = new Thread(this);
        renderer = new Renderer(this);
        thread.run();

    }

    public Window getWindow() {
        return window;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void stop(){


    }

    public void run(){

        running = true;
        boolean render = false;

        double firstTime = 0;
        double lastTime = System.nanoTime() / 1000000000.0;
        double passedTime = 0;
        double unprocessedTime = 0;
        double frameTime = 0;
        int frames = 0;
        int fps = 0;

        while (running){

            render = false;

            firstTime = System.nanoTime() / 1000000000.0;
            passedTime = firstTime - lastTime;
            lastTime = firstTime;

            unprocessedTime += passedTime;
            frameTime += passedTime;

            while (unprocessedTime >= Update_Cap){
                unprocessedTime -= Update_Cap;
                render = true;

                //Update Game

                if (frameTime >= 1.0){
                    frameTime = 0;
                    //frames = 0;
                    fps = frames;
                    frames = 0;

                    System.out.println("FPS: " + fps);
                }
            }

            if(render){
                renderer.clear();
                window.update();
                frames++;

            }
            else {
                try {
                    Thread.sleep(1);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }

        dispose();

    }

    private void dispose(){

    }

    public static void main(String args[]){
        GameContainer gc = new GameContainer();
        gc.start();
    }

}