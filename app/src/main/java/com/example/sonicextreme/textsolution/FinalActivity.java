package com.example.sonicextreme.textsolution;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Sonic.Extreme on 08-Feb-16.
 */
public class FinalActivity extends AppCompatActivity {

    TextView mTekst;
    String tekst, test;
    Button mNazad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        mTekst = (TextView)findViewById(R.id.output);
        mNazad = (Button)findViewById(R.id.dugmeNazad);

        Intent intent = getIntent();
        tekst= intent.getStringExtra("CAPS");
        test=intent.getStringExtra("CAPS1");

        init();

        mNazad.setOnClickListener(new NazadButtonListener());
    }

    private class NazadButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {

            Intent intent = new Intent(FinalActivity.this, MainActivity.class);

            Toast.makeText(FinalActivity.this, "Thank you for using this application", Toast.LENGTH_SHORT).show();

            startActivityForResult(intent, 0);
        }
    }

    public void init(){

        mTekst = (TextView)findViewById(R.id.output);
        mTekst.setText(tekst);

    }
}
