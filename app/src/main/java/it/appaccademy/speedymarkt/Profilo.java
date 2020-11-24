package it.appaccademy.speedymarkt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Profilo extends AppCompatActivity {
public static TextView TvAnagNome;
public static TextView TvAnagCognome;
public static TextView TvAnagEmail;
public static TextView TvAnagDatanasc;
public static TextView TvAnagCarta;
public static TextView TvAnagScadenza;
String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profilo);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            email = extras.getString("email");
        }
        System.out.println(email);
        TvAnagNome = (TextView) findViewById(R.id.tvAnagNome);
        TvAnagCognome = (TextView) findViewById(R.id.tvAnagCognome);
        TvAnagDatanasc = (TextView) findViewById(R.id.tvAnagDatanasc);
        TvAnagEmail = (TextView) findViewById(R.id.tvAnagEmail);
        TvAnagCarta = (TextView) findViewById(R.id.tvAnagCarta);
        TvAnagScadenza = (TextView) findViewById(R.id.tvAnagScadenza);
        BackgroundJSON  process = new BackgroundJSON();
        process.execute(email);



    }

    public void goHome(View view) {
        Intent intent=new Intent(this,RicercaSupermercati.class);
        startActivity(intent);
    }
    public void goAdmin(View view) {
        Intent intent = new Intent(this, Accesso_admin.class);
        intent.putExtra("email", email);
        startActivity(intent);
    }
}