package com.example.notebook.legoopengl.object3d;

import com.example.notebook.legoopengl.Vector3;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by notebook on 2015-11-06.
 */
public class Obj3d_BottomMark {
    float vert[] = {
            0.1f, 0, 0.15f,
            0.15f, 0, 0.1f,
            -0.1f,0, -0.15f,
            -0.15f, 0, -0.1f,

            -0.1f, 0, 0.15f,
            -0.15f, 0, 0.1f,
            0.1f, 0, -0.15f,
            0.15f, 0, -0.1f
    };
    byte index[] = {
            0, 1, 3,
            1, 2, 3,

            5, 6, 7,
            4, 5, 7
    };
    float color[] = {
            0.2f, 0.2f, 0.2f, 1,
            0.2f, 0.2f, 0.2f, 1,
            0.2f, 0.2f, 0.2f, 1,
            0.2f, 0.2f, 0.2f, 1,

            0.2f, 0.2f, 0.2f, 1,
            0.2f, 0.2f, 0.2f, 1,
            0.2f, 0.2f, 0.2f, 1,
            0.2f, 0.2f, 0.2f, 1
    };

    private FloatBuffer vertbuf;
    private FloatBuffer colorbuf;
    private ByteBuffer indexbuf;

    private Vector3 position;

    public Obj3d_BottomMark(Vector3 pos){
        vertbuf = arrayToBuffer(vert);
        colorbuf = arrayToBuffer(color);

        indexbuf = ByteBuffer.allocateDirect(index.length);
        indexbuf.put(index);
        indexbuf.position(0);

        position = new Vector3(pos.x, pos.y, pos.z);
    }

    public void draw(GL10 gl){
        gl.glPushMatrix();

        gl.glTranslatef(position.x, position.y, position.z);

        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertbuf);
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorbuf);
        gl.glDrawElements(GL10.GL_TRIANGLES, index.length,
                GL10.GL_UNSIGNED_BYTE, indexbuf);

        gl.glPopMatrix();
    }

    private FloatBuffer arrayToBuffer(float[] ar){
        ByteBuffer bytebuf = ByteBuffer.allocateDirect(ar.length * 4);
        bytebuf.order(ByteOrder.nativeOrder());
        FloatBuffer buf = bytebuf.asFloatBuffer();
        buf.put(ar);
        buf.position(0);
        return buf;
    }
}
