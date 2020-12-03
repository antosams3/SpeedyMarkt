package it.appaccademy.speedymarkt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Carrello extends AppCompatActivity {
    String email;
    protected void onCreate(Bundle savedInstanceState) {

        //Prelievo dato email
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            email = extras.getString("email");
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.carrello);


    }

    //Passaggio dato email a Ricercasupermercati
    public void goHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("email", email);
        startActivity(intent);
    }

    //Passaggio dato email a Accesso_admin
    public void goAdmin(View view) {
        Intent intent = new Intent(this, Accesso_admin.class);
        intent.putExtra("email", email);
        startActivity(intent);
    }

    //Passaggio dato email a Profilo
    public void visitaProfilo(View view) {
        Intent intent = new Intent(this, Profilo.class);
        intent.putExtra("email", email);
        startActivity(intent);
    }
}



