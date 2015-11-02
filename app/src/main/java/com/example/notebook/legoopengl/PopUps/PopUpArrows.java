package com.example.notebook.legoopengl.PopUps;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.example.notebook.legoopengl.R;
import com.example.notebook.legoopengl.Vector3;


/**
 * Created by notebook on 2015-11-01.
 */
public class PopUpArrows {
    private View popUpView;
    private PopupWindow popupWindow;
    private ImageView arrow[] = new ImageView[4];
    private float arrowSize;
    private Vector3 center_rot;
    private LinearLayout ll;

    public PopUpArrows(LayoutInflater LI){
        popUpView = LI.inflate(R.layout.popup_dir_arrows, null);
        popUpView.setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View v, MotionEvent evt){
                isArrowClicked(evt.getX(), evt.getY());
                return true;
            }
        });
        popupWindow = new PopupWindow(popUpView, RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        showAtInit();

        ll = (LinearLayout)popUpView.findViewById(R.id.ll_arrow);
        arrow[0] = (ImageView)popUpView.findViewById(R.id.arrow_lu);//left up 부터 시계방향으로 오름차순
        arrow[1] = (ImageView)popUpView.findViewById(R.id.arrow_ru);
        arrow[2] = (ImageView)popUpView.findViewById(R.id.arrow_rd);
        arrow[3] = (ImageView)popUpView.findViewById(R.id.arrow_ld);


    }

    public void isArrowClicked(float x, float y){
        Log.d("clicked", "x: " + x + " y: " + y);

        int pos_arrow[] = new int[2];
        arrow[3].getLocationOnScreen(pos_arrow);
        Log.d("d", pos_arrow[0] + " " + pos_arrow[1]);
        center_rot = new Vector3(pos_arrow[0] + arrow[3].getWidth(), pos_arrow[1] - arrow[3].getHeight() / 2, 0);
        Log.d("dd", center_rot.x + " " + center_rot.y);
        arrowSize = arrow[0].getWidth() * 0.4f;
        for(int loop = 0; loop < 1; loop++){

        }

    }
    private float distance(int i, int i2, float f, float f2){
        return (i - f) * (i - f) + (i2 - f2) * (i2 - f2);
    }

    public void syncRotation(float angle){
        ll.setRotation(45f - angle);
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
