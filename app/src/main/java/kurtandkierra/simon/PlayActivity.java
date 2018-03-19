package kurtandkierra.simon;

import android.app.Activity;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Created by Kierra on 2/25/2018.
 */

public class PlayActivity extends Activity {


    final ImageButton[] ids = new ImageButton[4];
    boolean simonTurn = true;
    Button buttonInput[] = new Button[8];
    //soundpool
    private SoundPool soundPool;
    private Set<Integer> soundsLoaded;
    int seqNum = 8;
    List<Integer> sequence = new ArrayList<Integer>();
    //int[] sequence = new int[seqNum];
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        //rotation
        String hs_label;

        if (savedInstanceState == null) {
            hs_label = "0";

        } else {
            hs_label = (String) savedInstanceState.get("highScore");

        }
        final TextView highScore_tv = findViewById(R.id.highScore_textview);

        highScore_tv.setText(hs_label);

        //for soundsLoaded
        soundsLoaded = new HashSet<Integer>();

             /*create ImageButton*/
             ids[0] = findViewById(R.id.green_imageButton);
            ids[1] = findViewById(R.id.red_imageButton);
            ids[2] = findViewById(R.id.yellow_imageButton);
            ids[3] = findViewById(R.id.blue_imageButton);
                for (int i = 0; i < ids.length; i++){
                   // ids[i].setOnClickListener(this);
                   ids[i].setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View view) {

                       }
                   });
                }

                //start button
        findViewById(R.id.start_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startCounter();
            }
        });

       //handler
        handler = new Handler();

        }

    //score
    private TextView highScore_tv;
    private TextView score_tv;
    private UpdateTask updateTask;
    int highScore;

    //start counter for score
    private void startCounter() {
        highScore_tv = findViewById(R.id.highScore_textview);

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

    /*** UpdateTask***********************************************************/
    int finalScore;

    class UpdateTask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            int num;
            Random random = new Random();

            //repeat sequence
            int i = 0;
            //for (int i = 0; i < seqNum; i++) {
            while (simonTurn && i < seqNum) {
                num = random.nextInt(4);
                sequence.add(num);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                onProgressUpdate(num);
                i++;
            }

                /*for (int  k = 0; k < sequence.size(); k++){
                    buttonPressed(ids[num]);
                }
                Handler user = new Handler();
                user.post(new Runnable() {
                    @Override
                    public void run() {
                        for (int k = 0; k < sequence.size(); k++){

                        }
                    }
                });*/



              /*if  (i > 0) {
                  for (int j = 0; j < sequence.size(); j++) {

                      try {
                          Thread.sleep(1000);
                      } catch (InterruptedException e) {
                          e.printStackTrace();
                      }
                      onProgressUpdate(sequence.get(j));
                  }
              }*/


            //repeat sequence
               /* if (sequence.length > 1){
                    for (int j = 0; j < sequence.length; j++){
                       sequence[j] = sequence[j];

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        onProgressUpdate(num);
                    }
                }*/
            //sequence
            /*    for (int j = 0; j < sequence.length; j++){
                        sequence[j].performClick();
                }*/


            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... num) {

            final int number = num[0];
            ImageButton b;

            switch (number) {
                case 0:
                    Log.i("Color: ", "GREEN");
                    break;
                case 1:
                    Log.i("Color: ", "RED");
                    break;
                case 2:
                    Log.i("Color: ", "YELLOW");
                    break;
                case 3:
                    Log.i("Color: ", "BLUE");
                    break;
                default:
                    break;
            }

            handler.post(new Runnable() {
                @Override
                public void run() {
                    ids[number].performClick();
                    ids[number].setPressed(true);
                }
            });

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ids[number].setPressed(false);
                }
            }, 500);

            simonTurn = false;

            // for (int i = 0; i < seqNum; i++) {
            while (simonTurn == false) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "Here!!!",
                                Toast.LENGTH_LONG).show();

                        try {
                            Thread.sleep(10000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                      //  for (int )
                    }
                });
                simonTurn = true;
            }

            //user input


        }
    }

//user turn
  public void usersTurn(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), "Here!!!",
                        Toast.LENGTH_LONG).show();
            }
        }, 10000);

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
        findViewById(R.id.green_imageButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playSound(gameBeepId);
            }
        });
        //red_button
        findViewById(R.id.red_imageButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playSound(gameBeepId);
            }
        });
        //yellow_button
        findViewById(R.id.yellow_imageButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playSound(gameBeepId);
            }
        });
        //blue_button
        findViewById(R.id.blue_imageButton).setOnClickListener(new View.OnClickListener() {
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
/*int score = 0;
    public boolean compare(List bc1){
       List int[] bc2 =  new int[bc1.size()];


            boolean flag = false;
            for(int i = 0; i < bc1.size(); i++){
                if (bc1.get(i) == bc2[i]){
                    flag = true;
                    score += 20;


                }else{
                  flag = false;
                    Toast.makeText(getApplicationContext(), "You lose!",
                            Toast.LENGTH_LONG).show();
                }
            }

        return flag;
    }*/

    //rotation
       @Override
   protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);

        TextView highScore = findViewById(R.id.highScore_textview);
        outState.putString("highScore", highScore.getText().toString());

    }

}
