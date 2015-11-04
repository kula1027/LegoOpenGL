package com.example.notebook.legoopengl.PopUps;

import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.example.notebook.legoopengl.MainActivity;
import com.example.notebook.legoopengl.R;


/**
 * Created by notebook on 2015-11-01.
 */
public class PopUpArrowController {
    private View popUpView;
    private PopupWindow popupWindow;
    private int arrowRadius = 70;
    private int circleCenter[] = {210, 210};
    private int circleRadius = (int)(75f * 1.414f);
    private int arrowPos[][] = new int[4][2];//lu부터 시계방향 오름차순
    private LinearLayout ll_main;

    public PopUpArrowController(LayoutInflater LI){
        popUpView = LI.inflate(R.layout.popup_dir_arrows, null);
        popUpView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent evt) {
                switch (evt.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        Log.d("touch", "x: " + evt.getX() + " y: " + evt.getY());
                        isArrowClicked(evt.getX(), evt.getY());
                        break;
                }
                return true;
            }
        });

        popupWindow = new PopupWindow(popUpView, RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        ll_main = (LinearLayout)popUpView.findViewById(R.id.ll_arrow);
        arrowPos[0][0] = 135;
        arrowPos[0][1] = 135;
        arrowPos[1][0] = 285;
        arrowPos[1][1] = 135;
        arrowPos[2][0] = 285;
        arrowPos[2][1] = 285;
        arrowPos[3][0] = 135;
        arrowPos[3][1] = 285;
        showAtInit();
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

    public void syncRotation(float angle)
    {
        ll_main.setRotation(45f - angle);
        for(int loop = 0; loop < 4; loop++) {
            arrowPos[loop][0] = (int) (circleRadius * Math.sin(Math.toRadians(-angle + loop * 90))) + circleCenter[0];
            arrowPos[loop][1] = -(int) (circleRadius * Math.cos(Math.toRadians(-angle + loop * 90))) + circleCenter[1];
        }
    }
    public void toggleView(){
        if(popupWindow.isShowing()){
            popupWindow.dismiss();
        }else {
            popupWindow.showAtLocation(popUpView, Gravity.NO_GRAVITY, 0, 0);
        }
    }

    public void hide(){
        popupWindow.dismiss();
    }
    public void showAtInit(){
        popUpView.post(new Runnable() {
            @Override
            public void run(){
                popupWindow.showAtLocation(popUpView, Gravity.NO_GRAVITY, 0, 0);
            }
        });
    }
}
