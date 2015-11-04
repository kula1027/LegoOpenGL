package com.example.notebook.legoopengl;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import com.example.notebook.legoopengl.object3d.Cube;
import com.example.notebook.legoopengl.object3d.PointingArrow;
import com.example.notebook.legoopengl.statics.Config;

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
    PointingArrow pa = new PointingArrow();

    int pointingPos[] = {0, 0};
    int currentColor;
    boolean displayTrans;

    public MainRenderer() {
        camera = new Camera(new Vector3(0f, 5f, 0f));
        displayTrans = true;
    }

    public void setCubeColor(int idx){
        currentColor = idx;
    }

    public void toggleTrans(){
        displayTrans = !displayTrans;
    }

    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClearColor(0.5f, 0.5f, 0.5f, 1);

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
        int height = stack3d.getHeight(pointingPos[0], pointingPos[1]);
        if(height == -1)return;

        Cube tempCube;
        if(currentColor == 0){tempCube = new Cube(currentColor, new Vector3(pointingPos[0], Config.dropYpos, pointingPos[1]), true);}//투명 큐브
        else{tempCube = new Cube(currentColor, new Vector3(pointingPos[0], Config.dropYpos, pointingPos[1]), false);}//유색 큐브
        cubeList.add(tempCube);
        tempCube.drop(height);

        stack3d.increaseHeight(pointingPos[0], pointingPos[1]);height++;
        pa.moveTo(new Vector3(pointingPos[0], height, pointingPos[1]));
    }

    public void removeCube(){
        if(cubeList.size() > 0){
            removeCubeInList();
            stack3d.remove(pointingPos[0], pointingPos[1]);
        }
    }

    private void removeCubeInList(){
        int[] tempArr;
        for(int loop = 0; loop < cubeList.size(); loop++){
            tempArr = cubeList.get(loop).getPosInt();
            if(tempArr[0] == pointingPos[0] &&
                    tempArr[1] == stack3d.getHeight(pointingPos[0], pointingPos[1]) - 1&&
                    tempArr[2] == pointingPos[1]){
                cubeList.remove(loop);
                pa.moveTo(new Vector3(pointingPos[0], tempArr[1], pointingPos[1]));
                break;
            }
        }
    }

    public void moveArrow(int direction){
        switch (direction){
            case 0://lu
                if(pointingPos[0] > -Config.size[0] / 2) {
                    pointingPos[0]--;
                    int height = stack3d.getHeight(pointingPos[0], pointingPos[1]);
                    pa.moveTo(new Vector3(pointingPos[0], height, pointingPos[1]));
                }
                break;
            case 1://
                if(pointingPos[1] > -Config.size[2] / 2) {
                    pointingPos[1]--;
                    int height = stack3d.getHeight(pointingPos[0], pointingPos[1]);
                    pa.moveTo(new Vector3(pointingPos[0], height, pointingPos[1]));
                }
                break;
            case 2://rd
                if(pointingPos[0] < Config.size[0] / 2) {
                    pointingPos[0]++;
                    int height = stack3d.getHeight(pointingPos[0], pointingPos[1]);
                    pa.moveTo(new Vector3(pointingPos[0], height, pointingPos[1]));
                }
                break;
            case 3://ld
                if (pointingPos[1] < Config.size[2] / 2){
                    pointingPos[1]++;
                    int height = stack3d.getHeight(pointingPos[0], pointingPos[1]);
                    pa.moveTo(new Vector3(pointingPos[0], height, pointingPos[1]));
                }
                break;
        }
    }

    public void onDrawFrame(GL10 gl) {
        gl.glClearColor(0.8f, 0.8f, 0.8f, 1);
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        gl.glMatrixMode(GL10.GL_MODELVIEW);

        gl.glLoadIdentity();
        camera.setCamera(gl);

        if(displayTrans)pa.draw(gl);
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
