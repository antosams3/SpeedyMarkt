package it.appaccademy.speedymarkt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class Registrazione extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrazione);
    }

    public void controlla(View view) {

        //Log.d("MainActvity2", "Clicca sul Button Avanti");
        Intent intent2 = new Intent(this, RicercaSupermercati.class);
        startActivity(intent2);


    }
}