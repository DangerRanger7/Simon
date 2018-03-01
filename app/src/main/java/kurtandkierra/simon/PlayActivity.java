package kurtandkierra.simon;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.Random;
import java.util.Timer;

/**
 * Created by Kierra on 2/25/2018.
 */

public class PlayActivity extends Activity {
    final int buttonColor[] = new int[4];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

       //final int buttonColor[] = new int[4];


        //green button
        findViewById(R.id.green_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //#07ed44 - lighter color

            }
        });

        // red button
        findViewById(R.id.red_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //#f21607- lighter color
            }
        });

        //yellow button
        findViewById(R.id.yellow_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //#ffff05- lighter color

            }
        });

        //blue button
        findViewById(R.id.blue_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // #1d04f7 - lighter color
            }
        });

        //call for sequence
        sequence();

    }
        //sequence for buttons*******************************************************************************************
        public void sequence(){
           // int x = 0;\
            int num = 0;
            for (int i = 0; i < 20; i++) {
                Random random = new Random();
                num = random.nextInt(4) + 1; //4 is max 1 is min

                //buttonColor[x] = num;

                //call for button pressed
                buttonPressed(num);
            }

    }
    public void buttonPressed(int number){
            Button b1, b2, b3, b4;

            switch(number){
                case 1:
                    b1 = findViewById(R.id.green_button);
                   // b1.onKeyDown(07ed44);
                  //  b1.setBackgroundColor(0x07ed44);
                    break;
                case 2:
                    b2 = findViewById(R.id.red_button);
                 //  b2.setBackgroundColor(0xf21607);
                    break;
                case 3:
                    b3 = findViewById(R.id.yellow_button);
                  //  b3.setBackgroundColor(0xffff05);
                    break;
                case 4:
                    b4 = findViewById(R.id.yellow_button);
                   // b4.setBackgroundColor(0x1d04f7);
                    break;
                default:
                    break;
            }

    }



}
