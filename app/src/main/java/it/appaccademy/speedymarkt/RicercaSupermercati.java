package it.appaccademy.speedymarkt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RicercaSupermercati extends AppCompatActivity {
    String negozio;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ricerca_supermercati);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            email = extras.getString("email");
        }
    }


    public void selezionaNegozio(View view) {
        negozio=((TextView)findViewById(R.id.ricercanegozio_input)).getText().toString();
        Intent intent = new Intent(this, ElencoSupermercati.class);
        intent.putExtra("negozio", negozio);
        startActivity(intent);
    }

    public void visitaProfilo(View view) {
        Intent intent = new Intent(this, Profilo.class);
        intent.putExtra("email", email);
        startActivity(intent);
    }
    public void goAdmin(View view) {
        Intent intent = new Intent(this, Accesso_admin.class);
        intent.putExtra("email", email);
        startActivity(intent);
    }

    public void goCarrello(View view) {
        Intent intent = new Intent(this, Carrello.class);
        intent.putExtra("email", email);
        startActivity(intent);
    }
}