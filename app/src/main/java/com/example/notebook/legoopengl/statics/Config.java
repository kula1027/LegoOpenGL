package com.example.notebook.legoopengl.statics;

/**
 * Created by notebook on 2015-10-31.
 */
public class Config {
    public static int size[] = {19, 19, 19};//max n of cubes in x, y, z

    public static float dropYpos = 25f;
    public static float dropSpeed_Cube = -0.3f;

    public static float rotateSensitivity = 0.1f;
    public static float cameraDistance_start = 15f;
    public static float cameraDistance_max = 30f;
    public static float cameraDistance_min = 10f;
    public static float lookLimit_max = 12;
    public static float lookLimit_min = 2;

    public static double arrowShakeSpeed = 0.05;
    public static int DPI;//will be set in onCreate();
}
