package it.appaccademy.speedymarkt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Accesso_admin extends AppCompatActivity {
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Prelievo dato email
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
             email = extras.getString("email");
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.accesso_admin);
    }

    //Passaggio a Inserimento_prodotti con dato email
    public void aggiungi_prodotto(View view) {
        Intent intent = new Intent(this, Inserimento_prodotti.class);
        intent.putExtra("email", email);
        startActivity(intent);
    }

    //Passaggio a Inserimento_attivita con dato email
    public void aggiungi_attivita(View view) {
        Intent intent = new Intent(this, Inserimento_attivita.class);
        intent.putExtra("email", email);
        startActivity(intent);
    }

    //Passaggio a Profilo con dato email
    public void goProfilo(View view) {
        Intent intent = new Intent(this, Profilo.class);
        intent.putExtra("email", email);
        startActivity(intent);
    }

    //Passaggio a RicercaSupermercati con dato email
    public void goHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("email", email);
        startActivity(intent);
    }

    //Passaggio a Carrello con dato email
    public void goCarrello(View view) {
        Intent intent = new Intent(this, Carrello.class);
        intent.putExtra("email", email);
        startActivity(intent);
    }
}