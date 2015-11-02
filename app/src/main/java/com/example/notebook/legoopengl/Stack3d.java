package com.example.notebook.legoopengl;

import com.example.notebook.legoopengl.statics.Config;

/**
 * Created by notebook on 2015-10-31.
 */
public class Stack3d {
    public boolean data[][][] = new boolean[Config.size[0]][Config.size[1]][Config.size[2]];//x, y, z
    public Vector3 preLocation = new Vector3();
    public int depthTest(int x_, int z_){//해당 xy좌표의 가장 높은 열린값 리턴, 해당 배열 true로 변환
        if(data[x_][Config.size[1] - 1][z_] == true)return -1;

        for(int loop = Config.size[1] - 2; loop >= 0 ; loop--){
            if(data[x_][loop][z_]){
                data[x_][loop + 1][z_] = true;
                preLocation = new Vector3(x_, loop + 1, z_);
                return loop + 1;
            }
            if(loop == 0){
                data[x_][loop][z_] = true;
                preLocation = new Vector3(x_, loop, z_);
                return 0;
            }
        }

        return -1;
    }

    public void remove(){
        data[Math.round(preLocation.x)][Math.round(preLocation.y)][Math.round(preLocation.z)] = false;
    }
}
