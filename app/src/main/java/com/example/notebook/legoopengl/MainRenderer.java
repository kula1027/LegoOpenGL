package com.example.notebook.legoopengl;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.util.Log;

import com.example.notebook.legoopengl.object3d.Obj3d_BottomMark;
import com.example.notebook.legoopengl.object3d.Obj3d_Cube;
import com.example.notebook.legoopengl.object3d.Obj3d_PointingArrow;
import com.example.notebook.legoopengl.statics.Config;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by notebook on 2015-10-31.
 */
public class MainRenderer implements GLSurfaceView.Renderer{
    public Camera camera;
    private Stack3d stack3d;
    private List<Obj3d_Cube> obj3dCubeList;
    private Obj3d_PointingArrow pa;
    private Obj3d_BottomMark[][] bottomMarks;

    private int pointingPos[];
    private int currentColor;
    private boolean displayTrans;
    public static int cubeDrop_countingSemaphore = 0;

    public MainRenderer() {
        camera = new Camera(new Vector3(0f, 5f, 0f));
        stack3d = new Stack3d();
        obj3dCubeList = new ArrayList<Obj3d_Cube>();
        pa = new Obj3d_PointingArrow();
        bottomMarks = new Obj3d_BottomMark[Config.size[0]][Config.size[2]];
        for(int loop = 0; loop < Config.size[0]; loop++){
            for(int loop2 = 0; loop2 < Config.size[2]; loop2++) {
                bottomMarks[loop][loop2] = new Obj3d_BottomMark(new Vector3(loop - Config.size[0] / 2, -0.5f, loop2 - Config.size[2] / 2));
            }
        }

        pointingPos = new int[2];
        pointingPos[0] = 0;
        pointingPos[1] = 0;
        currentColor = 0;
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
        if(height >= Config.size[1]){//height larger than max h
            return;
        }

        Obj3d_Cube tempObj3dCube;
        if(currentColor == 0){
            tempObj3dCube = new Obj3d_Cube(currentColor, new Vector3(pointingPos[0], Config.dropYpos, pointingPos[1]), true);}//���� ť��
        else{
            tempObj3dCube = new Obj3d_Cube(currentColor, new Vector3(pointingPos[0], Config.dropYpos, pointingPos[1]), false);}//���� ť��
        obj3dCubeList.add(tempObj3dCube);
        tempObj3dCube.drop(height);

        stack3d.increaseHeight(pointingPos[0], pointingPos[1]);height++;
        pa.moveTo(new Vector3(pointingPos[0], height, pointingPos[1]));
        Log.d("cubeCount", obj3dCubeList.size() + "");
    }

    public void removeCube(){
        if(stack3d.getHeight(pointingPos[0], pointingPos[1]) > 0 && cubeDrop_countingSemaphore == 0){
            removeCubeInList();
            stack3d.decreaseHeight(pointingPos[0], pointingPos[1]);
        }
        Log.d("cubeCount", obj3dCubeList.size() + "");
    }

    private void removeCubeInList(){
        int[] tempArr;
        for(int loop = 0; loop < obj3dCubeList.size(); loop++){
            tempArr = obj3dCubeList.get(loop).getPosInt();
            if(tempArr[0] == pointingPos[0] &&
                    tempArr[1] == stack3d.getHeight(pointingPos[0], pointingPos[1]) - 1&&
                    tempArr[2] == pointingPos[1]){
                obj3dCubeList.remove(loop);
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
        gl.glClearColor(1f, 1f, 1f, 1);
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        gl.glMatrixMode(GL10.GL_MODELVIEW);

        gl.glLoadIdentity();
        camera.setCamera(gl);

        for(int loop = 0; loop < obj3dCubeList.size(); loop++){//render colored cubes
            if(obj3dCubeList.get(loop).isTransCube == false){
                    obj3dCubeList.get(loop).draw(gl);
            }
        }
        if(displayTrans){//render tanscubes and poting arrow
            pa.draw(gl);
            for(int loop = 0; loop < Config.size[0]; loop++){//render bottom marks
                for(int loop2 = 0; loop2 < Config.size[2]; loop2++) {
                    bottomMarks[loop][loop2].draw(gl);
                }
            }
            for(int loop = 0; loop < obj3dCubeList.size(); loop++){
                if(obj3dCubeList.get(loop).isTransCube == true){
                    obj3dCubeList.get(loop).draw(gl);
                }
            }
        }

        gl.glFlush();
    }
}
