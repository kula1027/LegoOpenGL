package com.example.notebook.legoopengl;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.notebook.legoopengl.PopUps.PopUpSetColor;



public class MainActivity extends Activity {
    static public GamePlayView gamePlayView;
    PopUpSetColor popUpSetColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        popUpSetColor = new PopUpSetColor(getLayoutInflater());
        gamePlayView = (GamePlayView)findViewById(R.id.view);
        gamePlayView.init(getLayoutInflater());
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
            case R.id.restore:
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

            case R.id.arrow_ld:
                Log.d("arrow", "Left Down");
                break;
            case R.id.arrow_rd:
                Log.d("arrow", "Right Down" );
                break;
            case R.id.arrow_lu:
                Log.d("arrow", "Left Up" );
                break;
            case R.id.arrow_ru:
                Log.d("arrow", "Right Up" );
                break;
        }
    }
}
