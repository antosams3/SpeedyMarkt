package it.appaccademy.speedymarkt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Accesso_admin extends AppCompatActivity {
    String email_admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
             email_admin = extras.getString("user_name");
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accesso_admin);
    }

    public void aggiungi_prodotto(View view) {
        Intent intent = new Intent(this, SelezionaAttivita.class);
        intent.putExtra("email", email_admin);
        startActivity(intent);
    }

    public void aggiungi_attivita(View view) {
        Intent intent = new Intent(this, Inserimento_attivita.class);
        intent.putExtra("email", email_admin);
        startActivity(intent);
    }
}