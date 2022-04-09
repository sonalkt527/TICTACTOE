package com.example.connect_3_game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //0 is yellow 1 is red
    int activeplayer =0;
    boolean gameactive=true; //
    int[] gamestate={2,2,2,2,2,2,2,2,2}; //initial counter to keep which playerr is at which position
    int[][] win={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{2,5,8},{1,4,7},{0,4,8},{2,4,6}};//winning combination1111
   /* dropin method is for imagesview to drop from above, keep a counter about which position is filled by which player and check winning combination*/
    public void dropin(View view) {
        ImageView counter = (ImageView) view; //object counter of type View
        int tappedcounter = Integer.parseInt(counter.getTag().toString());//on which position did user click
        if (gamestate[tappedcounter] == 2 && gameactive) //check of the empty position
        {
        gamestate[tappedcounter] = activeplayer;

        counter.setTranslationY(-1500);


            if (activeplayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activeplayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activeplayer = 0;

            }
            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);
            for (int[] win2 : win) {
                if (gamestate[win2[0]] == gamestate[win2[1]] && gamestate[win2[1]] == gamestate[win2[2]] && gamestate[win2[0]] != 2) {
                    String winner;
                    //someone has won
                    gameactive=false;
                    if (activeplayer == 1) {
                        winner = "yellow";
                    } else
                        winner = "red";

                    Button playacting1 =(Button) findViewById(R.id.playagainbutton);
                    TextView text1=(TextView) findViewById(R.id.winnertext);
                    text1.setText(winner+" has won");
                    text1.setVisibility(View.VISIBLE);
                    playacting1.setVisibility(View.VISIBLE);
                }
            }

        }
    }
   public void playAgain(View view)
   {
       Button playagain1=(Button) findViewById(R.id.playagainbutton);
       TextView winnerTextview=(TextView) findViewById(R.id.winnertext);
       playagain1.setVisibility(View.INVISIBLE);
       winnerTextview.setVisibility(View.INVISIBLE);
       GridLayout gridlayout1= (GridLayout) findViewById(R.id.gridLayout);
       for(int i=0; i<gridlayout1.getChildCount();i++)
       {
           ImageView counter= (ImageView) gridlayout1.getChildAt(i);
           counter.setImageDrawable(null);
       }

        activeplayer =0;
        gameactive=true;
       for(int i=0; i<gamestate.length;i++)
       {
           gamestate[i]=2;
       }

   }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}