package com.example.notebook.legoopengl.PopUps;

import android.app.Activity;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.example.notebook.legoopengl.R;

import java.io.File;

/**
 * Created by notebook on 2015-11-23.
 */
public class PopUpMenu {
    private View popUpMenu;
    private View popUpSave;
    private View popUpLoad;
    private PopupWindow popupWindow_menu;
    private PopupWindow popupWindow_save;
    private PopupWindow popupWindow_load;

    public PopUpMenu(LayoutInflater LI){
        popUpMenu = LI.inflate(R.layout.popup_menu, null);
        popUpSave = LI.inflate(R.layout.popup_menusave, null);
        popUpLoad = LI.inflate(R.layout.popup_menuload, null);

        popupWindow_menu = new PopupWindow(popUpMenu, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow_save = new PopupWindow(popUpSave, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow_load = new PopupWindow(popUpLoad, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    public void toggleView_Menu(){
        if(popupWindow_menu.isShowing()){
            popupWindow_menu.dismiss();
        }else {
            popupWindow_menu.showAtLocation(popUpMenu, Gravity.CENTER, 0, 0);
        }
        popupWindow_save.dismiss();
        popupWindow_load.dismiss();
    }

    public String getStringSave(){
        EditText et = (EditText)popUpSave.findViewById(R.id.et_save);
        return et.getText().toString();
    }

    public void onSaveBtnClicked(){
        popupWindow_menu.dismiss();
        popupWindow_save.showAtLocation(popUpSave, Gravity.CENTER, 0, 0);
        popupWindow_save.setFocusable(true);
        popupWindow_save.update();
    }
    public void dismissSave(){
        popupWindow_save.dismiss();
    }

    public String getSelectedLoad(){
        RadioGroup radioGroup = (RadioGroup)popUpLoad.findViewById(R.id.radioGroup);
        int rgBtnID = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton)radioGroup.findViewById(rgBtnID);
        return radioButton.getText().toString();
    }
    public void onLoadBtnClicked(Activity mainAct){
        popupWindow_menu.dismiss();
        popupWindow_load.showAtLocation(popUpLoad, Gravity.CENTER, 0, 0);
        RadioGroup radioGroup = (RadioGroup)popUpLoad.findViewById(R.id.radioGroup);

        File fileDir = new File(Environment.getExternalStorageDirectory(), "CubeCube");
        File fileList[] = fileDir.listFiles();
        radioGroup.removeAllViews();
        for (int loop = 0; loop < fileList.length; loop++) {
                RadioButton radioButton = new RadioButton(mainAct);
                radioButton.setText(fileList[loop].getName());
                radioGroup.addView(radioButton);
        }
    }

    public void dismissLoad(){
        popupWindow_load.dismiss();
    }
}
