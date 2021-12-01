package com.example.thexogame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int[] gamestate = {2,2,2,2,2,2,2,2,2};
    int [][] winingpos={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int activeplayer = 0;
    boolean gameActive = true;
    public void dropin(View view){
        ImageView counter = (ImageView) view;
        Log.i("tag",counter.getTag().toString());
        int tappedcounter = Integer.parseInt(counter.getTag().toString());
        if (gamestate[tappedcounter] == 2 && gameActive) {
            gamestate[tappedcounter] = activeplayer;
            counter.setTranslationY(-1500);
            if (activeplayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activeplayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activeplayer = 0;
            }
            counter.animate().translationYBy(1500).rotation(3600).setDuration(350);
            for (int[] winingpos : winingpos) {
                if (gamestate[winingpos[0]] == gamestate[winingpos[1]] && gamestate[winingpos[1]] == gamestate[winingpos[2]] && gamestate[winingpos[0]] != 2) {
                    gameActive= false;
                    String winner = "";
                    if (activeplayer == 1) {
                        winner = "yellow";
                    } else {
                        winner = "red";
                    }


                    Button playagain = (Button) findViewById(R.id.playagain);
                    TextView textView = (TextView) findViewById(R.id.textView);
                    textView.setText(winner + " has won!");
                    playagain.setVisibility(view.VISIBLE);
                    textView.setVisibility(view.VISIBLE);

                }
            }
        }
    }
 public void playagain(View view){
     Button playagain = (Button) findViewById(R.id.playagain);
     TextView textView = (TextView) findViewById(R.id.textView);
     playagain.setVisibility(view.INVISIBLE);
     textView.setVisibility(view.INVISIBLE);
     GridLayout grid = (GridLayout) findViewById(R.id.grid);
     for(int i=0; i<grid.getChildCount(); i++ ){
         ImageView counter = (ImageView) grid.getChildAt(i);
         counter.setImageDrawable(null);
     }
     for (int i=0;i<gamestate.length;i++){
         gamestate[i]= 2;
     }

     activeplayer = 0;
      gameActive = true;
 }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}