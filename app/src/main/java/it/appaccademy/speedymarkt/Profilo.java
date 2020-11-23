package it.appaccademy.speedymarkt;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Profilo extends AppCompatActivity {
public static TextView TvAnagNome;
public static TextView TvAnagCognome;
public static TextView TvAnagEmail;
public static TextView TvAnagDatanasc;
public static TextView TvAnagCarta;
public static TextView TvAnagScadenza;
public static TextView data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profilo);
        TvAnagNome = (TextView) findViewById(R.id.tvAnagNome);
        TvAnagCognome = (TextView) findViewById(R.id.tvAnagCognome);
        TvAnagDatanasc = (TextView) findViewById(R.id.tvAnagDatanasc);
        TvAnagEmail = (TextView) findViewById(R.id.tvAnagEmail);
        TvAnagCarta = (TextView) findViewById(R.id.tvAnagCarta);
        TvAnagScadenza = (TextView) findViewById(R.id.tvAnagScadenza);
        Background  process = new Background(this);
        process.execute("profilo");



    }
}