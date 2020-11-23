package it.appaccademy.speedymarkt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RicercaSupermercati extends AppCompatActivity {
    String negozio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ricerca_supermercati);

    }


    public void selezionaNegozio(View view) {
        negozio=((TextView)findViewById(R.id.ricercanegozio_input)).getText().toString();
        Intent intent = new Intent(this, ElencoSupermercati.class);
        intent.putExtra("negozio", negozio);
        startActivity(intent);
    }
}