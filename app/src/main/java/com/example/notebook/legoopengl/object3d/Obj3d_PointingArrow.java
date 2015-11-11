package com.example.notebook.legoopengl.object3d;

import com.example.notebook.legoopengl.Vector3;
import com.example.notebook.legoopengl.statics.Config;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by notebook on 2015-11-03.
 */
public class Obj3d_PointingArrow implements Serializable{
    private float vert[] = {
            0, 0, 0,
            -0.6f, 1f, 0.6f,
            0.6f, 1f, 0.6f,
            0.6f, 1f, -0.6f,
            -0.6f, 1f, -0.6f,
            -0.6f, 1f, 0.6f,//�簢�� vertex 5

            -0.2f, 1f, 0.2f,
            -0.2f, 2f, 0.2f,//7
            0.2f, 1f, 0.2f,
            0.2f, 2f, 0.2f,//9
            0.2f, 1f, -0.2f,
            0.2f, 2f, -0.2f,//11
            -0.2f, 1f, -0.2f,
            -0.2f, 2f, -0.2f,//13
    };
    private byte index[] = {
            3, 4, 5,//�簢�� �ظ�
            2, 3, 5,

            0, 2, 1,
            0, 2, 3,
            0, 4, 3,
            1, 4, 0,//�簢�� ���̵� �ܸ�


            6, 7, 8,
            7, 8, 9,
            8, 9, 10,
            9, 10, 11,
            10, 11, 12,
            11, 12, 13,
            12, 7, 13,
            12, 7, 6, //������ü ���̵�

            11, 9, 7,
            11, 13, 7,//������ü ��
    };
    private float color[] = {
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
    private Vector3 position;
    private Vector3 velocity;

    public Obj3d_PointingArrow(){
        vertbuf = arrayToBuffer(vert);
        colorbuf = arrayToBuffer(color);

        indexbuf = ByteBuffer.allocateDirect(index.length);
        indexbuf.put(index);
        indexbuf.position(0);


        rotationSpeed = 0.8f;

        position = new Vector3(0, 0, 0);
        velocity = new Vector3();
    }

    private double upDownShake = 0;
    public void draw(GL10 gl){
        gl.glPushMatrix();

        upDownShake += Config.arrowShakeSpeed;
        Vector3.add(position, velocity);
        Vector3.add(position, new Vector3(0, (float)Math.sin(upDownShake) * 0.01f, 0));

        gl.glTranslatef(position.x, position.y, position.z);
        currentRot = (currentRot + rotationSpeed) % 360;
        gl.glRotatef(currentRot, 0, 1, 0);

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

    public void moveTo(Vector3 vec3){
        Vector3.copy(position, vec3);
    }
}
