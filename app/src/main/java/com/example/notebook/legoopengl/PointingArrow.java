package com.example.notebook.legoopengl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by notebook on 2015-11-03.
 */
public class PointingArrow {
    float vert[] = {
            0, 0, 0,
            -0.6f, 1f, 0.6f,
            0.6f, 1f, 0.6f,
            0.6f, 1f, -0.6f,
            -0.6f, 1f, -0.6f,
            -0.6f, 1f, 0.6f,//사각뿔 vertex 5

            -0.2f, 1f, 0.2f,
            -0.2f, 2f, 0.2f,//7
            0.2f, 1f, 0.2f,
            0.2f, 2f, 0.2f,//9
            0.2f, 1f, -0.2f,
            0.2f, 2f, -0.2f,//11
            -0.2f, 1f, -0.2f,
            -0.2f, 2f, -0.2f,//13
    };
    byte index[] = {
            3, 4, 5,//사각뿔 밑면
            2, 3, 5,

            0, 2, 1,
            0, 2, 3,
            0, 4, 3,
            1, 4, 0,//사각뿔 사이드 단면


            6, 7, 8,
            7, 8, 9,
            8, 9, 10,
            9, 10, 11,
            10, 11, 12,
            11, 12, 13,
            12, 7, 13,
            12, 7, 6, //직유면체 사이드

            11, 9, 7,
            11, 13, 7,//직육면체 위
    };
    float color[] = {
            0.2f, 0.2f, 0.6f, 1,
            0.2f, 0.2f, 0.6f, 1,
            0.5f, 0.5f, 0.5f, 1,
            0.2f, 0.2f, 0.6f, 1,
            0.2f, 0.2f, 0.6f, 1,
            0.2f, 0.5f, 0.8f, 1, //5

            0.2f, 0.2f, 0.6f, 1,
            0.2f, 0.5f, 0.8f, 1,//7
            0.2f, 0.2f, 0.6f, 1,
            0.2f, 0.2f, 0.6f, 1,//9
            0.2f, 0.2f, 0.6f, 1,
            0.2f, 0.2f, 0.6f, 1,//11
            0.2f, 0.2f, 0.6f, 1,
            0.2f, 0.2f, 0.6f, 1,//13
    };
    private FloatBuffer vertbuf;
    private FloatBuffer colorbuf;
    private ByteBuffer indexbuf;

    private float rotationSpeed;
    private float currentRot;
    public Vector3 position;
    private Vector3 velocity;

    public PointingArrow(){
        vertbuf = arrayToBuffer(vert);
        colorbuf = arrayToBuffer(color);

        indexbuf = ByteBuffer.allocateDirect(index.length);
        indexbuf.put(index);
        indexbuf.position(0);

        rotationSpeed = 0.5f;
        position = new Vector3(0, 0, 0);
        velocity = new Vector3();
    }
    public void draw(GL10 gl){
        gl.glPushMatrix();

        Vector3.add(position, velocity);

        gl.glTranslatef(position.x, position.y, position.z);
        gl.glRotatef(currentRot += rotationSpeed, 0, 1, 0);

        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertbuf);
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorbuf);
        gl.glDrawElements(GL10.GL_TRIANGLES, index.length,
                GL10.GL_UNSIGNED_BYTE, indexbuf);

        gl.glPopMatrix();
    }
    public FloatBuffer arrayToBuffer(float[] ar){
        ByteBuffer bytebuf = ByteBuffer.allocateDirect(ar.length * 4);
        bytebuf.order(ByteOrder.nativeOrder());
        FloatBuffer buf = bytebuf.asFloatBuffer();
        buf.put(ar);
        buf.position(0);
        return buf;
    }
    public void move(Vector3 vec3){
        Vector3.add(position, vec3);
    }
}
