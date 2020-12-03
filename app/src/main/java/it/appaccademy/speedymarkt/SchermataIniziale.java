package it.appaccademy.speedymarkt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;


public class SchermataIniziale extends AppCompatActivity {
    String email;
    String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schermata_iniziale);
    }


    public void Accesso(View view) {
        email = ((EditText) findViewById(R.id.login_email)).getText().toString();
        password = ((EditText) findViewById(R.id.login_password)).getText().toString();


        //Thread
        Background backgroundWorker = new Background(this);
        backgroundWorker.execute("login", email, password);
        //aggiungere controllo sul tipo di accesso effettuato
    }

    //Accesso a register
    public void Registrati(View view) {
        Intent intent1 = new Intent(this, Registrazione.class);
        startActivity(intent1);
    }

    protected void onResume(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schermata_iniziale);

    }


}