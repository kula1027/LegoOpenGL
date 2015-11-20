package com.example.notebook.legoopengl;

import android.util.Log;

import com.example.notebook.legoopengl.statics.Config;

import java.io.Serializable;
import java.util.Stack;

/**
 * Created by notebook on 2015-10-31.
 */
public class Stack3d implements Serializable{
    private int heightMap[][] = new int[Config.size[0]][Config.size[2]];
    public int cubeMap[][][] = new int[Config.size[0]][Config.size[1]][Config.size[2]];

    public Stack3d(){
        for(int loop = 0; loop < Config.size[0]; loop++){
            for(int loop2 = 0; loop2 < Config.size[1]; loop2++){
                for(int loop3 = 0; loop3 < Config.size[2]; loop3++){
                    cubeMap[loop][loop2][loop3] = -1;
                }
            }
        }
    }

    public int getHeight(int x_, int z_){//�ش� xy��ǥ�� ���� ����
        x_ += Config.size[0] / 2;
        z_ += Config.size[2] / 2;
        return heightMap[x_][z_];
    }
    public void increaseHeight(int x_, int z_){
        x_ += Config.size[0] / 2;
        z_ += Config.size[2] / 2;

        heightMap[x_][z_]++;
    }

    public void decreaseHeight(int x_, int z_){
        x_ += Config.size[0] / 2;
        z_ += Config.size[2] / 2;
        heightMap[x_][z_]--;
    }

    public void pushCube(int x_, int y_, int z_, int cubeNum){
        x_ += Config.size[0] / 2;
        z_ += Config.size[2] / 2;
        cubeMap[x_][y_][z_] = cubeNum;
        Log.d("CM", x_ + " " + y_ + " " + z_ + " " + cubeNum);
    }

    public void popCube(int x_, int y_, int z_){
        x_ += Config.size[0] / 2;
        z_ += Config.size[2] / 2;
        cubeMap[x_][y_][z_] = -1;
        Log.d("CM", x_ + " " + y_ + " " + z_);
    }
}
