package com.example.notebook.legoopengl.PopUps;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.example.notebook.legoopengl.R;

/**
 * Created by notebook on 2015-11-01.
 */
public class PopUpSetColor {
    private View popUpView;
    private PopupWindow popupWindow;

    public PopUpSetColor(LayoutInflater LI){
        popUpView = LI.inflate(R.layout.popup_setcolor, null);
        popupWindow = new PopupWindow(popUpView, RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
    }

    public void toggleView(){
        if(popupWindow.isShowing()){
            popupWindow.dismiss();
        }else {
            popupWindow.showAtLocation(popUpView, Gravity.CENTER, 0, 0);
        }
    }

    public void hide(){
        popupWindow.dismiss();
    }
}

