package kurtandkierra.simon;

import android.app.Activity;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by Kierra on 2/25/2018.
 */

public class PlayActivity extends Activity {

    // private Handler handler;

    final Button buttonColor[] = new Button[4];

    Button buttonInput[] = new Button[8];
    //soundpool
    private SoundPool soundPool;
    private Set<Integer> soundsLoaded;
    int seqNum = 8;
    int[] sequence = new int[seqNum];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        //rotation
        String hs_label;
        String score_label;

        if (savedInstanceState == null) {
            hs_label = "0";
            score_label = "0";
        } else {
            hs_label = (String) savedInstanceState.get("highScore");
            score_label = (String) savedInstanceState.get("score");
        }
        final TextView highScore_tv = findViewById(R.id.highScore_textview);
        final TextView score_tv = findViewById(R.id.score_textView);

        highScore_tv.setText(hs_label);
        score_tv.setText(score_label);

        //final int buttonColor[] = new int[4];

        //for soundsLoaded
        soundsLoaded = new HashSet<Integer>();

        //button id
        buttonColor[0] = findViewById(R.id.green_button);
        buttonColor[1] = findViewById(R.id.red_button);
        buttonColor[2] = findViewById(R.id.yellow_button);
        buttonColor[3] = findViewById(R.id.blue_button);

        for (int i = 0; i < 4; i++) {
            buttonColor[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

            /*create ImageButton*/
            //final int ids[] = {R.id.green_ImageButton, R.id.red_ImageButton, R.id.yellow_ImageButton, R.id.blueImageButton};
            /*
                for (int i = 0; i < ids.length; i++){
                    ids[i] = findViewById(this);
                }
            * */
        }

        //start button
        findViewById(R.id.start_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //call for sequence
                startCounter();
            }
        });

    }

    //score
    private TextView highScore_tv;
    private TextView score_tv;
    private UpdateTask updateTask;
    int highScore;

    //start counter for score
    private void startCounter() {
        highScore_tv = findViewById(R.id.highScore_textview);
        score_tv = findViewById(R.id.score_textView);

        if (updateTask != null && updateTask.getStatus() == AsyncTask.Status.FINISHED) {
            updateTask = null;
        }
        if (updateTask == null) {
            updateTask = new UpdateTask();
            updateTask.execute();
        } else {
            Log.i("START", "task is already running");
        }
    }

    /**
     * UpdateTask***********************************************************/
    int finalScore;

    class UpdateTask extends AsyncTask<Void, Integer, Void> {
               
        @Override
        protected Void doInBackground(Void... voids) {
            boolean simonTurn = true;
            int num;
            Random random = new Random();

            for (int i = 0; i < seqNum; i++) {

                num = random.nextInt(4);
                //buttonColor[num].performClick();
                sequence[i] = num;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                onProgressUpdate(num);
            }
            return null;
        }

       @Override
        protected void onProgressUpdate(Integer... num) {
          // Button buttonColor[] = new Button[4];
        int number = num[0];

          // Button b;
           switch (number) {
               case 1:
                  // b = findViewById(R.id.green_button);
                  // b.performClick();
                   //buttonColor[0].performClick();
                   Log.i("Color: ", "GREEN");
                   break;
               case 2:
                   //b = findViewById(R.id.red_button);
                 //  b.performClick();
                  // buttonColor[1].performClick();
                   Log.i("Color: ", "RED");
                   break;
               case 3:
                  // b = findViewById(R.id.yellow_button);
                  // b.performClick();
                  // buttonColor[2].performClick();
                   Log.i("Color: ", "YELLOW");
                   break;
               case 4:
                 // b = findViewById(R.id.blue_button);
                  // b.performClick();
                  // buttonColor[3].performClick();
                   Log.i("Color: ", "BLUE");
                   break;
               default:
                   break;
           }
           onResume();

           /* for (int i = 0; i < num; i++) {
                if (simonTurn == true) {
                    try {
                        sequence(num);
                        simonTurn = false;
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        simonTurn = true;
                        sequence(num);
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }*/


            // return null;
           /*for (int i = 0; i < num; i++) {
                           if (num == 9) {
                                Thread.interrupted();
                            } else {
                                if (simonTurn == true) {
                                    try {
                                        sequence(num);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    simonTurn = false;
                                    try {
                                        Thread.sleep(5000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    simonTurn = true;
                                    try {
                                        Thread.sleep(5000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }*/
        }
    }

/*=======SOUND=========********************************===================================******************************************/
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
                if (status == 0) {//means it was a success
                    soundsLoaded.add(sampleId);
                } else {
                    Log.i("Sound", "ERROR CAN'T LOAD SOUND!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1");
                }
            }
        });

        //load sounds
        final int gameBeepId = soundPool.load(this, R.raw.gamebeep, 1);
        //green_button
        findViewById(R.id.green_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playSound(gameBeepId);

            }
        });
        //red_button
        findViewById(R.id.red_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playSound(gameBeepId);
            }
        });
        //yellow_button
        findViewById(R.id.yellow_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playSound(gameBeepId);
            }
        });
        //blue_button
        findViewById(R.id.blue_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playSound(gameBeepId);
            }
        });
    }

    //onPause
    @Override
    protected void onPause() {
        super.onPause();
        if (soundPool != null) {
            soundPool.release();
            soundPool = null;
            soundsLoaded.clear();
        }
    }

    //play sound**********************************************************************
    private void playSound(int soundID) {
        if (soundsLoaded.contains(soundID)) {
            soundPool.play(soundID, 1.0f, 1.0f, 0, 0, 1.0f);
        }
    }
/*END OF SOUND ****************======================*********************************======================================************/

    //sequence for buttons*******************************************************************************************
    /*public void sequence(int num) throws InterruptedException {
        //int num = 1;
        int seq;
        int buttonSequence[] = new int[8];
        //Random random = new Random();
        //need boolean
        //true for simon
        //play sund, sleep
        //reach end flip variable inform user its their turn
        
            for (int i = 0; i < num; i++) {
                Random random = new Random();
                    seq = random.nextInt(4) + 1;
                    buttonPressed(seq);
                    buttonSequence[i] = seq;

                    onResume();
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    compare(buttonSequence);

        }
    }*/

  //  Handler handler = new Handler();
    /*public void buttonPressed(int number) {
        Button b;
        switch (number) {
            case 1:
                b = findViewById(R.id.green_button);
                b.performClick();
                Log.i("Color: ", "GREEN");
                break;
            case 2:
                b = findViewById(R.id.red_button);
                b.performClick();
                Log.i("Color: ", "RED");
                break;
            case 3:
                b = findViewById(R.id.yellow_button);
                b.performClick();
                Log.i("Color: ", "YELLOW");
                break;
            case 4:
                b = findViewById(R.id.blue_button);
                b.performClick();
                Log.i("Color: ", "BLUE");
                break;
            default:
                break;
        }
    }*/

//compare input and increase score
int score = 0;
    public boolean compare(int[] bc1){
        int[] bc2 =  new int[bc1.length];

        TextView tv = findViewById(R.id.score_textView);
            boolean flag = false;
            for(int i = 0; i < bc1.length; i++){
                if (bc1[i] == bc2[i]){
                    flag = true;
                    score += 20;
                    tv.setText("" + score);

                }else{
                  flag = false;
                    Toast.makeText(getApplicationContext(), "You lose!",
                            Toast.LENGTH_LONG).show();
                }
            }

        return flag;
    }

    //rotation
       @Override
   protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);

        TextView highScore = findViewById(R.id.highScore_textview);
        outState.putString("highScore", highScore.getText().toString());

        TextView score = findViewById(R.id.score_textView);
        outState.putString("score", score.getText().toString());
    }

}
