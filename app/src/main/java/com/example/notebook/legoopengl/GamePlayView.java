package com.example.notebook.legoopengl;

import android.app.Activity;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by notebook on 2015-10-31.
 */
public class GamePlayView extends GLSurfaceView{
    public MainRenderer renderer;
    private ArrowController popUpArrows;

    public GamePlayView(Context context, AttributeSet attrs){
        super(context, attrs);
        setFocusable(true);
        renderer = new MainRenderer();

        setRenderer(renderer);
    }
    public void saveState(Bundle saveState) {
        renderer.saveState(saveState);
    }
    public void restoreState(Bundle saveState){
        renderer.restoreState(saveState);
        //popUpArrows.syncRotation(renderer.camera.getRotation());
    }

    public void init(Activity act){
        popUpArrows = new ArrowController((LinearLayout)act.findViewById(R.id.ll_arrows));
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
                    float gradient = diff_y / diff_x;
                    if(Math.abs(gradient) < 1){//input horizontal
                        renderer.camera.rotate(diff_x);
                        popUpArrows.syncRotation(renderer.camera.getRotation());
                    }else{//input vertical
                        renderer.camera.setLookingDir(diff_y);
                    }
                }
                break;
        }

        x_pre = x;
        y_pre = y;

        return true;
    }
}
