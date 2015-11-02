package com.example.notebook.legoopengl;

import android.opengl.GLU;
import android.util.Log;

import com.example.notebook.legoopengl.statics.Config;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by notebook on 2015-10-31.
 */
public class Camera {
    private Vector3 position;
    private Vector3 rotation;
    private Vector3 lookingDir;
    private boolean isRotating;
    private float distance;
    private int direction;

    Camera( Vector3 lookingDir_) {
        rotation = new Vector3(45f, 45f, 45f);
        lookingDir = lookingDir_;
        isRotating = false;

        distance = Config.cameraDistance;

        float cosValue = (float)Math.cos(Math.toRadians(45f)) * distance;
        float sinValue = (float)Math.sin(Math.toRadians(45f)) * distance;
        position = new Vector3(cosValue, cosValue, sinValue);

        direction = 0;
    }

    void setCamera(GL10 gl) {
        GLU.gluLookAt(gl, position.x, position.y, position.z, lookingDir.x, lookingDir.y, lookingDir.z, 0, 1, 0);
    }

    public void Rotate(float dir){
        rotation.y += dir * Config.rotateSensitivity;

        float cosValue = (float)Math.cos(Math.toRadians(rotation.y));
        float sinValue = (float)Math.sin(Math.toRadians(rotation.y));
        position.x = cosValue * distance;
        position.z = sinValue * distance;
    }
    public float getRotation(){
        return rotation.y;
    }
}
