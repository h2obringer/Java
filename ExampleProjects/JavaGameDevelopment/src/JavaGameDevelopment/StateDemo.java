/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package JavaGameDevelopment;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;
/**
* Shows the use of different game states.
*
* @author Oskar
*/
public class StateDemo {
    private static enum State {
        INTRO, MAIN_MENU, GAME
    }
    private static State state = State.INTRO;
    /*public static void main(String args[]) {
        try {
            Display.setDisplayMode(new DisplayMode(640, 480));
            Display.setTitle("State Demo");
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            Display.destroy();
            System.exit(1);
        }
        glMatrixMode(GL_PROJECTION);
        glOrtho(0, 640, 480, 0, 1, -1);
        glMatrixMode(GL_MODELVIEW);
        while (!Display.isCloseRequested()) {
            checkInput();
            render();
            Display.update();
            Display.sync(60);
        }
        Display.destroy();
    }
    private static void render() {
        glClear(GL_COLOR_BUFFER_BIT);
        switch (state) {
            case INTRO:
                glColor3f(1.0f, 0f, 0f);
                glRectf(0, 0, 640, 480);
                break;
            case GAME:
                glColor3f(0f, 1.0f, 0f);
                glRectf(0, 0, 640, 480);
                break;
            case MAIN_MENU:
                glColor3f(0f, 0f, 1.0f);
                glRectf(0, 0, 640, 480);
                break;
        }
    }
    private static void checkInput() {
        switch (state) {
            case INTRO:
                if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
                    state = State.MAIN_MENU;
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
                    Display.destroy();
                    System.exit(0);
                }
                break;
            case GAME:
                if (Keyboard.isKeyDown(Keyboard.KEY_BACK)) {
                    state = State.MAIN_MENU;
                }
                break;
            case MAIN_MENU:
                if (Keyboard.isKeyDown(Keyboard.KEY_RETURN)) {
                    state = State.GAME;
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
                    state = State.INTRO;
                }
                break;
        }
    }*/
}