package pieces;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import pieces.BlockType;
import static utils.Variables.BLOCK_SIZE;
/**
 *
 * @author Administrator
 */
public class Block {
    
    private BlockType type = BlockType.GRID_BLOCK;
    private Texture texture = null;
    private int x; //x coordinate at grid location x
    private int y; //y coorinate in grid location y
    private int pixel_x; //draw block at actual pixel (x*BLOCK_SIZE)
    private int pixel_y; //draw block at actual pixel (y*BLOCK_SIZE)
    //private float z; //depth
    
    public Block(BlockType type, int x, int y){
        this.type=type;
        this.x=x;
        this.y=y;
        pixel_x=x*BLOCK_SIZE;
        pixel_y=y*BLOCK_SIZE;
        try{
            this.texture = TextureLoader.getTexture("PNG", new FileInputStream(new File(type.location)));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void draw(){
        //if(x>=0&&x<32&&y>=0&&y<24){
            texture.bind();
            glPushMatrix();
            //glLoadIdentity();
            glTranslatef(pixel_x,pixel_y,0);
            glBegin(GL_QUADS);
                glTexCoord2f(0,0);
                glVertex2f(0,0);
                glTexCoord2f(1,0);
                glVertex2f(BLOCK_SIZE,0);
                glTexCoord2f(1,1);
                glVertex2f(BLOCK_SIZE,BLOCK_SIZE);
                glTexCoord2f(0,1);
                glVertex2f(0,BLOCK_SIZE);
            glEnd();
            //glLoadIdentity();
            glPopMatrix();
        //}
    }
    
    public BlockType getType(){
        return type;
    }
    
    public void setType(BlockType type){
        this.type=type;
        
        try{
            this.texture = TextureLoader.getTexture("PNG", new FileInputStream(new File(type.location)));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public int getX(){
        return x;
    }
    
    public void setX(int x){
        this.x=x;
        pixel_x=x*BLOCK_SIZE;
    }
    
    public int getY(){
        return y;
    }
    
    public void setY(int y){
        this.y=y;
        pixel_y=y*BLOCK_SIZE;
    }
}
