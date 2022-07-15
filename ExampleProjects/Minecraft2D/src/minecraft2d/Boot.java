/*
* Copyright (c) 2013, Oskar Veerhoek
* All rights reserved.
*
* Redistribution and use in source and binary forms, with or without
* modification, are permitted provided that the following conditions are met:
*
* 1. Redistributions of source code must retain the above copyright notice, this
* list of conditions and the following disclaimer.
* 2. Redistributions in binary form must reproduce the above copyright notice,
* this list of conditions and the following disclaimer in the documentation
* and/or other materials provided with the distribution.
*
* THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
* ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
* WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
* DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
* ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
* (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
* LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
* ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
* (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
* SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*
* The views and conclusions contained in the software and documentation are those
* of the authors and should not be interpreted as representing official policies,
* either expressed or implied, of the FreeBSD Project.
*/
package minecraft2d;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import java.io.File;
import static org.lwjgl.opengl.GL11.*;
public class Boot {
    private static BlockGrid grid;
    private static BlockType selection = BlockType.STONE;
    private static int selector_x = 0, selector_y = 0;
    private static boolean mouseEnabled = true;
    public static void main(String[] args) {
        try {
            Display.setDisplayMode(new DisplayMode(640, 480));
            Display.setTitle("Minecraft 2D");
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            Display.destroy();
            System.exit(1);
        }
        grid = new BlockGrid();
        // Initialization code OpenGL
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, 640, 480, 0, 1, -1);
        glMatrixMode(GL_MODELVIEW);
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        while (!Display.isCloseRequested()) {
            // Render
            glClear(GL_COLOR_BUFFER_BIT);
            input();
            grid.draw();
            drawSelectionBox();
            Display.update();
            Display.sync(60);
        }
        Display.destroy();
        System.exit(0);
    }

    private static void drawSelectionBox() {
        int x = selector_x * World.BLOCK_SIZE;
        int y = selector_y * World.BLOCK_SIZE;
        int x2 = x + World.BLOCK_SIZE;
        int y2 = y + World.BLOCK_SIZE;
        if (grid.getAt(selector_x, selector_y).getType() != BlockType.AIR || selection == BlockType.AIR) {
            glBindTexture(GL_TEXTURE_2D, 0);
            glColor4f(1f, 1f, 1f, 0.5f);
            glBegin(GL_QUADS);
                glVertex2i(x, y);
                glVertex2i(x2, y);
                glVertex2i(x2, y2);
                glVertex2i(x, y2);
            glEnd();
            glColor4f(1f, 1f, 1f, 1f);
        } else {
            glColor4f(1f, 1f, 1f, 0.75f);
            new Block(selection, selector_x * World.BLOCK_SIZE, selector_y * World.BLOCK_SIZE).draw();
            glColor4f(1f, 1f, 1f, 1f);
        }
    }
    private static void input() {
        //when the mouse is clicked change it to what is showing
        if (mouseEnabled || Mouse.isButtonDown(0)) {
            mouseEnabled = true;
            int mousex = Mouse.getX();
            int mousey = 480 - Mouse.getY() - 1;
            boolean mouseClicked = Mouse.isButtonDown(0);
            selector_x = Math.round(mousex / World.BLOCK_SIZE);
            selector_y = Math.round(mousey / World.BLOCK_SIZE);
            if (mouseClicked) {
                grid.setAt(selector_x, selector_y, selection); //set piece
            }
        }
        while (Keyboard.next()) {
            if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT && Keyboard.getEventKeyState()) {
                    mouseEnabled = false;
                if (!(selector_x + 1 > World.BLOCKS_WIDTH - 2)) {
                    selector_x += 1;
                }
            }
            if (Keyboard.getEventKey() == Keyboard.KEY_LEFT && Keyboard.getEventKeyState()) {
                mouseEnabled = false;
                if (!(selector_x - 1 < 0)) {
                    selector_x -= 1;
                }
            }
            if (Keyboard.getEventKey() == Keyboard.KEY_UP && Keyboard.getEventKeyState()) {
                mouseEnabled = false;
                if (!(selector_y - 1 < 0)) {
                    selector_y -= 1;
                }
            }
            if (Keyboard.getEventKey() == Keyboard.KEY_DOWN && Keyboard.getEventKeyState()) {
                mouseEnabled = false;
                if (!(selector_y + 1 > World.BLOCKS_HEIGHT - 2)) {
                    selector_y += 1;
                }
            }
            if (Keyboard.getEventKey() == Keyboard.KEY_S) {
                //grid.save(new File("save.xml"));
            }
            if (Keyboard.getEventKey() == Keyboard.KEY_L) {
                //grid.load(new File("save.xml"));
            }
            if (Keyboard.getEventKey() == Keyboard.KEY_1) {
                selection = BlockType.STONE;
            }
            if (Keyboard.getEventKey() == Keyboard.KEY_2) {
                selection = BlockType.DIRT;
            }
            if (Keyboard.getEventKey() == Keyboard.KEY_3) {
                selection = BlockType.GRASS;
            }
            if (Keyboard.getEventKey() == Keyboard.KEY_4) {
                selection = BlockType.AIR;
            }
            if (Keyboard.getEventKey() == Keyboard.KEY_C) {
                grid.clear();
            }
            if (Keyboard.getEventKey() == Keyboard.KEY_ESCAPE) {
                Display.destroy();
                System.exit(0);
            }
        }
    }
}
