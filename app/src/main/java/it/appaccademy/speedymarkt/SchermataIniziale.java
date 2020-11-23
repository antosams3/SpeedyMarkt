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
    static boolean cond=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schermata_iniziale);
    }


    public void Accesso (View view) {
        email = ((EditText) findViewById(R.id.login_email)).getText().toString();
        password = ((EditText) findViewById(R.id.login_password)).getText().toString();
        //Intent intent1 = new Intent(this, MainActivity2.class);
        Background backgroundWorker = new Background(this);
        backgroundWorker.execute("login", email, password);
        if(cond==true){
            Intent intent = new Intent( this, RicercaSupermercati.class);
            intent.putExtra("email", email);
            intent.putExtra("password", password);
            startActivity(intent);
            startActivity(intent);
        }
    }

    public void Registrati(View view) {
        Intent intent1 = new Intent(this, Registrazione.class);
        startActivity(intent1);
    }


}