package com.example.notebook.legoopengl;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;

import com.example.notebook.legoopengl.PopUps.PopUpArrowController;

/**
 * Created by notebook on 2015-10-31.
 */
public class GamePlayView extends GLSurfaceView{
    public MainRenderer renderer;
    private PopUpArrowController popUpArrows;

    public GamePlayView(Context context, AttributeSet attrs){
        super(context, attrs);
        setFocusable(true);
        renderer = new MainRenderer();
        setRenderer(renderer);
    }
    public void init(LayoutInflater LI){
        popUpArrows = new PopUpArrowController(LI);
    }
    public void arrows_hide(){
        popUpArrows.hide();
    }
    public void arrows_toggle(){
        popUpArrows.toggleView();
    }

    private float x_pre = 0;
    private float y_pre = 0;
    private float distance_pre = 0;
    public boolean onTouchEvent(MotionEvent evt){
        float x = evt.getX();
        float y = evt.getY();
        int touchCount = evt.getPointerCount();

        float diff_x = x - x_pre;
        float diff_y = y - y_pre;

        switch (evt.getAction()){
            case MotionEvent.ACTION_MOVE :
                if(touchCount >= 2){
                    float disX = Math.abs(evt.getX(0) - evt.getX(1));
                    float disY = Math.abs(evt.getY(0) - evt.getY(1));
                    float distance = disX * disX + disY * disY;
                    if(distance > distance_pre){
                        renderer.camera.moveCloser();
                    }else{
                        renderer.camera.moveFarther();
                    }
                    distance_pre = distance;
                }else {
                    renderer.camera.rotate(diff_x);
                    popUpArrows.syncRotation(renderer.camera.getRotation());
                    renderer.camera.setLookingDir(diff_y);
                }
                break;
        }

        x_pre = x;
        y_pre = y;

        return true;
    }
}
