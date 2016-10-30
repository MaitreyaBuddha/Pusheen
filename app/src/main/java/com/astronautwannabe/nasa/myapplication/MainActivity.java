package com.astronautwannabe.nasa.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import java.util.Random;
import android.widget.TextView;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    String answers[] = {"I don't think so", "Ask again later",
            "No.", "Leave Pusheen alone", "Pusheen doesn't like you so no"
            , "Yes because I <3 you", "Only if you buy me food", "Yuuhhh", "Mhm.",
            "Let pizza decide"};

    TextView mPusheenText;
    Button mGeneratePusheenButton;
    ImageView mPusheenImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 1:
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mPusheenText = (TextView) findViewById(R.id.pusheenText);
        mPusheenImage = (ImageView) findViewById(R.id.pusheenImage);
        mGeneratePusheenButton = (Button) findViewById(R.id.pusheenButton);

        mGeneratePusheenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 5:
                int index = new Random().nextInt(answers.length);
                mPusheenText.setText(answers[index]);
                // 6
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
