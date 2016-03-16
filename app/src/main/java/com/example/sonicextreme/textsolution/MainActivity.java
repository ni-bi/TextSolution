package com.example.sonicextreme.textsolution;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //http://developer.android.com/training/basics/intents/filters.html
    //allow other apps to start your activity


    TextView mOpis;
    String tekst, pretraga;
    EditText mTekst;
    Button mDugme;
    RadioGroup mRg;
    RadioButton mRb1, mRb2, mToggle;
    CheckBox mPar, mSent;
    boolean result;
    final Context context = this;
    String[] recenice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mOpis = (TextView) findViewById(R.id.opis);
        mTekst = (EditText) findViewById(R.id.input);
        mRg = (RadioGroup)findViewById(R.id.radio_grupa);
        mRb1 = (RadioButton)findViewById(R.id.lower_case);
        mRb2 = (RadioButton) findViewById(R.id.upper_case);
        mDugme = (Button) findViewById(R.id.dugme);
        mPar = (CheckBox) findViewById(R.id.paragraf);
        mSent = (CheckBox) findViewById(R.id.recenice);
        mToggle = (RadioButton) findViewById(R.id.toogle_case);

        mDugme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Intent intent = new Intent(MainActivity.this, FinalActivity.class);

                tekst = mTekst.getText().toString();
                int proveri = mRg.getCheckedRadioButtonId();


                if (!mTekst.getText().toString().isEmpty()) {
//ako su i velika i mala slova
                    if(mToggle.isChecked()){

                    new AlertDialog.Builder(context)
                            .setTitle("Decision")
                            .setMessage("Do you want to transform to lower case?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                        tekst = tekst.substring(0, 1) + tekst.substring(1).toLowerCase();

                                        intent.putExtra("CAPS", tekst);
                                        startActivityForResult(intent, 0);


                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                        tekst = tekst.substring(1).toUpperCase();

                                        intent.putExtra("CAPS", tekst);
                                        startActivityForResult(intent, 0);

                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();

                }
//to lowercase
                    if(proveri ==mRb1.getId()) {
                        if (result = tekst.toUpperCase().equals(tekst)) {
                            tekst = tekst.toLowerCase();

                            intent.putExtra("CAPS", tekst);
                            startActivityForResult(intent, 0);
                        } else {
                            Toast.makeText(MainActivity.this, "It's already lower case", Toast.LENGTH_SHORT).show();
                        }
                    }
//to Uppercase
                    if(proveri == mRb2.getId()) {
                        if (result = tekst.toLowerCase().equals(tekst)) {
                            tekst = tekst.substring(1).toUpperCase();

                            intent.putExtra("CAPS", tekst);
                            startActivityForResult(intent, 0);
                        } else {
                            Toast.makeText(MainActivity.this, "It's already upper case", Toast.LENGTH_SHORT).show();
                        }
                    }
                    pretraga = ".";
                    String temp;
//odvaja recenice
                    if (mSent.isChecked()) {
                        if (mTekst.getText().toString().contains(pretraga)) {


                            List<String> recenice2 = new ArrayList<>();
                            String noviStrring = "";
                            StringBuilder sb = new StringBuilder();

                            for(char c : tekst.toCharArray()){

                                if(c == '.') {

                                    noviStrring = tekst.substring(0, tekst.indexOf('.'));
                                    Log.d("1",noviStrring);
                                    noviStrring = Character.toUpperCase(noviStrring.charAt(0)) + noviStrring.substring(1);
                                    Log.d("2",noviStrring);
                                    if (!recenice2.isEmpty()){
                                        for (String s : recenice2) {

                                            Log.d("3",s);
                                            sb.append(s);

                                        }
                                        Log.d("4",sb.toString());

                                    recenice2.add(tekst.substring(tekst.indexOf(sb.toString())));
                                }else{
                                        recenice2.add(noviStrring);


                                    }

                                    Log.d("5",String.valueOf(recenice2.size()));



                                }


                            }




                            tekst = tekst.replaceAll("\\.", ". ");

                            //String[] delovi = temp.split(".");
                            //String temp1 = delovi[0].substring(0, 1).toUpperCase()+delovi[0].substring(1);
                            //String temp2 = delovi[1].substring(0, 1).toUpperCase() + delovi[1].substring(1);
                            //for (int i = 0; i<delovi.length; i++) {


                              //  }
//treba napraviti da pise veliko prvo slovo posle ". "
                            // String[] deo = tekst.split(pretraga);
                            // for(String str: deo){
                            //  }
                            //tekst = deo[0].substring(0, 1).toUpperCase() + deo[0].substring(1) + ". " + deo[1].substring(0, 1).toUpperCase() + deo[1].substring(1);

                            intent.putExtra("CAPS", tekst);
                            startActivityForResult(intent, 0);
                        }
                    }
//Novi red
                            if(mPar.isChecked()){
                                tekst = "    " + tekst.substring(0, 1).toUpperCase() + tekst.substring(1);

                                intent.putExtra("CAPS", tekst);
                                startActivityForResult(intent, 0);
                            }


                } else
                    Toast.makeText(MainActivity.this, "Text field is empty", Toast.LENGTH_SHORT).show();
            }

                });
            }

}

//Da se uzme tekst i parsuje kao string, pa da se onda iscitava i proverava da li postoji novi red.
// ako postoji, da se uradi substring i umesto prvom malog slova stavi veliko.
// NAPRAVITI ISTU APLIKACIJU SAMO UMESTO DUGMADI DA BUDU RADIO BUTTONI I CHECKBOXOVI