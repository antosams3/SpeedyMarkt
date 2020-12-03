package it.appaccademy.speedymarkt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SelezionaAttivita extends AppCompatActivity {
    String email_admin;
    String negozio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seleziona_attivita);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            email_admin = extras.getString("user_name");
        }

    }


   /** public void selezionaNegozio(View view) {
        negozio=((TextView)findViewById(R.id.ricercanegozio_input)).getText().toString();
        Intent intent = new Intent(this, ElencoSupermercati.class);
        intent.putExtra("negozio", negozio);
        intent.putExtra("email",email_admin);
        startActivity(intent);*/
    }

