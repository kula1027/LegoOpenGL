package com.example.notebook.legoopengl;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by notebook on 2015-10-31.
 */
public class GamePlayView extends GLSurfaceView{
    MainRenderer renderer;

    public GamePlayView(Context context, AttributeSet attrs){
        super(context, attrs);
        setFocusable(true);
        renderer = new MainRenderer();
        setRenderer(renderer);
    }

    private float x_pre = 0;
    private float y_pre = 0;
    public boolean onTouchEvent(MotionEvent evt){
        float x = evt.getX();
        float y = evt.getY();

        float diff_x = x - x_pre;
        float diff_y = y - y_pre;

        switch (evt.getAction()){
            case MotionEvent.ACTION_MOVE :
                renderer.camera.Rotate(diff_x);
        }

        x_pre = x;
        y_pre = y;

        return true;
    }
}
