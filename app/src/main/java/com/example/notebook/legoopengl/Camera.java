package com.example.notebook.legoopengl;

import android.opengl.GLU;

import com.example.notebook.legoopengl.statics.Config;

import java.io.Serializable;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by notebook on 2015-10-31.
 */
public class Camera implements Serializable {
    private Vector3 position;
    private Vector3 rotation;
    private Vector3 lookingDir;
    private float distance;

    Camera( Vector3 lookingDir_) {
        rotation = new Vector3(45f, 45f, 45f);
        lookingDir = lookingDir_;

        distance = Config.cameraDistance_start;

        float cosValue = (float)Math.cos(Math.toRadians(45f)) * distance;
        float sinValue = (float)Math.sin(Math.toRadians(45f)) * distance;
        position = new Vector3(cosValue, cosValue, sinValue);
    }

    public void setCamera(GL10 gl) {
        GLU.gluLookAt(gl, position.x, position.y, position.z, lookingDir.x, lookingDir.y, lookingDir.z, 0, 1, 0);
    }

    public void rotate(float dir){
        rotation.y += dir * Config.rotateSensitivity;

        float cosValue = (float)Math.cos(Math.toRadians(rotation.y));
        float sinValue = (float)Math.sin(Math.toRadians(rotation.y));
        position.x = cosValue * distance;
        position.z = sinValue * distance;
    }
    public void moveFarther(){
        if(distance < Config.cameraDistance_max){
            distance += 0.3f;
            float cosValue = (float)Math.cos(Math.toRadians(rotation.y));
            float sinValue = (float)Math.sin(Math.toRadians(rotation.y));
            position.x = cosValue * distance;
            position.z = sinValue * distance;
        }
    }
    public void moveCloser(){
        if(distance > Config.cameraDistance_min){
            distance -= 0.3f;
            float cosValue = (float)Math.cos(Math.toRadians(rotation.y));
            float sinValue = (float)Math.sin(Math.toRadians(rotation.y));
            position.x = cosValue * distance;
            position.z = sinValue * distance;
        }
    }
    public void setLookingDir(float diff){
        if(lookingDir.y < Config.lookLimit_max && diff < 0){
            lookingDir.y += 0.2f;
        }
        if(lookingDir.y > Config.lookLimit_min && diff > 0){
            lookingDir.y -= 0.2f;
        }
    }

    public float getRotation(){
        return rotation.y;
    }
}
