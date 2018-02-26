package kurtandkierra.simon;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Kierra on 2/25/2018.
 */

public class PlayActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

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
    }

}
