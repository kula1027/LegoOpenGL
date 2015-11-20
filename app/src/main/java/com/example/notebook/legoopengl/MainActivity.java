package com.example.notebook.legoopengl;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.example.notebook.legoopengl.statics.Config;


public class MainActivity extends Activity {
    static public GamePlayView gamePlayView;
    private PopUpSetColor popUpSetColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setDPI();

        popUpSetColor = new PopUpSetColor(getLayoutInflater());
        gamePlayView = (GamePlayView)findViewById(R.id.view);
        gamePlayView.init(this);

        if(savedInstanceState != null){
            gamePlayView.restoreState(savedInstanceState);
        }
    }
    private void setDPI(){
        Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        Config.DPI = metrics.densityDpi;
    }

    protected  void onSaveInstanceState(Bundle saveState){
        super.onSaveInstanceState(saveState);
        gamePlayView.saveState(saveState);
    }

    public void onClickBtn(View v){
        switch (v.getId()){
            case R.id.setCube:
                gamePlayView.renderer.dropCube();
                break;
            case R.id.chCube:
                popUpSetColor.toggleView();
                break;
            case R.id.hideTr:
                gamePlayView.renderer.toggleTrans();
                gamePlayView.arrows_toggle();
                break;
            case R.id.remove:
                gamePlayView.renderer.removeCube();
                break;

            case R.id.btn_trans:
                gamePlayView.renderer.setCubeColor(0);
                popUpSetColor.hide();
                break;
            case R.id.btn_red:
                gamePlayView.renderer.setCubeColor(1);
                popUpSetColor.hide();
                break;
            case R.id.btn_blue:
                gamePlayView.renderer.setCubeColor(2);
                popUpSetColor.hide();
                break;
            case R.id.btn_green:
                gamePlayView.renderer.setCubeColor(3);
                popUpSetColor.hide();
                break;
            case R.id.btn_yellow:
                gamePlayView.renderer.setCubeColor(4);
                popUpSetColor.hide();
                break;
            case R.id.btn_orange:
                gamePlayView.renderer.setCubeColor(5);
                popUpSetColor.hide();
                break;
            case R.id.btn_black:
                gamePlayView.renderer.setCubeColor(6);
                popUpSetColor.hide();
                break;
            case R.id.btn_white:
                gamePlayView.renderer.setCubeColor(7);
                popUpSetColor.hide();
                break;
            case R.id.btn_brown:
                gamePlayView.renderer.setCubeColor(8);
                popUpSetColor.hide();
                break;
        }
    }
}
