/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class Transform {
    private static float zNear;
    private static float zFar;
    private static float width; //screen size
    private static float height; //screen size
    private static float fov; //field of view for projection not orthographic view
    
    private Vector3f translation; //represent x,y,z of our translation
    private Vector3f rotation;
    private Vector3f scale;
    
    public Transform(){
        translation = new Vector3f(0,0,0);
        rotation = new Vector3f(0,0,0);
        scale = new Vector3f(1,1,1);
    }
    
    public Matrix4f getTransformation(){
        Matrix4f translationMatrix = new Matrix4f().initTranslation(translation.getX(),translation.getY(),translation.getZ());
        Matrix4f rotationMatrix = new Matrix4f().initRotation(rotation.getX(),rotation.getY(),rotation.getZ());
        Matrix4f scaleMatrix = new Matrix4f().initScale(scale.getX(), scale.getY(), scale.getZ());
        
        //effectively applies the rotation first before the translation
        return translationMatrix.mul(rotationMatrix.mul(scaleMatrix)); //need to multiply by scale matrix now
    }
    
    public Matrix4f getProjectedTransformation(){
        Matrix4f transformationMatrix = getTransformation();
        Matrix4f projectionMatrix = new Matrix4f().initProjection(fov, width, height, zNear, zFar);
        
        return projectionMatrix.mul(transformationMatrix);
    }
    
    public void setProjection(float fov, float width, float height, float zNear, float zFar){
        Transform.fov = fov;
        Transform.width = width;
        Transform.height = height;
        Transform.zNear = zNear;
        Transform.zFar = zFar;
    }
    
    public Vector3f getTranslation(){
        return translation;
    }
    
    public void setTranslation(Vector3f translation){
        this.translation=translation;
    }
    
    public void setTranslation(float x, float y, float z){
        this.translation = new Vector3f(x, y, z);
    }
    
    public Vector3f getRotation(){
        return rotation;
    }
    
    public void setRotation(Vector3f rotation){
        this.rotation=rotation;
    }
    
    public void setRotation(float x, float y, float z){
        this.rotation = new Vector3f(x,y,z);
    }
    
    public Vector3f getScale(){
        return scale;
    }
    
    public void setScale(Vector3f scale){
        this.scale=scale;
    }
    
    public void setScale(float x, float y, float z){
        this.scale = new Vector3f(x,y,z);
    }
}
