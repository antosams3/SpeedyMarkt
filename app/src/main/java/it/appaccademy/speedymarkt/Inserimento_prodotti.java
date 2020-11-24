package it.appaccademy.speedymarkt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Inserimento_prodotti extends AppCompatActivity {
EditText EtEan, EtMarchio, EtNome, EtPrezzo, EtQuantita;
String negozio,email,nome,via;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inserimento_prodotti);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            email = extras.getString("email");

        }

        // metodo che consente di calcolare l'attività in base al nome, via ed email del proprietario
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
        backgroundWorker.execute(type, ean, marchio, nome, prezzo, quantita,"1",email);
    }

    public void goHome(View view) {
        Intent intent = new Intent(this, RicercaSupermercati.class);
        intent.putExtra("email", email);
        startActivity(intent);
    }
    public void visitaProfilo(View view) {
        Intent intent = new Intent(this, Profilo.class);
        intent.putExtra("email", email);
        startActivity(intent);
    }
}
