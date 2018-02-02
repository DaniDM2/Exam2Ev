package com.example.dm2.exam2ev;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Actividad3 extends AppCompatActivity {

    Spinner spinner;
    SoundPool soundPool;
    int idDonkey, idHorse,idGoat,idHen,idRooster,idCat,idSheep,idCow;

    void goBack(View v){
        Intent intent = new Intent(Actividad3.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad3);

        spinner = (Spinner) findViewById(R.id.spinnerAudio);
        //Para cargar desde archivo
        ArrayList<String> audios = new ArrayList<String>();
        try{
            //Cogemos los animales de un .txt
            InputStream fraw = getResources().openRawResource(R.raw.animales);
            BufferedReader brin = new BufferedReader(new InputStreamReader(fraw));
            String linea = brin.readLine();
            while (linea != null){
                //Miramos si el nombre coincide
Log.e("ANIMAL",linea);
                if (linea.equals("burro"))
                    audios.add(getResources().getString(R.string.donkey));
                else if (linea.equals("caballos"))
                    audios.add(getResources().getString(R.string.horse));
                else if (linea.equals("cabra"))
                    audios.add(getResources().getString(R.string.goat));
                else if (linea.equals("gallina"))
                    audios.add(getResources().getString(R.string.hen));
                else if (linea.equals("gallo"))
                    audios.add(getResources().getString(R.string.rooster));
                else if (linea.equals("gato"))
                    audios.add(getResources().getString(R.string.cat));
                else if (linea.equals("ovejas"))
                    audios.add(getResources().getString(R.string.sheep));
                else if (linea.equals("vaca"))
                    audios.add(getResources().getString(R.string.cow));
                linea = brin.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Pasamos la lista a array
        final String[] audioArray = new String[audios.size()];
        for(int i = 0;i<audios.size();i++){
Log.i("SPINNER",audios.get(i));
            audioArray[i] = audios.get(i);
        }

        //cargamos el spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,audioArray);
        spinner.setAdapter(adapter);

        //cargamos el soundPool
        soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC,0);
        idDonkey = soundPool.load(this,R.raw.burro,0);
        idHorse = soundPool.load(this,R.raw.caballos,0);
        idGoat = soundPool.load(this,R.raw.cabra,0);
        idHen = soundPool.load(this,R.raw.gallina,0);
        idRooster = soundPool.load(this,R.raw.gallo,0);
        idCat = soundPool.load(this,R.raw.gato,0);
        idSheep = soundPool.load(this,R.raw.ovejas,0);
        idCow = soundPool.load(this,R.raw.vaca,0);

        //Listener
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
    Log.d("SONIDOS", String.valueOf(l));
                if (l==0)
                    soundPool.play(idHen,1,1,0,0,1);
                else if (l==1)
                    soundPool.play(idDonkey,1,1,0,0,1);
                else if (l==2)
                    soundPool.play(idCow,1,1,0,0,1);
                else if (l==3)
                    soundPool.play(idHorse,1,1,0,0,1);
                else if (l==4)
                    soundPool.play(idCat,1,1,0,0,1);
                else if (l==5)
                    soundPool.play(idRooster,1,1,0,0,1);
                else if (l==6)
                    soundPool.play(idSheep,1,1,0,0,1);
                else
                    soundPool.play(idGoat,1,1,0,0,1);
            }

            //Esto vacio porque no hace nada
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
