package com.example.notebook.legoopengl;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.notebook.legoopengl.MainActivity;
import com.example.notebook.legoopengl.statics.Config;


/**
 * Created by notebook on 2015-11-01.
 */
public class ArrowController {
    private int arrowRadius;
    private int arrowPos[][] = new int[4][2];//start from lu, clockwise
    private LinearLayout ll_arrows;

    public ArrowController(LinearLayout li){
        ll_arrows = li;

        ll_arrows.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent evt) {
                switch (evt.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.d("touch", "x: " + evt.getX() + " y: " + evt.getY());
                        isArrowClicked(evt.getX(), evt.getY());
                        break;
                }
                return true;
            }
        });

        init();
    }

    private void init(){
        arrowRadius = 29 * (Config.DPI / 160);

        arrowPos[0][0] = 50 * (Config.DPI / 160);
        Log.d("D", arrowPos[0][0] + " " + Config.DPI);
        arrowPos[0][1] = 50 * (Config.DPI / 160);
        arrowPos[1][0] = 110 * (Config.DPI / 160);
        arrowPos[1][1] = 50 * (Config.DPI / 160);
        arrowPos[2][0] = 110 * (Config.DPI / 160);
        arrowPos[2][1] = 110 * (Config.DPI / 160);
        arrowPos[3][0] = 50 * (Config.DPI / 160);
        arrowPos[3][1] = 110 * (Config.DPI / 160);
    }

    public void isArrowClicked(float x, float y){
        for(int loop = 0; loop < 4; loop++){
            if(distance(arrowPos[loop][0], arrowPos[loop][1], x, y) < arrowRadius * arrowRadius){
                MainActivity.gamePlayView.renderer.moveArrow(loop);
                break;
            }
        }
    }

    private float distance(int i, int i2, float f, float f2){
        return (i - f) * (i - f) + (i2 - f2) * (i2 - f2);
    }

    public void syncRotation(float angle){
        ll_arrows.setRotation(45f - angle);
    }
    public void toggleView(){
        if(ll_arrows.getVisibility() == View.VISIBLE){
            ll_arrows.setVisibility(View.GONE);
        }else {
            ll_arrows.setVisibility(View.VISIBLE);
        }
    }
}
