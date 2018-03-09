package kurtandkierra.simon;

import android.app.Activity;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.lang.reflect.Array;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Timer;

/**
 * Created by Kierra on 2/25/2018.
 */

public class PlayActivity extends Activity {

    private Handler handler;

    final Button buttonColor[] = new Button[4];
    //soundpool
    private SoundPool soundPool;
    private Set<Integer> soundsLoaded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

       //final int buttonColor[] = new int[4];

        //for soundsLoaded
        soundsLoaded = new HashSet<Integer>();

        //button id
        buttonColor[0] = findViewById(R.id.green_button);
        buttonColor[1] = findViewById(R.id.red_button);
        buttonColor[2] = findViewById(R.id.yellow_button);
        buttonColor[3] = findViewById(R.id.blue_button);

        for (int i = 0; i < 4; i++){
            buttonColor[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }

        //start button
        findViewById(R.id.start_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //call for sequence

                sequence();
            }
        });

    }
    //onResume
    @Override
    protected void onResume() {
        super.onResume();
        Button b;

        AudioAttributes.Builder attrBuilder = new AudioAttributes.Builder();
        attrBuilder.setUsage(AudioAttributes.USAGE_GAME);

        SoundPool.Builder spBuilder = new SoundPool.Builder();
        spBuilder.setAudioAttributes(attrBuilder.build());
        spBuilder.setMaxStreams(2);
        soundPool = spBuilder.build();

        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                if (status == 0){//means it was a success
                    soundsLoaded.add(sampleId);
                }else{
                    Log.i("Sound","ERROR CAN'T LOAD SOUND");
                }
            }
        });

        //load sounds
        final int gameBeepId = soundPool.load(this, R.raw.gamebeep, 1);
        //green_button
        findViewById(R.id.green_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setPressed(true);
                playSound(gameBeepId);
              // view.setBackgroundColor(Color.rgb(7, 237, 68));
                // view.setBackgroundColor(0x07ed44);
                //view.setBackgroundColor(Color.rgb(3, 150, 42));

            }
        });
        //red_button
        findViewById(R.id.red_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setPressed(true);
                playSound(gameBeepId);

            }
        });
        //yellow_button
        findViewById(R.id.yellow_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setPressed(true);
                playSound(gameBeepId);
            }
        });
        //blue_button
        findViewById(R.id.blue_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setPressed(true);
               playSound(gameBeepId);
            }
        });
    }
    //onPause


    @Override
    protected void onPause() {
        super.onPause();
        if (soundPool != null){
            soundPool.release();
            soundPool = null;

            soundsLoaded.clear();
        }
    }

    //play sound**********************************************************************
    private void playSound(int soundID){
        if (soundsLoaded.contains(soundID)){
            soundPool.play(soundID, 1.0f, 1.0f, 0,0,1.0f);
        }
    }

    /*******Handler***********************************************************************/

    //sequence for buttons*******************************************************************************************

        public void sequence() {
                  int[] array = new int[8];
                 // int random;
                  int num;
            Random random = new Random();
           for (int i = 0; i < 8; i++) {
               num = random.nextInt(4) + 1;
                array[i] = num;

               buttonPressed(num);
               onResume();
               try {
                   Thread.sleep(1000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }

               /*if (i > 0) {
                   for (int j = 0; j < 8; j++) {

                       array[j] = num;
                       onResume();
                       buttonPressed(num);
                   }
               }*/
                /*   num = array[0];
                   onResume();
                   buttonPressed(num);
                   for(int j = 0; j < array[j]; j++){
                       num = random.nextInt(4) + 1;
                       array[i] = num;

                       onResume();
                       buttonPressed(num);
                   }
               }*/

           }
    }
    public void buttonPressed(int number)  {
            Button b1, b2, b3, b4;

            switch(number){
                case 1:
                    b1 = findViewById(R.id.green_button);
                   b1.setPressed(true);
                    Log.i("color:", "GREEN");
                   // b1.setBackgroundColor(Color.rgb(3, 150, 42));
                   // Thread.sleep(1000);
                 //   b1.setBackgroundColor(0x03962a);

                   // b1.onKeyDown(07ed44);
                  //  b1.setBackgroundColor(0x07ed44);
                    break;
                case 2:
                    b2 = findViewById(R.id.red_button);
                    b2.setPressed(true);
                        Log.i("color:", "RED");
                 //  b2.setBackgroundColor(0xf21607);
                    break;
                case 3:
                    b3 = findViewById(R.id.yellow_button);
                    b3.setPressed(true);
                    Log.i("color:", "YELLOW");
                  //  b3.setBackgroundColor(0xffff05);
                    break;
                case 4:
                    b4 = findViewById(R.id.blue_button);
                    b4.setPressed(true);
                    Log.i("color:", "BLUE");
                   // b4.setBackgroundColor(0x1d04f7);
                    break;
                default:
                    break;
            }

    }



}
