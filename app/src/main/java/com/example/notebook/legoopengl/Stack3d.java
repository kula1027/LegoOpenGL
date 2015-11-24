package com.example.notebook.legoopengl;

import android.util.Log;

import com.example.notebook.legoopengl.statics.Config;

import org.json.JSONArray;

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

    public void JSONToCubeMap(JSONArray jsonArray){
        try {
            for (int loop = 0; loop < Config.size[0]; loop++) {
                for(int loop2 = 0; loop2 < Config.size[1]; loop2++){
                    for(int loop3 = 0; loop3 < Config.size[2]; loop3++){
                        cubeMap[loop][loop2][loop3] = Integer.parseInt(jsonArray.getJSONArray(loop).getJSONArray(loop2).getString(loop3));
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void JSONToCubeMapHeight(JSONArray jsonArray){
        try {
            for (int loop = 0; loop < Config.size[0]; loop++) {
                for (int loop2 = 0; loop2 < Config.size[1]; loop2++) {
                    heightMap[loop][loop2] = Integer.parseInt(jsonArray.getJSONArray(loop).getString(loop2));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String toJSONstr_cubeMap(){
        String[][][] strArr = new String[Config.size[0]][Config.size[1]][Config.size[2]];

        for(int loop = 0; loop < Config.size[0]; loop++){
            for(int loop2 = 0; loop2 < Config.size[1]; loop2++){
                for(int loop3 = 0; loop3 < Config.size[2]; loop3++){
                    strArr[loop][loop2][loop3] = Integer.toString(cubeMap[loop][loop2][loop3]);
                }
            }
        }

        JSONArray ja = null;
        try {
            ja = new JSONArray(strArr);
        }catch (Exception e){
            e.printStackTrace();
        }

        return ja.toString();
    }

    public String toJSONstr_cubeMapHeight(){
        String[][] strArr = new String[Config.size[0]][Config.size[2]];

        for(int loop = 0; loop < Config.size[0]; loop++){
            for(int loop2 = 0; loop2 < Config.size[2]; loop2++){
                strArr[loop][loop2] = Integer.toString(heightMap[loop][loop2]);
            }
        }

        JSONArray ja = null;
        try {
            ja = new JSONArray(strArr);
        }catch (Exception e){
            e.printStackTrace();
        }

        return ja.toString();
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
