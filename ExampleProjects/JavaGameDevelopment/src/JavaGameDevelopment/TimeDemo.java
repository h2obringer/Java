/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package JavaGameDevelopment;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;
/**
* Shows framerate-independent movement
*
* @author Oskar
*/
public class TimeDemo {
    private static long lastFrame;
    private static long getTime() {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }
    private static double getDelta() {
        long currentTime = getTime();
        double delta = (double) (currentTime - lastFrame);
        lastFrame = getTime();
        return delta;
    }
    public static void main(String[] args) {
        try {
            Display.setDisplayMode(new DisplayMode(640, 480));
            Display.setTitle("Timer Demo");
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            Display.destroy();
            System.exit(1);
        }
        int x = 100;
        int y = 100;
        int dx = 1;
        int dy = 1;
        // Initialization code OpenGL
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, 640, 480, 0, 1, -1);
        glMatrixMode(GL_MODELVIEW);
        lastFrame = getTime();
        while (!Display.isCloseRequested()) {
            // Render
            glClear(GL_COLOR_BUFFER_BIT);
            double delta = getDelta();
            x += delta * dx * 0.1;
            y += delta * dy * 0.1;
            glRecti(x, y, x + 30, y + 30);
            Display.update();
            Display.sync(60);
        }
        Display.destroy();
        System.exit(0);
        }
    }
    interface Entity2D {
        public float getX();
        public float getY();
        public void setX(float x);
        public void setY(float y);
        public void setLocation(float x, float y);
        public void setUp();
        public void destroy();
        public void draw();
    }
    abstract class AbstractEntity2D implements Entity2D {
        float x;
        float y;
        public float getX() {
            return x;
        }
        public float getY() {
            return y;
        }
        public void setX(float x) {
            this.x = x;
        }
        public void setY(float y) {
            this.y = y;
        }
        public void setLocation(float x, float y) {
            this.x = x;
            this.y = y;
        }
        public abstract void setUp();
        public abstract void destroy();
        public abstract void draw();
    }
    class Box2D extends AbstractEntity2D {
        private final float size;
        public Box2D(float x, float y, float size) {
            this.x = x;
            this.y = y;
            this.size = size;
        }
        public Box2D() {
            this.x = this.y = this.size = 0;
        }
        @Override
        public void setUp() {
            // We don't need anything here for a box
        }
        @Override
        public void destroy() {
            // We don't need anything here for a box
        }
        @Override
        public void draw() {
        glBegin(GL_TRIANGLES);
            glVertex2f(x + size / 2, y + size / 2);
            glVertex2f(x + size / 2, y - size / 2);
            glVertex2f(x - size / 2, y - size / 2);
            glVertex2f(x - size / 2, y - size / 2);
            glVertex2f(x - size / 2, y + size / 2);
            glVertex2f(x + size / 2, y + size / 2);
        glEnd();
    }
}