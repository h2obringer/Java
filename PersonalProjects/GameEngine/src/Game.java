
import org.lwjgl.input.Keyboard;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class Game {
    private Mesh mesh;
    private Shader shader;
    private Transform transform;
    
    public Game(){
        //mesh = new Mesh();
        
        
        //WHY ARE WE ONLY GETTING 4 OUT OF THE 6 FACES OF THE CUBE!?!?!?
        mesh = ResourceLoader.loadMesh("test.obj");
        shader = new Shader();
        transform = new Transform();
        transform.setProjection(70f, MainComponent.WIDTH,MainComponent.HEIGHT,0.1f, 1000);
        //create a triangle
        /*Vertex[] vertices = new Vertex[] {new Vertex(new Vector3f(-1,-1,0)),
                                    new Vertex(new Vector3f(0,1,0)),
                                    new Vertex(new Vector3f(1,-1,0)),
                                    new Vertex(new Vector3f(0,-1,1))};
        int[] indices = new int[]{0,1,3,
                                  3,1,2,
                                  2,1,0,
                                  0,2,3};*/
        /*Vertex[] vertices = new Vertex[] {new Vertex(new Vector3f(1,-1,-1)),
                                    new Vertex(new Vector3f(1,-1,1)),
                                    new Vertex(new Vector3f(-1,-1,1)),
                                    new Vertex(new Vector3f(-1,-1,-1)),
                                    new Vertex(new Vector3f(1,1,-1)),
                                    new Vertex(new Vector3f(1,1,1)),
                                    new Vertex(new Vector3f(-1,1,1)),
                                    new Vertex(new Vector3f(-1,1,-1))};
        int[] indices = new int[]{1,2,3,
                                  7,6,5,
                                  4,5,1,
                                  5,6,2,
                                  2,6,7,
                                  0,3,7,
                                  0,1,3,
                                  4,7,5,
                                  0,4,1,
                                  1,5,2,
                                  3,2,7,
                                  4,0,7};
        mesh.addVertices(vertices, indices);*/
        
        shader.addVertexShader(ResourceLoader.loadShader("VertexShader.vs"));
        shader.addFragmentShader(ResourceLoader.loadShader("FragmentShader.fs"));
        shader.compileShader();
        
        shader.addUniform("uniformFloat");
        shader.addUniform("transform");
        
        shader.bind();
    }
    
    public void input(){
        if(Input.GetKeyDown(Keyboard.KEY_UP)){
            System.out.println("We've just pressed up!");
        }
        if(Input.GetKeyUp(Keyboard.KEY_UP)){
            System.out.println("We've just released up!");
        }
        if(Input.getMouseDown(1)){
            System.out.println("We've just right clicked!");
        }
        if(Input.getMouseUp(1)){
            System.out.println("We've just released right click!");
        }
    }
    
    float temp = 0.0f;
    public void update(){
        temp+=Time.getDelta();
        float sinTemp=(float)Math.sin(temp);
        shader.setUniformf("uniformFloat", (float)Math.abs(Math.cos(temp)));
        transform.setTranslation(sinTemp,0,5);
        transform.setRotation(0,sinTemp*180,0);
        //transform.setScale(0.7f,0.7f,0.7f);
    }
    
    public void render(){
        //shader.bind(); //removing due to GL_INVALID_OPERATION being thrown due to 
        //1st time call being before a program is compiled in the graphics driver
        //moving to the end of the game constructor once.
        
        
        shader.setUniform("transform", transform.getProjectedTransformation());
        mesh.draw();
    }
}
