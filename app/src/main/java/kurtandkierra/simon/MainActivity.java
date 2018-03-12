package kurtandkierra.simon;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create button for AboutActivity
        Button b = findViewById(R.id.about_button);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),
                        AboutActivity.class);
                startActivity(intent);

            }
        });

        //play button
        findViewById(R.id.play_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // AlertDialog.Builder builder = new AlertDialog.Builder(this);

                Intent intent = new Intent(getApplicationContext(),
                        PlayActivity.class);
                startActivity(intent);

            }

        });
    }

    }

