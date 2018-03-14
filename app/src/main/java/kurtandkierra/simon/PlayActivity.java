package kurtandkierra.simon;

import android.app.Activity;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
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
        }

        //start button
        findViewById(R.id.start_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //call for sequence
                startCounter();
                sequence();

            }
        });


        //High score and score textview
   /* TextView highScore_tv = findViewById(R.id.highScore_textview);
    final int HIGHSCORE = 0;
        highScore_tv.setText(""+HIGHSCORE);
    TextView score_tv = findViewById(R.id.score_textView);
    final int SCORE = 0;
        score_tv.setText(""+SCORE);*/


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
     * UpdateTask*************************************************FOR SCORE
     **********/
    int finalScore;

    class UpdateTask extends AsyncTask<Void, Void, Void> {
        /************NEED TO FIX******************************************************************/
        @Override
        protected Void doInBackground(Void... voids) {

            try {
                int score = 0;
                while (!Thread.interrupted()) {
                   /* final String scoreString = "The score is " + score;
                    Log.i("Update: ", scoreString);
                    score++;
                    finalScore = score;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            score_tv.setText("" + finalScore);
                        }
                    });*/
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                Log.i("THREAD", "Sleep was interrupted for counter. ");
            }
            return null;
        }
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
                if (status == 0) {//means it was a success
                    soundsLoaded.add(sampleId);
                } else {
                    Log.i("Sound", "ERROR CAN'T LOAD SOUND");
                }
            }
        });

        //load sounds
        final int gameBeepId = soundPool.load(this, R.raw.gamebeep, 1);
        //green_button
        findViewById(R.id.green_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // view.setPressed(true);
                playSound(gameBeepId);

                //int ev = MotionEvent.ACTION_DOWN;
                //int action = ev.getActionMasked();
                //event(R.id.green_button, ev);

                // view.setBackgroundColor(Color.rgb(7, 237, 68));
                // view.setBackgroundColor(0x07ed44);
                //view.setBackgroundColor(Color.rgb(3, 150, 42));

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

    //event
    /*  MotionEvent event = MotionEvent.ACTION_DOWN);
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    view.setBackgroundColor(Color.rgb(7, 237, 68));
                }*/
   /* private void event(int id, int event) {
        Button b;
        switch(id) {
            case R.id.green_button:
               if(event == MotionEvent.ACTION_DOWN){
                   b = findViewById(id);
                   b.setBackgroundColor(Color.rgb(7, 237, 68));
               }
               break;

            default: break;
            /* if (event.getAction() == ACTION_DOWN) {
                view.setBackgroundColor(Color.rgb(7, 237, 68));
            }
            return true;
        }
    }*/

    /*******Handler***********************************************************************/

    //sequence for buttons*******************************************************************************************
    public void sequence() {
        //int num = 1;
        int seq;
        int buttonSequence[] = new int[8];
        //Random random = new Random();

     //   do{

            for (int i = 0; i < 8; i++) {
                Random random = new Random();
                seq = random.nextInt(4) + 1;
                buttonPressed(seq);
                buttonSequence[i] = seq;

                onResume();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            /* num++;
            } while (compare(buttonSequence[seq], buttonInput[]) == true)*/

        }


//button input
  /*  public void seq_input(Button si[]){

    }*/

  //  Handler handler = new Handler();
    public void buttonPressed(int number) {
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
    }

//compare input and increase score
int score = 0;
    public boolean compare(Button[] bc1, Button[] bc2){
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


    //on key down
   /* @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
       // return super.onKeyDown(keyCode, event);

        if (keyCode == KeyEvent.ACTION_DOWN){
           buttonColor[0].setBackgroundColor(0x07ed44);
           buttonColor[1].setBackgroundColor(0xf21607);
           buttonColor[2].setBackgroundColor(0xffff05);
           buttonColor[3].setBackgroundColor(0x1d04f7);
        }
        return true;
    }*/

}
