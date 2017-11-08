package com.astronautwannabe.nasa.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Random;

import android.widget.TextView;
import android.widget.Button;
import android.widget.ImageView;

import com.leanplum.Leanplum;
import com.leanplum.LeanplumPushService;
import com.leanplum.annotations.Parser;
import com.leanplum.annotations.Variable;
import com.leanplum.callbacks.VariablesChangedCallback;

public class MainActivity extends AppCompatActivity {

    // All variables must be defined before calling Leanplum.start.
    // Use the Parser class (see docs) since your variables are outside of your main activity.
    @Variable
    public static String welcomeMessage = "Welcome to Leanplum!";

    String answers[] = {"I don't think so", "Ask again later",
            "No.", "Leave BLAh alone", "Pusheen doesn't like you so no"
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

        setupLeanplum();
    }

    private void setupLeanplum() {
        String server = "leanplum-staging.appspot.com";
        String appID = "app_N59g6QL2dVfbcEiih1tgrcItbrjipxdheGZ3AHBci2w";
        String dbgKey = "dev_BnjHjeJa4M3MIC0hVwKHr4Wh3AfhimhHDBCNgBZfOPw";
        Leanplum.setAppIdForDevelopmentMode(appID, dbgKey);


        LeanplumPushService.setGcmSenderId(LeanplumPushService.LEANPLUM_SENDER_ID);

        // Be sure to set the context to this in the Parser.
        Parser.parseVariables(this);

        // It's important to use the variables changed callback if the value is needed
        // around the time the app starts, so that we're guaranteed to have the latest value.
        Leanplum.addVariablesChangedHandler(new VariablesChangedCallback() {
            @Override
            public void variablesChanged() {
                Log.i("Test", welcomeMessage);
            }
        });

        Leanplum.setApiConnectionSettings(server, "api", true);
        Leanplum.start(this, "Meowz");
        Leanplum.setUserId("Meowz");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
