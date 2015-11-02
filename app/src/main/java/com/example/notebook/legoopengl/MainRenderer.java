package com.example.notebook.legoopengl;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.util.Log;

import com.example.notebook.legoopengl.statics.Config;
import com.example.notebook.legoopengl.statics.CubeColor;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by notebook on 2015-10-31.
 */
public class MainRenderer implements GLSurfaceView.Renderer{
    Camera camera;
    Stack3d stack3d = new Stack3d();
    List<Cube> cubeList = new ArrayList<Cube>();
    int currentColor;
    boolean displayTrans;

    public MainRenderer() {
        camera = new Camera(15f, new Vector3(0f, 5f, 0f));
        displayTrans = true;
    }

    public void setCubeColor(int idx){
        currentColor = idx;
    }

    public void toggleTrans(){
        displayTrans = !displayTrans;
    }

    public void removeCube(){
        if(cubeList.size() > 0){
            cubeList.remove(cubeList.size() - 1);
            stack3d.remove();
        }
    }

    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClearColor(0.3f, 0.3f, 0.3f, 1);

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        gl.glShadeModel(GL10.GL_FLAT);
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
        gl.glEnable(GL10.GL_LINE_SMOOTH);
    }

    public void onSurfaceChanged(GL10 gl, int width, int height) {
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        float ratio = (float) width / height;
        GLU.gluPerspective(gl, 60.0f, ratio, 1, 100f);
    }

    public void dropCube(){
        int tempLevel = stack3d.depthTest(0, 0);
        if(tempLevel == -1)return;
        Cube tempCube;
        if(currentColor == 0){tempCube = new Cube(currentColor, new Vector3(0, 20, 0), true);}
        else{tempCube = new Cube(currentColor, new Vector3(0, 20, 0), false);}
        cubeList.add(tempCube);
        tempCube.drop(tempLevel);
    }

    public void onDrawFrame(GL10 gl) {
        gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        gl.glMatrixMode(GL10.GL_MODELVIEW);

        gl.glLoadIdentity();
        camera.setCamera(gl);

        gl.glEnable(GL10.GL_BLEND);
        for(int loop = 0; loop < cubeList.size(); loop++){
            if(displayTrans) {
                cubeList.get(loop).draw(gl);
            }else{
                if(cubeList.get(loop).isTransCube == false){
                    cubeList.get(loop).draw(gl);
                }
            }
        }

        gl.glFlush();
    }
}
