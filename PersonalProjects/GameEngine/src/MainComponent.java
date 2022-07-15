
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class MainComponent {
    
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final String TITLE = "3D Engine";
    public static final double FRAME_CAP = 5000.0;
    
    private boolean isRunning;
    private Game game;
    
    public MainComponent(){
        System.out.println(RenderUtil.getOpenGLVersion());
        RenderUtil.initGraphics();
        isRunning = false;
        game = new Game();
    }
    
    public void start(){
        if(isRunning==true){
            return;
        }else{
            run();
        }
    }
    
    public void stop(){
        if(isRunning==true){
            isRunning=false;
        }
    }
    
    private void run(){
        isRunning=true;
        int frames=0;
        long frameCounter=0;
        
        final double frameTime = 1.0 / FRAME_CAP; //the amount of time one frame takes
        
        long lastTime = Time.getTime();
        double unprocessedTime = 0; //how many times do we still need to updatet the game
        
        while(isRunning){
            boolean render = false;
            long startTime = Time.getTime();
            long passedTime = startTime-lastTime;
            lastTime = startTime;
            
            unprocessedTime+=passedTime /(double)Time.SECOND; //how much time we still need to account for
            frameCounter+=passedTime;
            while(unprocessedTime > frameTime){
                
                render = true;
                unprocessedTime -= frameTime;
            
                if(Window.isCloseRequested()){
                    stop();
                }
                
                //change delta before updating game
                Time.setDelta(frameTime);
                
                game.input();
                game.update(); //update to account for user input
                
                Input.update();
                if(frameCounter >= Time.SECOND){
                    System.out.println(frames);
                    frames=0;
                    frameCounter=0;
                }
            }
            if(render==true){
                render();
                frames++;
            }else{ //otherwise don't waste processor time. We don't need to render
                try {
                    Thread.sleep(1); //put the thread to sleep for a millisecond
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        cleanUp();
    }
    
    private void render(){
        RenderUtil.clearScreen();
        game.render();
        Window.render();
    }
    
    private void cleanUp(){
        Window.dispose();
    }
    
    public static void main(String[] args){
        Window.createWindow(WIDTH,HEIGHT,TITLE);
        MainComponent game = new MainComponent();
        game.start();
    }
}
