package com.example.notebook.legoopengl;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.Button;

import com.example.notebook.legoopengl.PopUps.PopUpArrows;

/**
 * Created by notebook on 2015-10-31.
 */
public class GamePlayView extends GLSurfaceView{
    MainRenderer renderer;
    PopUpArrows popUpArrows;

    public GamePlayView(Context context, AttributeSet attrs){
        super(context, attrs);
        setFocusable(true);
        renderer = new MainRenderer();
        setRenderer(renderer);
    }
    public void init(LayoutInflater LI){
        popUpArrows = new PopUpArrows(LI);
    }
    public void arrows_hide(){
        popUpArrows.hide();
    }
    public void arrows_toggle(){
        popUpArrows.toggleView();
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
                popUpArrows.syncRotation(renderer.camera.getRotation());
                break;
        }

        x_pre = x;
        y_pre = y;

        return true;
    }
}
