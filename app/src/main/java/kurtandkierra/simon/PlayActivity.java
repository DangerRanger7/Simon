package kurtandkierra.simon;

import android.app.Activity;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Timer;

/**
 * Created by Kierra on 2/25/2018.
 */

public class PlayActivity extends Activity {
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


        //call for sequence
        sequence();

    }
    //onResume
    @Override
    protected void onResume() {
        super.onResume();

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
                if (soundsLoaded.contains(gameBeepId)){
                    soundPool.play(gameBeepId, 1.0f, 1.0f, 0,0,1.0f);
                }
               
            }
        });
        //red_button
        findViewById(R.id.red_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (soundsLoaded.contains(gameBeepId)){
                    soundPool.play(gameBeepId, 1.0f, 1.0f, 0,0,1.0f);
                }
            }
        });
        //yellow_button
        findViewById(R.id.yellow_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (soundsLoaded.contains(gameBeepId)){
                    soundPool.play(gameBeepId, 1.0f, 1.0f, 0,0,1.0f);
                }
            }
        });
        //blue_button
        findViewById(R.id.blue_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (soundsLoaded.contains(gameBeepId)){
                    soundPool.play(gameBeepId, 1.0f, 1.0f, 0,0,1.0f);
                }
            }
        });
    }

    //sequence for buttons*******************************************************************************************
        public void sequence(){
           // int x = 0;\
            int num = 0;
            for (int i = 0; i < 20; i++) {
                Random random = new Random();
                num += random.nextInt(4) + 1; //4 is max 1 is min

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
                    b4 = findViewById(R.id.blue_button);
                   // b4.setBackgroundColor(0x1d04f7);
                    break;
                default:
                    break;
            }

    }



}
