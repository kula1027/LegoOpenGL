package com.example.notebook.legoopengl;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.notebook.legoopengl.PopUps.PopUpSetColor;
import com.example.notebook.legoopengl.PopUps.popup_arrows;



public class MainActivity extends Activity {
    GamePlayView gamePlayView;
    popup_arrows popuparrows;
    PopUpSetColor popUpSetColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        popuparrows = new popup_arrows(getLayoutInflater());
        popUpSetColor = new PopUpSetColor(getLayoutInflater());
        gamePlayView = (GamePlayView)findViewById(R.id.view);
    }

    public void onClickBtn(View v){
        switch (v.getId()){
            case R.id.setCube:
                gamePlayView.renderer.dropCube();
                break;
            case R.id.chCube:
                popUpSetColor.showORhide();
                popuparrows.hide();
                break;
            case R.id.hideTr:
                gamePlayView.renderer.toggleTrans();
                popuparrows.toggleView();
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
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
