package com.example.notebook.legoopengl;

/**
 * Created by notebook on 2015-10-31.
 */
public class Vector3 {
    float x;
    float y;
    float z;

    Vector3() {
        x = 0;
        y = 0;
        z = 0;
    }
    Vector3(float x_, float y_, float z_) {
        x = x_;
        y = y_;
        z = z_;
    }

    static  public void add(Vector3 vec1, Vector3 vec2){
        vec1.x +=  vec2.x;
        vec1.y += vec2.y;
        vec1.z += vec2.z;
    }

    static public boolean equal(Vector3 vec1, Vector3 vec2){
        if(vec1.x == vec2.x){
            if(vec1.y == vec2.y){
                if(vec1.z == vec2.z){
                    return true;
                }
            }
        }
        return false;
    }

    public static void copy(Vector3 vec1, Vector3 vec2){
        vec1.x = vec2.x;
        vec1.y = vec2.y;
        vec1.z = vec2.z;
    }

    public float length(){
        return (float)Math.sqrt(x*x+y*y+z*z);
    }
}
