package it.appaccademy.speedymarkt;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Inserimento_prodotti extends AppCompatActivity {
EditText EtEan, EtMarchio, EtNome, EtPrezzo, EtQuantita;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inserimento_prodotti);
        EtEan = (EditText) findViewById(R.id.etEan);
        EtMarchio = (EditText) findViewById(R.id.etMarchio);
        EtNome = (EditText) findViewById(R.id.etProdotto);
        EtPrezzo = (EditText) findViewById(R.id.etPrezzo);
        EtQuantita = (EditText) findViewById(R.id.etQuantita);
    }

    public void aggiungiProdotto(View view) {
        String ean = EtEan.getText().toString();
        String marchio = EtMarchio.getText().toString();
        String nome = EtNome.getText().toString();
        String prezzo = EtPrezzo.getText().toString();
        String quantita = EtQuantita.getText().toString();
        String type = "insert_product";
        Background backgroundWorker = new Background(this);
        backgroundWorker.execute(type, /**idattivita*/, ean, marchio, nome, prezzo, quantita);
    }
}
