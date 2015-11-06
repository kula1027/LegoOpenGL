package com.example.notebook.legoopengl.object3d;

import com.example.notebook.legoopengl.MainRenderer;
import com.example.notebook.legoopengl.Vector3;
import com.example.notebook.legoopengl.statics.Config;
import com.example.notebook.legoopengl.statics.CubeColor;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by notebook on 2015-10-31.
 */
public class Cube {
    float vert[] = {//face 0 ~ 5
            0.05f, 0.05f, 0, //face 0
            0.05f, 0.95f, 0,
            0.95f, 0.05f, 0,
            0.95f, 0.95f, 0,

            0.05f, 0.05f, 1, //face 5, opposite to face 0
            0.05f, 0.95f, 1,
            0.95f, 0.05f, 1,
            0.95f, 0.95f, 1,

            0, 0.05f, 0.05f,
            0, 0.05f, 0.95f,
            0, 0.95f, 0.05f,
            0, 0.95f, 0.95f,

            1, 0.05f, 0.05f,
            1, 0.05f, 0.95f,
            1, 0.95f, 0.05f,
            1, 0.95f, 0.95f,

            0.05f, 0, 0.05f,
            0.05f, 0, 0.95f,
            0.95f, 0, 0.05f,
            0.95f, 0, 0.95f,

            0.05f, 1, 0.05f,
            0.05f, 1, 0.95f,
            0.95f, 1, 0.05f,
            0.95f, 1, 0.95f
    };

    byte index[] = {
            //////////////////6 faces////////////////////////////
            0, 1, 2,
            1, 3, 2,

            4, 5, 6,
            5, 7, 6,

            8, 9, 10,
            9, 11, 10,

            12, 13, 14,
            13, 15, 14,

            16, 17, 18,
            17, 19, 18,

            20, 21, 22,
            21, 23, 22,
//////////////////6 faces////////////////////////////
            0, 8, 16,
            1, 10, 20,
            2, 18, 12,
            22, 14, 3,

            4, 9, 17,
            5, 11, 21,
            6, 13, 19,
            7, 15, 23,

            0, 1, 8,
            1, 10, 8,
            2, 14, 12,
            2, 14, 3,

            1, 22, 3,
            1, 22, 20,
            0, 2, 16,
            2, 18 ,16,

            8, 16, 17,
            8, 9, 17,
            12, 18, 19,
            12, 13, 19,

            10, 11, 21,
            10, 20, 21,
            14, 22, 23,
            14, 15, 23,

            5, 21, 23,
            5, 7, 23,
            4, 6, 19,
            4, 17, 19,

            4, 9, 11,
            4, 5, 11,
            6, 7, 13,
            7, 13, 15
    };
    private float color[];
    private FloatBuffer vertbuf;
    private FloatBuffer colorbuf;
    private ByteBuffer indexbuf;
    private Vector3 position;
    private Vector3 velocity;
    public boolean isTransCube;

    public Cube(int colorIndex, Vector3 position_, boolean isTransCube_){
        for(int loop = 0; loop < vert.length; loop++) {
            vert[loop] -= 0.5f;
        }

        color = CubeColor.color[colorIndex];
        vertbuf = arrayToBuffer(vert);
        colorbuf = arrayToBuffer(color);

        indexbuf = ByteBuffer.allocateDirect(index.length);
        indexbuf.put(index);
        indexbuf.position(0);

        position = new Vector3();
        Vector3.copy(position, position_);
        velocity = new Vector3(0, 0, 0);
        isTransCube = isTransCube_;
    }
    public int[] getPosInt(){
        int[] tempArr = new int[3];
        tempArr[0] = Math.round(position.x);
        tempArr[1] = Math.round(position.y);
        tempArr[2] = Math.round(position.z);

        return  tempArr;
    }
    public void drop(int targetHeight){
        new DropRoutine(targetHeight).start();
    }

    class DropRoutine extends Thread{
        float targetHeight;
        DropRoutine(float targetHeight_){
            targetHeight = targetHeight_;
            velocity = new Vector3(0, Config.moveSpeed_Cube, 0);
        }

        public void run(){
            super.run();

            MainRenderer.countingSemaphore++;
            while(true){
                if(position.y <= targetHeight){
                    velocity = new Vector3(0, 0, 0);
                    position = new Vector3(Math.round(position.x), Math.round(position.y), Math.round(position.z));
                    MainRenderer.countingSemaphore--;
                    break;
                }
                try { Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void draw(GL10 gl) {
        gl.glPushMatrix();


        Vector3.add(position, velocity);
        gl.glTranslatef(position.x, position.y, position.z);

        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertbuf);
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorbuf);
        gl.glDrawElements(GL10.GL_TRIANGLES, index.length,
                GL10.GL_UNSIGNED_BYTE, indexbuf);

        gl.glPopMatrix();
    }

    public FloatBuffer arrayToBuffer(float[] ar) {
        ByteBuffer bytebuf = ByteBuffer.allocateDirect(ar.length * 4);
        bytebuf.order(ByteOrder.nativeOrder());
        FloatBuffer buf = bytebuf.asFloatBuffer();
        buf.put(ar);
        buf.position(0);
        return buf;
    }
}
