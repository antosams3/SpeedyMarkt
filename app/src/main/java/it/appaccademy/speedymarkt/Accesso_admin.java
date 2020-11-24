package it.appaccademy.speedymarkt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Accesso_admin extends AppCompatActivity {
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
             email = extras.getString("email");
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accesso_admin);
    }

    public void aggiungi_prodotto(View view) {
        Intent intent = new Intent(this, Inserimento_prodotti.class);
        intent.putExtra("email", email);
        startActivity(intent);
    }

    public void aggiungi_attivita(View view) {
        Intent intent = new Intent(this, Inserimento_attivita.class);
        intent.putExtra("email", email);
        startActivity(intent);
    }
    public void goProfilo(View view) {
        Intent intent = new Intent(this, Profilo.class);
        intent.putExtra("email", email);
        startActivity(intent);
    }
    public void goHome(View view) {
        Intent intent = new Intent(this, RicercaSupermercati.class);
        intent.putExtra("email", email);
        startActivity(intent);
    }
    public void goAdmin(View view) {
        Intent intent = new Intent(this, Accesso_admin.class);
        intent.putExtra("email", email);
        startActivity(intent);
    }
}