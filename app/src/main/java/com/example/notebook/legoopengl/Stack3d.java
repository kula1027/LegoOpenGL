package com.example.notebook.legoopengl;

import com.example.notebook.legoopengl.statics.Config;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by notebook on 2015-10-31.
 */
public class Stack3d {
    private int heightMap[][] = new int[Config.size[0]][Config.size[2]];

    public int getHeight(int x_, int z_){//해당 xy좌표의 높이 리턴
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
}
